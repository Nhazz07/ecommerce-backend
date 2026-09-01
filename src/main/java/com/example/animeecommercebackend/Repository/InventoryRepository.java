package com.example.animeecommercebackend.Repository;

import com.example.animeecommercebackend.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Inventory findByProductVariantId(Long productVariantId);
}
