package com.aisinna.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travels")
@RequiredArgsConstructor
public class TravelController {

    // 사용자 필요
    // 내 여행 계획
    // all
    @GetMapping("/my")
    public ResponseEntity<?> getAllMyTravel() {

        return null;
    }

    // 다가오는 1개
    @GetMapping("/oncoming")
    public ResponseEntity<?> getOncomingTravel() {

        return null;
    }

    // 내 여행 삭제
    @DeleteMapping("/my/{travelId}")
    public ResponseEntity<?> deleteMyTravel(@PathVariable("travelId") String travelId) {
        return null;
    }

    // 여행지 리뷰 작성
    @PostMapping("/{travelId}/review")
    public ResponseEntity<?> postTravelReview(@PathVariable String travelId) {

        return null;
    }

    // 여행지 좋아요(저장)
    @PatchMapping("/{travelId}/like")
    public ResponseEntity<?> likeTravel(@PathVariable String travelId) {

        return null;
    }

    @GetMapping("/likes")
    public ResponseEntity<?> getLikesTravel() {

        return null;
    }

    // 사용자 불필요
    // AI 추천 여행지(컨셉)
    @GetMapping("/recommend")
    public ResponseEntity<?> getRecommendTravel() {

        return null;
    }

    // AI 추천 여행지(프론트->챗봇을 통한 저장)
    @PostMapping("/recommend")
    public ResponseEntity<?> postRecommendTravel() {

        return null;
    }


    // AI 추천 여행지 상세 조회
    @GetMapping("/{travelId}/detail")
    public ResponseEntity<?> getTravelDetail(@PathVariable("travelId") String travelId) {
        return null;
    }
}
