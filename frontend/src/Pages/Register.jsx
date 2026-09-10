import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../Context/AuthContext";

const Input = ({ label, name, type = "text", value, onChange, required = true }) => (
    <div>
        <label className="text-sm text-gray-300">{label}</label>
        <input
            type={type}
            name={name}
            value={value}
            onChange={onChange}
            required={required}
            className="mt-2 w-full px-4 py-3 rounded-lg bg-white/5 border border-white/10 text-white outline-none focus:border-pink-400"
        />
    </div>
);

export default function Register() {
    const { register } = useAuth();
    const navigate = useNavigate();

    const [form, setForm] = useState({
        username: "",
        email: "",
        firstName: "",
        lastName: "",
        phoneNumber: "",
        password: "",
        confirmPassword: "",
    });

    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const handleChange = (e) =>
        setForm({ ...form, [e.target.name]: e.target.value });

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        setLoading(true);

        try {
            await register({ ...form, status: "ACTIVE" });
            navigate("/login");
        } catch (error) {
            setError(error.response?.data?.message || "Registration failed");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div
            className="min-h-screen flex items-center justify-center px-4 bg-cover bg-center"
            style={{
                backgroundImage: `
                    linear-gradient(rgba(11,16,32,.88), rgba(11,16,32,.96)),
                    url("https://i.pinimg.com/1200x/2c/de/6b/2cde6b4a0f2f790f2d9042ebcee0133f.jpg")
                `,
            }}
        >
            <div className="w-full max-w-lg">
                <div className="text-center mb-7">
                    <h1 className="text-4xl font-bold text-white">
                        Ani<span className="text-pink-400">Store</span>
                    </h1>
                    <p className="text-gray-400 mt-2">Create your account</p>
                </div>

                <div className="bg-[#0B1020]/90 backdrop-blur-xl border border-pink-400/20 rounded-2xl p-8 shadow-2xl">
                    <h2 className="text-xl font-semibold text-white mb-6">
                        Join AniStore
                    </h2>

                    {error && (
                        <p className="mb-5 text-sm text-pink-400">{error}</p>
                    )}

                    <form onSubmit={handleSubmit} className="grid grid-cols-2 gap-4">
                        <div className="col-span-2">
                            <Input label="Username" name="username" value={form.username} onChange={handleChange} />
                        </div>

                        <Input label="First name" name="firstName" value={form.firstName} onChange={handleChange} />
                        <Input label="Last name" name="lastName" value={form.lastName} onChange={handleChange} />

                        <div className="col-span-2">
                            <Input label="Email" name="email" type="email" value={form.email} onChange={handleChange} />
                        </div>

                        <Input label="Phone number" name="phoneNumber" value={form.phoneNumber} onChange={handleChange} required={false} />
                        <Input label="Password" name="password" type="password" value={form.password} onChange={handleChange} />

                        <div className="col-span-2">
                            <Input label="Confirm password" name="confirmPassword" type="password" value={form.confirmPassword} onChange={handleChange} />
                        </div>

                        <button
                            disabled={loading}
                            className="col-span-2 py-3 rounded-lg bg-pink-400 text-[#0B1020] font-semibold hover:bg-pink-300 transition disabled:opacity-50"
                        >
                            {loading ? "Creating..." : "Create account"}
                        </button>
                    </form>

                    <p className="text-center text-sm text-gray-500 mt-6">
                        Already have an account?
                        <button
                            type="button"
                            onClick={() => navigate("/login")}
                            className="text-pink-400 ml-1"
                        >
                            Sign in
                        </button>
                    </p>
                </div>

                <p className="text-center text-xs text-gray-600 mt-5">
                    © 2026 AniStore
                </p>
            </div>
        </div>
    );
}
