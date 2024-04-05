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
		
		//기존의 DTO에 내 위치로부터의 distance를 추가함 
		//DTO 리스트로 변환하여 저장
		for (int i = 0; i < aroundList.size(); i++) {
			aroundList.get(i).getAttraction().setDistance(aroundList.get(i).getDistance());
			attractionList.add(aroundList.get(i).getAttraction());
		}

	   return attractionList;
	}
	
	public static void mergeSortByDistance(List<AttractionDistance> aroundList) {
	    if (aroundList == null || aroundList.size() <= 1) {
	        return; // 리스트가 비어있거나 하나의 요소만 있는 경우는 정렬할 필요가 없음
	    }

	    // 리스트를 두 부분으로 분할
	    List<AttractionDistance> leftHalf = new ArrayList<>(aroundList.subList(0, aroundList.size() / 2));
	    List<AttractionDistance> rightHalf = new ArrayList<>(aroundList.subList(aroundList.size() / 2, aroundList.size()));

	    // 재귀적으로 각 부분을 정렬
	    mergeSortByDistance(leftHalf);
	    mergeSortByDistance(rightHalf);

	    // 두 부분을 병합
	    merge(aroundList, leftHalf, rightHalf);
	}

	private static void merge(List<AttractionDistance> mergedList, List<AttractionDistance> leftHalf, List<AttractionDistance> rightHalf) {
	    int leftIndex = 0, rightIndex = 0, mergedIndex = 0;

	    // 두 부분을 순회하면서 정렬된 순서대로 mergedList에 병합
	    while (leftIndex < leftHalf.size() && rightIndex < rightHalf.size()) {
	        if (leftHalf.get(leftIndex).getDistance() >= rightHalf.get(rightIndex).getDistance()) {
	            mergedList.set(mergedIndex++, leftHalf.get(leftIndex++));
	        } else {
	            mergedList.set(mergedIndex++, rightHalf.get(rightIndex++));
	        }
	    }

	    // 남은 요소들을 병합
	    while (leftIndex < leftHalf.size()) {
	        mergedList.set(mergedIndex++, leftHalf.get(leftIndex++));
	    }
	    while (rightIndex < rightHalf.size()) {
	        mergedList.set(mergedIndex++, rightHalf.get(rightIndex++));
	    }
	}

}


