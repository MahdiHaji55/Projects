# SwiftTrade Backend

This repository contains the code for SwiftTrade, a stock trading application. 

## Features

- User registration and login with JWT authentication
- Wallet management (add funds, view balance)
- Stock trading engine with order book
- Market and limit orders
- User portfolio tracking
- Transaction history

## Technologies Used

- Node.js
- Express.js
- MongoDB
- Docker

## Installation and Running with Docker

1.  **Clone the repository:** `git clone https://github.com/Chaiitanyaa/DayTrade.git`
2.  **Build the Docker image:**
    ```bash
    docker compose build 
    ```
3.  **Run the Docker container:**
    ```bash
    docker compose up
    ```
4. Change the host for your testing to 3001

## API Endpoints

- **Authentication:**
    - `POST /authentication/register`: Register a new user
    - `POST /authentication/login`: Login user
    - `GET /authentication/profile`: Get current user profile
- **Wallet:**
    - `POST /transaction/addMoneyToWallet`: Add money to wallet
    - `GET /transaction/getWalletBalance`: Get wallet balance
    - `GET /transaction/getWalletTransactions`: Get wallet transactions history
- **Stocks:**
    - `GET /setup`: Fetch all stocks
    - `POST /setup/createStock`: Create a new stock (admin only)
    - `POST /setup/addStockToUser`: Add stock to user's portfolio (admin only)
- **Transactions:**
    - `POST /transaction/placeStockOrder`: Place a stock order (buy/sell)
    - `GET /transaction/getOrderBook`: Get the current order book
    - `GET /transaction/getStockTransactions`: Get user's stock transactions history
    - `POST /transaction/cancelStockTransaction`: Cancel a stock transaction
    - `GET /transaction/getStockPrices`: Get current stock prices
- **User Portfolio:**
    - `GET /transaction/`: Get all user portfolios (for testing/admin)
    - `POST /transaction/`: Add a new user portfolio (for testing/admin)
    - `GET /transaction/getStockPortfolio`: Get user's stock portfolio