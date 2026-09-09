package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.AddressRequestDto;
import com.example.animeecommercebackend.Dto.Response.AddressResponseDto;
import com.example.animeecommercebackend.Entity.Address;
import com.example.animeecommercebackend.Entity.User;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.AddressMapper;
import com.example.animeecommercebackend.Repository.AddressRepository;
import com.example.animeecommercebackend.Repository.UserRepository;
import com.example.animeecommercebackend.Service.AddressService;
import com.example.animeecommercebackend.Service.CurrentUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;

    @Override
    @Transactional
    public AddressResponseDto createdAddress(AddressRequestDto dto) {

        User currentUser = currentUserService.getCurrentUser();

        Address address = AddressMapper.toEntity(dto);

        address.setUser(currentUser);

        Address saved = addressRepository.save(address);

        return AddressMapper.toResponse(saved);
    }

    @Override
    public AddressResponseDto getAddressById(Long id) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Address Not Found"));

        checkOwnership(address);

        return AddressMapper.toResponse(address);
    }

    @Override
    public List<AddressResponseDto> getAllAddress() {

        User currentUser = currentUserService.getCurrentUser();

        return addressRepository.findByUserId(currentUser.getId())
                .stream()
                .map(AddressMapper::toResponse)
                .toList();
    }

    @Override
    public List<AddressResponseDto> getAddressByUserId(Long userId) {

        User currentUser = currentUserService.getCurrentUser();

        if (!currentUser.getId().equals(userId)) {
            throw new AccessDeniedException(
                    "You cannot access another user's addresses"
            );
        }

        return addressRepository.findByUserId(userId)
                .stream()
                .map(AddressMapper::toResponse)
                .toList();
    }

    @Override
    public AddressResponseDto updateAddress(
            Long id,
            AddressRequestDto dto) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Address Not Found!"));

        checkOwnership(address);

        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPostalCode(dto.getPostalCode());
        address.setAddressType(dto.getAddressType());
        address.setCountry(dto.getCountry());
        address.setIsDefault(dto.getIsDefault());

        Address updated = addressRepository.save(address);

        return AddressMapper.toResponse(updated);
    }

    @Override
    public void deleteAddress(Long id) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Address Not Found"));

        checkOwnership(address);

        addressRepository.delete(address);
    }

    @Override
    @Transactional
    public AddressResponseDto setDefaultAddress(
            Long addressId,
            Long userId) {

        User currentUser = currentUserService.getCurrentUser();

        if (!currentUser.getId().equals(userId)) {
            throw new AccessDeniedException(
                    "You cannot modify another user's address"
            );
        }

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Address Not Found"));

        if (!address.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException(
                    "This address does not belong to you"
            );
        }

        List<Address> addresses =
                addressRepository.findByUserId(currentUser.getId());

        for (Address a : addresses) {
            a.setIsDefault(false);
        }

        address.setIsDefault(true);

        addressRepository.saveAll(addresses);

        return AddressMapper.toResponse(address);
    }

    private void checkOwnership(Address address) {

        User currentUser = currentUserService.getCurrentUser();

        if (!address.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException(
                    "You cannot access this address"
            );
        }
    }
}