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
public class ProjectWeijiweiAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ProjectWeijiweiAppModel> jsondata;

	public List<ProjectWeijiweiAppModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<ProjectWeijiweiAppModel> jsondata) {
		this.jsondata = jsondata;
	}
	
}
