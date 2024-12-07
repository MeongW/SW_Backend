package com.aisinna.controller;

import com.aisinna.dto.TravelPreferenceDTO;
import com.aisinna.global.dto.ApiResponse;
import com.aisinna.global.dto.ApiResponseDTO;
import com.aisinna.global.exception.enums.SuccessMessage;
import com.aisinna.service.UserTravelPreferenceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/travel-preferences")
@RestController
@Tag(name = "여행 선호 정보", description = "여행 선호 정보 관련 API")
public class TravelPreferenceController {

    private final UserTravelPreferenceService userTravelPreferenceService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<TravelPreferenceDTO>>> getTravelPreference() {

        List<TravelPreferenceDTO> travelPreferenceList = userTravelPreferenceService.getAllPreferences();

        return ApiResponse.success(SuccessMessage.RESOURCE_FETCHED, travelPreferenceList);
    }
}
