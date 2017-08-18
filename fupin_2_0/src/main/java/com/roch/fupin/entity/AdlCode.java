package com.roch.fupin.entity;

import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Unique;

/**
 * 行政区划实体，里面包含了行政区划得所有字段，继承自BaseEntity，父类里面仅只有如下一个方法： 把json字符串通过gson转换成实体类，实现了Serializable序列化接口
 * @author ZhaoDongShao
 * 2016年6月23日
 */
public class AdlCode extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String nt;
	private String ts;
	private String orders;
	
	@Unique
	private String ad_cd;
	private String pinyi;
	
	/**
	 * 行政区的拼音名
	 */
	private String ad_nm;

	public AdlCode() {
	}

	public AdlCode(String ad_cd, String ad_nm, String pinyi) {
		this.ad_nm = ad_nm;
		this.pinyi = pinyi;
		this.ad_cd = ad_cd;
	}

	public String getNt() {
		return nt;
	}

	public void setNt(String nt) {
		this.nt = nt;
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

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public String getAd_cd() {
		return ad_cd;
	}

	public void setAd_cd(String ad_cd) {
		this.ad_cd = ad_cd;
	}

	public String getPinyi() {
		return pinyi;
	}

	public void setPinyi(String pinyi) {
		this.pinyi = pinyi;
	}
}
