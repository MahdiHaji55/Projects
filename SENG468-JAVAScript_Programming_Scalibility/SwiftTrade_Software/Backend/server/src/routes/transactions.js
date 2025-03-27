// src/routes/transactions.js
const express = require("express");
const authMiddleware = require("../middleware/authMiddleware");
const MatchingEngine = require("../matchingEngine/matchingEngine");
const Transaction = require("../models/Transaction");
const { v4: uuidv4 } = require("uuid");
const Stock = require("../models/Stock"); 
const router = express.Router();
const Wallet = require("../models/Wallet");
const UserPortfolio = require("../models/UserPortfolio");
const User = require("../models/User");
const engine = require("../matchingEngine/matchingEngine.js");
const client = require("../config/redis"); 

router.post("/placeStockOrder", authMiddleware, async (req, res) => {
    
	
    const user_id = req.user.id;
    const current_user_id = user_id;
    let { stock_id, is_buy, order_type, quantity, price } = req.body;

    if (!stock_id || typeof is_buy !== "boolean" || !order_type || !quantity) {
        
        return res.status(400).json({ error: "Missing required fields" });
    }

    try {
        const stock = await Stock.findOne({ stock_id: stock_id.toString() });
        if (!stock) {
            return res.status(404).json({ success: false, data: { error: "Stock not found" } });
        }

        const user = await User.findOne({ _id: user_id });
        if (!user) {
            return res.status(404).json({ success: false, data: { error: "User not found" } });
        }

        let allTransactions = [];

        // --- SELL ORDER Handling (Deduct stocks from UserPortfolio) ---
        if (!is_buy) {
            

            const userPortfolio = await UserPortfolio.findOne({ userid: user_id, stock_id: stock_id.toString() });
            if (!userPortfolio || userPortfolio.quantity_owned < quantity) {
                
                return res.status(400).json({ success: false, data: { error: "Not enough stocks to sell" } });
            }

            // Log Sell Order in Transactions
            const initialSellTransaction = new Transaction({
                stock_tx_id: uuidv4(),
                stock_id,
                wallet_tx_id: null,
                order_status: "IN_PROGRESS",
                parent_stock_tx_id: null,
                is_buy: false,
                order_type,
                stock_price: order_type === "MARKET" ? 0 : price,
                quantity,
                time_stamp: new Date(),
                buyer_id: null,
                seller_id: user_id,
            });

            await initialSellTransaction.save();
            allTransactions.push(initialSellTransaction);
            

            // Send Sell Order to Redis-backed Matching Engine
            const sellOrder = {
                id: initialSellTransaction.stock_tx_id,
                stock_id,
                user_id,
                is_buy: false,
                order_type,
                quantity,
                price: order_type === "MARKET" ? 0 : price,
            };

            await engine.placeOrder(sellOrder);
        }

        // --- BUY ORDER Handling ---
        if (is_buy) {
            

            let sellOrders = engine.orderBook.sellOrders.filter(order => 
                order.stock_id === stock_id && order.user_id !== user_id
            );

            if (!sellOrders.length) {
                
                return res.status(400).json({ success: false, data: { error: "No available sell orders for this stock." } });
            }

            // Check if total available quantity is enough
            let totalAvailable = sellOrders.reduce((sum, order) => sum + order.quantity, 0);
            if (totalAvailable < quantity) {
                
                return res.status(400).json({ success: false, data: { error: "Not enough stocks available for this order." } });
            }

            let remainingQuantity = quantity;
            let totalCost = 0;

            let user = await User.findById(user_id);
            if (!user) {
                
                return res.status(400).json({ success: false, data: { error: "User not found" } });
            }

            

            for (const sellOrder of sellOrders) {
                if (remainingQuantity <= 0) break;

                const matchQuantity = Math.min(remainingQuantity, sellOrder.quantity);
                const matchPrice = sellOrder.price;
                totalCost += matchQuantity * matchPrice;

                if (user.wallet_balance < totalCost) {
                    
                    return res.status(400).json({ success: false, data: { error: "Insufficient funds in wallet." } });
                }

                user.wallet_balance -= matchQuantity * matchPrice;
                await user.save();
                

                // Create BUY Transaction
                const buyTransaction = new Transaction({
                    stock_tx_id: uuidv4(),
                    stock_id,
                    wallet_tx_id: uuidv4(),
                    order_status: "COMPLETED",
                    parent_stock_tx_id: sellOrder.id,
                    is_buy: true,
                    order_type,
                    stock_price: matchPrice,
                    quantity: matchQuantity,
                    time_stamp: new Date(),
                    buyer_id: user_id,
                    seller_id: sellOrder.user_id,
                });

                await buyTransaction.save();
                allTransactions.push(buyTransaction);

                // Create SELL Transaction
                const sellTransaction = new Transaction({
                    stock_tx_id: uuidv4(),
                    stock_id,
                    wallet_tx_id: uuidv4(),
                    order_status: "COMPLETED",
                    parent_stock_tx_id: buyTransaction.parent_stock_tx_id,
                    is_buy: false,
                    order_type,
                    stock_price: matchPrice,
                    quantity: matchQuantity,
                    time_stamp: new Date(),
                    buyer_id: user_id,
                    seller_id: sellOrder.user_id,
                });

                await sellTransaction.save();

                const sellerWalletTransaction = new Wallet({
                    wallet_tx_id: sellTransaction.wallet_tx_id,
                    user_id: buyTransaction.seller_id,
                    stock_tx_id: sellTransaction.stock_tx_id,
                    is_debit: false,
                    amount: matchQuantity * matchPrice,
                    timestamp: new Date(),
                });

                await sellerWalletTransaction.save();
               

                // Log Debit Transaction in Wallet
                const walletTransaction = new Wallet({
                    wallet_tx_id: buyTransaction.wallet_tx_id,
                    user_id: current_user_id,
                    stock_tx_id: buyTransaction.stock_tx_id,
                    is_debit: true,
                    amount: matchQuantity * matchPrice,
                    timestamp: new Date(),
                });

                await walletTransaction.save();
                

                // Seller Updates
                const sellerUser = await User.findById(sellOrder.user_id);
                if (sellerUser) {
                    sellerUser.wallet_balance += matchQuantity * matchPrice;
                    await sellerUser.save();
                }

                // Sell Order Updates
                sellOrder.quantity -= matchQuantity;
                if (sellOrder.quantity === 0) {
                    
                    await engine.cancelOrder(sellOrder.id, sellOrder.user_id, false);
                }

                remainingQuantity -= matchQuantity;
            }

            let buyerPortfolio = await UserPortfolio.findOne({ userid: user_id, stock_id });
            if (!buyerPortfolio) {
                buyerPortfolio = new UserPortfolio({ userid: user_id, stock_id, quantity_owned: quantity });
            } else {
                buyerPortfolio.quantity_owned += quantity;
            }
            await buyerPortfolio.save();
            
        }

        return res.json({ success: true, data: allTransactions });

    } catch (error) {
        
        return res.status(500).json({ success: false, data: { error: error.message } });
    }
});

