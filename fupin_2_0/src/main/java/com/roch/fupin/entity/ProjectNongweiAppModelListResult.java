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
public class ProjectNongweiAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ProjectNongweiAppModel> jsondata;

	public List<ProjectNongweiAppModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<ProjectNongweiAppModel> jsondata) {
		this.jsondata = jsondata;
	}
	
}
