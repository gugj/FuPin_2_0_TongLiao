package com.roch.fupin.entity;

/**
 * 低保五保情况统计实体类
 */
public class DiBaoWuBaoQingKuangTongJi extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String adl_nm; //行政区

	private String db_h; //低保贫困户
	private String wb_h; //五保贫困户
	private String db_p; //享受低保贫困人口
	private String wb_p; //享受五保贫困人口

	private String poorhousenm;// 贫困户数
	private String poorpeoplenm;// 贫困人口数

	public String getAdl_nm() {
		return adl_nm;
	}

	public void setAdl_nm(String adl_nm) {
		this.adl_nm = adl_nm;
	}

	public String getDb_h() {
		return db_h;
	}

	public void setDb_h(String db_h) {
		this.db_h = db_h;
	}

	public String getWb_h() {
		return wb_h;
	}

	public void setWb_h(String wb_h) {
		this.wb_h = wb_h;
	}

	public String getDb_p() {
		return db_p;
	}

	public void setDb_p(String db_p) {
		this.db_p = db_p;
	}

	public String getWb_p() {
		return wb_p;
	}

	public void setWb_p(String wb_p) {
		this.wb_p = wb_p;
	}

	public String getPoorhousenm() {
		return poorhousenm;
	}

	public void setPoorhousenm(String poorhousenm) {
		this.poorhousenm = poorhousenm;
	}

	public String getPoorpeoplenm() {
		return poorpeoplenm;
	}

	public void setPoorpeoplenm(String poorpeoplenm) {
		this.poorpeoplenm = poorpeoplenm;
	}
}
