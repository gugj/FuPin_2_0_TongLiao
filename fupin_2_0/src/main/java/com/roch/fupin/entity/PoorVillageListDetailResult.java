/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 *
 * 2016年5月24日 
 *
 */
public class PoorVillageListDetailResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PoorVillage> jsondata;
	public List<PoorVillage> getJsondata() {
		return jsondata;
	}
	public void setJsondata(List<PoorVillage> jsondata) {
		this.jsondata = jsondata;
	}
	
}
