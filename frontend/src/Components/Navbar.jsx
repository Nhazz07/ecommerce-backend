import {
    Moon,
    Search,
    ShoppingCart,
    Sun
} from "lucide-react";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useCart } from "../Context/CartContent";
export default function Navbar() {
    const navigate = useNavigate();
    const location = useLocation();
    const {cartCount} = useCart();
    const [darkMode, setDarkMode] = useState(
        localStorage.getItem("theme") === "dark"
    );

    useEffect(() => {
        document.documentElement.classList.toggle("dark", darkMode);
        localStorage.setItem("theme", darkMode ? "dark" : "light");
    }, [darkMode]);

    const isActive = (path) => location.pathname === path;

    return (
        <nav className="fixed top-0 w-full z-50 bg-white dark:bg-[#0B1020] border-b border-gray-200 dark:border-white/10">
            <div className="max-w-7xl mx-auto h-16 px-6 flex items-center justify-between">

                {/* Logo */}
                <h1
                    onClick={() => navigate("/")}
                    className="text-2xl font-bold text-[#0B1020] dark:text-white cursor-pointer"
                >
                    Ani<span className="text-pink-400">Store</span>
                </h1>

                {/* Navigation */}
                <div className="hidden md:flex items-center gap-7 text-sm font-medium text-gray-600 dark:text-gray-300">
                    <button
                        onClick={() => navigate("/")}
                        className={`h-16 ${
                            isActive("/")
                                ? "text-pink-400 border-b-2 border-pink-400"
                                : "hover:text-pink-400"
                        }`}
                    >
                        Home
                    </button>

                    <button
                        onClick={() => navigate("/products")}
                        className={`h-16 ${
                            isActive("/products")
                                ? "text-pink-400 border-b-2 border-pink-400"
                                : "hover:text-pink-400"
                        }`}
                    >
                        Products
                    </button>

                    <button
                        onClick={() => navigate("/wishlist")}
                        className={`h-16 ${
                            isActive("/wishlist")
                                ? "text-pink-400 border-b-2 border-pink-400"
                                : "hover:text-pink-400"
                        }`}
                    >
                        Wishlist
                    </button>

                    <button
                        onClick={() => navigate("/cart")}
                        className={`h-16 ${
                            isActive("/cart")
                                ? "text-pink-400 border-b-2 border-pink-400"
                                : "hover:text-pink-400"
                        }`}
                    >
                        Cart
                    </button>
                </div>

                {/* Actions */}
                <div className="flex items-center gap-4">

                    {/* Search */}
                    <button
                        onClick={() => navigate("/products")}
                        className="text-[#0B1020] dark:text-white hover:text-pink-400 transition"
                        title="Search products"
                    >
                        <Search size={20} strokeWidth={2} />
                    </button>

                    {/* Cart */}
                    <button
                        onClick={() => navigate("/cart")}
                        className="relative text-[#0B1020] dark:text-white hover:text-pink-400 transition"
                        title="Shopping cart"
                    >
                        <ShoppingCart size={20} strokeWidth={2} />

                        <span className="absolute -top-3 -right-3 min-w-4 h-4 px-1 flex items-center justify-center rounded-full bg-pink-400 text-[#0B1020] text-[10px] font-bold">
                            {cartCount}
                        </span>
                    </button>

                    {/* Theme Toggle */}
                    <button
                        onClick={() => setDarkMode(!darkMode)}
                        className="text-[#0B1020] dark:text-white hover:text-pink-400 transition"
                        title="Change theme"
                    >
                        {darkMode ? (
                            <Sun size={19} />
                        ) : (
                            <Moon size={19} />
                        )}
                    </button>

                    {/* Login */}
                    <button
                        onClick={() => navigate("/login")}
                        className="px-5 py-2 rounded-lg bg-pink-400 text-[#0B1020] font-semibold hover:bg-pink-300 transition"
                    >
                        Login
                    </button>

                </div>
            </div>
        </nav>
    );
}
