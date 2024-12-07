package com.aisinna.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TravelSpot {

    @Id
    private String contentid; // 콘텐츠 ID (고유 식별자)
    private String addr1; // 주소 (상세)
    private String addr2; // 주소 (부가)
    private String areacode; // 지역 코드
    private String booktour; // 예약 여부
    private String cat1; // 대분류 카테고리
    private String cat2; // 중분류 카테고리
    private String cat3; // 소분류 카테고리
    private String contenttypeid; // 콘텐츠 유형 ID
    private String createdtime; // 생성 시간 (yyyyMMddHHmmss 형식)
    private String dist; // 현재 위치로부터의 거리
    private String firstimage; // 대표 이미지 URL
    private String firstimage2; // 추가 이미지 URL
    private String cpyrhtDivCd; // 저작권 구분 코드
    private String mapx; // X좌표 (경도)
    private String mapy; // Y좌표 (위도)
    private String mlevel; // 지도 확대 레벨
    private String modifiedtime; // 수정 시간 (yyyyMMddHHmmss 형식)
    private String sigungucode; // 시군구 코드
    private String tel; // 연락처
    private String title; // 장소 제목
}
