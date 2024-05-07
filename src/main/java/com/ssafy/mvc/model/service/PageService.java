package com.ssafy.mvc.model.service;

import com.ssafy.mvc.model.AttractionDto;

import java.sql.SQLException;
import java.util.List;

public interface PageService {

    List<AttractionDto> getRandomAttractions(int attractionCount) throws SQLException;
}
