/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 * 2016年6月27日
 */
public class BasicPoorpeopleModelListResult extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<BasicPoorpeopleModel> jsondata;

	public List<BasicPoorpeopleModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<BasicPoorpeopleModel> jsondata) {
		this.jsondata = jsondata;
	}
}
