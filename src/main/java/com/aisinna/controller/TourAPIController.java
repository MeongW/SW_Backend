package com.aisinna.controller;

import com.aisinna.converter.RegionCodeConverter;
import com.aisinna.domain.enums.RegionCode;
import com.aisinna.dto.tourAPI.FestivalDTO;
import com.aisinna.dto.tourAPI.FestivalDetailDTO;
import com.aisinna.dto.tourAPI.RegionCodeDTO;
import com.aisinna.service.tourAPI.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/festivals/{regionCode}")
    public ResponseEntity<List<FestivalDTO.FestivalResponseDTO>> getFestivalByRegionCode(@PathVariable String regionCode, @RequestParam String date) {

        List<FestivalDTO.FestivalResponseDTO> festivalResponseDTOList = new ArrayList<>();
        List<FestivalDTO> festivals = festivalService.getFestivals(regionCode, date);

        for (FestivalDTO festivalDTO: festivals) {
            festivalResponseDTOList.add(FestivalDTO.toResponse(festivalDTO));
        }
        return ResponseEntity.ok(festivalResponseDTOList);
    }

    @GetMapping("/festivals")
    public ResponseEntity<List<FestivalDTO.FestivalResponseDTO>> getAllFestival(@RequestParam String date) {

        List<FestivalDTO.FestivalResponseDTO> festivalResponseDTOList = new ArrayList<>();
        List<FestivalDTO> festivals = festivalService.getFestivals("0", date);

        for (FestivalDTO festivalDTO: festivals) {
            festivalResponseDTOList.add(FestivalDTO.toResponse(festivalDTO));
        }
        return ResponseEntity.ok(festivalResponseDTOList);
    }

    @GetMapping("/festivals/details/{contentId}")
    public ResponseEntity<FestivalDetailDTO.FestivalDetailResponseDTO> getFestivalDetailsByContentId(@PathVariable String contentId) {

        FestivalDetailDTO.FestivalDetailResponseDTO festivalDetails = festivalService.getFestivalsDetails(contentId);
        return ResponseEntity.ok(festivalDetails);
    }
}
