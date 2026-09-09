package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.CartRequestDto;
import com.example.animeecommercebackend.Dto.Response.CartItemResponseDto;
import com.example.animeecommercebackend.Dto.Response.CartResponseDto;
import com.example.animeecommercebackend.Entity.Cart;
import com.example.animeecommercebackend.Entity.CartItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CartMapper {

    public static Cart toEntity(CartRequestDto dto) {

        Cart cart = new Cart();

        return cart;
    }

    public static CartResponseDto toResponse(Cart cart) {

        CartResponseDto dto = new CartResponseDto();

        dto.setId(cart.getId());
        dto.setCartToken(cart.getCartToken());

        if (cart.getUser() != null) {
            dto.setUserId(cart.getUser().getId());
        }

        dto.setCreatedAt(cart.getCreatedAt());
        dto.setUpdatedAt(cart.getUpdatedAt());

        if (cart.getCartItems() != null) {

            List<CartItemResponseDto> items = cart.getCartItems()
                    .stream()
                    .map(CartMapper::toCartItemResponse)
                    .toList();

            dto.setItems(items);

            BigDecimal totalAmount = items.stream()
                    .map(CartItemResponseDto::getSubtotal)
                    .filter(subtotal -> subtotal != null)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            dto.setTotalAmount(totalAmount);
        }

        return dto;
    }

    private static CartItemResponseDto toCartItemResponse(
            CartItem cartItem) {

        CartItemResponseDto dto = new CartItemResponseDto();

        // CartItem information
        dto.setId(cartItem.getId());
        dto.setQuantity(cartItem.getQuantity());
        dto.setCreatedAt(cartItem.getCreatedAt());
        dto.setUpdatedAt(cartItem.getUpdatedAt());

        // Product Variant information
        if (cartItem.getProductVariant() != null) {

            dto.setProductVariantId(
                    cartItem.getProductVariant().getId()
            );

            BigDecimal price =
                    cartItem.getProductVariant().getPrice();

            dto.setPrice(price);

            // Product information
            if (cartItem.getProductVariant().getProduct() != null) {

                dto.setProductId(
                        cartItem.getProductVariant()
                                .getProduct()
                                .getId()
                );

                dto.setProductName(
                        cartItem.getProductVariant()
                                .getProduct()
                                .getProductName()
                );
            }

            // Calculate subtotal
            if (price != null && cartItem.getQuantity() != null) {

                BigDecimal subtotal = price.multiply(
                        BigDecimal.valueOf(
                                cartItem.getQuantity()
                        )
                );

                dto.setSubtotal(subtotal);
            }
        }

        return dto;
    }
}