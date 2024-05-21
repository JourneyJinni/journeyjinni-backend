package com.ssafy.mvc.controller;


import java.io.File;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.mvc.model.CategoryDto;
import com.ssafy.mvc.model.FilterRequestDto;
import com.ssafy.mvc.model.GugunDto;
import com.ssafy.mvc.model.MetadataDto;
import com.ssafy.mvc.model.NowLocation;
import com.ssafy.mvc.model.SidoDto;
import com.ssafy.mvc.model.UserAttractionDto;
import com.ssafy.mvc.model.UserMapImageDto;
import com.ssafy.mvc.model.UserTripDto;
import com.ssafy.mvc.service.AttractionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AttractionRestController {


    private final AttractionService attractionService;

    @Autowired
    public AttractionRestController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @GetMapping("/getcity")
    public ResponseEntity<List<SidoDto>> getCity() {
        try {
            return ResponseEntity.ok(attractionService.getCities());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/getcategory")
    public ResponseEntity<List<CategoryDto>> getCategory() {
        try {
            return ResponseEntity.ok(attractionService.getCategories());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping("/getgugun/{sidoCode}")
    public ResponseEntity<List<GugunDto>> getGugun(@PathVariable("sidoCode") String sidoCode) {
        try {
            return ResponseEntity.ok(attractionService.getGugun(sidoCode));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping("/filterlist")
    public ResponseEntity<List<AttractionDto>> filterList(@RequestBody FilterRequestDto request) {

        log.info(request.getSido());
        log.info(request.getAttractionType());
        try {
            List<AttractionDto> filteredList = attractionService.getFilteredList(request);

            return ResponseEntity.ok(filteredList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //에러 발생시 서버 에러 및 빈 리스트 반환
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
    }


    @PostMapping("/nowLocation")
    public ResponseEntity<List<AttractionDto>> nowLocation(@RequestBody NowLocation nowLocation) throws SQLException {
        log.info("nowLocation" + nowLocation.toString());
        List<AttractionDto> attractionList = attractionService.allAttractions(nowLocation);

        List<AttractionDto> limitedList = attractionList.size() > 100 ? attractionList.subList(0, 100) : attractionList;
        return ResponseEntity.ok(limitedList);
    }
    
    @GetMapping("/picturetest")
    public void getImageMetadata() {
        String filePath = "C:\\Users\\SSAFY\\Downloads\\1708953894311.jpg"; // 이미지 파일 경로를 설정하세요.

        try {
            File imageFile = new File(filePath);
            Metadata metadata = ImageMetadataReader.readMetadata(imageFile);

            // 날짜 정보 추출
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
            System.out.println("사진이 찍힌 날짜: " + date);

            // 위도 경도 정보 추출
            GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);
            if (gpsDirectory != null) {
                Double latitude = gpsDirectory.getGeoLocation().getLatitude();
                Double longitude = gpsDirectory.getGeoLocation().getLongitude();
                System.out.println("위도: " + latitude + ", 경도: " + longitude);
            } else {
                System.out.println("위치 정보가 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostMapping("/map-img-upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("images") MultipartFile[] images,
                                              @RequestParam("metadata") String[] metadataList) {
        Map<String, Object> response = new HashMap<>();
        for (int i = 0; i < images.length; i++) {
            MultipartFile image = images[i];
            String metadataJson = metadataList[i];
            
            try {
                // 메타데이터를 JSON으로 파싱
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> metadata = objectMapper.readValue(metadataJson, Map.class);

                // 응답에 메타데이터 추가
                response.put(image.getOriginalFilename(), metadata);

                // 여기서 이미지를 저장하거나 추가 작업을 수행할 수 있습니다.

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process file: " + image.getOriginalFilename());
            }
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/get-usertrip/{userId}")
    public ResponseEntity<List<UserTripDto>> getUserTrip(@PathVariable("userId") String userId) {
        try {
            return ResponseEntity.ok(attractionService.getUserTrip(userId));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    @PostMapping("/register-trip")
    public ResponseEntity<?> registerTrip(@RequestParam("userId") String userId, @RequestParam("tripName") String tripName){
        try {
            
        	System.out.println(userId);
        	System.out.println(tripName);
        	attractionService.registerUserTrip(userId, tripName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed");
        }
    
    return ResponseEntity.ok("ok");
    }
    @PostMapping("/register-attraction")
    public ResponseEntity<?> registerAttraction(@RequestParam("tripId") String tripId, @RequestParam("attractionName") String attractionName,@RequestParam("attractionDes") String attractionDes,
    		@RequestParam("images") MultipartFile[] images, @RequestParam("metadata") String[] metadataList){
    	
    		List<UserMapImageDto> imageList = new ArrayList<UserMapImageDto>(); 
        try {
            UserAttractionDto dto = new UserAttractionDto();
            dto.setAttraction_description(attractionDes);
            dto.setAttraction_name(attractionName);
            dto.setTrip_id(tripId);
            
        	System.out.println(dto.getAttraction_description());
            System.out.println(dto.getAttraction_name());
            System.out.println(dto.getTrip_id());
        	attractionService.registerUserAttraction(dto);
        	if(images.length ==1) {
        		String meta = metadataList[0] + ","+ metadataList[1] + "," + metadataList[2];
        		ObjectMapper objectMapper = new ObjectMapper();
        		MetadataDto metadata = objectMapper.readValue(meta, MetadataDto.class);
        		System.out.println("---------------------------");
                System.out.println("Date: " + metadata.getDate());
                System.out.println("Latitude: " + metadata.getLatitude());
                System.out.println("Longitude: " + metadata.getLongitude());
                System.out.println("---------------------------");
                
                
                String dateStr = metadata.getDate().substring(0,10) + " " + metadata.getDate().substring(11,19);
                System.out.println("dateStr: " + dateStr);
                
                // 문자열을 LocalDateTime으로 파싱
                LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                // 그리니치 표준시(GMT)로부터 9시간을 더하여 한국 시간으로 변환
                ZonedDateTime gmtDateTime = ZonedDateTime.of(localDateTime, java.time.ZoneId.of("GMT"));
                ZonedDateTime koreaDateTime = gmtDateTime.plusHours(9);
                
                System.out.println(koreaDateTime.toString());
                
                dateStr = koreaDateTime.toString().substring(0,10) + " " + koreaDateTime.toString().substring(11,19);
                
                
                
        		UserMapImageDto userImage = new UserMapImageDto();
        		userImage.setAttraction_id(dto.getAttraction_id());
        		userImage.setImage(images[0].getBytes());
        		userImage.setDate(dateStr);
        		userImage.setLatitude(metadata.getLatitude());
        		userImage.setLongitude(metadata.getLongitude());
        		
        		attractionService.registerUserMapImage(userImage);
        		
        		
                
        	}else {
        	
        	for(int i=0;i<images.length;i++) {
        		System.out.println(metadataList[i]);
        		ObjectMapper objectMapper = new ObjectMapper();
        		MetadataDto metadata = objectMapper.readValue(metadataList[i], MetadataDto.class);
        		System.out.println("---------------------------");
                System.out.println("Date: " + metadata.getDate());
                System.out.println("Latitude: " + metadata.getLatitude());
                System.out.println("Longitude: " + metadata.getLongitude());
                System.out.println("---------------------------");
                
                String dateStr = metadata.getDate().substring(0,10) + " " + metadata.getDate().substring(11,19);
                System.out.println("dateStr: " + dateStr);
                
                // 문자열을 LocalDateTime으로 파싱
                LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                // 그리니치 표준시(GMT)로부터 9시간을 더하여 한국 시간으로 변환
                ZonedDateTime gmtDateTime = ZonedDateTime.of(localDateTime, java.time.ZoneId.of("GMT"));
                ZonedDateTime koreaDateTime = gmtDateTime.plusHours(9);
                
                System.out.println(koreaDateTime.toString());
                
                dateStr = koreaDateTime.toString().substring(0,10) + " " + koreaDateTime.toString().substring(11,19);
                
                
                
        		UserMapImageDto userImage = new UserMapImageDto();
        		userImage.setAttraction_id(dto.getAttraction_id());
        		userImage.setImage(images[i].getBytes());
        		userImage.setDate(dateStr);
        		userImage.setLatitude(metadata.getLatitude());
        		userImage.setLongitude(metadata.getLongitude());
        		
        		attractionService.registerUserMapImage(userImage);
                
                
        		}
        	}

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed");
        }
        
        return ResponseEntity.ok("ok");
    }
    
    @GetMapping("/get-userattraction/{tripId}")
    public ResponseEntity<List<UserTripDto>> getUserAttraction(@PathVariable("tripId") String tripId) {
        try {
            return ResponseEntity.ok(attractionService.getUserAttraction(tripId));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping("/get-mymap-imgs/{userId}")
    public ResponseEntity<List<UserMapImageDto>> getImage(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(attractionService.getMyMapImages(userId));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    @DeleteMapping("/delete-userattraction/{attractionId}")
    public ResponseEntity<?> deleteUserAttraction(@PathVariable("attractionId") String attractionId){
		
    	try {
    		attractionService.deleteUserAttraction(attractionId);
            return ResponseEntity.ok("well deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    	
    
}
    


