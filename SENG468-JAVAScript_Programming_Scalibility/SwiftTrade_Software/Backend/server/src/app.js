const express = require("express");
const cors = require("cors");
const mongoose = require("mongoose");

const usersRoute = require("./routes/users");
const walletRoutes = require("./routes/wallets");
const stocksRoute = require("./routes/stocks");
const transactionsRoute = require("./routes/transactions");
const userPortfolioRoutes = require("./routes/userportfolio");

const app = express();
const DEV_ENV = process.env.DEV === "true";

app.use(express.json());
app.use(cors());

app.get("/", (req, res) => {
  res.send("Backend is running!");
});

app.use("/authentication", usersRoute);

app.use("/transaction", walletRoutes);
app.use("/transaction", userPortfolioRoutes);
app.use("/transaction", transactionsRoute);

app.use("/engine", transactionsRoute);

app.use("/setup", stocksRoute);

const helmet = require("helmet");

// Middleware
app.use(express.json({ limit: "10mb" }));  // Increase request body limit
app.use(express.urlencoded({ extended: true, limit: "10mb" }));
app.use(cors());
app.use(helmet());  // Adds security headers

module.exports = app;