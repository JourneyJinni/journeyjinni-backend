package com.ssafy.mvc.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.mvc.model.SidoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.mvc.model.NowLocation;
import com.ssafy.mvc.model.service.AttractionService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AttractionRestController {


    private final AttractionService attractionService;

    @Autowired
    public AttractionRestController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @GetMapping("/getCity")
    public ResponseEntity<List<SidoDto>> getCity() {
        try {
            return ResponseEntity.ok(attractionService.getCities());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    /**
     * 반환 타입 수정 완료 그러나 얻는 타입 추후 수정 필요
     * @param city
     * @param category
     * @return
     */
    @PostMapping("/filterList")
    public ResponseEntity<List<AttractionDto>> filterList(@RequestBody String city, @RequestBody String category) {

        Map<String, Object> map = new HashMap<>();

//        for (String value : request.getParameterValues("city")) {
//            map.put("cities", value);
//        }
//
//        for (String value : request.getParameterValues("category")) {
//            map.put("categorys", value);
//        }

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