router.get("/getOrderBook", async (req, res) => {
    try {
        const buyOrders = engine.orderBook.buyOrders;
        const sellOrders = engine.orderBook.sellOrders;

        return res.json({
            success: true,
            data: {
                buy_orders: buyOrders.map(order => ({
                    stock_id: order.stock_id,
                    price: order.price,
                    quantity: order.quantity
                })),
                sell_orders: sellOrders.map(order => ({
                    stock_id: order.stock_id,
					user_id: order.user_id,
                    price: order.price,
                    quantity: order.quantity
                }))
            }
        });
    } catch (error) {
        
        return res.status(500).json({ success: false, data: { error: error.message } });
    }
}); 

router.get("/getStockTransactions", authMiddleware, async (req, res) => {
    try {
        const user_id = req.user.id; // Extract user ID from JWT
        

        // Fetch transactions directly filtered from the database
        const transactions = await Transaction.find({
            $or: [{ buyer_id: user_id, is_buy: true }, { seller_id: user_id, is_buy: false }]
        }).lean(); // Use `lean()` for better performance

        if (!transactions.length) {
            
            return res.json({ success: true, data: [] });
        }

        // Format transactions in a single step
        const formattedTransactions = transactions.map(tx => ({
            stock_tx_id: tx.stock_tx_id,  
            parent_stock_tx_id: tx.buyer_id === user_id ? null : tx.parent_stock_tx_id,
            stock_id: tx.stock_id,
            wallet_tx_id: tx.wallet_tx_id || null,
            order_status: tx.order_status,
            is_buy: tx.is_buy,
            order_type: tx.order_type,
            stock_price: tx.stock_price,
            quantity: tx.quantity,
            time_stamp: tx.time_stamp
        }));

        

        return res.json({ success: true, data: formattedTransactions });

    } catch (error) {
        
        return res.status(500).json({ success: false, data: { error: error.message } });
    }
});


