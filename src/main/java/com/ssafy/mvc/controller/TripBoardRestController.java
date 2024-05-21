package com.ssafy.mvc.controller;

import com.ssafy.mvc.model.trip.TripDto;
import com.ssafy.mvc.model.trip.TripImageDto;
import com.ssafy.mvc.model.trip.TripAttractionDto;
import com.ssafy.mvc.service.TripBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/tripboard")
@Slf4j
public class TripBoardRestController {

    private final TripBoardService tripBoardService;

    public TripBoardRestController(TripBoardService tripBoardService) {
        this.tripBoardService = tripBoardService;
    }

    @GetMapping("/getTrip")
    public ResponseEntity<List<TripDto>> getTrip() throws SQLException {
        List<TripDto> tripDtoList;

        tripDtoList = tripBoardService.getTrips();
        return ResponseEntity.ok(tripDtoList);
    }

    @GetMapping("/getTripDetail/{tripId}")
    public ResponseEntity<List<TripAttractionDto>> getTripDetail(@PathVariable int tripId) {
        try {
            List<TripAttractionDto> tripAttractions = tripBoardService.getTripAttraction(tripId);
            return ResponseEntity.ok(tripAttractions);
        } catch (Exception e) {
            log.error("Failed to get trip detail", e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/getTripImageByAttraction/{attractionId}")
    public ResponseEntity<List<TripImageDto>> getTripImage(@PathVariable int attractionId) {
        try {
            List<TripImageDto> tripImages = tripBoardService.getTripImages(attractionId);
            return ResponseEntity.ok(tripImages);
        } catch (Exception e) {
            log.error("Failed to get trip images", e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/getTripImageByTrip/{tripId}")
    public ResponseEntity<TripImageDto> getTripImageByTrip(@PathVariable int tripId) {
        try {
            log.info("Get trip image by trip id {}", tripId);
            TripImageDto tripImages = tripBoardService.getTripImages(tripBoardService.getTripAttraction(tripId).get(0).
                    getAttractionId()).get(0);
            return ResponseEntity.ok(tripImages);
        } catch (Exception e) {
            log.error("Failed to get trip images " + tripId , e);
            return ResponseEntity.status(500).build();
        }
    }
}
