package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 * 2016年6月27日
 */
public class PoorPeopleCaseDetail_ResultList extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<PeopleCaseDetailEntity> jsondata;

	public List<PeopleCaseDetailEntity> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<PeopleCaseDetailEntity> jsondata) {
		this.jsondata = jsondata;
	}
}
