/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 *
 * 2016年6月2日 
 *
 */
public class ProjectMinzhengItemAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ProjectMinzhengItemAppModel> jsondata;

	public List<ProjectMinzhengItemAppModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<ProjectMinzhengItemAppModel> jsondata) {
		this.jsondata = jsondata;
	}
	
}
