package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 * 2017年3月6日 
 */
public class XiangMuZhaoPian_ResultList extends BaseResult{

	private static final long serialVersionUID = 1L;
	
	private List<PoorFamilyPhoto> jsondata;

	public List<PoorFamilyPhoto> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<PoorFamilyPhoto> jsondata) {
		this.jsondata = jsondata;
	}
	
	
}
