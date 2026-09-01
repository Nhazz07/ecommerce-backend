package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.WishlistRequestDto;
import com.example.animeecommercebackend.Dto.Response.WishlistResponseDto;
import com.example.animeecommercebackend.Entity.Product;
import com.example.animeecommercebackend.Entity.Wishlist;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WishlistMapper {

    public static Wishlist toEntity(WishlistRequestDto dto) {

        Wishlist wishlist = new Wishlist();

        wishlist.setName(dto.getName());
        wishlist.setDescription(dto.getDescription());
        wishlist.setIsPublic(dto.getIsPublic());

        return wishlist;
    }

    public static WishlistResponseDto toResponse(Wishlist wishlist) {

        WishlistResponseDto dto = new WishlistResponseDto();

        dto.setId(wishlist.getId());
        dto.setName(wishlist.getName());
        dto.setDescription(wishlist.getDescription());
        dto.setIsPublic(wishlist.getIsPublic());
        dto.setCreatedAt(wishlist.getCreatedAt());
        dto.setUpdatedAt(wishlist.getUpdatedAt());

        if (wishlist.getProducts() != null) {

            dto.setProductIds(
                    wishlist.getProducts()
                            .stream()
                            .map(Product::getId)
                            .toList()
            );

        } else {

            dto.setProductIds(List.of());
        }

        if (wishlist.getUser() != null) {
            dto.setUserId(wishlist.getUser().getId());
        }

        return dto;
    }
}