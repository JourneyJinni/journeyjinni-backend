package com.ssafy.mvc.service;

import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.mvc.model.mapper.PageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PageServiceImpl implements PageService{

    PageMapper pageMapper;

    @Autowired
    public PageServiceImpl(PageMapper pageMapper) throws SQLException {
        this.pageMapper = pageMapper;
    }

    @Override
    public List<AttractionDto> getRandomAttractions(int attractionCount) {
        return pageMapper.getRandomAttractions(attractionCount);
    }
}
