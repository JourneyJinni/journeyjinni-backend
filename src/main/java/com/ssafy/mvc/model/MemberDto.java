package com.ssafy.mvc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberDto {
	private String user_id;
	private String user_name;
	private String user_password;
	private String email_id;
	private String email_domain;
	private String join_date;
}
