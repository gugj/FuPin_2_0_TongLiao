/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * 贫困户基本信息
 * @author ZhaoDongShao
 * 2016年5月16日
 */
public class PoorFamilyListResult extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<PoorFamilyBase> jsondata;

	private String total;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<PoorFamilyBase> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<PoorFamilyBase> jsondata) {
		this.jsondata = jsondata;
	}
}
