package com.aisinna.converter;

import com.aisinna.dto.tourAPI.FestivalDetailDTO;
import com.aisinna.dto.tourAPI.FestivalDetailCommonDTO;
import com.aisinna.dto.tourAPI.FestivalDetailImageDTO;
import com.aisinna.dto.tourAPI.FestivalDetailInfoDTO;
import com.aisinna.dto.tourAPI.FestivalDetailIntroDTO;

import java.util.List;
import java.util.stream.Collectors;

public class FestivalDetailConverter {

    public static FestivalDetailDTO convertToDetailDTO(
            List<FestivalDetailCommonDTO> commons,
            List<FestivalDetailImageDTO> images,
            List<FestivalDetailInfoDTO> infos,
            List<FestivalDetailIntroDTO> intros
    ) {
        FestivalDetailDTO detailDTO = new FestivalDetailDTO();

        // Convert and set CommonDTO
        if (commons != null) {
            List<FestivalDetailDTO.CommonDTO> commonDTOs = commons.stream()
                    .map(FestivalDetailConverter::convertCommon)
                    .collect(Collectors.toList());
            detailDTO.setCommons(commonDTOs);
        }

        // Convert and set ImageDTO
        if (images != null) {
            List<FestivalDetailDTO.ImageDTO> imageDTOs = images.stream()
                    .map(FestivalDetailConverter::convertImage)
                    .collect(Collectors.toList());
            detailDTO.setImages(imageDTOs);
        }

        // Convert and set InfoDTO
        if (infos != null) {
            List<FestivalDetailDTO.InfoDTO> infoDTOs = infos.stream()
                    .map(FestivalDetailConverter::convertInfo)
                    .collect(Collectors.toList());
            detailDTO.setInfos(infoDTOs);
        }

        // Convert and set IntroDTO
        if (intros != null) {
            List<FestivalDetailDTO.IntroDTO> introDTOs = intros.stream()
                    .map(FestivalDetailConverter::convertIntro)
                    .collect(Collectors.toList());
            detailDTO.setIntros(introDTOs);
        }

        return detailDTO;
    }

    private static FestivalDetailDTO.CommonDTO convertCommon(FestivalDetailCommonDTO source) {
        FestivalDetailDTO.CommonDTO common = new FestivalDetailDTO.CommonDTO();
        common.setContentId(source.getContentId());
        common.setContentTypeId(source.getContentTypeId());
        common.setTitle(source.getTitle());
        common.setCreatedTime(source.getCreatedTime());
        common.setModifiedTime(source.getModifiedTime());
        common.setTel(source.getTel());
        common.setTelName(source.getTelName());
        common.setHomepage(source.getHomepage());
        common.setBookTour(source.getBookTour());
        common.setFirstImage(source.getFirstImage());
        common.setFirstImage2(source.getFirstImage2());
        common.setCpyrhtDivCd(source.getCpyrhtDivCd());
        common.setAreaCode(source.getAreaCode());
        common.setSigunguCode(source.getSigunguCode());
        common.setCat1(source.getCat1());
        common.setCat2(source.getCat2());
        common.setCat3(source.getCat3());
        common.setAddr1(source.getAddr1());
        common.setAddr2(source.getAddr2());
        common.setZipcode(source.getZipcode());
        common.setMapX(source.getMapX());
        common.setMapY(source.getMapY());
        common.setMlevel(source.getMlevel());
        common.setOverview(source.getOverview());
        return common;
    }

    private static FestivalDetailDTO.ImageDTO convertImage(FestivalDetailImageDTO source) {
        FestivalDetailDTO.ImageDTO image = new FestivalDetailDTO.ImageDTO();
        image.setContentId(source.getContentId());
        image.setOriginImgUrl(source.getOriginImgUrl());
        image.setImgName(source.getImgName());
        image.setSmallImageUrl(source.getSmallImageUrl());
        image.setCpyrhtDivCd(source.getCpyrhtDivCd());
        image.setSerialNum(source.getSerialNum());
        return image;
    }

    private static FestivalDetailDTO.InfoDTO convertInfo(FestivalDetailInfoDTO source) {
        FestivalDetailDTO.InfoDTO info = new FestivalDetailDTO.InfoDTO();
        info.setContentId(source.getContentId());
        info.setContentTypeId(source.getContentTypeId());
        info.setSerialNum(source.getSerialNum());
        info.setInfoName(source.getInfoName());
        info.setInfoText(source.getInfoText());
        info.setFldGubun(source.getFldGubun());
        return info;
    }

    private static FestivalDetailDTO.IntroDTO convertIntro(FestivalDetailIntroDTO source) {
        FestivalDetailDTO.IntroDTO intro = new FestivalDetailDTO.IntroDTO();
        intro.setContentId(source.getContentId());
        intro.setContentTypeId(source.getContentTypeId());
        intro.setSponsor1(source.getSponsor1());
        intro.setSponsor1Tel(source.getSponsor1Tel());
        intro.setSponsor2(source.getSponsor2());
        intro.setSponsor2Tel(source.getSponsor2Tel());
        intro.setEventEndDate(source.getEventEndDate());
        intro.setPlayTime(source.getPlayTime());
        intro.setEventPlace(source.getEventPlace());
        intro.setEventHomepage(source.getEventHomepage());
        intro.setAgeLimit(source.getAgeLimit());
        intro.setBookingPlace(source.getBookingPlace());
        intro.setPlaceInfo(source.getPlaceInfo());
        intro.setSubEvent(source.getSubEvent());
        intro.setProgram(source.getProgram());
        intro.setEventStartDate(source.getEventStartDate());
        intro.setUsetimeFestival(source.getUsetimeFestival());
        intro.setDiscountInfoFestival(source.getDiscountInfoFestival());
        intro.setSpendTimeFestival(source.getSpendTimeFestival());
        intro.setFestivalGrade(source.getFestivalGrade());
        return intro;
    }
}
