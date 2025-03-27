import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav className="flex justify-between items-center p-5 bg-white shadow-md">
      <Link to="/" className="text-lg font-bold text-blue-700">SwiftTrade</Link>
      <div className="space-x-6 flex items-center text-gray-700">
        <Link to="/" className="hover:underline">Home</Link>
        <Link to="/login" className="hover:underline">Login</Link>
        <button className="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700">
          Sign Up
        </button>
      </div>
    </nav>
  );
}