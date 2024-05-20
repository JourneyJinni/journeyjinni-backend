package com.ssafy.mvc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMapImageDto {
	private String image_id;
	private String attraction_id;
	private byte[] image;
	private String image_description;
	private double latitude;
	private double longitude;
	private String date;
	
	
}
