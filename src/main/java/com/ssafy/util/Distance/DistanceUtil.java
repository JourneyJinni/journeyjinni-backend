package com.ssafy.util.Distance;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DistanceUtil {
	
	
	private static final double EARTH_RADIUS_KM = 6371.0; // 지구 반지름
	
	// TODO : 정확도가 127km 씩 차이가 나서 설정한 값 추후 정확도 상승시 삭제해야함
	private static final double ACCURACY = 127.0;
	/**
	 * 두 위도와 경도 사이의 거리를 구하는 메서드
	 * @return d는 두 경도 사이의 거리이다.
	 */
	public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
		double dLat = Math.toRadians(lat2 - lat1);
	    double dLon = Math.toRadians(lon2 - lon1);
	    
	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double d = EARTH_RADIUS_KM * c;    // 거리 단위를 미터로 변환
		
	    return Math.abs(d - ACCURACY);
	}
}


