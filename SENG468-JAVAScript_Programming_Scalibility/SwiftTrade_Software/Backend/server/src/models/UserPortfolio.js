const mongoose = require("mongoose");

const UserPortfolioSchema = new mongoose.Schema({
  userid: { type: mongoose.Schema.Types.ObjectId, ref: 'User' },
  stock_id: { type: String, required: true },
  quantity_owned: { type: Number, default: 0 }
});

module.exports = mongoose.model("UserPortfolio", UserPortfolioSchema);
