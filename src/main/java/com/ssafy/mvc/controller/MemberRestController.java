package com.ssafy.mvc.controller;

import com.ssafy.mvc.model.MemberDto;
import com.ssafy.mvc.model.mapper.MemberMapper;
import com.ssafy.mvc.model.service.MemberService;
import com.ssafy.util.jwt.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/user")
@RestController
public class MemberRestController {

	private final MemberService memberService;
	private final JWTUtil jwtUtil;

	public MemberRestController(MemberService memberService, JWTUtil jwtUtil) {
		this.memberService = memberService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto) {
		log.info("login user : {}", memberDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			MemberDto loginUser = memberService.memberLogin(memberDto);
			if(loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getUser_id());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUser_id());
				log.info("access token : {}", accessToken);
				log.info("refresh token : {}", refreshToken);

//				발급받은 refresh token 을 DB에 저장.
				memberService.saveRefreshToken(loginUser.getUser_id(), refreshToken);

//				JSON 으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);

				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}

		} catch (Exception e) {
			log.info("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}



	@GetMapping("/token_confirm")
	public ResponseEntity<Map<String, Object>> tokenConfirm(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 인증 토큰!!!");
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}

		status = HttpStatus.INTERNAL_SERVER_ERROR;
		log.error("사용 불가능 토큰!!!");
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId")String userId, HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.memberView(userId);
				resultMap.put("userInfo", memberDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
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


	@GetMapping("/idcheck/{userid}")
	@ResponseBody
    public ResponseEntity<Object> idCheck(@PathVariable String userid) throws Exception {
		int cnt = memberService.memberIdCheck(userid);
        if (cnt == 0)
			return ResponseEntity.ok().body(Map.of("success", true, "message", "사용가능한 ID입니다."));

		return ResponseEntity.ok().body(Map.of("success", true, "message", "사용불가능한 ID입니다."));
    }
}
