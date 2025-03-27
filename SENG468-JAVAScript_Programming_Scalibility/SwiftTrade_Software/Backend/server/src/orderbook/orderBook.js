class OrderBook {
    constructor() {
        this.buyOrders = []; // Highest price first
        this.sellOrders = []; // Lowest price first
    }

    addOrder(order) {
        if (order.is_buy) {
            this.buyOrders.push(order);
            this.buyOrders.sort((a, b) => b.price - a.price); // Sort descending
        } else {
            this.sellOrders.push(order);
            this.sellOrders.sort((a, b) => a.price - b.price); // Sort ascending
        }
    }

    getBestBuy() {
        return this.buyOrders.length > 0 ? this.buyOrders[0] : null;
    }

    getBestSell() {
        return this.sellOrders.length > 0 ? this.sellOrders[0] : null;
    }

    removeOrder(orderId, isBuy) {
        if (isBuy) {
            this.buyOrders = this.buyOrders.filter(order => order.id !== orderId);
        } else {
            this.sellOrders = this.sellOrders.filter(order => order.id !== orderId);
        }
    }
}

module.exports = OrderBook;
