package com.roch.fupin.entity;

/**
 * 贫困人口统计报表实体
 * @author ZhaoDongShao
 * 2016年6月25日 
 */
public class PoorcityLedgerModel extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String adl_cd;// 行政区划代码
	private int populationnumber;// 总人口
	private int poorhousenm;// 贫困户数
	private int poorpeoplenm;// 贫困人数
	private int poorreson_b;// 基础设施
	private int poorreson_c;// 基础设施
	private int poorreson_x;// 基础设施
	private int poorreson_l;// 基础设施
	private int poorreson_z;// 基础设施
	private int poorreson_j;// 基础设施
	private String poorpercent;

	private String adl_nm;

	// 用于查询
	private int length;
	private String condition;

	public int getPopulationnumber() {
		return populationnumber;
	}

	public void setPopulationnumber(int populationnumber) {
		this.populationnumber = populationnumber;
	}

	public int getPoorhousenm() {
		return poorhousenm;
	}

	public void setPoorhousenm(int poorhousenm) {
		this.poorhousenm = poorhousenm;
	}

	public int getPoorpeoplenm() {
		return poorpeoplenm;
	}

	public void setPoorpeoplenm(int poorpeoplenm) {
		this.poorpeoplenm = poorpeoplenm;
	}

	public String getPoorpercent() {
		return poorpercent;
	}

	public void setPoorpercent(String poorpercent) {
		this.poorpercent = poorpercent;
	}

	public int getPoorreson_b() {
		return poorreson_b;
	}

	public void setPoorreson_b(int poorreson_b) {
		this.poorreson_b = poorreson_b;
	}

	public int getPoorreson_c() {
		return poorreson_c;
	}

	public void setPoorreson_c(int poorreson_c) {
		this.poorreson_c = poorreson_c;
	}

	public int getPoorreson_x() {
		return poorreson_x;
	}

	public void setPoorreson_x(int poorreson_x) {
		this.poorreson_x = poorreson_x;
	}

	public int getPoorreson_l() {
		return poorreson_l;
	}

	public void setPoorreson_l(int poorreson_l) {
		this.poorreson_l = poorreson_l;
	}

	public int getPoorreson_z() {
		return poorreson_z;
	}

	public void setPoorreson_z(int poorreson_z) {
		this.poorreson_z = poorreson_z;
	}

	public int getPoorreson_j() {
		return poorreson_j;
	}

	public void setPoorreson_j(int poorreson_j) {
		this.poorreson_j = poorreson_j;
	}

	public String getAdl_cd() {
		return adl_cd;
	}

	public void setAdl_cd(String adl_cd) {
		this.adl_cd = adl_cd;
	}

	public String getAdl_nm() {
		return adl_nm;
	}

	public void setAdl_nm(String adl_nm) {
		this.adl_nm = adl_nm;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
