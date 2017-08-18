/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 *
 * 2016年6月27日 
 *
 */
public class RateofProjectsModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<RateofProjectsModel> jsondata;
	public List<RateofProjectsModel> getJsondata() {
		return jsondata;
	}
	public void setJsondata(List<RateofProjectsModel> jsondata) {
		this.jsondata = jsondata;
	}
}
