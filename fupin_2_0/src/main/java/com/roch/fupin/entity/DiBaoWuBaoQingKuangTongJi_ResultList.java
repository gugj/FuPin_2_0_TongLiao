package com.roch.fupin.entity;

import java.util.List;

/**
 * 低保五保情况统计ResultList
 * @author ZhaoDongShao
 * 2016年6月27日
 */
public class DiBaoWuBaoQingKuangTongJi_ResultList extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<DiBaoWuBaoQingKuangTongJi> jsondata;

	public List<DiBaoWuBaoQingKuangTongJi> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<DiBaoWuBaoQingKuangTongJi> jsondata) {
		this.jsondata = jsondata;
	}
	
}
