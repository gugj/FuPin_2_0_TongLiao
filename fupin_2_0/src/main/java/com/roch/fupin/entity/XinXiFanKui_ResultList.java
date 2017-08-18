package com.roch.fupin.entity;

import java.util.List;

/**
 * 信息反馈ResultList
 * @author ZhaoDongShao
 * 2016年5月26日
 */
public class XinXiFanKui_ResultList extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<XinXinFanKui> jsondata;

	public List<XinXinFanKui> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<XinXinFanKui> jsondata) {
		this.jsondata = jsondata;
	}
}
