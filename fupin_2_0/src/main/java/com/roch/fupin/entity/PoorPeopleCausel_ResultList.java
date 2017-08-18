package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 * 2016年6月27日
 */
public class PoorPeopleCausel_ResultList extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<PoorPeopleCause> jsondata;

	public List<PoorPeopleCause> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<PoorPeopleCause> jsondata) {
		this.jsondata = jsondata;
	}
}
