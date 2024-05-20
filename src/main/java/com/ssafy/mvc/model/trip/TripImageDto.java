package com.ssafy.mvc.model.trip;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TripImageDto {
    private int imageId;
    private int attractionId;
    private byte[] image;
    private String imageDescription;
    private double latitude;
    private double longitude;
    private Date date;
}