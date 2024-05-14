package com.ssafy.mvc.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.mvc.model.CategoryDto;
import com.ssafy.mvc.model.GugunDto;
import com.ssafy.mvc.model.SidoDto;

@Mapper
public interface AttractionMapper {
	List<SidoDto> getCities() throws SQLException;
	List<CategoryDto> getCategories() throws SQLException;
	List<GugunDto> getGugun(String sidoCode) throws SQLException;
	
	List<AttractionDto> listAttraction() throws SQLException;
	AttractionDto getAttractionbyContentId(int contentId) throws SQLException;

	List<AttractionDto> fetchFilteredList(Map<String, Object> map) throws SQLException;
}
