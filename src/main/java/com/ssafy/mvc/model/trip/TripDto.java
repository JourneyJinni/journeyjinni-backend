package com.ssafy.mvc.model.trip;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TripDto {
    private int tripId;
    private String userId;
    private String tripName;

}
