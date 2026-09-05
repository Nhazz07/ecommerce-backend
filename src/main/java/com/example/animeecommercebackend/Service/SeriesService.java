package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.SeriesRequestDto;
import com.example.animeecommercebackend.Dto.Response.SeriesResponseDto;
import com.example.animeecommercebackend.Entity.Series;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SeriesService {
    SeriesResponseDto createSeries(
            Long seriesId,
            MultipartFile file,
            String name,
            String description
    );
    SeriesResponseDto getSeriesById(Long id);
    List<SeriesResponseDto> getAllSeries();
    SeriesResponseDto updateSeries(Long id,
                                   MultipartFile file,
                                   String name,
                                   String description
                                   );
    void deleteSeries(Long id);
}
