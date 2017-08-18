package com.roch.fupin.entity;

import java.util.List;

/**
 * 行政区划代码的实体类，里面含有serialVersionUID和jsondata两个字段，继承自BaseResult实体类，父类只含有三个字段Boolean类型的success和String类型的code、msg，
 * 通过get和set方法获取，其父类又继承自BaseEntity实体类，里面仅只有如下一个方法：
 * 把json字符串通过gson转换成实体类，实现了Serializable序列化接口
 * @author ZhaoDongShao 2016年6月23日
 */
public class AdlCodeListResult extends BaseResult {

	private static final long serialVersionUID = 1L;
	private List<AdlCode> jsondata;

	public List<AdlCode> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<AdlCode> jsondata) {
		this.jsondata = jsondata;
	}
}
