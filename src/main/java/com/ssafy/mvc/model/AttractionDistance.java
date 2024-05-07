package com.ssafy.mvc.model;

import lombok.Getter;

/**
 * 기존의 관광지에 거리를 추가하여 정렬용 객체
 * @author 김규형
 */
@Getter
public class AttractionDistance implements Comparable<AttractionDistance>{
	
	private final AttractionDto attraction;
	private final double distance;
	
	public AttractionDistance(AttractionDto attraction, double distance) {
		this.attraction = attraction;
		this.distance = distance;
	}

    @Override
	public int compareTo(AttractionDistance o) {
		return (int) (this.distance - o.distance);
	}

	@Override
	public String toString() {
		return "AttractionDistance [attraction=" + attraction + ", distance=" + distance + "]";
	}
	
	
}