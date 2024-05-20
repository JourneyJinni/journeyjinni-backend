package com.ssafy.mvc.service;

import com.ssafy.mvc.model.mapper.TripBoardMapper;
import com.ssafy.mvc.model.trip.TripDto;
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
        return tripBoardMapper.getTrips();
    }
}
