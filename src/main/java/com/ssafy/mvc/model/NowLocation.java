package com.ssafy.mvc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 현재 고객의 현재 위치를 가지고 있는 객체
 *
 * @author 김규형
 */
@Getter
@Setter
@Component
@ToString
public class NowLocation {

	private Double latitude;
	private Double longitude;

	// 기본 생성자
	public NowLocation() {
	}

	public void setLatitude(String latitude) {
		try {
			this.latitude = Double.parseDouble(latitude);
		} catch (NumberFormatException e) {
			this.latitude = null;
		}
	}

	// longitude 설정 메서드 오버라이딩
	public void setLongitude(String longitude) {
		try {
			this.longitude = Double.parseDouble(longitude);
		} catch (NumberFormatException e) {
			this.longitude = null;
		}
	}
}
