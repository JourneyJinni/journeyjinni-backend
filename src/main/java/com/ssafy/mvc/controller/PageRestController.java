package com.ssafy.mvc.controller;

import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.mvc.model.service.PageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RestController
public class PageRestController {

    private final PageService pageService;

    @Autowired
    public PageRestController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping(value = {"/", "/index", "/main"})
    public ResponseEntity<List<AttractionDto>> index(HttpServletRequest request) throws SQLException {

        int MAIN_GALLERY_COUNT = 4;
        List<AttractionDto> attractionList = pageService.getRandomAttractions(MAIN_GALLERY_COUNT);
        request.setAttribute("attractionList",attractionList );
        log.info("메인 화면을 불러옴!");

        return ResponseEntity.ok(attractionList);
    }
}
