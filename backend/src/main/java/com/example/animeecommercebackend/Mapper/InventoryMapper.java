package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.InventoryRequestDto;
import com.example.animeecommercebackend.Dto.Response.InventoryResponseDto;
import com.example.animeecommercebackend.Entity.Inventory;
import org.springframework.stereotype.Component;


@Component
public class InventoryMapper {
    public static Inventory toEntity(InventoryRequestDto dto){

        Inventory inventory = new Inventory();
        inventory.setQuantity(dto.getQuantity());
        inventory.setReservedQuantity(dto.getReservedQuantity());
        inventory.setReOrderLevel(dto.getReorderLevel());

        return inventory;
    }

    public static InventoryResponseDto toResponse(Inventory inventory){
        InventoryResponseDto dto = new InventoryResponseDto();

        dto.setId(inventory.getId());
        dto.setQuantity(inventory.getQuantity());
        dto.setReservedQuantity(inventory.getReservedQuantity());
        dto.setReorderLevel(inventory.getReOrderLevel());
        dto.setAvailableQuantity(inventory.getAvailableQuantity());
        dto.setUpdatedAt(inventory.getUpdateAt());
        dto.setCreatedAt(inventory.getCreatedAt());
        dto.setUpdatedAt(inventory.getUpdateAt());
        if(inventory.getProductVariant() != null){
            dto.setProductVariantId(inventory.getProductVariant().getId());
        }
        return dto;
    }
}


