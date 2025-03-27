const express = require("express");
const Wallet = require("../models/Wallet");
const router = express.Router();
const authMiddleware = require("../middleware/authMiddleware");
const User = require("../models/User");

router.post("/addMoneyToWallet", authMiddleware, async (req, res) => {
    try {
        const { amount } = req.body;
        const user_id = req.user.id;

        if (!amount || amount <= 0) {
            return res.status(400).json({ error: "Invalid amount" });
        }

        const updatedUser = await User.findOneAndUpdate(
            { _id: user_id },
            { $inc: { wallet_balance: amount } },
            { new: true }
        );

        if (!updatedUser) {
            return res.status(404).json({ error: "User not found" });
        }

        
        return res.json({ success: true, balance: updatedUser.wallet_balance });

    } catch (error) {
        
        return res.status(500).json({ error: "Server error" });
    }
});

router.get("/getWalletBalance", authMiddleware, async (req, res) => {
    try {
        const user_id = req.user.id; // Extract user ID from JWT
        

        // Fetch only the required field directly from the database
        const user = await User.findById(user_id).select("wallet_balance").lean();

        if (!user) {
            
            return res.status(404).json({ success: false, data: { error: "User not found" } });
        }

        

        return res.json({ success: true, data: { balance: user.wallet_balance } });

    } catch (error) {
        
        return res.status(500).json({ success: false, data: { error: error.message } });
    }
});

router.get("/getWalletTransactions", authMiddleware, async (req, res) => {
    try {
        const user_id = req.user.id; // Extract user ID from JWT
        

        // Fetch only necessary fields directly from the database
        const walletTransactions = await Wallet.find({ user_id })
            .select("wallet_tx_id stock_tx_id is_debit amount timestamp")
            .lean();

        if (!walletTransactions.length) {
            
            return res.json({ success: true, data: [] });
        }

        // Format transactions
        const formattedTransactions = walletTransactions.map(({ wallet_tx_id, stock_tx_id, is_debit, amount, timestamp }) => ({
            wallet_tx_id,
            stock_tx_id,
            is_debit,
            amount,
            time_stamp: timestamp // Ensure consistent naming
        }));

        

        return res.json({ success: true, data: formattedTransactions });

    } catch (error) {
        
        return res.status(500).json({ success: false, data: { error: error.message } });
    }
});

module.exports = router;
