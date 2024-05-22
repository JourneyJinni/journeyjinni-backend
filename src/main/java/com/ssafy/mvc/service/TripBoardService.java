package com.ssafy.mvc.service;

import com.ssafy.mvc.model.ImageDto;
import com.ssafy.mvc.model.UserMapImageDto;
import com.ssafy.mvc.model.trip.TripAttractionDto;
import com.ssafy.mvc.model.trip.TripDto;
import com.ssafy.mvc.model.trip.TripImageDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


public interface TripBoardService {

    List<TripDto> getTrips() throws SQLException;
    List<TripAttractionDto> getTripAttraction(int tripId) throws SQLException;
    List<TripImageDto> getTripImages(int attractionId) throws SQLException;

    TripImageDto getImageByImageId(int imageId) throws SQLException;
}
