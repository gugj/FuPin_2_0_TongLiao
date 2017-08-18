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
public class ProjectFupinbanTrainItemAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ProjectFupinbanTrainItemAppModel> jsondata;

	public List<ProjectFupinbanTrainItemAppModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<ProjectFupinbanTrainItemAppModel> jsondata) {
		this.jsondata = jsondata;
	}
}
