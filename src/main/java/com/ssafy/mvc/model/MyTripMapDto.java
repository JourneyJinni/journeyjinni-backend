package com.ssafy.mvc.model;

import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MyTripMapDto {
	private String user_id;
	private String trip_id;
	private String attraction_id;
	private String attraction_name;
	private String attraction_description;
	private byte[] image;
	private String image_description;
	private double latitude;
	private double longitude;
	private String date;
	
	@Override
	public String toString() {
		return "MyTripMapDto [user_id=" + user_id + ", trip_id=" + trip_id + ", attraction_id=" + attraction_id
				+ ", attraction_name=" + attraction_name + ", attraction_description=" + attraction_description
				+ ", image=" + Arrays.toString(image) + ", image_description=" + image_description + ", latitude="
				+ latitude + ", longitude=" + longitude + ", date=" + date + "]";
	}
	
}
