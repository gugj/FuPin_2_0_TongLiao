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
public class ProjectCanlianRebuildAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ProjectCanlianRebuildAppModel> jsondata;

	public List<ProjectCanlianRebuildAppModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<ProjectCanlianRebuildAppModel> jsondata) {
		this.jsondata = jsondata;
	}
	
}
