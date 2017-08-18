package com.roch.fupin.entity;

import java.util.List;

/**
 * 年龄构成统计ResultList
 * @author ZhaoDongShao
 * 2016年6月27日
 */
public class PoorPeopleAgeListResult extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<MapEntity> jsondata;

	public List<MapEntity> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<MapEntity> jsondata) {
		this.jsondata = jsondata;
	}
}
