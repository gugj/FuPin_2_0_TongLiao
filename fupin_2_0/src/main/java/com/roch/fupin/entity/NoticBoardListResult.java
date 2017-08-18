package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 * 2016年5月26日
 */
public class NoticBoardListResult extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<NoticBoard> jsondata;

	public List<NoticBoard> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<NoticBoard> jsondata) {
		this.jsondata = jsondata;
	}
}
