package com.ssafy.mvc.service;

import com.ssafy.mvc.model.trip.TripDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


public interface TripBoardService {

    List<TripDto> getTrips() throws SQLException;
}
