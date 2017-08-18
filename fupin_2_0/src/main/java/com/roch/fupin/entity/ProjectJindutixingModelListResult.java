/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 *
 * 2016年6月4日 
 *
 */
public class ProjectJindutixingModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ProjectJindutixingModel> jsondata;

	public List<ProjectJindutixingModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<ProjectJindutixingModel> jsondata) {
		this.jsondata = jsondata;
	}
}
