/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 *
 * 2016年6月6日 
 *
 */
public class ProjectFupinbanTrainAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ProjectFupinbanTrainAppModel> jsondata;

	public List<ProjectFupinbanTrainAppModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<ProjectFupinbanTrainAppModel> jsondata) {
		this.jsondata = jsondata;
	}
}
