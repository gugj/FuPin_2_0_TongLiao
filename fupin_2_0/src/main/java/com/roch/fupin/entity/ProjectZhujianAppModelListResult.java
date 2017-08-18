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
public class ProjectZhujianAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ProjectZhujianAppModel> jsondata;
	public List<ProjectZhujianAppModel> getJsondata() {
		return jsondata;
	}
	public void setJsondata(List<ProjectZhujianAppModel> jsondata) {
		this.jsondata = jsondata;
	}
	
}
