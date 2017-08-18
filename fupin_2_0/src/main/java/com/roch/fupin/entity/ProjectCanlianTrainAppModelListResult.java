/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 *
 * 2016年6月3日 
 *
 */
public class ProjectCanlianTrainAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ProjectCanlianTrainAppModel> jsondata;

	public List<ProjectCanlianTrainAppModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<ProjectCanlianTrainAppModel> jsondata) {
		this.jsondata = jsondata;
	}
	
}
