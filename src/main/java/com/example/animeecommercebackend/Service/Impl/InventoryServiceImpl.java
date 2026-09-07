package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.InventoryRequestDto;
import com.example.animeecommercebackend.Dto.Response.InventoryResponseDto;
import com.example.animeecommercebackend.Entity.Inventory;
import com.example.animeecommercebackend.Entity.ProductVariant;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.InventoryMapper;
import com.example.animeecommercebackend.Repository.InventoryRepository;
import com.example.animeecommercebackend.Repository.ProductVariantRepository;
import com.example.animeecommercebackend.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductVariantRepository productVariantRepository;

    @Override
    public InventoryResponseDto createInventory(InventoryRequestDto dto) {

        // Find the ProductVariant
        ProductVariant productVariant = productVariantRepository
                .findById(dto.getProductVariantId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Variant Not Found"));

        // Convert DTO -> Entity
        Inventory inventory = InventoryMapper.toEntity(dto);

        // Connect Inventory -> ProductVariant
        inventory.setProductVariant(productVariant);

        // Calculate available quantity
        inventory.setAvailableQuantity(
                inventory.getQuantity() - inventory.getReservedQuantity()
        );

        // Save
        Inventory saved = inventoryRepository.save(inventory);

        return InventoryMapper.toResponse(saved);
    }

    @Override
    public InventoryResponseDto getInventoryById(Long id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Inventory Not Found!"));

        return InventoryMapper.toResponse(inventory);
    }

    @Override
    public InventoryResponseDto getInventoryByProductVariantId(Long productVariantId) {

        Inventory inventory = inventoryRepository.findByProductVariantId(productVariantId);

        if (inventory == null) {
            throw new ResourceNotFoundException("Inventory Not Found");
        }

        return InventoryMapper.toResponse(inventory);
    }

    @Override
    public List<InventoryResponseDto> getAllInventory() {

        return inventoryRepository.findAll()
                .stream()
                .map(InventoryMapper::toResponse)
                .toList();
    }

    @Override
    public InventoryResponseDto updateInventory(Long id, InventoryRequestDto dto) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Inventory Not Found!!"));

        inventory.setQuantity(dto.getQuantity());
        inventory.setReservedQuantity(dto.getReservedQuantity());
        inventory.setReOrderLevel(dto.getReorderLevel());

        // Recalculate available quantity
        inventory.setAvailableQuantity(
                inventory.getQuantity() - inventory.getReservedQuantity()
        );

        Inventory updated = inventoryRepository.save(inventory);

        return InventoryMapper.toResponse(updated);
    }

    @Override
    public void deleteInventory(Long id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Inventory Not Found"));

        inventoryRepository.delete(inventory);
    }

    @Override
    public boolean hasEnoughStock(Long productVariantId, int quantity) {
        Inventory inventory =
                inventoryRepository.findByProductVariantId(productVariantId);
        if(inventory == null){
            throw new ResourceNotFoundException("Inventory Not Found");
        }
        return inventory.getAvailableQuantity() >= quantity;
    }

    @Override
    public void decreaseStock(Long productVariantId, int quantity) {
        Inventory inventory =
                inventoryRepository.findByProductVariantId(productVariantId);
        if(inventory == null){
            throw new ResourceNotFoundException("Inventory Not Found");
        }
        if(inventory.getAvailableQuantity() < quantity){
            throw new RuntimeException("Not Enough Stock!");
        }
        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventory.setAvailableQuantity(inventory.getAvailableQuantity() - quantity);

        inventoryRepository.save(inventory);
    }
}