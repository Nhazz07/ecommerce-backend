import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Sun, Moon } from "lucide-react";

export default function Navbar() {
    const navigate = useNavigate();

    const [darkMode, setDarkMode] = useState(
        localStorage.getItem("theme") === "dark"
    );

    useEffect(() => {
        document.documentElement.classList.toggle("dark", darkMode);
        localStorage.setItem("theme", darkMode ? "dark" : "light");
    }, [darkMode]);

    return (
        <nav className="fixed top-0 w-full z-50 bg-white dark:bg-[#0B1020] border-b border-gray-200 dark:border-white/10">
            <div className="max-w-7xl mx-auto px-6 h-16 flex items-center justify-between">

                <h1
                    onClick={() => navigate("/")}
                    className="text-2xl font-bold text-[#0B1020] dark:text-white cursor-pointer"
                >
                    Ani<span className="text-pink-400">Store</span>
                </h1>

                <div className="flex items-center gap-8 text-sm text-gray-600 dark:text-gray-300">

                    <button onClick={() => navigate("/")}>
                        Home
                    </button>

                    <button onClick={() => navigate("/products")}>
                        Products
                    </button>

                    <button onClick={() => navigate("/wishlist")}>
                        Wishlist
                    </button>

                    <button onClick={() => navigate("/cart")}>
                        Cart
                    </button>

                    <button
                        onClick={() => setDarkMode(!darkMode)}
                        className="p-2 rounded-lg hover:bg-gray-100 dark:hover:bg-white/10 transition"
                        title="Change theme"
                    >
                        {darkMode ? (
                            <Sun size={20} />
                        ) : (
                            <Moon size={20} />
                        )}
                    </button>

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
