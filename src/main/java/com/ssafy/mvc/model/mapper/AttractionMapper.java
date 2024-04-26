package com.ssafy.mvc.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mvc.model.AttractionDto;

@Mapper
public interface AttractionMapper {
	List<AttractionDto> listAttraction() throws SQLException;
	AttractionDto getAttractionbyContentId(int contentId) throws SQLException;

	List<AttractionDto> fetchFilteredList(String[] cities, String[] categotys) throws SQLException;
}
