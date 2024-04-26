package com.ssafy.mvc.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.mvc.model.MemberDto;
import com.ssafy.mvc.model.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	private MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@PostMapping("/login")
	protected String login(@RequestParam Map<String,String> map , HttpSession session) {
		System.out.println(map);
		
		try {
			MemberDto memberDto = memberService.memberLogin(map);
			System.out.println("member : " + memberDto);
			if(memberDto == null) return "redirect:/";
			
			session.setAttribute("userInfo", memberDto);
			
			return "redirect:/";
		} catch(Exception e) {
			e.printStackTrace();
			return "common/error";
		}
	}
	
}
