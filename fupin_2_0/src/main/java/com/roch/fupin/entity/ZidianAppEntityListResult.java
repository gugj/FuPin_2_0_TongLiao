/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * 返回乡镇信息，致贫原因，帮扶措施
 * 
 * @author ZhaoDongShao
 *
 * 2016年5月30日 
 *
 */
public class ZidianAppEntityListResult extends BaseResult{

	private static final long serialVersionUID = 1L;
	private List<ZidianAppEntity> jsondata;

	public List<ZidianAppEntity> getJsondata() {
		return jsondata;
	}
	public void setJsondata(List<ZidianAppEntity> jsondata) {
		this.jsondata = jsondata;
	}

}
