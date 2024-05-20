package com.ssafy.mvc.service;

import com.ssafy.mvc.model.mapper.TripBoardMapper;
import com.ssafy.mvc.model.trip.TripAttractionDto;
import com.ssafy.mvc.model.trip.TripDto;
import com.ssafy.mvc.model.trip.TripImageDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TripBoardServiceImpl implements TripBoardService{

    TripBoardMapper tripBoardMapper;

    TripBoardServiceImpl(TripBoardMapper tripBoardMapper) {
        this.tripBoardMapper = tripBoardMapper;
    }

    @Override
    public List<TripDto> getTrips() throws SQLException {
        return tripBoardMapper.findTrips();
    }

    @Override
    public List<TripAttractionDto> getTripAttraction(int tripId) throws SQLException {
        return tripBoardMapper.findTripAttractionById(tripId);
    }

    @Override
    public List<TripImageDto> getTripImages(int attractionId) throws SQLException {
        return tripBoardMapper.findTripImagesByAttractionId(attractionId);
    }
}
