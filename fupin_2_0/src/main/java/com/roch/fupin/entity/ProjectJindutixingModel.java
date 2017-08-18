package com.roch.fupin.entity;

/**
 * 
 * <br>
 * <b>功能：</b>ProjectJindutixingPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class ProjectJindutixingModel extends BaseProject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   项目ID
	//	private String projectname;//   项目名称
	//	private String planstartdate;//   计划开始时间
	//	private String planenddate;//   计划结束时间
	//	private double investtotal;//   投资合计（万元）,中省+市级+镇村配套
	//	private String projectcategoryid;//   项目类型ID,字典表
	//	private String projectscheduleid;//   项目进度ID,字典表
	//	private String projectstatusid;//   项目状态ID,字典表
	//	
	//	private String projectcategoryidcall;//项目类型
	//	private String projectscheduleidcall;//项目进度
	//	private String projectstatusidcall;//项目状态
	private String projectjw;//局委

	//	private String dutydeptname;//   项目责任单位
	//	private String dutyperson;//   项目责任人
	//	private String dutypersonphone;//   项目责任人联系方式
	private String dep_code;
	private String dep_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDep_code() {
		return dep_code;
	}
	public void setDep_code(String dep_code) {
		this.dep_code = dep_code;
	}
	public String getDep_id() {
		return dep_id;
	}
	public void setDep_id(String dep_id) {
		this.dep_id = dep_id;
	}
	public String getProjectjw() {
		return projectjw;
	}
	public void setProjectjw(String projectjw) {
		this.projectjw = projectjw;
	}

}
