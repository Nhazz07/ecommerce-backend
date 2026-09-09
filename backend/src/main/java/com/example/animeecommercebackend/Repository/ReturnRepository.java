package com.example.animeecommercebackend.Repository;

import com.example.animeecommercebackend.Entity.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnRepository extends JpaRepository<Return,Long> {
    List<Return> findByOrderId(Long orderId);
}
