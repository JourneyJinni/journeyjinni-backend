package com.ssafy.mvc.controller;

import com.ssafy.mvc.model.MemberDto;
import com.ssafy.mvc.service.MemberService;
import com.ssafy.util.jwt.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
				resultMap.put("userInfo", memberDto);
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

	@GetMapping("/logout/{user_id}")
	public ResponseEntity<?> removeToken(@PathVariable ("user_id") String user_id) {
		Map<String, Object> resultMap = new HashMap<>();
		log.info("logout user : user_id : {}", user_id);
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleteRefreshToken(user_id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
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

	@PostMapping("/refresh/{user_id}")
	public ResponseEntity<?> refreshToken(@PathVariable("user_id") String user_id, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, memberDto : {}", token, user_id);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(user_id))) {
				String accessToken = jwtUtil.createAccessToken(user_id);
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 access token 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("userInfo", memberService.memberView(user_id));
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("refresh token 도 사용 불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
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

	@PostMapping("/update")
	public ResponseEntity<Map<String, Object>> update(@RequestBody MemberDto memberDto) {
		log.info("login user : {}", memberDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.memberModify(memberDto);
		} catch (Exception e) {
			log.info("회원 정보 업데이트 중 오류 발생");
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}


	@PostMapping("/signup")
	@ResponseBody
	public ResponseEntity<Object> join(@RequestBody Map<String,String> map) {
		
		log.info("map: {}", map);
		try {
			memberService.memberSignUp(map);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("success", false, "message", "서버 오류가 발생했습니다."));
		}
		return ResponseEntity.ok()
				.body(Map.of("success", true, "message", "로그인이 성공적으로 완료되었습니다."));
	}


	@PostMapping("/checkUserId")
	@ResponseBody
	public ResponseEntity<Object> idCheck(@RequestBody Map<String, String> request) throws Exception {
		String pattern = "^[a-zA-Z0-9]+$";
		String userid = request.get("userid");
		int cnt = memberService.memberIdCheck(userid);
		if (cnt == 0 && userid.matches(pattern))
			return ResponseEntity.ok().body(Map.of("success", true, "message", "사용가능한 ID입니다."));

		return ResponseEntity.ok().body(Map.of("success", false, "message", "사용불가능한 ID입니다."));
	}

	@GetMapping("/delete/{user_id}")
	public ResponseEntity<?> delete(@PathVariable ("user_id") String user_id) {
		Map<String, Object> resultMap = new HashMap<>();
		log.info("delete user : user_id : {}", user_id);
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.memberDelete(user_id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("탈퇴 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
