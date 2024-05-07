package com.ssafy.mvc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class AttractionDto {
    private int contentId;
    private int contentTypeId;
    private String title;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String tel;
    private String firstImage;
    private String firstImage2;
    private int readCount;
    private int sidoCode;
    private int gugunCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String mlevel;
    private String overview;
    private Double distance;
    // 생성자
    
    public AttractionDto() {}
    
    public AttractionDto(int contentId, int contentTypeId, String title, String addr1, String addr2, String zipcode,
                      String tel, String firstImage, String firstImage2, int readCount, int sidoCode, int gugunCode,
                      BigDecimal latitude, BigDecimal longitude, String mlevel,String overview) {
        this.contentId = contentId;
        this.contentTypeId = contentTypeId;
        this.title = title;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.zipcode = zipcode;
        this.tel = tel;
        this.firstImage = firstImage;
        this.firstImage2 = firstImage2;
        this.readCount = readCount;
        this.sidoCode = sidoCode;
        this.gugunCode = gugunCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mlevel = mlevel;
        this.overview=overview;
    }
    
}

