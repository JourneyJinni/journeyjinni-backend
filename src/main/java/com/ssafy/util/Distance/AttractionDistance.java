package com.ssafy.util.Distance;

/**
 * 기존의 관광지에 거리를 추가하여 정렬용 객체 
 * @param <T>
 */
class AttractionDistance <T> implements Comparable<T>{
	
	private T attraction;
	private double distance;
	
	public AttractionDistance(T attraction, double distance) {
		this.attraction = attraction;
		this.distance = distance;
	}
	
	public T getAttraction () {
		return attraction;
	}

	@Override
	public int compareTo(T o) {
		@SuppressWarnings("unchecked")
		AttractionDistance<T> attractionDistance = (AttractionDistance<T>) o;
		
		return (int) (this.distance - attractionDistance.distance);
	}
	
}