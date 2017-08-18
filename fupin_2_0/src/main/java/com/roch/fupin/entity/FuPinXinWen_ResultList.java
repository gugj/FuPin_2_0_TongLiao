package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 * 2016年5月26日
 */
public class FuPinXinWen_ResultList extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<FuPinXinWen> jsondata;

	public List<FuPinXinWen> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<FuPinXinWen> jsondata) {
		this.jsondata = jsondata;
	}
}
