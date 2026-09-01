package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.SeriesRequestDto;
import com.example.animeecommercebackend.Dto.Response.SeriesResponseDto;
import com.example.animeecommercebackend.Entity.Product;
import com.example.animeecommercebackend.Entity.Series;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeriesMapper {

    public static Series toEntity(SeriesRequestDto dto

                           ){
        Series series = new Series();

        series.setName(dto.getName());
        series.setDescription(dto.getDescription());
        series.setImageUrl(dto.getImageUrl());


        return series;
    }

    public static SeriesResponseDto toResponse(Series series){
        SeriesResponseDto dto = new SeriesResponseDto();

        dto.setId(series.getId());
        dto.setName(series.getName());
        dto.setDescription(series.getDescription());
        dto.setImageUrl(series.getImageUrl());

        if(series.getProducts() != null){
            dto.setProductIds(series.getProducts().stream().map(Product::getId).toList());
        }else{
            dto.setProductIds(List.of());
        }
        return dto;
    }
}
