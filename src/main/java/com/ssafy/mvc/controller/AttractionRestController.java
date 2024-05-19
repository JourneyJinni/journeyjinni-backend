package com.ssafy.mvc.controller;


import java.io.File;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.ssafy.mvc.model.NowLocation;
import com.ssafy.mvc.model.SidoDto;
import com.ssafy.mvc.model.UserTripDto;
import com.ssafy.mvc.model.service.AttractionService;

import jakarta.servlet.http.HttpServletRequest;

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

    /**
     * 반환 타입 수정 완료 그러나 얻는 타입 추후 수정 필요
     * @param city
     * @param category
     * @return
     */
    @PostMapping("/filterlist")
    public ResponseEntity<List<AttractionDto>> filterList(@RequestBody FilterRequestDto request) {

          String sido = request.getSido();
          String gugun = request.getGugun();
          String attractionType = request.getAttractionType();
        
    	  System.out.println(sido);
    	  System.out.println(gugun);
    	  System.out.println(attractionType);

        try {
            List<AttractionDto> filteredList = attractionService.getFilteredList(request);
            System.out.println("[Log] : filterList 실행");

            return ResponseEntity.ok(filteredList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //에러 발생시 서버 에러 및 빈 리스트 반환
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
    }


    @PostMapping("/nowLocation")
    public String nowLocation(HttpServletRequest request) {
        System.out.println("[Log] : 위치 정보가 사용됨!");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        new NowLocation(latitude, longitude);

        return "attraction";
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
    	
        System.out.println(tripId);
        System.out.println(attractionName);
        System.out.println(attractionDes);
//        for (int i = 0; i < images.length; i++) {
//            MultipartFile image = images[i];
//            String metadataJson = metadataList[i];
//            
//            try {
//  
//
//                // 여기서 이미지를 저장하거나 추가 작업을 수행할 수 있습니다.
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process file: ");
//            }
//        }
        return ResponseEntity.ok("ok");
    }
}
    


