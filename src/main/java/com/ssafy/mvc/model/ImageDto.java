package com.ssafy.mvc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
public class ImageDto {


    private Long image_id;
    private byte[] image;
}
