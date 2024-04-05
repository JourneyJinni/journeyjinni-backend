package com.ssafy.util.Distance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Attr;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;
import com.ssafy.mvc.model.AttractionDto;

public class DistanceSort {
	
	public static List<AttractionDto> isAroundSort (double nowLat, double nowlon, List<AttractionDto> attractions) {
		List<AttractionDistance> aroundList = new ArrayList<>();
		List<AttractionDto> attractionList = new ArrayList<>();
		
		// 각 리스트를 순회하며 리스트에 추가 
		for (AttractionDto attractionDto : attractions) {
			double d = DistanceUtil.getDistance(nowLat, nowlon, attractionDto.getLatitude().doubleValue(), attractionDto.getLongitude().doubleValue());
			aroundList.add(new AttractionDistance(attractionDto, d));
		}
		
		Collections.sort(aroundList);
		
		for (int i = 0; i < aroundList.size(); i++) {
			System.out.println(aroundList.get(i).getAttraction().getTitle() + "의 위도 경도 : " + aroundList.get(i).getAttraction().getLongitude() + ", " +aroundList.get(i).getAttraction().getLatitude() );
			System.out.println( "거리 : " + aroundList.get(i).getDistance() + "km");
			attractionList.add(aroundList.get(i).getAttraction());
		}

	   return attractionList;
	}
}
