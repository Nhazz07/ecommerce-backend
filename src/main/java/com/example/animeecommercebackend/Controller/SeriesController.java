package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.SeriesRequestDto;
import com.example.animeecommercebackend.Dto.Response.SeriesResponseDto;
import com.example.animeecommercebackend.Service.Impl.SeriesServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.MULTIPART_FORM_DATA;

@RestController
@RequestMapping("/api/series")
public class SeriesController {
    private final SeriesServiceImpl seriesServiceImpl;

    public SeriesController(SeriesServiceImpl seriesServiceImpl) {
        this.seriesServiceImpl = seriesServiceImpl;
    }

    @PostMapping(consumes = MULTIPART_FORM_DATA)
    public ResponseEntity<ApiResponseDto<SeriesResponseDto>> createSeries(
            @RequestParam("seriesId")
            @Positive Long seriesId,

            @RequestParam("file")
            MultipartFile file,

            @RequestParam("name")
            String name,

            @RequestParam("description")
            String description
            ){
        SeriesResponseDto series = seriesServiceImpl.createSeries(
                seriesId,
                file,
                name,
                description
        );

        ApiResponseDto<SeriesResponseDto> response = new ApiResponseDto<>(
                true,
                "Series Created Successfully!",
                series
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/{id}", consumes = MULTIPART_FORM_DATA)
    public ResponseEntity<ApiResponseDto<SeriesResponseDto>> getSeriesById(@PathVariable @Positive Long id){
        SeriesResponseDto series = seriesServiceImpl.getSeriesById(id);

        ApiResponseDto<SeriesResponseDto> response = new ApiResponseDto<>(
                true,
                "Series Retrieved Successfully!",
                series
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<SeriesResponseDto>>> getAllSeries(){
        List<SeriesResponseDto> series = seriesServiceImpl.getAllSeries();

        ApiResponseDto<List<SeriesResponseDto>> response = new ApiResponseDto<>(
                true,
                "Series Retrieved Successfully",
                series
        );
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<SeriesResponseDto>> updateSeries(
            @PathVariable
            @Positive Long id,

            @RequestParam("file")
            MultipartFile file,

            @RequestParam("name")
            String name,

            @RequestParam("description")
            String description
            ){
        SeriesResponseDto series = seriesServiceImpl.updateSeries(
                id,
                file,
                name,
                description
                );

        ApiResponseDto<SeriesResponseDto> response = new ApiResponseDto<>(
                true,
                "Series Updated Successfully",
                series
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteSeries(@PathVariable @Positive Long id){
        seriesServiceImpl.deleteSeries(id);
        ApiResponseDto<Void> response  = new ApiResponseDto<>(
                true,
                "Series Deleted Successfully",
                null
        );
        return ResponseEntity.ok(response);
    }
}
