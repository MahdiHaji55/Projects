import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { HomeIcon, CreditCardIcon, ChartBarIcon, UsersIcon, Cog6ToothIcon, ArrowLeftOnRectangleIcon } from "@heroicons/react/24/outline";

export default function Dashboard() {
  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const [walletBalance, setWalletBalance] = useState(0);
  const [walletTransactions, setWalletTransactions] = useState([]);
  const [stockPortfolio, setStockPortfolio] = useState([]);
  const [stockPrices, setStockPrices] = useState([]);
  const [stockTransactions, setStockTransactions] = useState([]);
  const [orderBook, setOrderBook] = useState([]);

  useEffect(() => {
    const token = localStorage.getItem("token");

    if (!token) {
      navigate("/login"); // Redirect if not logged in
      return;
    }

    const fetchData = async () => {
      try {
        const headers = { Authorization: `Bearer ${token}` };

        // Fetch user profile
        const userResponse = await axios.get("http://localhost:3001/authentication/profile", { headers });
        setUser(userResponse.data);

        // Fetch wallet balance
        const walletResponse = await axios.get("http://localhost:3001/transaction/getWalletBalance", { headers });
        setWalletBalance(walletResponse.data.balance);

        // Fetch wallet transactions
        const transactionsResponse = await axios.get("http://localhost:3001/transaction/getWalletTransactions", { headers });
        setWalletTransactions(transactionsResponse.data.transactions);

        // Fetch stock portfolio
        const portfolioResponse = await axios.get("http://localhost:3001/transaction/getStockPortfolio", { headers });
        setStockPortfolio(portfolioResponse.data.stocks);

        // Fetch live stock prices
        const pricesResponse = await axios.get("http://localhost:3001/transaction/getStockPrices", { headers });
        setStockPrices(pricesResponse.data.prices);

        // Fetch recent stock transactions
        const stockTransactionsResponse = await axios.get("http://localhost:3001/transaction/getStockTransactions", { headers });
        setStockTransactions(stockTransactionsResponse.data.transactions);

        // Fetch order book
        const orderBookResponse = await axios.get("http://localhost:3001/transaction/getOrderBook", { headers });
        setOrderBook(orderBookResponse.data.orders);

      } catch (error) {
        console.error("Error fetching dashboard data:", error);
      }
    };

    fetchData();
  }, [navigate]);

  return (
    <div className="flex min-h-screen bg-gray-100 dark:bg-gray-900 text-white">
      {/* Sidebar */}
      <aside className="w-64 bg-blue-800 dark:bg-blue-900 p-6">
        <h2 className="text-xl font-bold mb-6">TaskMaster</h2>
        <nav>
          {[
            { name: "Dashboard", icon: HomeIcon },
            { name: "Transactions", icon: CreditCardIcon },
            { name: "Analytics", icon: ChartBarIcon },
            { name: "Users", icon: UsersIcon },
            { name: "Settings", icon: Cog6ToothIcon },
          ].map(({ name, icon: Icon }) => (
            <button key={name} className="flex items-center w-full py-2 px-3 rounded-lg text-left transition hover:bg-blue-700">
              <Icon className="h-5 w-5 mr-2" />
              {name}
            </button>
          ))}
        </nav>
        <button
          onClick={() => {
            localStorage.removeItem("token"); // Remove token
            navigate("/"); // Redirect to home page
          }}
          className="mt-6 flex items-center text-red-400 hover:text-red-500"
        >
          <ArrowLeftOnRectangleIcon className="h-5 w-5 mr-2" />
          Logout
        </button>
      </aside>

      {/* Main Content */}
      <main className="flex-1 p-6">
        <h1 className="text-3xl font-bold mb-4">Dashboard</h1>

        {/* User Profile */}
        <div className="bg-gray-800 p-6 rounded-lg shadow-md mb-6">
          <h3 className="text-lg font-semibold">User Profile</h3>
          {user ? (
            <p className="mt-2">{user.user_name} ({user.email})</p>
          ) : (
            <p>Loading user profile...</p>
          )}
        </div>

        {/* Wallet Balance */}
        <div className="bg-gray-800 p-6 rounded-lg shadow-md mb-6">
          <h3 className="text-lg font-semibold">Wallet Balance</h3>
          <p className="text-2xl font-bold mt-2">${walletBalance}</p>
        </div>

        {/* Wallet Transactions */}
        <div className="bg-gray-800 p-6 rounded-lg shadow-md mb-6">
          <h3 className="text-lg font-semibold">Wallet Transactions</h3>
          <ul className="mt-2">
            {walletTransactions.map((t, index) => (
              <li key={index} className="flex justify-between py-1 text-gray-300">
                <span>{t.description}</span> <span>${t.amount}</span>
              </li>
            ))}
          </ul>
        </div>

        {/* Stock Portfolio */}
        <div className="bg-gray-800 p-6 rounded-lg shadow-md mb-6">
          <h3 className="text-lg font-semibold">Stock Portfolio</h3>
          <ul className="mt-2">
            {stockPortfolio.map((stock, index) => (
              <li key={index} className="flex justify-between py-1 text-gray-300">
                <span>{stock.name}</span> <span>{stock.shares} Shares</span>
              </li>
            ))}
          </ul>
        </div>

        {/* Stock Prices */}
        <div className="bg-gray-800 p-6 rounded-lg shadow-md mb-6">
          <h3 className="text-lg font-semibold">Stock Prices</h3>
          <ul className="mt-2">
            {stockPrices.map((stock, index) => (
              <li key={index} className="flex justify-between py-1 text-gray-300">
                <span>{stock.name}</span> <span>${stock.price}</span>
              </li>
            ))}
          </ul>
        </div>
      </main>
    </div>
  );
}
