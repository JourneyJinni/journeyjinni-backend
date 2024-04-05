package com.ssafy.mvc.model.service;

import com.ssafy.mvc.model.MemberDto;
import com.ssafy.mvc.model.dao.MemberDao;
import com.ssafy.mvc.model.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService{
	
	private static MemberService instance = new MemberServiceImpl();
	
	private MemberServiceImpl() {}
	
	public static MemberService getInstance() {
		return instance;
	}
	
	private MemberDao memberDao = MemberDaoImpl.getInstance();

	@Override
	public MemberDto memberView(String user_id) throws Exception {
		return memberDao.memberView(user_id);
	}
	
	@Override
	public MemberDto memberLogin(String user_id, String user_password) throws Exception {
		System.out.println("service");
		return memberDao.memberLogin(user_id, user_password);
	}

	@Override
	public int memberIdCheck(String user_id) throws Exception {
		return memberDao.memberIdCheck(user_id);
	}

	@Override
	public void memberSignUp(MemberDto memberDto) throws Exception {
		memberDao.memberSignUp(memberDto);		
	}

	@Override
	public void memberModify(MemberDto memberDto) throws Exception {
		memberDao.memberModify(memberDto);		
	}

	@Override
	public void memberDelete(String user_id) throws Exception {
		memberDao.memberDelete(user_id);		
	}


}
