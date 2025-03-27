const mongoose = require("mongoose");

const TransactionSchema = new mongoose.Schema({
    stock_tx_id: { type: String, required: true, unique: true },
    stock_id: { type: String, required: true }, 
    wallet_tx_id: { type: String, default: null },
    order_status: { type: String, required: true, enum: ["IN_PROGRESS", "PARTIALLY_COMPLETED", "COMPLETED", "CANCELLED"] }, 
    parent_stock_tx_id: { type: String, default: null }, 
    is_buy: { type: Boolean, required: true },
    order_type: { type: String, required: true, enum: ["MARKET", "LIMIT"] },
    stock_price: { type: Number, required: true },
    quantity: { type: Number, required: true },
    time_stamp: { type: Date, default: Date.now },
    buyer_id: { type: String, default: null }, 
    seller_id: { type: String, default: null }  
});

module.exports = mongoose.model("Transaction", TransactionSchema);
