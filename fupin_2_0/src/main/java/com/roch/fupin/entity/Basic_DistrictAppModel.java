package com.roch.fupin.entity;

/**
 * 乡镇
 * 
 * APP 请求返回值封装类
 * 
 * @author wf
 *
 */
public class Basic_DistrictAppModel extends BaseEntity{

	private static final long serialVersionUID = 1L;
//	private String id;
//	private String pid;
//	private String name;
//	private String oid;
//	private String descript;
	private String ad_cd;//   行政区编码
	private String ad_nm;//   名称
	private String ts;//   
	private String nt;//   
	private String lon;
	private String lat;
	private String povertystatusid; //如果povertystatus == ‘01’标记为红色，并在ad_nm后边加（贫）

	public String getPovertystatusid() {
		return povertystatusid;
	}

	public void setPovertystatusid(String povertystatusid) {
		this.povertystatusid = povertystatusid;
	}

	public String getAd_cd() {
		return ad_cd;
	}
	public void setAd_cd(String ad_cd) {
		this.ad_cd = ad_cd;
	}
	public String getAd_nm() {
		return ad_nm;
	}
	public void setAd_nm(String ad_nm) {
		this.ad_nm = ad_nm;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getNt() {
		return nt;
	}
	public void setNt(String nt) {
		this.nt = nt;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}

}
