package com.ssafy.mvc.model.service;

import com.ssafy.mvc.model.MemberDto;

public interface MemberService {

	MemberDto memberView(String user_id) throws Exception;
	MemberDto memberLogin(String user_id, String user_password) throws Exception;
	int memberIdCheck(String user_id) throws Exception;
	void memberSignUp(MemberDto memberDto) throws Exception;
	void memberModify(MemberDto memberDto) throws Exception;
	void memberDelete(String user_id) throws Exception;
}
