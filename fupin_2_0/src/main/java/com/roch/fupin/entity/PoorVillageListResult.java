/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 * 2016年5月24日
 */
public class PoorVillageListResult extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<PoorVillageBase> jsondata;

	private String total;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<PoorVillageBase> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<PoorVillageBase> jsondata) {
		this.jsondata = jsondata;
	}
}
