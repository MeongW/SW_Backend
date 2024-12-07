package com.aisinna.controller;

import com.aisinna.domain.*;
import com.aisinna.dto.*;
import com.aisinna.dto.openAI.TravelThemeRecommendationDTO;
import com.aisinna.global.dto.ApiResponse;
import com.aisinna.global.dto.ApiResponseDTO;
import com.aisinna.global.exception.TravelExceptionHandler;
import com.aisinna.global.exception.enums.ErrorMessage;
import com.aisinna.global.exception.enums.SuccessMessage;
import com.aisinna.oauth2.domain.CustomUserDetails;
import com.aisinna.service.UserTravelPreferenceService;
import com.aisinna.service.travel.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/travels")
@RequiredArgsConstructor
@Tag(name = "여행 정보", description = "여행 정보 관련 API")
public class TravelController {

    private final TravelService travelService;
    private final TravelLikeService travelLikeService;
    private final UserTravelService userTravelService;
    private final TravelReviewService travelReviewService;
    private final UserTravelPreferenceService userTravelPreferenceService;
    private final TravelRecommendService travelRecommendService;
    private final TravelRecommendationCacheService travelRecommendationCacheService;

    // 사용자 필요
    // 내 여행 계획 모두
    // 사용자의 모든 여행 썸네일 반환
    @GetMapping("/my")
    public ResponseEntity<ApiResponseDTO<List<UserTravelSummaryDTO>>> getAllMyTravel(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            throw new TravelExceptionHandler(ErrorMessage.AUTHENTICATION_REQUIRED);
        }

