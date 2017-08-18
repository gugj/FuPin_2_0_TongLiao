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
public class ProjectCanlianTrainitemAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ProjectCanlianTrainitemAppModel> jsondata;
	public List<ProjectCanlianTrainitemAppModel> getJsondata() {
		return jsondata;
	}
	public void setJsondata(List<ProjectCanlianTrainitemAppModel> jsondata) {
		this.jsondata = jsondata;
	}
}
