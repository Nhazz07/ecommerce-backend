package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.SeriesRequestDto;
import com.example.animeecommercebackend.Dto.Response.SeriesResponseDto;
import com.example.animeecommercebackend.Entity.Series;

import java.util.List;

public interface SeriesService {
    SeriesResponseDto createSeries(SeriesRequestDto dto);
    SeriesResponseDto getSeriesById(Long id);
    List<SeriesResponseDto> getAllSeries();
    SeriesResponseDto updateSeries(Long id, SeriesRequestDto dto);
    void deleteSeries(Long id);
}
