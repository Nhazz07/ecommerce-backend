import React, { createContext, useContext, useState } from "react";
import {createCart} from "../Services/cartApi";

const CartContext = createContext();

export function CartProvider({ children }) {
    const [cartItems, setCartItems] = useState([]);
    const [cartId, setCartId] = useState(null);
    const [cartToken, setCartToken] = useState(
        localStorage.getItem("cartToken")
    );

    const addToCart = async(product) => {
        try{

            console.log("Product being added:", product);
            console.log("Prduct ID: ", product.id);
            console.log("Product Variant ID:", product.productVariantId);

            const response = await createCart(
                product.id,
                product.productVariantId,
                1
            );
            const cart = response.data;

            setCartId(cart.id);
            setCartToken(cart.cartToken);
            setCartItems(cart.items);

            localStorage.setItem("cartId", cart.id);
            localStorage.setItem("cartToken", cart.cartToken);

            console.log("Cart added successfully", cart);
        }catch(error){
            console.log("Failed to add product to cart:", error);
        }
    };

    const removeFromCart = (productId) => {
        setCartItems((prevItems) =>
            prevItems.filter((item) => item.id !== productId)
        );
    };

    const updateQuantity = (productId, quantity) => {
        setCartItems((prevItems) =>
            prevItems.map((item) =>
                item.id === productId
                    ? { ...item, quantity }
                    : item
            )
        );
    };

    const cartCount = cartItems.reduce(
        (total, item) => total + item.quantity,
        0
    );

    return (
        <CartContext.Provider
            value={{
                cartItems,
                cartId,
                cartToken,
                addToCart,
                removeFromCart,
                updateQuantity,
                cartCount,
            }}
        >
            {children}
        </CartContext.Provider>
    );
}

export function useCart() {
    return useContext(CartContext);
}
