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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public AddressResponseDto createdAddress(AddressRequestDto dto) {

        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        Address address = AddressMapper.toEntity(dto);

        address.setUser(user);

        Address saved = addressRepository.save(address);

        return AddressMapper.toResponse(saved);
    }

    @Override
    public AddressResponseDto getAddressById(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address Not Found"));

        return AddressMapper.toResponse(address);
    }

    @Override
    public List<AddressResponseDto> getAllAddress() {
         return addressRepository.findAll().stream().map(AddressMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<AddressResponseDto> getAddressByUserId(Long userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        return addresses.stream().map(AddressMapper::toResponse).toList();
    }

    @Override
    public AddressResponseDto updateAddress(Long id, AddressRequestDto dto) {
        User user =userRepository.findById(dto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        Address address = addressRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Address Not Found!"));

        address.setUser(user);
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
        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address Not Found"));
        addressRepository.delete(address);
    }

    @Override
    public AddressResponseDto setDefaultAddress(Long addressId, Long userId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address Not Found"));

        if(!address.getUser().getId().equals(userId)){
            throw new IllegalArgumentException("This address does not belong to this user");
        }
        List<Address> addresses = addressRepository.findByUserId(userId);
        for(Address a : addresses){
            a.setIsDefault(false);
        }
            address.setIsDefault(true);
        addressRepository.saveAll(addresses);
        addressRepository.save(address);
        return AddressMapper.toResponse(address);
    }
}

