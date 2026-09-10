import React, { useState } from "react";
import { useAuth } from "../Context/AuthContext";

export default function Login() {

    const { login } = useAuth();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();

        setError("");
        setLoading(true);

        try {
            await login({
                email,
                password,
            });

            console.log("Login successful!");

        } catch (error) {

            console.error(error);

            setError(
                error.response?.data?.message ||
                "Invalid email or password"
            );

        } finally {
            setLoading(false);
        }
    };

    return (
        <div
            className="min-h-screen flex items-center justify-center px-4 bg-cover bg-center"
            style={{
                backgroundImage: `
                    linear-gradient(
                        rgba(8, 12, 18, 0.75),
                        rgba(8, 12, 18, 0.75)
                    ),
                    url(https://i.pinimg.com/1200x/2c/de/6b/2cde6b4a0f2f790f2d9042ebcee0133f.jpg)
                `,
            }}
        >

            {/* Login Card */}
            <div
                className="
                    w-full
                    max-w-md
                    bg-white/10
                    backdrop-blur-xl
                    border
                    border-white/20
                    p-8
                    rounded-2xl
                    shadow-2xl
                "
            >

                {/* Title */}
                <h1 className="text-3xl font-bold text-center text-white mb-2">
                    Welcome Back
                </h1>

                <p className="text-center text-gray-300 mb-8">
                    Sign in to your account
                </p>

                {/* Error Message */}
                {error && (
                    <div
                        className="
                            mb-5
                            p-3
                            bg-red-500/20
                            border
                            border-red-400/30
                            text-red-300
                            rounded-lg
                            text-sm
                        "
                    >
                        {error}
                    </div>
                )}

                {/* Login Form */}
                <form
                    onSubmit={handleSubmit}
                    className="space-y-5"
                >

                    {/* Email */}
                    <div>

                        <label
                            className="
                                block
                                mb-2
                                text-sm
                                font-medium
                                text-gray-200
                            "
                        >
                            Email
                        </label>

                        <input
                            type="email"
                            value={email}
                            onChange={(e) =>
                                setEmail(e.target.value)
                            }
                            placeholder="Enter your email"
                            required
                            className="
                                w-full
                                px-4
                                py-3
                                bg-black/30
                                border
                                border-white/20
                                text-white
                                placeholder-gray-400
                                rounded-lg
                                outline-none
                                focus:border-white/50
                                focus:ring-2
                                focus:ring-white/20
                                transition
                            "
                        />

                    </div>

                    {/* Password */}
                    <div>

                        <label
                            className="
                                block
                                mb-2
                                text-sm
                                font-medium
                                text-gray-200
                            "
                        >
                            Password
                        </label>

                        <input
                            type="password"
                            value={password}
                            onChange={(e) =>
                                setPassword(e.target.value)
                            }
                            placeholder="Enter your password"
                            required
                            className="
                                w-full
                                px-4
                                py-3
                                bg-black/30
                                border
                                border-white/20
                                text-white
                                placeholder-gray-400
                                rounded-lg
                                outline-none
                                focus:border-white/50
                                focus:ring-2
                                focus:ring-white/20
                                transition
                            "
                        />

                    </div>

                    {/* Login Button */}
                    <button
                        type="submit"
                        disabled={loading}
                        className="
                            w-full
                            bg-white
                            text-black
                            py-3
                            rounded-lg
                            font-semibold
                            transition
                            hover:bg-gray-200
                            disabled:opacity-50
                            disabled:cursor-not-allowed
                        "
                    >
                        {loading
                            ? "Logging in..."
                            : "Login"
                        }
                    </button>

                </form>

            </div>

        </div>
    );
}
