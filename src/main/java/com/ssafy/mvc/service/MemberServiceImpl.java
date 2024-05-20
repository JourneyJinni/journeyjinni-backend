package com.ssafy.mvc.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.mvc.model.MemberDto;
import com.ssafy.mvc.model.mapper.MemberMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService{
	
	private MemberMapper memberMapper;
	
	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Override
	public MemberDto memberView(String user_id) throws Exception {
		return memberMapper.memberView(user_id);
	}
	
	@Override
	public MemberDto memberLogin(MemberDto memberDto) throws Exception {
		return memberMapper.memberLogin(memberDto);
	}

	@Override
	public int saveRefreshToken(String user_id, String refreshToken) throws SQLException {
		Map<String, String> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("refreshToken", refreshToken);

		return memberMapper.saveRefreshToken(map);
	}

	@Override
	public void deleteRefreshToken(String user_id) throws SQLException {
		memberMapper.deleteRefreshToken(user_id);
	}

	@Override
	public String getRefreshToken(String user_id) throws SQLException {
		return memberMapper.getRefreshToken(user_id);
	}

	@Override
	public int memberIdCheck(String user_id) throws Exception {
		return memberMapper.memberIdCheck(user_id);
	}

	@Override
	@Transactional
	public void memberSignUp(Map<String,String> map) throws Exception {
		memberMapper.memberSignUp(map);		
	}

	@Override
	public void memberModify(MemberDto memberDto) throws Exception {
		memberMapper.memberModify(memberDto);		
	}

	@Override
	public void memberDelete(String user_id) throws Exception {
		memberMapper.memberDelete(user_id);		
	}


}
