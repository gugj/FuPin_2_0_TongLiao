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
public class ProjectWeijiweiItemAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ProjectWeijiweiItemAppModel> jsondata;

	public List<ProjectWeijiweiItemAppModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<ProjectWeijiweiItemAppModel> jsondata) {
		this.jsondata = jsondata;
	}
	
}
