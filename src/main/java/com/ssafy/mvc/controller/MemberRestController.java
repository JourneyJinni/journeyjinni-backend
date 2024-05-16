package com.ssafy.mvc.controller;

import com.ssafy.mvc.model.MemberDto;
import com.ssafy.mvc.model.mapper.MemberMapper;
import com.ssafy.mvc.model.service.MemberService;
import com.ssafy.util.jwt.JWTUtil;
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
	
//	@PostMapping("/login")
//	protected ResponseEntity<Object> login(@RequestBody Map<String,String> map , HttpSession session) {
//
//		try {
//			MemberDto memberDto = memberService.memberLogin(map);
//			log.info("map = " + map.toString());
//			log.info("memberDto: {}", memberDto);
//
//			if(memberDto == null) {
//				return ResponseEntity.badRequest()
//						.body(Map.of("success", false, "message", "아이디 또는 비밀번호가 잘못되었습니다."));
//			}
//
//			return ResponseEntity.ok()
//					.body(Map.of("success", true, "message", "로그인에 성공했습니다.", "user", memberDto));
//		} catch(Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body(Map.of("success", false, "message", "서버 오류가 발생했습니다."));
//		}
//	}

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

	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(@PathVariable ("userId") String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleteRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
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
