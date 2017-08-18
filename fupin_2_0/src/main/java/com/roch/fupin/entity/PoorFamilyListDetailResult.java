/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * 贫困户tab页总信息的对象，包括基本情况、家庭成员、台账、照片、帮扶记录5个fragment和indacator,这里面只有一个字段，
 *  是一个存有PoorFamily对象的List<PoorFamily>类型的jsondata，通过get和set方法获取和赋值
 * 
 * @author ZhaoDongShao
 *
 *  2016年5月18日
 *
 */
public class PoorFamilyListDetailResult extends BaseResult {

	private static final long serialVersionUID = 1L;

	private List<PoorFamily> jsondata;

	/**
	 * 获取List<PoorFamily>类型的jsondata集合
	 * @return
	 * 2016年10月31日
	 */
	public List<PoorFamily> getJsondata() {
		return jsondata;
	}

	/**
	 * 设置List<PoorFamily>类型的jsondata集合
	 * @param jsondata
	 * 2016年10月31日
	 */
	public void setJsondata(List<PoorFamily> jsondata) {
		this.jsondata = jsondata;
	}
}
