package com.ssafy.mvc.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mvc.model.AttractionDto;

@Mapper
public interface AttractionMapper {
	List<AttractionDto> listAttraction() throws SQLException;
	AttractionDto getAttractionbyContentId(int contentId) throws SQLException;

	List<AttractionDto> fetchFilteredList(Map<String, Object> map) throws SQLException;
}
