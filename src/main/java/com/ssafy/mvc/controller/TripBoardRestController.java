package com.ssafy.mvc.controller;


import com.ssafy.mvc.model.SidoDto;
import com.ssafy.mvc.model.trip.TripDto;
import com.ssafy.mvc.service.TripBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/tripboard")
@Slf4j
public class TripBoardRestController {
    TripBoardService tripBoardService;

    TripBoardRestController(TripBoardService tripBoardService) {
        this.tripBoardService = tripBoardService;
    }

    @GetMapping("/getTrip")
    public ResponseEntity<List<TripDto>> getTrip() throws SQLException {
        List<TripDto> tripDtoList;

        tripDtoList = tripBoardService.getTrips();
        return ResponseEntity.ok(tripDtoList);
    }


}
