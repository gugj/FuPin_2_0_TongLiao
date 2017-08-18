/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * 民政局返回列表
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月2日 
 *
 */
public class ProjectMinzhengAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ProjectMinzhengAppModel> jsondata;

	public List<ProjectMinzhengAppModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<ProjectMinzhengAppModel> jsondata) {
		this.jsondata = jsondata;
	}
}
