package com.ssafy.mvc.model;

/**
 * 현재 고객의 현재 위치를 가지고 있는 객체
 * 
 * @author 김규형
 */
public class NowLocation {
	
	private Double latitiude;
	private Double longtitude;
	
	private static NowLocation nowLocation;


	/**
	 * 문자열로 생성시에 Double 타입으로 형변환
	 */
	public NowLocation(String latitiude, String longtitude) {
		this(Double.parseDouble(latitiude), Double.parseDouble(longtitude));
	}

	public NowLocation(Double latitiude, Double longtitude) {
		this.latitiude = latitiude;
        this.longtitude = longtitude;
        
        nowLocation = this;
		System.out.println("[Log] : 현재 내위치 : " + this.latitiude + " , " + this.longtitude);
	}
	
	public static NowLocation getLocation() {
		return nowLocation;
	}

	public Double getLatitiude() {
		return latitiude;
	}

	public void setLatitiude(Double latitiude) {
		this.latitiude = latitiude;
	}

	public Double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}
	
	
	
	
}
