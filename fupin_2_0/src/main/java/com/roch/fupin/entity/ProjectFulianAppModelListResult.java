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
public class ProjectFulianAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ProjectFulianAppModel> jsondata;

	public List<ProjectFulianAppModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<ProjectFulianAppModel> jsondata) {
		this.jsondata = jsondata;
	}
	
}
