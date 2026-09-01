package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.GiftCardRequestDto;
import com.example.animeecommercebackend.Dto.Response.GiftCardResponseDto;
import com.example.animeecommercebackend.Entity.GiftCard;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.GiftCardMapper;
import com.example.animeecommercebackend.Repository.GiftCardRepository;
import com.example.animeecommercebackend.Repository.UserRepository;
import com.example.animeecommercebackend.Service.GiftCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftCardServiceImpl implements GiftCardService {

    private final GiftCardRepository giftCardRepository;
    private final UserRepository userRepository;
    @Override
    public GiftCardResponseDto createGiftCard(GiftCardRequestDto dto) {
        GiftCard giftCard = GiftCardMapper.toEntity(dto);

        GiftCard saved = giftCardRepository.save(giftCard);
        return GiftCardMapper.toResponse(saved);
    }

    @Override
    public GiftCardResponseDto getGiftCardById(Long id) {
        GiftCard giftCard = giftCardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("GiftCard Not Found!!"));
        return GiftCardMapper.toResponse(giftCard);
    }

    @Override
    public List<GiftCardResponseDto> getAllGiftCard() {
        return giftCardRepository.findAll().stream().map(GiftCardMapper::toResponse).toList();
    }

    @Override
    public List<GiftCardResponseDto> getGiftCardsByUserId(Long userId) {
        List<GiftCard> giftCards = giftCardRepository.findByUserId(userId);
        return giftCards.stream().map(GiftCardMapper::toResponse).toList();
    }

    @Override
    public GiftCardResponseDto updateGiftCard(Long id, GiftCardRequestDto dto) {
        GiftCard giftCard = giftCardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("GiftCard Not Found!!"));

        giftCard.setCode(dto.getCode());
        giftCard.setExpirationDate(dto.getExpirationDate());
        giftCard.setInitialBalance(dto.getInitialBalance());
        giftCard.setStatus(dto.getStatus());

        GiftCard updated = giftCardRepository.save(giftCard);
        return GiftCardMapper.toResponse(updated);
    }

    @Override
    public void deleteGiftCard(Long id) {
        GiftCard giftCard = giftCardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("GiftCard Not Found!!"));

        giftCardRepository.delete(giftCard);

    }
}
