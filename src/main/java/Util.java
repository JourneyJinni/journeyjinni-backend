import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Util {
	
	
	private static final double EARTH_RADIUS_KM = 6371.0; // 지구 둘레
	
	public static <T> List<T> isAroundSort (double aroundDistance, T attractions) {
		List<AttractionDistance> aroundList = new ArrayList<>();
		
		// TODO #1 : 객체에서 위도와 경도를 가져오기 
		
	    
	   // return aroundList;
	}
	
	/**
	 * 두 위도와 경도 사이의 거리를 구하는 메서드
	 * @return aroundDisatnce 미만이라면 거리 d를 return 하고 이상이라면 -1을 리턴한다.
	 */
	private double getDistance(double lat1, double lon1, double lat2, double lon2) {
		double dLat = Math.toRadians(lat2 - lat1);
	    double dLon = Math.toRadians(lon2 - lon1);
	    
	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double d = Math.abs(EARTH_RADIUS_KM * c * 1000);    // 거리 단위를 미터로 변환
		
	    return d;
	}
}

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
