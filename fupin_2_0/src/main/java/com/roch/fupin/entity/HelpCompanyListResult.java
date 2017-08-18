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
public class HelpCompanyListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<HelpCompany> jsondata;

	public List<HelpCompany> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<HelpCompany> jsondata) {
		this.jsondata = jsondata;
	}
}
