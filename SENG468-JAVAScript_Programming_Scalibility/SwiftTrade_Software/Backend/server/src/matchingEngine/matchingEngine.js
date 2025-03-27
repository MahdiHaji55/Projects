const OrderBook = require("../orderbook/orderBook");
const UserPortfolio = require("../models/UserPortfolio");
const Redis = require("ioredis");

// Initialize Redis
const redisClient = new Redis({ host: process.env.REDIS_HOST || "localhost", port: 6379 });
const redisSubscriber = new Redis({ host: process.env.REDIS_HOST || "localhost", port: 6379 });

class MatchingEngine {
    constructor() {
        this.orderBook = new OrderBook();
        this.tradeHistory = [];

        // Load Order Book from Redis on startup
        this.loadOrderBook();

        // Listen for order updates from Redis Pub/Sub
        this.listenForUpdates();
    }

    //Load order book from Redis
    async loadOrderBook() {
        try {
            const buyOrders = await redisClient.get("order_book:buyOrders");
            const sellOrders = await redisClient.get("order_book:sellOrders");

            this.orderBook.buyOrders = buyOrders ? JSON.parse(buyOrders) : [];
            this.orderBook.sellOrders = sellOrders ? JSON.parse(sellOrders) : [];

            
        } catch (error) {
            console.error("Error loading order book from Redis:", error);
        }
    }

    //Save order book to Redis
    async saveOrderBook() {
        try {
            await redisClient.set("order_book:buyOrders", JSON.stringify(this.orderBook.buyOrders));
            await redisClient.set("order_book:sellOrders", JSON.stringify(this.orderBook.sellOrders));
            
        } catch (error) {
            console.error("Error saving order book to Redis:", error);
        }
    }

    //Place an order and sync with Redis
    async placeOrder(order) {
        

        if (order.is_buy) {
            let bestSell = this.orderBook.getBestSell();
            while (bestSell && order.price >= bestSell.price && order.quantity > 0) {
                let matchQuantity = Math.min(order.quantity, bestSell.quantity);
                order.quantity -= matchQuantity;
                bestSell.quantity -= matchQuantity;

                this.tradeHistory.push({
                    buyOrderId: order.id,
                    sellOrderId: bestSell.id,
                    sellerUserId: bestSell.user_id,
                    price: bestSell.price,
                    quantity: matchQuantity
                });

                if (bestSell.quantity === 0) {
                    this.orderBook.removeOrder(bestSell.id, false);
                }
                bestSell = this.orderBook.getBestSell();
            }
        } else {
            let bestBuy = this.orderBook.getBestBuy();
            while (bestBuy && order.price <= bestBuy.price && order.quantity > 0) {
                let matchQuantity = Math.min(order.quantity, bestBuy.quantity);
                order.quantity -= matchQuantity;
                bestBuy.quantity -= matchQuantity;

                this.tradeHistory.push({
                    buyOrderId: bestBuy.id,
                    sellOrderId: order.id,
                    price: bestBuy.price,
                    quantity: matchQuantity
                });

                if (bestBuy.quantity === 0) {
                    this.orderBook.removeOrder(bestBuy.id, true);
                }
                bestBuy = this.orderBook.getBestBuy();
            }
        }

        if (order.quantity > 0) {
            this.orderBook.addOrder(order);
            

            //Remove stock from UserPortfolio
            try {
                const portfolio = await UserPortfolio.findOne({ userid: order.user_id, stock_id: order.stock_id });
                if (portfolio) {
                    portfolio.quantity_owned = Math.max(0, portfolio.quantity_owned - order.quantity);
                    await portfolio.save();
                    
                } else {
                    console.warn(`UserPortfolio not found for user ${order.user_id} and stock ${order.stock_id}`);
                }
            } catch (error) {
                console.error(`Error updating UserPortfolio:`, error);
            }
        }

        //Save updated order book to Redis
        await this.saveOrderBook();

        //Publish update to notify all instances
        await redisClient.publish("order_update", JSON.stringify(order));

        return { status: "Order placed", tradeHistory: this.tradeHistory };
    }

    //Cancel an order and sync with Redis
    async cancelOrder(orderId, userId, isBuy) {
        let order;
        if (isBuy) {
            order = this.orderBook.buyOrders.find(o => o.id === orderId);
            this.orderBook.buyOrders = this.orderBook.buyOrders.filter(o => o.id !== orderId);
        } else {
            order = this.orderBook.sellOrders.find(o => o.id === orderId);
            this.orderBook.sellOrders = this.orderBook.sellOrders.filter(o => o.id !== orderId);
        }

        if (!order) {
            return { status: "Error", message: "Order not found or already executed" };
        }

        

        //Restore stocks to UserPortfolio
        if (!isBuy) {
            try {
                const portfolio = await UserPortfolio.findOne({ userid: userId, stock_id: order.stock_id });
                if (portfolio) {
                    portfolio.quantity_owned += order.quantity;
                    await portfolio.save();
                    
                } else {
                    
                    const newPortfolio = new UserPortfolio({
                        userid: userId,
                        stock_id: order.stock_id,
                        quantity_owned: order.quantity
                    });
                    await newPortfolio.save();
                }
            } catch (error) {
                console.error(`Error restoring UserPortfolio:`, error);
            }
        }

        //Save updated order book to Redis
        await this.saveOrderBook();

        //Publish update to notify all instances
        await redisClient.publish("order_update", JSON.stringify({ cancel: true, orderId }));

        return { status: "Order cancelled", order };
    }

    //Listen for order updates from Redis
    async listenForUpdates() {
        redisSubscriber.subscribe("order_update", (err, count) => {
            if (err) {
                console.error("Failed to subscribe to order updates:", err);
            } else {
                console.log(`Subscribed to order updates (${count} channels).`);
            }
        });

        redisSubscriber.on("message", async (channel, message) => {
            if (channel === "order_update") {
                const order = JSON.parse(message);
                

                // Reload order book when any order is placed/canceled
                await this.loadOrderBook();
            }
        });
    }

    //Return full order book
    getOrderBook() {
        return this.orderBook;
    }
}

const engineInstance = new MatchingEngine(); // Singleton instance

module.exports = engineInstance;