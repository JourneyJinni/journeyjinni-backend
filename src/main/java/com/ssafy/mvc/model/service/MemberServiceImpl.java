package com.ssafy.mvc.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.mvc.model.MemberDto;
import com.ssafy.mvc.model.mapper.MemberMapper;

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
	public MemberDto memberLogin(Map<String,String> map) throws Exception {
		System.out.println("service");
		return memberMapper.memberLogin(map);
	}

	@Override
	public int memberIdCheck(String user_id) throws Exception {
		return memberMapper.memberIdCheck(user_id);
	}

	@Override
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
