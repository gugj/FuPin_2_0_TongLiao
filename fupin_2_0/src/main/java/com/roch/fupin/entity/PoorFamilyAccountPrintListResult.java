package com.roch.fupin.entity;

import java.util.List;

/**
 * 
 *PoorFamilyAccountPrintListResult实体类，继承自BaseResult实体类，父类又继承自BaseEntity实体类
 *
 * 2016年7月20日 
 *
 */
public class PoorFamilyAccountPrintListResult extends BaseResult{

	private static final long serialVersionUID = 1L;

	private List<PoorFamilyAccountPrint> jsondata;

	public List<PoorFamilyAccountPrint> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<PoorFamilyAccountPrint> jsondata) {
		this.jsondata = jsondata;
	}
}
