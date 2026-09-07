package com.example.animeecommercebackend.Repository;

import com.example.animeecommercebackend.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findOrderByUserId(long userId);

    List<Order> findByUserId(Long userId);
}
