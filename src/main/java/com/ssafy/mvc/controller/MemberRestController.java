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
			log.info("map = " + map.toString());
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
	public ResponseEntity<Object> logout(HttpSession session) {
		try {
			session.invalidate();
			return ResponseEntity.ok().body(Map.of("success", true, "message", "로그아웃이 성공적으로 처리되었습니다."));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("success", false, "message", "서버 오류가 발생했습니다."));
		}
	}

	@GetMapping("/idcheck/{userid}")
	@ResponseBody
    public ResponseEntity<Object> idCheck(@PathVariable String userid) throws Exception {
		int cnt = memberService.memberIdCheck(userid);
        if (cnt == 0)
			return ResponseEntity.ok().body(Map.of("success", true, "message", "사용가능한 ID입니다."));

		return ResponseEntity.ok().body(Map.of("success", true, "message", "사용불가능한 ID입니다."));
    }
}
