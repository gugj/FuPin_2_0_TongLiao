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
public class ProjectCaizhengjuAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ProjectCaizhengjuAppModel> jsondata;
	public List<ProjectCaizhengjuAppModel> getJsondata() {
		return jsondata;
	}
	public void setJsondata(List<ProjectCaizhengjuAppModel> jsondata) {
		this.jsondata = jsondata;
	}

}
