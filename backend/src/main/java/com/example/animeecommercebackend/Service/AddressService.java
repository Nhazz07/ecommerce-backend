package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.AddressRequestDto;
import com.example.animeecommercebackend.Dto.Response.AddressResponseDto;

import java.util.List;

public interface AddressService {
   AddressResponseDto createdAddress(AddressRequestDto dto);
    AddressResponseDto getAddressById(Long id);
    List<AddressResponseDto> getAllAddress();
    List<AddressResponseDto> getAddressByUserId(Long userId);
    AddressResponseDto updateAddress(Long id, AddressRequestDto dto);
    void deleteAddress(Long id);
   AddressResponseDto setDefaultAddress(Long addressId, Long userId);
}
