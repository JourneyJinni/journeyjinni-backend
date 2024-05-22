package com.ssafy.mvc.model.mapper;

import com.ssafy.mvc.model.UserMapImageDto;
import com.ssafy.mvc.model.trip.TripAttractionDto;
import com.ssafy.mvc.model.trip.TripDto;
import com.ssafy.mvc.model.trip.TripImageDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface TripBoardMapper {

    List<TripDto> findTrips() throws SQLException;
    List<TripAttractionDto> findTripAttractionById(int tripId) throws SQLException;
    List<TripImageDto> findTripImagesByAttractionId(int attractionId) throws SQLException;

    TripImageDto findImageByImageId(int imageId) throws SQLException;

}
