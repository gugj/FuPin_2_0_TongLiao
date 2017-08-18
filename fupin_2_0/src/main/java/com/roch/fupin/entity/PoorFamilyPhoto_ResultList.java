package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 * 2016年5月18日
 */
public class PoorFamilyPhoto_ResultList extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<PoorFamilyPhoto> jsondata;

	public List<PoorFamilyPhoto> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<PoorFamilyPhoto> jsondata) {
		this.jsondata = jsondata;
	}
}
