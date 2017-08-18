/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 *
 * 2016年6月1日 
 *
 */
public class ProjectInfoAppEntityListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ProjectInfoAppEntity> jsondata;
	public List<ProjectInfoAppEntity> getJsondata() {
		return jsondata;
	}
	public void setJsondata(List<ProjectInfoAppEntity> jsondata) {
		this.jsondata = jsondata;
	}

}
