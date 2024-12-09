package com.aisinna.dto.tourAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OpenAPIResponseDTO<T> {
    private Response<T> response;

    @Data
    public static class Response<T> {
        private Header header;
        private Body<T> body;
    }

    @Data
    public static class Header {
        @JsonProperty("resultCode")
        private String resultCode;
        @JsonProperty("resultMsg")
        private String resultMsg;
    }

    @Data
    public static class Body<T> {
        private Items<T> items;
    }

    @Data
    public static class Items<T> {
        private T item;
    }
}
