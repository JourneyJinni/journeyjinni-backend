package com.ssafy.mvc.model.mapper;

import com.ssafy.mvc.model.trip.TripDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface TripBoardMapper {

    List<TripDto> getTrips() throws SQLException;


}
