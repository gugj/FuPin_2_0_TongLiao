/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 *
 * 2016年5月26日 
 *
 */
public class HelpCompanyPoorVillageListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<PoorVillageBase> jsondata;

	public List<PoorVillageBase> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<PoorVillageBase> jsondata) {
		this.jsondata = jsondata;
	}
}
