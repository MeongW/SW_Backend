package com.aisinna.controller;

import com.aisinna.converter.RegionCodeConverter;
import com.aisinna.domain.enums.RegionCode;
import com.aisinna.dto.tourAPI.FestivalDTO;
import com.aisinna.dto.tourAPI.FestivalDetailDTO;
import com.aisinna.dto.tourAPI.RegionCodeDTO;
import com.aisinna.global.dto.ApiResponse;
import com.aisinna.global.dto.ApiResponseDTO;
import com.aisinna.global.exception.enums.SuccessMessage;
import com.aisinna.service.tourAPI.FestivalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tour-api")
@Tag(name = "Tour API(공공데이터)", description = "여행 정보 공공데이터 관련 API")
public class TourAPIController {

    private final FestivalService festivalService;

    @Operation(summary = "지역 코드 조회", description = "지역 코드 조회 API")
    @GetMapping("/region-code")
    public ResponseEntity<ApiResponseDTO<List<RegionCodeDTO>>> getAllRegionCodes() {

        List<RegionCodeDTO> regionCodeDTOList = Arrays.stream(RegionCode.values())
                .map(RegionCodeConverter::toDTO)
                .toList();

        return ApiResponse.success(SuccessMessage.RESOURCE_FETCHED, regionCodeDTOList);
    }

    @Operation(summary = "지역 기반 축제 조회", description = "지역 기반 축제 조회 API",
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "regionCode", description = "지역 코드", required = true, example = "0"),
                    @Parameter(in = ParameterIn.QUERY, name = "date", description = "조회 기준 날짜", required = true, example = "20240101")
            })
    @GetMapping("/festivals/{regionCode}")
    public ResponseEntity<ApiResponseDTO<List<FestivalDTO.FestivalResponseDTO>>> getFestivalByRegionCode(@PathVariable String regionCode, @RequestParam String date) {

        List<FestivalDTO.FestivalResponseDTO> festivalResponseDTOList = new ArrayList<>();
        List<FestivalDTO> festivals = festivalService.getFestivals(regionCode, date);

        for (FestivalDTO festivalDTO: festivals) {
            festivalResponseDTOList.add(FestivalDTO.toResponse(festivalDTO));
        }
        return ApiResponse.success(SuccessMessage.RESOURCE_FETCHED, festivalResponseDTOList);
    }

    @Operation(summary = "전국 축제 조회", description = "전국 축제 조회 API",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "date", description = "조회 기준 날짜", required = true, example = "20240101")
            })
    @GetMapping("/festivals")
    public ResponseEntity<ApiResponseDTO<List<FestivalDTO.FestivalResponseDTO>>> getAllFestival(@RequestParam String date) {

        List<FestivalDTO.FestivalResponseDTO> festivalResponseDTOList = new ArrayList<>();
        List<FestivalDTO> festivals = festivalService.getFestivals("0", date);

        for (FestivalDTO festivalDTO: festivals) {
            festivalResponseDTOList.add(FestivalDTO.toResponse(festivalDTO));
        }
        return ApiResponse.success(SuccessMessage.RESOURCE_FETCHED, festivalResponseDTOList);
    }

    @Operation(summary = "축제 정보 상세 조회", description = "축제 정보 상세 조회 API",
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "contentId", description = "축제 콘텐츠 ID", required = true, example = "2785797")
            })
    @GetMapping("/festivals/details/{contentId}")
    public ResponseEntity<ApiResponseDTO<FestivalDetailDTO.FestivalDetailResponseDTO>> getFestivalDetailsByContentId(@PathVariable String contentId) {

        FestivalDetailDTO.FestivalDetailResponseDTO festivalDetails = festivalService.getFestivalsDetails(contentId);
        return ApiResponse.success(SuccessMessage.RESOURCE_FETCHED, festivalDetails);
    }
}
