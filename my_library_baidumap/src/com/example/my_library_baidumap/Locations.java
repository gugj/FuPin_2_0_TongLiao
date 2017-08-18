package com.example.my_library_baidumap;

import java.io.Serializable;

/**
 * 定位信息
 * @author wfs
 *
 */
public class Locations implements Serializable{
	private static final long serialVersionUID = 1L;
	private Double lat;
	private Double lon;
	private String province;
	private String cityName;
	private String address;
	private String time;
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

}
