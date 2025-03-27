const express = require("express");
const router = express.Router();
const UserPortfolio = require("../models/UserPortfolio");
const authMiddleware = require("../middleware/authMiddleware");
const Stock = require("../models/Stock");

// Get all user portfolios
router.get("/", async (req, res) => {
    try {
        const portfolios = await UserPortfolio.find();
        res.json(portfolios);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});

// Add a new user portfolio
router.post("/", async (req, res) => {
    try {
        const newPortfolio = new UserPortfolio(req.body);
        await newPortfolio.save();
        res.status(201).json(newPortfolio);
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
});
 
// Ensure indexes for fast lookup
async function ensureIndexes() {
    try {
        await UserPortfolio.collection.createIndex({ userid: 1, stock_id: 1 });
        await Stock.collection.createIndex({ stock_id: 1 });
        console.log("✅ Indexes ensured for UserPortfolio and Stock collections.");
    } catch (error) {
        console.error("Error creating indexes:", error);
    }
}
ensureIndexes();

router.get("/getStockPortfolio", authMiddleware, async (req, res) => {
    try {
        const user_id = req.user.id;
        console.log(`Fetching stock portfolio for user: ${user_id}`);

        // 🔹 Step 1: Get all user's stocks, ensuring **oldest stocks appear first**
        const portfolio = await UserPortfolio.find(
            { userid: user_id, quantity_owned: { $gt: 0 } },
            { stock_id: 1, quantity_owned: 1, _id: 0 }
        )
        .sort({ _id: 1 }) // Forces **bottom-up reading (oldest first)**
        .lean();

        if (!portfolio.length) {
            console.log("No stocks found in portfolio.");
            return res.json({ success: true, data: [] });
        }

        // Extract stock IDs for batch lookup
        const stockIds = portfolio.map(stock => stock.stock_id);

        // 🔹 Step 2: Fetch stock names in **one** DB call
        const stocks = await Stock.find(
            { stock_id: { $in: stockIds } },
            { stock_id: 1, stock_name: 1, _id: 0 }
        ).lean();

        // Convert stock data into a **Map** for fast lookups
        const stockMap = new Map(stocks.map(stock => [stock.stock_id, stock.stock_name]));

        // 🔹 Step 3: Merge stock data with portfolio
        const formattedPortfolio = portfolio.map(stock => ({
            stock_id: stock.stock_id,
            stock_name: stockMap.get(stock.stock_id) || "Unknown",
            quantity_owned: stock.quantity_owned,
            updated_at: new Date().toISOString()
        }));

        console.log(`Stock portfolio returned with ${formattedPortfolio.length} entries.`);
        
        return res.json({ success: true, data: formattedPortfolio });

    } catch (err) {
        console.error("❌ Error fetching stock portfolio:", err);
        return res.status(500).json({ success: false, data: { error: err.message } });
    }
});


module.exports = router;
