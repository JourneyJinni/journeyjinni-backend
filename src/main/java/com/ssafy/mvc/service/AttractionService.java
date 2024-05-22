package com.ssafy.mvc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.mvc.model.*;

public interface AttractionService {

	List<SidoDto> getCities() throws SQLException;
	List<CategoryDto> getCategories() throws SQLException;
	List<GugunDto> getGugun(String sidoCode) throws SQLException;

	List<AttractionDto> list() throws SQLException;
	public List<AttractionDto> allAttractions(NowLocation nowLocation) throws SQLException;
	AttractionDto getAttraction(int contentId) throws SQLException;
	void registerUserTrip(String userId, String tripName) throws SQLException;
	void registerUserAttraction(UserAttractionDto dto) throws SQLException;
	void registerUserMapImage(UserMapImageDto dto) throws SQLException;
	List<UserMapImageDto> getMyMapImages(String userId) throws SQLException;
	
	List<UserTripDto> getUserTrip(String userId) throws SQLException;
	List<UserTripDto> getUserAttraction(String tripId) throws SQLException;
	public List<AttractionDto> getFilteredList(FilterRequestDto request) throws SQLException;
	List<Integer> getRoute(ArrayList<String[]> list) throws SQLException;
	
	UserAttractionDto getUserAttractionById(String attractionId) throws SQLException;
	UserTripDto getUserTripById(String tripId) throws SQLException;
	UserMapImageDto getUserMapImageById(String imageId) throws SQLException;
	public Long findMostSimilarImage(byte[] queryImageData) throws SQLException;

	void deleteUserAttractionById(String attractionId) throws SQLException;
	void deleteTripById(String tripId) throws SQLException;
	void deleteUserMapImageById(String imageId) throws SQLException;

	void updateUserTripById(String tripId, String tripName) throws SQLException;
	void updateUserAttractionById(String attractionId, String attractionName, String attractionDes) throws SQLException;
	void updateUserMapImageById(String imageId, String imageDes) throws SQLException;
}
