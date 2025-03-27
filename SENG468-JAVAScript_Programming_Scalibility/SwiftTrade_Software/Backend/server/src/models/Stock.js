const mongoose = require("mongoose");

const StockSchema = new mongoose.Schema({
  stock_id: { type: String, required: true, unique: true },
  stock_name: { type: String, required: true },
  price: { type: Number, required: true },
  availability: { type: Number, required: true },

});

module.exports = mongoose.model("Stock", StockSchema);
