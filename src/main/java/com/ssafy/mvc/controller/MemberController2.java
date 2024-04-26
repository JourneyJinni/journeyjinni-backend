package com.ssafy.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.mvc.model.MemberDto;
import com.ssafy.mvc.model.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController2 {
	
	private MemberService memberService;
	
	public MemberController2(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("login")
	protected String login(@RequestParam String user_id,@RequestParam String user_password , HttpSession session) {
		System.out.println(user_id + " " + user_password);
		
		try {
			MemberDto memberDto = memberService.memberLogin(user_id, user_password);
			if(memberDto.getUser_name() == null) return "/index.jsp";
			
			//request.setAttribute("memberDto", memberDto);
			session.setAttribute("userInfo", memberDto);
			
			return "index.jsp";
		} catch(Exception e) {
			e.printStackTrace();
			return "common/error.jsp";
		}
	}
	
}