        List<UserTravelSummaryDTO> travels = userTravelService.getAllMyTravel(userDetails.getUser().getUserInfo());
        return ApiResponse.success(SuccessMessage.RESOURCE_FETCHED, travels);
    }

    // 내 여행 상세 조회
    // 특정 UserTravel에 맞춰 TravelPlan 반환
    @GetMapping("/my/{travelId}")
    public ResponseEntity<ApiResponseDTO<TravelPlanDetailDTO>> getMyTravel(
            @PathVariable("travelId") Long travelId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            throw new TravelExceptionHandler(ErrorMessage.AUTHENTICATION_REQUIRED);
        }

        TravelPlanDetailDTO travelPlan = userTravelService.getMyTravel(userDetails.getUser().getUserInfo(), travelId);
        return ApiResponse.success(SuccessMessage.RESOURCE_FETCHED, travelPlan);
    }

    // 내 여행 저장
    @PostMapping
    public ResponseEntity<ApiResponseDTO<Object>> saveUserTravel(
            @RequestParam Long travelRecommendId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            throw new TravelExceptionHandler(ErrorMessage.AUTHENTICATION_REQUIRED);
        }

        userTravelService.saveUserTravel(userDetails.getUser().getUserInfo(), travelRecommendId, startDate, endDate);
        return ApiResponse.success(SuccessMessage.RESOURCE_CREATED);
    }

    // 다가오는 1개
    @GetMapping("/oncoming")
    public ResponseEntity<?> getOncomingTravel(@AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            throw new TravelExceptionHandler(ErrorMessage.AUTHENTICATION_REQUIRED);
        }

        // 서비스에서 다가오는 여행을 가져옴
        UserTravel oncomingTravel = userTravelService.getOncomingTravel(userDetails.getUser().getUserInfo());

        // 응답 DTO 생성
        if (oncomingTravel != null) {
            OncomingTravelDTO oncomingTravelDTO = OncomingTravelDTO.builder()
                    .userTravelId(oncomingTravel.getId())
                    .startDate(oncomingTravel.getStartDate().toString())
                    .endDate(oncomingTravel.getEndDate().toString())
                    .build();

            return ApiResponse.success(SuccessMessage.RESOURCE_FETCHED, oncomingTravelDTO);
        }

        return ApiResponse.success(SuccessMessage.RESOURCE_FETCHED);
    }

    // 내 여행 삭제
    @DeleteMapping("/my/{travelId}")
    public ResponseEntity<?> deleteMyTravel(@PathVariable("travelId") Long travelId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            throw new TravelExceptionHandler(ErrorMessage.AUTHENTICATION_REQUIRED);
        }

        userTravelService.deleteTravel(userDetails.getUser().getUserInfo(), travelId);
        return ApiResponse.success(SuccessMessage.RESOURCE_DELETED);
    }

    // 여행지 리뷰 작성
    @PostMapping("/{travelId}/review")
    public ResponseEntity<?> postTravelReview(
            @PathVariable Long travelId,
            @RequestBody TravelReviewDTO reviewDTO,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            throw new TravelExceptionHandler(ErrorMessage.AUTHENTICATION_REQUIRED);
        }

        TravelReview travelReview = travelReviewService.createReview(userDetails.getUser().getUserInfo(), travelId, reviewDTO);
        return ApiResponse.success(SuccessMessage.RESOURCE_CREATED, travelReview);
    }

    // 여행지 좋아요(저장)
    @Operation(summary = "여행지(테마) 저장", description = "여행지 저장 API")
    @PostMapping("/like")
    public ResponseEntity<ApiResponseDTO<Void>> likeTravelRecommend(
            @RequestBody TravelThemeRecommendationDTO recommend,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            throw new TravelExceptionHandler(ErrorMessage.AUTHENTICATION_REQUIRED);
        }

        TravelLike like = travelLikeService.likeTravelRecommend(userDetails.getUser().getUserInfo(), recommend);
        return ApiResponse.success(SuccessMessage.RESOURCE_CREATED);
    }

    // 여행지 좋아요(조회)
    @Operation(summary = "여행지(테마) 저장 조회", description = "여행지 저장 조회 API")
    @GetMapping("/like")
    public ResponseEntity<ApiResponseDTO<List<TravelThemeRecommendationDTO>>> getLikedTravelRecommends(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            throw new TravelExceptionHandler(ErrorMessage.AUTHENTICATION_REQUIRED);
        }

        List<TravelThemeRecommendationDTO> likedRecommends = travelLikeService.getLikedTravelRecommends(userDetails.getUser().getUserInfo());
        return ApiResponse.success(SuccessMessage.RESOURCE_FETCHED, likedRecommends);
    }

    // 사용자 불필요
    // AI 추천 여행지(컨셉)
    // AI 추천 여행지 반환
    @GetMapping("/recommend")
    public ResponseEntity<ApiResponseDTO<List<TravelThemeRecommendationDTO>>> getRecommendTravel() {
        return ApiResponse.success(SuccessMessage.RESOURCE_CREATED, travelLikeService.getTopLikedTravelRecommends());
    }

    // AI 추천 여행지(프론트->챗봇을 통한 저장)
    @Operation(summary = "AI 추천 테마 생성", description = "AI 추천 테마 생성 API",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "mapX", description = "경도", required = true, example = "126.97725"),
                    @Parameter(in = ParameterIn.QUERY, name = "mapY", description = "위도", required = true, example = "37.570892"),
                    @Parameter(in = ParameterIn.QUERY, name = "date", description = "조회 기준 날짜", required = true, example = "20240101")
            })
    @PostMapping("/recommend")
    public ResponseEntity<ApiResponseDTO<List<TravelThemeRecommendationDTO>>> createRecommend(
            @RequestParam Double mapX,
            @RequestParam Double mapY,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        if (userDetails == null) {
            throw new TravelExceptionHandler(ErrorMessage.AUTHENTICATION_REQUIRED);
        }

        Long userId = userDetails.getUser().getUserId();

        // Redis에서 추천 데이터 조회
        List<TravelThemeRecommendationDTO> cachedRecommendations = travelRecommendationCacheService.getRecommendations(userId.toString());
        if (cachedRecommendations != null) {
            return ApiResponse.success(SuccessMessage.RESOURCE_FETCHED, cachedRecommendations);
        }

        // 추천 데이터 생성
        List<UserTravelPreferenceDTO> preferences = userTravelPreferenceService.getPreferencesByUserId(userDetails.getUser().getUserInfo());
        List<TravelThemeRecommendationDTO> recommendations;
        try {
            recommendations = travelRecommendService.generateTravelRecommend(mapX, mapY, preferences);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new TravelExceptionHandler(ErrorMessage.FASTAPI_COMMUNICATION_FAILED);
        }

        // Redis에 저장
        travelRecommendationCacheService.saveRecommendations(userId.toString(), recommendations);

        return ApiResponse.success(SuccessMessage.RESOURCE_CREATED, recommendations);
    }



    // AI 추천 여행지 상세 조회
    @GetMapping("/recommend/{recommendId}")
    public ResponseEntity<ApiResponseDTO<TravelPlan>> getRecommendAndCreatePlan(@PathVariable Long recommendId) {
        TravelPlan travelPlan = travelService.createTravelPlanFromRecommend(recommendId);
        return ApiResponse.success(SuccessMessage.RESOURCE_FETCHED, travelPlan);
    }
}
