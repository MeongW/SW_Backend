package com.aisinna.service.tourAPI;

import com.aisinna.converter.FestivalDetailConverter;
import com.aisinna.dto.tourAPI.FestivalDTO;
import com.aisinna.dto.tourAPI.OpenAPIResponseDTO;
import com.aisinna.dto.tourAPI.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class FestivalService {

    private final RestTemplate restTemplate;

    @Value("${secret.api.open-api.key}")
    private String serviceKey;

    public List<FestivalDTO> getFestivals(String regionCode, String date) {
        String baseUrl = "https://apis.data.go.kr/B551011/KorService1/searchFestival1";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "MobileApp")
                .queryParam("_type", "json")
                .queryParam("arrange", "A")
                .queryParam("eventStartDate", date)
                .queryParam("eventEndDate", date)
                .queryParam("serviceKey", serviceKey);

        if (!"0".equals(regionCode)) {
            builder.queryParam("areaCode", regionCode);
        }

        return fetchDetailDataList(builder.toUriString(), FestivalDTO.class);
    }

    public FestivalDetailDTO.FestivalDetailResponseDTO getFestivalsDetails(String contentID) {
        String baseUrl = "https://apis.data.go.kr/B551011/KorService1";

        List<FestivalDetailImageDTO> images = fetchDetailDataList(
                UriComponentsBuilder.fromHttpUrl(baseUrl + "/detailImage1")
                        .queryParam("MobileOS", "ETC")
                        .queryParam("MobileApp", "MobileApp")
                        .queryParam("_type", "json")
                        .queryParam("contentId", contentID)
                        .queryParam("imageYN", "Y")
                        .queryParam("subImageYN", "Y")
                        .queryParam("serviceKey", serviceKey)
                        .toUriString(),
                FestivalDetailImageDTO.class
        );

        List<FestivalDetailInfoDTO> info = fetchDetailDataList(
                UriComponentsBuilder.fromHttpUrl(baseUrl + "/detailInfo1")
                        .queryParam("MobileOS", "ETC")
                        .queryParam("MobileApp", "MobileApp")
                        .queryParam("_type", "json")
                        .queryParam("contentId", contentID)
                        .queryParam("contentTypeId", "15")
                        .queryParam("serviceKey", serviceKey)
                        .toUriString(),
                FestivalDetailInfoDTO.class
        );

        List<FestivalDetailIntroDTO> intro = fetchDetailDataList(
                UriComponentsBuilder.fromHttpUrl(baseUrl + "/detailIntro1")
                        .queryParam("MobileOS", "ETC")
                        .queryParam("MobileApp", "MobileApp")
                        .queryParam("_type", "json")
                        .queryParam("contentId", contentID)
                        .queryParam("contentTypeId", "15")
                        .queryParam("serviceKey", serviceKey)
                        .toUriString(),
                FestivalDetailIntroDTO.class
        );

        List<FestivalDetailCommonDTO> common = fetchDetailDataList(
                UriComponentsBuilder.fromHttpUrl(baseUrl + "/detailCommon1")
                        .queryParam("MobileOS", "ETC")
                        .queryParam("MobileApp", "MobileApp")
                        .queryParam("_type", "json")
                        .queryParam("contentId", contentID)
                        .queryParam("defaultYN", "Y")
                        .queryParam("firstImageYN", "Y")
                        .queryParam("areacodeYN", "Y")
                        .queryParam("catcodeYN", "Y")
                        .queryParam("addrinfoYN", "Y")
                        .queryParam("mapinfoYN", "Y")
                        .queryParam("overviewYN", "Y")
                        .queryParam("serviceKey", serviceKey)
                        .toUriString(),
                FestivalDetailCommonDTO.class
        );

        FestivalDetailDTO detail = FestivalDetailConverter.convertToDetailDTO(common, images, info, intro);

        return FestivalDetailDTO.toResponse(detail);
    }

    public <T> List<T> getResponseItems(OpenAPIResponseDTO<List<T>> response, Class<T> responseType) {
        if (response != null && "0000".equals(response.getResponse().getHeader().getResultCode())) {
            if (response.getResponse().getBody() != null &&
                    response.getResponse().getBody().getItems() != null &&
                    response.getResponse().getBody().getItems().getItem() != null) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    // Convert items to the desired List<T> type
                    return objectMapper.convertValue(
                            response.getResponse().getBody().getItems().getItem(),
                            objectMapper.getTypeFactory().constructCollectionType(List.class, responseType)
                    );
                } catch (IllegalArgumentException e) {
                    log.warn("Failed to convert response items to type {}: {}", responseType.getSimpleName(), e.getMessage());
                }
            }
        }
        log.warn("No items found or API response is invalid.");
        return Collections.emptyList();
    }

    private <T> List<T> fetchData(String apiUrl, Class<T> responseType) {
        try {
            String decodedUrl = URLDecoder.decode(apiUrl, StandardCharsets.UTF_8);
            log.info("Fetching data from API: {}", decodedUrl);

            ParameterizedTypeReference<OpenAPIResponseDTO<List<T>>> typeRef =
                    new ParameterizedTypeReference<>() {};

            OpenAPIResponseDTO<List<T>> response = restTemplate.exchange(
                    decodedUrl,
                    HttpMethod.GET,
                    null,
                    typeRef
            ).getBody();

            return getResponseItems(response, responseType);
        } catch (RestClientException e) {
            log.warn("Failed to fetch data from API: {}. Error: {}", apiUrl, e.getMessage());
            return Collections.emptyList();
        }
    }

    // items 1개인 경우
    private <T> T fetchDetailData(String apiUrl, Class<T> responseType) {
        List<T> items = fetchData(apiUrl, responseType);
        return items.isEmpty() ? null : items.get(0);
    }

    // items 여러개인 경우
    private <T> List<T> fetchDetailDataList(String apiUrl, Class<T> responseType) {
        return fetchData(apiUrl, responseType);
    }

}
