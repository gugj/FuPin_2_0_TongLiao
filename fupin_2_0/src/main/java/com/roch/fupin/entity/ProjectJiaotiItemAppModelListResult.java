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
public class ProjectJiaotiItemAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ProjectJiaotiItemAppModel> jsondata;

	public List<ProjectJiaotiItemAppModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<ProjectJiaotiItemAppModel> jsondata) {
		this.jsondata = jsondata;
	}
}
