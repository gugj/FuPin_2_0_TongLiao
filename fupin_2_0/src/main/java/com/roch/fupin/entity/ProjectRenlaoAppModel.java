package com.roch.fupin.entity;

import java.util.List;

/**
 * 
 * <br>
 * <b>功能：</b>ProjectRenlaoAppPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class ProjectRenlaoAppModel extends BaseProject {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   ID	private String projectid;//   项目ID	private String trainname;//   培训名称	private String traincompanyname;//   培训机构名称	private String traintypeid;//   培训类别,字典表
	private String traintypeidcall;//   培训类别名称
	private String traindate;//   培训时间	private String trainaddress;//   培训地点	private String trainmodeid;//   培训方式,字典表
	private String trainmodeidcall;//   培训方式名称
	private String personname;//   培训负责人	private String personphone;//   负责人联系方式	private String remark;//   备注
	private List<ProjectRenlaoItemAppModel> pam;//人劳分类

		public List<ProjectRenlaoItemAppModel> getPam() {
		return pam;
	}
	public void setPam(List<ProjectRenlaoItemAppModel> pam) {
		this.pam = pam;
	}
	public String getId() {	    return this.id;	}	public void setId(String id) {	 this.id=id;	}	public String getProjectid() {	    return this.projectid;	}	public void setProjectid(String projectid) {	 this.projectid=projectid;	}	public String getTrainname() {	    return this.trainname;	}	public void setTrainname(String trainname) {	 this.trainname=trainname;	}	public String getTraincompanyname() {	    return this.traincompanyname;	}	public void setTraincompanyname(String traincompanyname) {	 this.traincompanyname=traincompanyname;	}	public String getTraintypeid() {	    return this.traintypeid;	}	public void setTraintypeid(String traintypeid) {	 this.traintypeid=traintypeid;	}	public String getTraindate() {	    return this.traindate;	}	public void setTraindate(String traindate) {	 this.traindate=traindate;	}	public String getTrainaddress() {	    return this.trainaddress;	}	public void setTrainaddress(String trainaddress) {	 this.trainaddress=trainaddress;	}	public String getTrainmodeid() {	    return this.trainmodeid;	}	public void setTrainmodeid(String trainmodeid) {	 this.trainmodeid=trainmodeid;	}	public String getPersonname() {	    return this.personname;	}	public void setPersonname(String personname) {	 this.personname=personname;	}	public String getPersonphone() {	    return this.personphone;	}	public void setPersonphone(String personphone) {	 this.personphone=personphone;	}	public String getRemark() {	    return this.remark;	}	public void setRemark(String remark) {	 this.remark=remark;	}
	public String getTrainmodeidcall() {
		return trainmodeidcall;
	}
	public void setTrainmodeidcall(String trainmodeidcall) {
		this.trainmodeidcall = trainmodeidcall;
	}
	public String getTraintypeidcall() {
		return traintypeidcall;
	}
	public void setTraintypeidcall(String traintypeidcall) {
		this.traintypeidcall = traintypeidcall;
	}
}
