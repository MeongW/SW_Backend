package com.aisinna.dto.tourAPI;

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

                @Data
                public static class Item {
                    private String addr1;
                    private String addr2;
                    private String areacode;
                    private String booktour;
                    private String cat1;
                    private String cat2;
                    private String cat3;
                    private String contentid;
                    private String contenttypeid;
                    private String createdtime;
                    private String firstimage;
                    private String firstimage2;
                    private String cpyrhtDivCd;
                    private String mapx;
                    private String mapy;
                    private String mlevel;
                    private String modifiedtime;
                    private String sigungucode;
                    private String tel;
                    private String title;
                }
            }

        }
    }
}
