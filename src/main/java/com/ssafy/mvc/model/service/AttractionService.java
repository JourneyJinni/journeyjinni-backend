package com.ssafy.mvc.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.mvc.model.CategoryDto;
import com.ssafy.mvc.model.FilterRequestDto;
import com.ssafy.mvc.model.GugunDto;
import com.ssafy.mvc.model.SidoDto;
import com.ssafy.mvc.model.UserTripDto;

public interface AttractionService {

	List<SidoDto> getCities() throws SQLException;
	List<CategoryDto> getCategories() throws SQLException;
	List<GugunDto> getGugun(String sidoCode) throws SQLException;

	List<AttractionDto> list() throws SQLException;
	AttractionDto getAttraction(int contentId) throws SQLException;
	void registerUserTrip(String userId, String tripName) throws SQLException;
	List<UserTripDto> getUserTrip(String userId) throws SQLException;
	public List<AttractionDto> getFilteredList(FilterRequestDto request) throws SQLException;
	List<Integer> getRoute(ArrayList<String[]> list) throws SQLException;;
	
}
