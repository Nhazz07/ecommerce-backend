import React from "react";
import {ShoppingCart} from "lucide-react";
import {useNavigate} from "react-router-dom";

function ProductCard({product}){
  const navigate = useNavigate();

  return(
    <div
    onClick={() => navigate(`/products/${product.id}`)}
    className="group overflow-hidden rounded-xl border border-gray-200 dark-border-white/10 bg-white dark-bg-white/5 cursor-pointer hover:border-pink-400 transition"
    >
      {/* Product Image */}
      <div className="h-56 bg-gray-100 dark:bg-white/5 overflow-hidden">
      <img src={product.image}
       alt={product.name}
       className="w-full h-full object-cover group-hover:scale-105 transition duration-300"
       />

       {/* Product Information */}
       <div className="p-4">
        <p className="text-sm text-gray-500 dark:text-gray-400">
          {product.category}
        </p>
        <h3 className="mt-1 font-semibold text-[#0B1020] dark:text-white truncate">
          {product.name}
        </h3>
        <div className="flex items-center justify-between mt-4">
          <span className="font-bold text[#0B1020] dark:text-white">
            {product.price}
          </span>
          <button
          onClick={(e) => {
            e.stopPropagation();
            console.log("Add to cart:", product.name)
          }}
          className="p-2 rounded-lg bg-pink-400 text-[#0B1020] hover:bg-pink-300 transition"
          title="Add to cart"
          >
            <ShoppingCart size={18}/>
          </button>
        </div>
       </div>
      </div>
    </div>
  )
}

export default ProductCard;
