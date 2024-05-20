package com.ssafy.mvc.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.mvc.model.MemberDto;

public interface MemberService {

	MemberDto memberView(String user_id) throws Exception;
	MemberDto memberLogin(MemberDto memberDto) throws Exception;

	int saveRefreshToken(String user_id, String refreshToken) throws SQLException;
	void deleteRefreshToken(String user_id) throws SQLException;
	String getRefreshToken(String user_id) throws SQLException;
	int memberIdCheck(String user_id) throws Exception;
	void memberSignUp(Map<String,String> map) throws Exception;
	void memberModify(MemberDto memberDto) throws Exception;
	void memberDelete(String user_id) throws Exception;
}
