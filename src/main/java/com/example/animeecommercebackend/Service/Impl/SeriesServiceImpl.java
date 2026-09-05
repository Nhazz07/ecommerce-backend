package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.SeriesRequestDto;
import com.example.animeecommercebackend.Dto.Response.CloudinaryUploadResponseDto;
import com.example.animeecommercebackend.Dto.Response.SeriesResponseDto;
import com.example.animeecommercebackend.Entity.Series;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.SeriesMapper;
import com.example.animeecommercebackend.Repository.SeriesRepository;
import com.example.animeecommercebackend.Service.CloudinaryService;
import com.example.animeecommercebackend.Service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesServiceImpl implements SeriesService {
    private final SeriesRepository seriesRepository;
    private final CloudinaryService cloudinaryService;
    @Override
    public SeriesResponseDto createSeries(
                                          MultipartFile file,
                                          String name,
                                          String description
                                          ) {

        CloudinaryUploadResponseDto cloudinaryUploadResponseDto = cloudinaryService.uploadImage(file);
        Series series = new Series();
        series.setImageUrl(cloudinaryUploadResponseDto.getImageUrl());
        series.setPublicId(cloudinaryUploadResponseDto.getPublicId());
        series.setName(name);
        series.setDescription(description);

        Series saved = seriesRepository.save(series);


        return SeriesMapper.toResponse(saved);
    }

    @Override
    public SeriesResponseDto getSeriesById(Long id) {
        Series series = seriesRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Series Not Found"));

        return SeriesMapper.toResponse(series);
    }

    @Override
    public List<SeriesResponseDto> getAllSeries() {
        return seriesRepository.findAll().stream().map(SeriesMapper::toResponse).toList();
    }

    @Override
    public SeriesResponseDto updateSeries(Long id,
                                          MultipartFile file,
                                          String name,
                                          String description
                                          ) {
        Series series = seriesRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Series Not Found"));

        // delete old series image from Cloudinary
        if(series.getPublicId() != null){
            cloudinaryService.deleteImage(
                    series.getPublicId()
            );
        }

        // upload new image
        CloudinaryUploadResponseDto cloudinaryUploadResponseDto = cloudinaryService.uploadImage(file);

        series.setImageUrl(cloudinaryUploadResponseDto.getImageUrl());
        series.setPublicId(cloudinaryUploadResponseDto.getPublicId());
        series.setName(name);
        series.setDescription(description);

        Series updated = seriesRepository.save(series);

        return SeriesMapper.toResponse(updated);
    }

    @Override
    public void deleteSeries(Long id) {
        Series series = seriesRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Series Not Found"));
        seriesRepository.delete(series);
    }
}
