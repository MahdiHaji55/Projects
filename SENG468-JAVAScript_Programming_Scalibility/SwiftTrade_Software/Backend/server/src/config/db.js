const mongoose = require("mongoose");

const connectDB = async () => {
    try {
        await mongoose.connect(process.env.MONGO_URI || "mongodb://mongo:27017/tradingDB", {
            useNewUrlParser: true,
            useUnifiedTopology: true,
            maxPoolSize: 500, //Increased from 200 to 500
            socketTimeoutMS: 120000, //Increased timeout
            serverSelectionTimeoutMS: 30000, //Increased selection timeout
        });
        console.log("✅ MongoDB Connected");
    } catch (err) {
        console.error("MongoDB Connection Error:", err.message);
        setTimeout(connectDB, 5000); // Retry every 5 seconds
    }
};

module.exports = connectDB;
