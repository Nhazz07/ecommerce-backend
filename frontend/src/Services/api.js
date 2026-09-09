import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080",
    header: {
        "Content-Type": "application/json",
    },
});

// Add Jwt automatically to every request
api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem(token);

        if(token){
            config.header.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return  Promise.reject(error);
    }
)

export default api;
