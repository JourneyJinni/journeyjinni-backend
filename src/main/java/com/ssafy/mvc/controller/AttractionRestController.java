package com.ssafy.mvc.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.mvc.model.NowLocation;
import com.ssafy.mvc.model.service.AttractionService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttractionRestController {


    private final AttractionService attractionService;

    @Autowired
    public AttractionRestController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }


    /**
     * 사용자에게 어떤 리스트를 제공할지 결정할 컨트롤러
     * 프론트 결정 후에 로직 처리
     * @param city
     * @param category
     * @param request
     * @return
     */
    @PostMapping("/filterList")
    public String filterList(@RequestParam String city, @RequestParam(name = "category") String category,
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
            request.setAttribute("filteredList", filteredList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "tour/attraction";
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