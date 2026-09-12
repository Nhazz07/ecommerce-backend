const API_URL = "http://localhost:8080/api/cart";

export const createCart = async(productId, productVariantId,quantity) => {
    const response = await fetch(API_URL, {
        method: "POST",
        headers:{
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            productId,
            productVariantId,
            quantity
        }),
    });

    const result = await response.json();

    console.log("Backend cart response:", result);
    if(!response.ok){
        throw new Error(`Failed to create cart: ${response.status}` );
    }
    return result;
}
