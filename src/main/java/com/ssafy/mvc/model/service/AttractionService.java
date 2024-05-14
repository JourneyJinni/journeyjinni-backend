package com.ssafy.mvc.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.mvc.model.CategoryDto;
import com.ssafy.mvc.model.SidoDto;

public interface AttractionService {

	List<SidoDto> getCities() throws SQLException;
	List<CategoryDto> getCategories() throws SQLException;


	List<AttractionDto> list() throws SQLException;
	AttractionDto getAttraction(int contentId) throws SQLException;

	public List<AttractionDto> getFilteredList(Map<String, Object> map) throws SQLException;
	List<Integer> getRoute(ArrayList<String[]> list) throws SQLException;;
}
