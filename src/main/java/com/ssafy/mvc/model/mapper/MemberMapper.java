package com.ssafy.mvc.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mvc.model.MemberDto;

@Mapper
public interface MemberMapper {
	MemberDto memberView(String user_id) throws SQLException; 
	MemberDto memberLogin(Map<String,String> map) throws SQLException;
	int memberIdCheck(String user_id) throws SQLException;
	void memberSignUp(MemberDto memberDto) throws SQLException;
	void memberModify(MemberDto memberDto) throws SQLException;
	void memberDelete(String user_id) throws SQLException;
}
