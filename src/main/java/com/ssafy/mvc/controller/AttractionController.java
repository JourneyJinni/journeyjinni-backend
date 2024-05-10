package com.ssafy.mvc.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.mvc.model.NowLocation;
import com.ssafy.mvc.model.service.AttractionService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AttractionController {


    private final AttractionService attractionService;

	@Autowired
    public AttractionController(AttractionService attractionService) {
    	this.attractionService = attractionService; 
	}
    
	 @GetMapping("/attraction")
	 public String attraction() {
		 System.out.println("[Log] attraction으로 이동");
		 return "tour/attraction"; 
	 }

	/**
	 * 반환 타입 수정 완료 그러나 얻는 타입 추후 수정 필요
	 * @param city
	 * @param category
	 * @param request
	 * @return
	 */
	@PostMapping("/filterList")
    public ResponseEntity<List<AttractionDto>> filterList(@RequestParam(name = "city") String city, @RequestParam(name = "category") String category,
														  HttpServletRequest request) {
    	
    	Map<String, Object> map = new HashMap<>();
    	
    	for (String value : request.getParameterValues("city")) {
    		map.put("cities", value);
    	}
    	
    	for (String value : request.getParameterValues("category")) {
    		map.put("categorys", value);
    	}
		 try {
			 List<AttractionDto> filteredList = attractionService.getFilteredList(map);
			System.out.println("[Log] : filterList 실행");

			return ResponseEntity.ok(filteredList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 //에러 발생시 서버 에러 및 빈 리스트 반환
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
    }
    
    @PostMapping("/nowLocation")
    public String nowLocation(HttpServletRequest request) {
    	System.out.println("[Log] : 위치 정보가 사용됨!");
    	String latitude = request.getParameter("latitude");
    	String longitude = request.getParameter("longitude");
    	new NowLocation(latitude, longitude);
    	
    	return "attraction";
    }


}