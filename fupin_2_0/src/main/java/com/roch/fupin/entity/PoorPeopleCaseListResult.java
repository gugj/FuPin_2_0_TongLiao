package com.roch.fupin.entity;

import java.util.List;

/**
 * 主要致贫原因ResultList
 * @author ZhaoDongShao
 * 2016年6月27日
 */
public class PoorPeopleCaseListResult extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<PoorPeopleCause> jsondata;

	public List<PoorPeopleCause> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<PoorPeopleCause> jsondata) {
		this.jsondata = jsondata;
	}
}
