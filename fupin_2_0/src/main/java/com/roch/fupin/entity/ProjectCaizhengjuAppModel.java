package com.roch.fupin.entity;

import java.util.List;

/**
 * 
 * <br>
 * <b>功能：</b>ProjectCaizhengjuAppPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class ProjectCaizhengjuAppModel extends BaseProject {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//   项目ID
	private String juweicall;//   局委名称
	private List<ProjectCaizhengjuBofuAppModel> pbf;
	private String remark;//   备注
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJuweicall() {
		return juweicall;
	}
	public void setJuweicall(String juweicall) {
		this.juweicall = juweicall;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<ProjectCaizhengjuBofuAppModel> getPbf() {
		return pbf;
	}
	public void setPbf(List<ProjectCaizhengjuBofuAppModel> pbf) {
		this.pbf = pbf;
	}
}
