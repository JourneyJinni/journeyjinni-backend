package com.ssafy.mvc.model.trip;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TripAttractionDto {
    private int attractionId;
    private int tripId;
    private String attractionName;
    private String attractionDescription;
}
