package com.roch.fupin.entity;

import java.util.List;

/**
 * 反馈管理ResultList
 * @author ZhaoDongShao
 * 2016年5月26日
 */
public class FanKuiGuanLi_ResultList extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<FanKuiGuanLi> jsondata;

	public List<FanKuiGuanLi> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<FanKuiGuanLi> jsondata) {
		this.jsondata = jsondata;
	}
}
