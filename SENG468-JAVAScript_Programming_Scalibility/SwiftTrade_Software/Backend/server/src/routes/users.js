const express = require("express");
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");
const User = require("../models/User");
const redisClient = require("../config/redis");
const router = express.Router();


// Ensure user_name is indexed in MongoDB
(async () => {
    await User.createIndexes({ user_name: 1 }, { unique: true });
    
})();


// Register a new user
router.post("/register", async (req, res) => {
    try {
        const { user_name, password, name } = req.body;

        //Step 1: Check Redis Cache (but don't rely on it)
        let isCached = false;
        try {
            const cachedUser = await redisClient.get(`user:${user_name}`);
            if (cachedUser) isCached = true;
        } catch (redisError) {
            
        }

        if (isCached) {
            return res.status(400).json({ success: false, error: "User already exists (cached)" });
        }

        //Step 2: Atomic Insert Using `findOneAndUpdate`
        const filter = { user_name };
        const update = { user_name, password, name };
        const options = { upsert: true, new: true, setDefaultsOnInsert: true };

        const user = await User.findOneAndUpdate(filter, update, options);

        if (!user) {
            return res.status(400).json({ success: false, error: "User registration failed" });
        }

        //Step 3: Update Redis Cache Only AFTER Successful Insert
        try {
            await redisClient.set(`user:${user_name}`, "exists", "EX", 300);
        } catch (redisError) {
            
        }

        return res.status(201).json({ success: true, message: "User registered successfully" });

    } catch (error) {
        
        return res.status(500).json({ success: false, error: "Server error" });
    }
});

// Login user (Ensure it's optimized for multi-threading)
router.post("/login", async (req, res) => {
    try {
        const { user_name, password } = req.body;
        const user = await User.findOne({ user_name });
/*
        if (!user || !(await bcrypt.compare(password, user.password))) {
            return res.status(400).json({ success: false, error: "Invalid credentials" });
        }
*/
		
		if(!user || password == !user.password){
			
			return res.status(400).json({ success: false, error: "Invalid credentials" });
			
			
		}
		
        const token = jwt.sign(
            { id: user._id.toString(), user_name: user.user_name },
            process.env.JWT_SECRET || "your_secret",
            { expiresIn: "1h" }
        );

        user.jwt_token = token;
        await user.save();

        return res.json({ success: true, data: { token } });

    } catch (error) {
       
        return res.status(500).json({ success: false, error: "Server error" });
    }
});

module.exports = router;
