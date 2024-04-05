package com.ssafy.mvc.model.dao;

import java.sql.SQLException;

import com.ssafy.mvc.model.MemberDto;

public interface MemberDao {
	MemberDto memberView(String user_id) throws SQLException; 
	MemberDto memberLogin(String user_id, String user_password) throws SQLException;
	int memberIdCheck(String user_id) throws SQLException;
	void memberSignUp(MemberDto memberDto) throws SQLException;
	void memberModify(MemberDto memberDto) throws SQLException;
	void memberDelete(String user_id) throws SQLException;
}