router.post("/cancelStockTransaction", authMiddleware, async (req, res) => {
    try {
        const { stock_tx_id } = req.body;
        const user_id = req.user.id; // Extract user ID from JWT token

        if (!stock_tx_id) {
            return res.status(400).json({ success: false, error: "Missing stock transaction ID." });
        }

        

        //Find the exact transaction using `stock_tx_id`
        const transaction = await Transaction.findOne({ stock_tx_id });

        if (!transaction) {
            
            return res.status(404).json({ success: false, error: "Transaction not found." });
        }

        console.log(`Found transaction:`, transaction);

        //Ensure that the order is NOT completed
        if (transaction.order_status === "COMPLETED") {
            
            return res.status(400).json({ success: false, error: "Cannot cancel a completed transaction." });
        }

        //Mark the transaction as canceled
        transaction.order_status = "CANCELLED";
        await transaction.save();
        
		
		// Find all child transactions where parent_stock_tx_id matches the canceled stock_tx_id
		const childTransactions = await Transaction.find({
			parent_stock_tx_id: stock_tx_id,
			is_buy: true
		});
		
		engine.orderBook.sellOrders = engine.orderBook.sellOrders.filter(order => {
		    return !(order.stock_id === transaction.stock_id && order.user_id === transaction.seller_id);
		});

		let matchedQuantity = 0;

		// Sum up all completed buy transactions that originated from the canceled sell transaction
		childTransactions.forEach(tx => {
			matchedQuantity += tx.quantity;
		});

		// Calculate the amount that needs to be refunded
		const refundQuantity = transaction.quantity - matchedQuantity;

		if (refundQuantity > 0) {
			

			// Find the user's portfolio entry for this stock
			let userPortfolio = await UserPortfolio.findOne({
				userid: transaction.seller_id,
				stock_id: transaction.stock_id
			});

			if (!userPortfolio) {
				
				userPortfolio = new UserPortfolio({
					userid: transaction.seller_id,
					stock_id: transaction.stock_id,
					quantity_owned: refundQuantity
				});
			} else {
				userPortfolio.quantity_owned += refundQuantity;
			}

			await userPortfolio.save();
			
		}
			
		return res.json({ success: true, message: "Order canceled successfully.", transaction });

	} 
    catch (error) {
	    
		return res.status(500).json({ success: false, error: error.message });
	}
});

router.get("/getStockPrices", async (req, res) => {
    try {
        

        // Get order book data
        const buyOrders = engine.orderBook.buyOrders;
        const sellOrders = engine.orderBook.sellOrders;

        if (!sellOrders || sellOrders.length === 0) {
            
            return res.json({ success: true, data: [] });
        }

        // Create an array of unique stock_ids from sell orders
        const stockIds = [...new Set(sellOrders.map(order => order.stock_id))];

        // Check Redis for cached stock prices
        const cacheKey = `stock_prices_${stockIds.join("_")}`;
        const cachedData = await client.get(cacheKey);

        if (cachedData) {
            
            return res.json({ success: true, data: JSON.parse(cachedData) });
        }

        // Fetch stock names from MongoDB
        const stockData = await Stock.find({ stock_id: { $in: stockIds } });
        const stockMap = stockData.reduce((map, stock) => {
            map[stock.stock_id] = stock.stock_name;
            return map;
        }, {});

        // Find the lowest price for each stock and attach the stock name
        const stockPrices = {};
        sellOrders.forEach(order => {
            if (!stockPrices[order.stock_id] || order.price < stockPrices[order.stock_id].current_price) {
                stockPrices[order.stock_id] = {
                    stock_id: order.stock_id,
                    stock_name: stockMap[order.stock_id] || "Unknown", // Get stock_name from DB
                    current_price: order.price
                };
            }
        });

        // Cache the stock prices in Redis for 1 hour
        await client.setEx(cacheKey, 3600, JSON.stringify(Object.values(stockPrices)));

        return res.json({ success: true, data: Object.values(stockPrices) });

    } catch (error) {
        
        return res.status(500).json({ success: false, data: { error: error.message } });
    }
});

module.exports = router;