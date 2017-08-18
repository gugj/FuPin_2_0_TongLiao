package com.roch.fupin.entity;

import java.util.List;

/**
 * 
 * <br>
 * <b>功能：</b>ProjectFupinbanTrainPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class ProjectFupinbanTrainAppModel extends BaseProject {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   ID	private String projectid;//   项目ID	private String trainname;//   培训名称	private String traincompanyname;//   培训机构名称	private String traintypeid;//   培训类别,字典表	private String traindate;//   培训时间	private String trainaddress;//   培训地点	private String trainmodeid;//   培训方式,字典表	private String personname;//   培训负责人	private String personphone;//   负责人联系方式	private String remark;//   备注
	
	private String traintypetext;//培训类别文字
	private String trainmodetext;//培训方式文字
	private String location;
	private List<ProjectFupinbanTrainItemAppModel> pam;
	
	
	public List<ProjectFupinbanTrainItemAppModel> getPam() {
		return pam;
	}
	public void setPam(List<ProjectFupinbanTrainItemAppModel> pam) {
		this.pam = pam;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getTrainname() {
		return trainname;
	}
	public void setTrainname(String trainname) {
		this.trainname = trainname;
	}
	public String getTraincompanyname() {
		return traincompanyname;
	}
	public void setTraincompanyname(String traincompanyname) {
		this.traincompanyname = traincompanyname;
	}
	public String getTraintypeid() {
		return traintypeid;
	}
	public void setTraintypeid(String traintypeid) {
		this.traintypeid = traintypeid;
	}
	public String getTraindate() {
		return traindate;
	}
	public void setTraindate(String traindate) {
		this.traindate = traindate;
	}
	public String getTrainaddress() {
		return trainaddress;
	}
	public void setTrainaddress(String trainaddress) {
		this.trainaddress = trainaddress;
	}
	public String getTrainmodeid() {
		return trainmodeid;
	}
	public void setTrainmodeid(String trainmodeid) {
		this.trainmodeid = trainmodeid;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public String getPersonphone() {
		return personphone;
	}
	public void setPersonphone(String personphone) {
		this.personphone = personphone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTraintypetext() {
		return traintypetext;
	}
	public void setTraintypetext(String traintypetext) {
		this.traintypetext = traintypetext;
	}
	public String getTrainmodetext() {
		return trainmodetext;
	}
	public void setTrainmodetext(String trainmodetext) {
		this.trainmodetext = trainmodetext;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
