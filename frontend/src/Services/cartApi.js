const API_URL = "http://localhost:8080/api/cart";
export const createCart = async(prooductId, productVariantId,quantity) => {
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
    if(!response.ok){
        throw new Error("Failed to create cart");
    }
    return response.json;
}
