package com.ssafy.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.mvc.model.AttractionDto;

public interface AttractionDao {
	List<AttractionDto> listAttraction() throws SQLException;
	AttractionDto getAttractionbyContentId(int contentId) throws SQLException;

	List<AttractionDto> fetchFilteredList(String[] cities, String[] categotys) throws SQLException;
}
