import { Minus, Plus, Trash2 } from "lucide-react";
import { useCart } from "../Context/CartContent";

function Cart(){
  const {
    cartItems,
    removeFromCart,
    updateQuantity,
  } = useCart();
  const totalPrice = cartItems.reduce(
    (total,item) => total + parseFloat(item.price.replace("$", "")) * item.quantity,
    0
  );
  if(cartItems.length === 0){
    return (
      <main className="min-h-screen pt-24 px-6 bg-white text-[#0B1020] dark:bg-[#0B1020] dark:text-white">
        <div className="max-w-7xl mx-auto text-center py-24">
          <h1 className="text-3xl font-bold">Your Cart Is Empty</h1>
          <p className="mt-3 text-gray-500 dark:text-gray-400">Add some anime products to your cart.</p>
        </div>
      </main>
    )
  }
return (
  <main className="min-h-screen pt-24 px-6 bg-white text-[#0B1020] dark:bg-[#0B1020] dark:text-white">
    <div className="max-w-7xl mx-auto">
      <h1 className="text-3xl font-bold mb-8 ">Shopping Cart</h1>
      <div className="grid lg-grid-cols-3 gap-8">
        {/* Cart Item */}
        <div className="lg:col-span-2 space-y-4">
          {cartItems.map((item) => (
            <div
            key={item.id}
            className="flex gap-4 p-4 rounded-xl border-gray-200 dark:border-white/10 bg-white dark:bg-white/5"
            >
              <img
              src={item.image}
              alt={item.name}
              className="w-28 h-28 rounded-lg object-cover"
              />
              <div className="flex-1">
                <div className="flex justify-between gap-4">
                  <div>
                    <h2 className="font-semibold">
                      {item.name}
                    </h2>
                    <p className="text-sm text-gray-500 dark:text-gray-400 mt-1">
                      {item.category}
                    </p>
                  </div>
                  <button
                  onClick={() => removeFromCart(item.id)}
                  className="text-gray-400 hover:text-red-500 transition"
                  title="Remove items"
                  >
                    <Trash2 size={18}/>
                  </button>
                </div>
                <div className="flex items-center justify-between mt-6">
                  <span className="font-bold">
                    {item.price}
                  </span>
                  <div className="flex items-center gap-3">
                    <button
                    onClick={() => updateQuantity(
                      item.id,
                      Math.max(1, item.quantity - 1)
                    )}
                    className="p-1 rounded border bordder-gray-200 dark:border-white/10 hover:border-pink-400"
                    >
                      <Minus size={15}/>
                    </button>
                    <span className="min-w-5 text-center">
                      {item.quantity}
                    </span>
                    <button
                    onClick={() => updateQuantity(
                      item.id,
                      item.quantity + 1
                    )}
                    className="p-1 rounded border border-gray-200 dark:border-white/10 hover:border-pink-400"
                    >
                      <Plus size={15}/>
                    </button>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
        {/* Order Summary */}
        <div className="h-fit p-6 rounded-xl border border-gray-200 dark:border-white/10 bg-white dark:bg-white/5">
        <h2 className="text-xl font-bold">Order Susmmary</h2>
        <div className="flex justify-between mt-6 text-gray-500 dark:text-gray-400">
          <span>Subtotal</span>
          <span>${totalPrice.toFixed(2)}</span>
        </div>
        <div className="flex justify-between mt-3 text-gray-500 dark:text-gray-400">
          <span>Shipping</span>
          <span>Calculated at checkout</span>
        </div>
        <div className="borderr-t border-gray-200 dark:border-white/10 my-5">
        <div className="flex justify-between text-lg font-bold">
          <span>Total</span>
          <span>${totalPrice.toFixed(2)}</span>
        </div>
        <button
        onClick={() => console.log("Procced to checkout")}
        className="w-full mt-6 py-3 rounded-lg bg-pink-400 text-[#0B1020] font-semibold hover:bg-pink-300 transition"
        >Procced to checkout</button>
        </div>
        </div>
      </div>
    </div>
  </main>
)
}
export default Cart;
