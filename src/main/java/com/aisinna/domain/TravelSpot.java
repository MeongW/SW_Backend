package com.aisinna.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TravelSpot extends BaseEntity {

    private String addr1;
    private String addr2;
    private int contentTypeId;
    private String firstImage;
    private String firstImage2;
    private String mapX;
    private String mapY;
    private String title;

    @ManyToOne
    @JoinColumn(name = "travel_plan_id")
    private TravelPlan travelPlan;


    /*
        "addr1": "인천광역시 계양구 계산로 149 영동프라자",
        "addr2": "",
        "areacode": "2",
        "booktour": "",
        "cat1": "A05",
        "cat2": "A0502",
        "cat3": "A05020700",
        "contentid": "2860230",
        "contenttypeid": "39",
        "createdtime": "20220926180532",
        "dist": "673.1518476136927",
        "firstimage": "",
        "firstimage2": "",
        "cpyrhtDivCd": "",
        "mapx": "126.7289001620",
        "mapy": "37.5373281891",
        "mlevel": "6",
        "modifiedtime": "20240221143142",
        "sigungucode": "2",
        "tel": "",
        "title": "계산동수제돈까스"
     */
}
