package com.ssafy.mvc.controller;

import com.ssafy.mvc.model.MemberDto;
import com.ssafy.mvc.model.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class MemberRestController {

	private final MemberService memberService;

	public MemberRestController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@PostMapping("/login")
	protected ResponseEntity<Object> login(@RequestBody Map<String,String> map , HttpSession session) {

		try {
			MemberDto memberDto = memberService.memberLogin(map);
			log.info("memberDto: {}", memberDto);

			if(memberDto == null) {
				return ResponseEntity.badRequest()
						.body(Map.of("success", false, "message", "아이디 또는 비밀번호가 잘못되었습니다."));
			}

			return ResponseEntity.ok()
					.body(Map.of("success", true, "message", "로그인에 성공했습니다.", "user", memberDto));
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("success", false, "message", "서버 오류가 발생했습니다."));
		}


	}
	
	@PostMapping("/join")
	public ResponseEntity<Object> join(@RequestBody Map<String,String> map) {
		
		log.info("map: {}", map);
		try {
			memberService.memberSignUp(map);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("success", false, "message", "서버 오류가 발생했습니다."));
		}
		return ResponseEntity.ok()
				.body(Map.of("success", false, "message", "로그인이 성공적으로 완료되었습니다."));
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/idcheck/{userid}")
	@ResponseBody
    public String idCheck(@PathVariable("userid") String userId) throws Exception {
		int cnt = memberService.memberIdCheck(userId);
        return cnt+"";

    }
}
