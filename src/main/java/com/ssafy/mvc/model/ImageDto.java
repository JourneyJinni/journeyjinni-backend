package com.ssafy.mvc.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ImageDto {


    private Long image_id;
    private byte[] image;
}
