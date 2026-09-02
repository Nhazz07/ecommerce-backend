package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.AddressRequestDto;
import com.example.animeecommercebackend.Dto.Response.AddressResponseDto;
import com.example.animeecommercebackend.Entity.Address;
import com.example.animeecommercebackend.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public static Address toEntity(AddressRequestDto dto
                            ){
        Address address = new Address();

        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPostalCode(dto.getPostalCode());
        address.setCountry(dto.getCountry());
        address.setAddressType(dto.getAddressType());
        address.setIsDefault(dto.getIsDefault());

        return address;
    }
    public static AddressResponseDto toResponse(Address address){
        AddressResponseDto dto = new AddressResponseDto();

        dto.setId(address.getId());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setPostalCode(address.getPostalCode());
        dto.setCountry(address.getCountry());
        dto.setAddressType(address.getAddressType());
        dto.setIsDefault(address.getIsDefault());
        dto.setCreatedAt(address.getCreatedAt());
        dto.setUpdatedAt(address.getUpdatedAt());

        if(address.getUser() != null){
            dto.setUserId(address.getUser().getId());
        }
        return dto;
    }
}
