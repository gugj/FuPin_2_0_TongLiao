package com.roch.fupin.entity;

//项目进度
public class RateofProjectsModel extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private String name; //行政区
	private String jt_ct;//交通局
	private String sl_ct;//水利局
	private String ly_ct;//林业局
	private String fpb_ct;//扶贫办
	private String xcb_ct;//新村办
	private String lx_c;//立项
	private String wg_c;//完工
	private String kg_c;//开工
	private String startDate;
	private String endDate;
	private int year;
	private int month;
	
	private String adl_cd;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJt_ct() {
		return jt_ct;
	}

	public void setJt_ct(String jt_ct) {
		this.jt_ct = jt_ct;
	}

	public String getSl_ct() {
		return sl_ct;
	}

	public void setSl_ct(String sl_ct) {
		this.sl_ct = sl_ct;
	}

	public String getLy_ct() {
		return ly_ct;
	}

	public void setLy_ct(String ly_ct) {
		this.ly_ct = ly_ct;
	}

	public String getFpb_ct() {
		return fpb_ct;
	}

	public void setFpb_ct(String fpb_ct) {
		this.fpb_ct = fpb_ct;
	}

	public String getXcb_ct() {
		return xcb_ct;
	}

	public void setXcb_ct(String xcb_ct) {
		this.xcb_ct = xcb_ct;
	}

	public String getLx_c() {
		return lx_c;
	}

	public void setLx_c(String lx_c) {
		this.lx_c = lx_c;
	}

	public String getKg_c() {
		return kg_c;
	}

	public void setKg_c(String kg_c) {
		this.kg_c = kg_c;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getWg_c() {
		return wg_c;
	}

	public void setWg_c(String wg_c) {
		this.wg_c = wg_c;
	}

	public String getAdl_cd() {
		return adl_cd;
	}

	public void setAdl_cd(String adl_cd) {
		this.adl_cd = adl_cd;
	}
}
