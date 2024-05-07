package com.ssafy.mvc.model.mapper;

import com.ssafy.mvc.model.AttractionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PageMapper {

    List<AttractionDto> getRandomAttractions(int attractionCount);
}
