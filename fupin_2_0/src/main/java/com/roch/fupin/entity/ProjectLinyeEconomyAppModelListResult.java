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
public class ProjectLinyeEconomyAppModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ProjectLinyeEconomyAppModel> jsondata;

	public List<ProjectLinyeEconomyAppModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<ProjectLinyeEconomyAppModel> jsondata) {
		this.jsondata = jsondata;
	}
	
}
