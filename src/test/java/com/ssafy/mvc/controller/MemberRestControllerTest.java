package com.ssafy.mvc.controller;

import com.ssafy.mvc.model.MemberDto;
import com.ssafy.mvc.model.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MemberRestControllerTest {

    @Mock
    private MemberService memberService;

    @Mock
    private HttpSession session;

    @InjectMocks
    private MemberRestController memberRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin(){
        // Given
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("user_id", "user1");
        requestMap.put("user_pw", "1234");

        // When
        ResponseEntity<Object> response = memberRestController.login(requestMap, session);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, ((Map) response.getBody()).get("success"));
        assertEquals("로그인에 성공했습니다.", ((Map) response.getBody()).get("message"));
    }

    // 다른 테스트 메서드들도 유사하게 작성할 수 있습니다.

}
