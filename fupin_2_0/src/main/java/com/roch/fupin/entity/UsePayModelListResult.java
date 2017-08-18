package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 * 2016年6月27日
 */
public class UsePayModelListResult extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<UsePayModel> jsondata;

	public List<UsePayModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<UsePayModel> jsondata) {
		this.jsondata = jsondata;
	}
	
}
