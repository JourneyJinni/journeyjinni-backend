package com.ssafy.mvc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter

public class FilterRequestDto {
    private String sido;
    private String gugun;
    private String attractionType;
    private String keyWord;
}
