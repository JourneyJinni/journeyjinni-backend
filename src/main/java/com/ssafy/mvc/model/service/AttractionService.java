package com.ssafy.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.mvc.model.AttractionDto;

public interface AttractionService {
	List<AttractionDto> list() throws SQLException;
	AttractionDto getAttraction(int contentId) throws SQLException;

	List<AttractionDto> getFilteredList(String[] cities, String[] categorys) throws SQLException;

}
