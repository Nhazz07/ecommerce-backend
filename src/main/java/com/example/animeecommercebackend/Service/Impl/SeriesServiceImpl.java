package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.SeriesRequestDto;
import com.example.animeecommercebackend.Dto.Response.SeriesResponseDto;
import com.example.animeecommercebackend.Entity.Series;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.SeriesMapper;
import com.example.animeecommercebackend.Repository.SeriesRepository;
import com.example.animeecommercebackend.Service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesServiceImpl implements SeriesService {
    private final SeriesRepository seriesRepository;
    @Override
    public SeriesResponseDto createSeries(SeriesRequestDto dto) {
        Series series = SeriesMapper.toEntity(dto);

        Series saved = seriesRepository.save(series);


        return SeriesMapper.toResponse(saved);
    }

    @Override
    public SeriesResponseDto getSeriesById(Long id) {
        Series series = seriesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Series Not Found"));

        return SeriesMapper.toResponse(series);
    }

    @Override
    public List<SeriesResponseDto> getAllSeries() {
        return seriesRepository.findAll().stream().map(SeriesMapper::toResponse).toList();
    }

    @Override
    public SeriesResponseDto updateSeries(Long id, SeriesRequestDto dto) {
        Series series = seriesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Series Not Found"));

        series.setImageUrl(dto.getImageUrl());
        series.setDescription(dto.getDescription());
        series.setName(dto.getName());

        Series updated = seriesRepository.save(series);
        return SeriesMapper.toResponse(updated);
    }

    @Override
    public void deleteSeries(Long id) {
        Series series = seriesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Series Not Found"));
        seriesRepository.delete(series);
    }
}
