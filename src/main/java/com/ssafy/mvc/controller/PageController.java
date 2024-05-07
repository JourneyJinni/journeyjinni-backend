package com.ssafy.mvc.controller;

import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.mvc.model.service.AttractionService;
import com.ssafy.mvc.model.service.PageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class PageController {

    private final PageService pageService;

    @Autowired
    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping(value = {"/", "/index", "/main"})
    public String index(HttpServletRequest request) throws SQLException {

        int MAIN_GALLERY_COUNT = 4;
        List<AttractionDto> attractionList = pageService.getRandomAttractions(MAIN_GALLERY_COUNT);
        request.setAttribute("attractionList",attractionList );
        System.out.println("[Log] : 메인화면을 불러옴! ");

        return "index";
    }
}
