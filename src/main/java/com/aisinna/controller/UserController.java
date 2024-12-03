package com.aisinna.controller;

import com.aisinna.domain.UserInfo;
import com.aisinna.dto.UserTravelPreferenceDTO;
import com.aisinna.global.dto.ApiResponse;
import com.aisinna.global.dto.ApiResponseDTO;
import com.aisinna.global.exception.TravelExceptionHandler;
import com.aisinna.global.exception.enums.ErrorMessage;
import com.aisinna.global.exception.enums.SuccessMessage;
import com.aisinna.oauth2.domain.CustomUserDetails;
import com.aisinna.service.UserInfoService;
import com.aisinna.service.UserTravelPreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserInfoService userInfoService;
    private final UserTravelPreferenceService userTravelPreferenceService;

    // 유저 선호 여행 컨셉 저장
    public ApiResponse<?> saveUserTravelPreference() {

        return null;
    }

    // 선호 여행 컨셉 저장
    @PostMapping("/preferences")
    public ResponseEntity<ApiResponseDTO<Object>> addPreferences(
            @RequestBody List<UserTravelPreferenceDTO> preferences,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        if (userDetails == null) {
            throw new TravelExceptionHandler(ErrorMessage.AUTHENTICATION_REQUIRED);
        }
        UserInfo userInfo = userDetails.getUser().getUserInfo();

        userInfoService.addPreferences(userInfo, preferences);
        return ApiResponse.success(SuccessMessage.RESOURCE_CREATED);
    }

    // 선호 여행 컨셉 조회
    @GetMapping("/preferences")
    public ResponseEntity<ApiResponseDTO<List<UserTravelPreferenceDTO>>> getPreferencesByUserId(@AuthenticationPrincipal CustomUserDetails userDetails) {

        if (userDetails == null) {
            throw new TravelExceptionHandler(ErrorMessage.AUTHENTICATION_REQUIRED);
        }
        UserInfo userInfo = userDetails.getUser().getUserInfo();

        List<UserTravelPreferenceDTO> preferences = userTravelPreferenceService.getPreferencesByUserId(userInfo);
        return ApiResponse.success(SuccessMessage.RESOURCE_FETCHED, preferences);
    }
}
