package com.ssafy.mvc.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.mvc.model.CategoryDto;
import com.ssafy.mvc.model.FilterRequestDto;
import com.ssafy.mvc.model.GugunDto;
import com.ssafy.mvc.model.SidoDto;
import com.ssafy.mvc.model.UserAttractionDto;
import com.ssafy.mvc.model.UserMapImageDto;
import com.ssafy.mvc.model.UserTripDto;

@Mapper
public interface AttractionMapper {
	List<SidoDto> getCities() throws SQLException;
	List<CategoryDto> getCategories() throws SQLException;
	List<GugunDto> getGugun(String sidoCode) throws SQLException;
	
	void registerUserTrip(String userId, String tripName) throws SQLException;
	void registerUserAttraction(UserAttractionDto dto) throws SQLException;
	void registerUserMapImage(UserMapImageDto dto) throws SQLException;
	
	List<UserMapImageDto> getMyMapImages(String userId) throws SQLException;
	
	List<AttractionDto> listAttraction() throws SQLException;
	List<AttractionDto> allAttractions() throws SQLException;
	AttractionDto getAttractionbyContentId(int contentId) throws SQLException;
	List<UserTripDto> getUserTrip(String userId) throws SQLException;
	List<UserTripDto> getUserAttraction(String tripId) throws SQLException;
	List<AttractionDto> fetchFilteredList(FilterRequestDto request) throws SQLException;
	
	UserAttractionDto getUserAttractionById(String attractionId) throws SQLException;
	UserTripDto getUserTripById(String tripId) throws SQLException;
	UserMapImageDto getUserMapImageById(String imageId) throws SQLException;
	
	void deleteUserAttractionById(String attractionId) throws SQLException;
	void deleteTripById(String tripId) throws SQLException;
	void deleteUserMapImageById(String imageId) throws SQLException;
	 
	
}
