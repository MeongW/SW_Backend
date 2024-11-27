package com.aisinna.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OpenAPIResponseDTO {
    @JsonProperty("response")
    private Response response;

    @Data
    public static class Response {
        @JsonProperty("header")
        private Header header;

        @JsonProperty("body")
        private Body body;

        @Data
        public static class Header {
            private String resultCode;
            private String resultMsg;
        }

        @Data
        public static class Body {
            private Items items;

            @Data
            public static class Items <T> {
                private List<T> item;
            }
        }
    }
}
