import api from "./api";

// Register
export const register = async (userData) => {
    const response = await api.post(
        "/api/auth/register",
        userData
    );

    return response.data;
};

// Login
export const login = async (loginData) => {
    const response = await api.post(
        "/api/auth/login",
        loginData
    );

    const data = response.data;

    // Save JWT
    if (data.data?.accessToken) {
        localStorage.setItem(
            "token",
            data.data.accessToken
        );
    }

    return data;
};

// Logout
export const logout = () => {
    localStorage.removeItem("token");
};

// Get stored token
export const getToken = () => {
    return localStorage.getItem("token");
};
