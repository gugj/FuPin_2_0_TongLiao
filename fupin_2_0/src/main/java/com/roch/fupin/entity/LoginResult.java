package com.roch.fupin.entity;

import java.util.List;

/**
 * 登陆结果LoginResult的实体类，继承自BaseResult，只含有三个字段Boolean类型的success和String类型的code、msg，
 * 通过get和set方法获取， 继承自BaseEntity实体类
 * @author ZhaoDongShao 2016年5月13日
 */
public class LoginResult extends BaseResult {

	private static final long serialVersionUID = 1L;

	private List<LoginEntity> jsondata;

	public List<LoginEntity> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<LoginEntity> jsondata) {
		this.jsondata = jsondata;
	}
}
