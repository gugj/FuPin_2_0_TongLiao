package com.roch.fupin.entity;

import java.util.List;

/**
 * 获取乡镇、致贫原因；实体
 * 
 * APP 请求返回值封装类
 * 
 * @author rc
 *
 */
public class ZidianAppEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private List<Basic_DistrictAppModel> tab;//乡镇
	private List<T_SYS_DATADICTAppModel> tda;//致贫原因/帮扶措施

	public List<Basic_DistrictAppModel> getTab() {
		return tab;
	}
	public void setTab(List<Basic_DistrictAppModel> tab) {
		this.tab = tab;
	}
	public List<T_SYS_DATADICTAppModel> getTda() {
		return tda;
	}
	public void setTda(List<T_SYS_DATADICTAppModel> tda) {
		this.tda = tda;
	}
	
}
