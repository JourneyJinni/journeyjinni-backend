//package com.ssafy.mvc.controller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ssafy.mvc.model.MemberDto;
//import com.ssafy.mvc.model.service.MemberService;
//
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class MemberController {
//
//	private final MemberService memberService;
//
//	public MemberController(MemberService memberService) {
//		this.memberService = memberService;
//	}
//
//	@PostMapping("/login")
//	protected String login(@RequestParam Map<String,String> map , HttpSession session) {
//		System.out.println(map);
//
//		try {
//			MemberDto memberDto = memberService.memberLogin(map);
//			System.out.println("member : " + memberDto);
//			if(memberDto == null) return "redirect:/";
//
//			session.setAttribute("userInfo", memberDto);
//
//			return "redirect:/";
//		} catch(Exception e) {
//			e.printStackTrace();
//			return "common/error";
//		}
//	}
//
//	@PostMapping("/join")
//	public String join(@RequestParam Map<String,String> map) {
//
//		System.out.println(map);
//		try {
//			memberService.memberSignUp(map);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "redirect:/";
//	}
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/";
//	}
//	@GetMapping("/idcheck/{userid}")
//	@ResponseBody
//    public String idCheck(@PathVariable("userid") String userId) throws Exception {
//		int cnt = memberService.memberIdCheck(userId);
//        return cnt+"";
//
//    }
//}
