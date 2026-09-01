package com.example.animeecommercebackend.Repository;

import com.example.animeecommercebackend.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findOrderByUserId(long userId);
}
