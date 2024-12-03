package com.aisinna.controller;

import com.aisinna.domain.*;
import com.aisinna.dto.*;
import com.aisinna.global.dto.ApiResponse;
import com.aisinna.global.dto.ApiResponseDTO;
import com.aisinna.global.exception.TravelExceptionHandler;
import com.aisinna.global.exception.enums.ErrorMessage;
import com.aisinna.global.exception.enums.SuccessMessage;
import com.aisinna.oauth2.domain.CustomUserDetails;
import com.aisinna.service.travel.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/travels")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;
    private final TravelLikeService travelLikeService;
    private final UserTravelService userTravelService;
    private final TravelReviewService travelReviewService;
    private final TravelRecommendService travelRecommendService;

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
                    .region(oncomingTravel.getTravelRecommend().getRegionCode().getName())
                    .theme(oncomingTravel.getTravelRecommend().getTheme())
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
    @PostMapping("/{recommendId}/like")
    public ResponseEntity<ApiResponseDTO<TravelLike>> likeTravelRecommend(
            @PathVariable Long recommendId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            throw new TravelExceptionHandler(ErrorMessage.AUTHENTICATION_REQUIRED);
        }

        TravelLike like = travelLikeService.likeTravelRecommend(userDetails.getUser().getUserInfo(), recommendId);
        return ApiResponse.success(SuccessMessage.RESOURCE_CREATED, like);
    }

    // 여행지 좋아요(조회)
    @GetMapping("/like")
    public ResponseEntity<ApiResponseDTO<List<TravelLike>>> getLikedTravelRecommends(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            throw new TravelExceptionHandler(ErrorMessage.AUTHENTICATION_REQUIRED);
        }

        List<TravelLike> likedRecommends = travelLikeService.getLikedTravelRecommends(userDetails.getUser().getUserInfo());
        return ApiResponse.success(SuccessMessage.RESOURCE_FETCHED, likedRecommends);
    }

    // 사용자 불필요
    // AI 추천 여행지(컨셉)
    // AI 추천 여행지 반환
    @GetMapping("/recommend")
    public ResponseEntity<ApiResponseDTO<List<TravelRecommend>>> getRecommendTravel(@RequestParam(required = false) String theme) {
        List<TravelRecommend> recommendations;

        // Generate AI recommendations based on theme
        recommendations = travelRecommendService.generateAIRecommendations(theme);

        return ApiResponse.success(SuccessMessage.RESOURCE_FETCHED, recommendations);
    }

    // AI 추천 여행지(프론트->챗봇을 통한 저장)
    @PostMapping("/recommend")
    public ResponseEntity<ApiResponseDTO<TravelRecommend>> createRecommend(@RequestParam String region, @RequestParam String theme) {
        TravelRecommend recommend = travelService.generateTravelRecommend(region, theme);
        return ApiResponse.success(SuccessMessage.RESOURCE_CREATED, recommend);
    }


    // AI 추천 여행지 상세 조회
    @GetMapping("/recommend/{recommendId}")
    public ResponseEntity<ApiResponseDTO<TravelPlan>> getRecommendAndCreatePlan(@PathVariable Long recommendId) {
        TravelPlan travelPlan = travelService.createTravelPlanFromRecommend(recommendId);
        return ApiResponse.success(SuccessMessage.RESOURCE_FETCHED, travelPlan);
    }
}
