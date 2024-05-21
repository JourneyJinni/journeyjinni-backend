package com.ssafy.mvc.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.mvc.model.CategoryDto;
import com.ssafy.mvc.model.FilterRequestDto;
import com.ssafy.mvc.model.GugunDto;
import com.ssafy.mvc.model.SidoDto;
import com.ssafy.mvc.model.UserTripDto;

@Mapper
public interface AttractionMapper {
	List<SidoDto> getCities() throws SQLException;
	List<CategoryDto> getCategories() throws SQLException;
	List<GugunDto> getGugun(String sidoCode) throws SQLException;
	
	void registerUserTrip(String userId, String tripName) throws SQLException;
	void registerUserAttraction(String tripId, String attractionName, String attractionDes) throws SQLException;
	
	List<AttractionDto> listAttraction() throws SQLException;
	List<AttractionDto> allAttractions() throws SQLException;
	AttractionDto getAttractionbyContentId(int contentId) throws SQLException;
	List<UserTripDto> getUserTrip(String userId) throws SQLException;
	List<UserTripDto> getUserAttraction(String tripId) throws SQLException;
	List<AttractionDto> fetchFilteredList(FilterRequestDto request) throws SQLException;
}
