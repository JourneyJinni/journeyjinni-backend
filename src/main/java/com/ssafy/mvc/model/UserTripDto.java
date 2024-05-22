package com.ssafy.mvc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTripDto {
	private String trip_id;
	private String user_id;
	private String trip_name;
	private String is_shared;
}
