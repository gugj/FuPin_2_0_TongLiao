/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 *
 * 2016年5月26日 
 *
 */
public class HelpPeopleListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<HelpPeople> jsondata;

	public List<HelpPeople> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<HelpPeople> jsondata) {
		this.jsondata = jsondata;
	}
}
