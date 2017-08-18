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
public class ProjectJiaotiAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ProjectJiaotiAppModel> jsondata;

	public List<ProjectJiaotiAppModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<ProjectJiaotiAppModel> jsondata) {
		this.jsondata = jsondata;
	}
	
	
}
