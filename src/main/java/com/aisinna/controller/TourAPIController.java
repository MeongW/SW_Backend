package com.aisinna.controller;

import com.aisinna.converter.RegionCodeConverter;
import com.aisinna.domain.enums.RegionCode;
import com.aisinna.dto.FestivalDTO;
import com.aisinna.dto.tourAPI.FestivalDetailDTO;
import com.aisinna.dto.tourAPI.RegionCodeDTO;
import com.aisinna.service.tourAPI.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tour-api")
public class TourAPIController {

    private final FestivalService festivalService;

    @GetMapping("/region-code")
    public ResponseEntity<List<RegionCodeDTO>> getAllRegionCodes() {

        List<RegionCodeDTO> regionCodeDTOList = Arrays.stream(RegionCode.values())
                .map(RegionCodeConverter::toDTO)
                .toList();

        return ResponseEntity.ok().body(regionCodeDTOList);
    }

    @GetMapping("/festivals")
    public ResponseEntity<?> getFestivalByRegionCode(@RequestParam String regionCode, @RequestParam String date) {

        List<FestivalDTO> festivals = festivalService.getFestivals(regionCode, date);
        return ResponseEntity.ok(festivals);
    }

    @GetMapping("/festivals/details/{contentId}")
    public ResponseEntity<?> getFestivalDetailsByContentId(@PathVariable String contentId) {

        FestivalDetailDTO festivalDetails = festivalService.getFestivalsDetails(contentId);
        return ResponseEntity.ok(festivalDetails);
    }
}
