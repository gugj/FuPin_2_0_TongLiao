package com.roch.fupin.entity;

import java.util.List;

/**
 * 六个一批统计详情ResultList
 * @author ZhaoDongShao
 * 2016年6月27日
 */
public class LiuGeYiPiTongJiDetail_ResultList extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<LiuGeYiPinTongJiDetail> jsondata;

	public List<LiuGeYiPinTongJiDetail> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<LiuGeYiPinTongJiDetail> jsondata) {
		this.jsondata = jsondata;
	}
}
