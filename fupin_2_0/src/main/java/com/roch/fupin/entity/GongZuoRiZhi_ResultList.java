package com.roch.fupin.entity;

import java.util.List;

/**
 * 工作日志ResultList
 * @author ZhaoDongShao
 * 2016年5月26日
 */
public class GongZuoRiZhi_ResultList extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<GongZuoRiZhi> jsondata;

	public List<GongZuoRiZhi> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<GongZuoRiZhi> jsondata) {
		this.jsondata = jsondata;
	}
}
