package com.example.animeecommercebackend.Repository;

import com.example.animeecommercebackend.Entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
    Optional<Refund> findByAReturnId(Long returnId);
}
