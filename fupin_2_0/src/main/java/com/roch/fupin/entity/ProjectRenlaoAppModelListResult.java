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
public class ProjectRenlaoAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ProjectRenlaoAppModel> jsondata;
	public List<ProjectRenlaoAppModel> getJsondata() {
		return jsondata;
	}
	public void setJsondata(List<ProjectRenlaoAppModel> jsondata) {
		this.jsondata = jsondata;
	}
}
