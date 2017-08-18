package com.roch.fupin.entity;

import java.util.List;

/**
 * 六个一批统计ResultList实体类
 * @author ZhaoDongShao
 * 2016年6月27日
 */
public class LiuGeYiPiTongJi_ResultList extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<LiuGeYiPiTongJi> jsondata;

	public List<LiuGeYiPiTongJi> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<LiuGeYiPiTongJi> jsondata) {
		this.jsondata = jsondata;
	}
}
