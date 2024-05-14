package com.ssafy.mvc.model.service;

import java.util.Map;

import com.ssafy.mvc.model.MemberDto;

public interface MemberService {

	MemberDto memberView(String user_id) throws Exception;
	MemberDto memberLogin(MemberDto memberDto) throws Exception;
	int memberIdCheck(String user_id) throws Exception;
	void memberSignUp(Map<String,String> map) throws Exception;
	void memberModify(MemberDto memberDto) throws Exception;
	void memberDelete(String user_id) throws Exception;
}
