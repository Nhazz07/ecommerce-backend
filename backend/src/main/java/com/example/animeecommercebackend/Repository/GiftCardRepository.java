package com.example.animeecommercebackend.Repository;

import com.example.animeecommercebackend.Entity.GiftCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftCardRepository extends JpaRepository<GiftCard,Long> {

    List<GiftCard> findByUserId(Long userId);
}
