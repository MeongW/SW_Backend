package com.aisinna.controller;

import com.aisinna.domain.TravelSpot;
import com.aisinna.dto.tourAPI.OpenAPIResponseDTO;
import com.aisinna.global.dto.ApiResponse;
import com.aisinna.global.dto.ApiResponseDTO;
import com.aisinna.global.exception.enums.SuccessMessage;
import com.aisinna.service.travel.TravelService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/travel-spot")
public class TravelSpotController {

    private final TravelService travelService;

    @Operation(summary = "여행지 저장", description = "여행지 저장 API")
    @PostMapping
    public ResponseEntity<ApiResponseDTO<Void>> saveTravelSpot(OpenAPIResponseDTO dto) {
        travelService.saveTravelSpots(dto);

        return ApiResponse.success(SuccessMessage.RESOURCE_CREATED);
    }
}
