package com.example.animeecommercebackend.Repository;

import com.example.animeecommercebackend.Entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
    Shipment findByOrderId(Long orderId);
}
