package com.ssafy.mvc.model;

/**
 * 기존의 관광지에 거리를 추가하여 정렬용 객체
 * @author 김규형
 */
public class AttractionDistance implements Comparable<AttractionDistance>{
	
	private AttractionDto attraction;
	private double distance;
	
	public AttractionDistance(AttractionDto attraction, double distance) {
		this.attraction = attraction;
		this.distance = distance;
	}
	
	public AttractionDto getAttraction() {
		return attraction;
	}
	
	public double getDistance() {
		return distance;
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