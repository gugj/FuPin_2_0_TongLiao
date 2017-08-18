package com.roch.fupin.entity;

/**
 * 危房改造
 * <br>
 * <b>功能：</b>ProjectCanlianRebuildPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class ProjectCanlianRebuildAppModel extends BaseProject {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   ID	private String projectid;//   项目ID	private String poorfamilyid;//   贫困户户主ID	private double yearincome;//   年均收入	private String familytypeid;//   家庭类型ID,字典表
	private String familytypeidcall;//   家庭类型
	private String address;//   家庭住址	private String approvestatusid;//   申请状态ID,字典表
	private String approvestatusidcall;//   申请状态
	private String approvedate;//   申请日期	private double oldarea;//   旧房房屋面积	private String oldstruct;//   旧房房屋结构
	private String oldstructcall;//   旧房房屋结构名称
	private String rebuildmodeid;//   改造方式ID,字典表
	private String rebuildmodeidcall;//   改造方式
	private String rebuildstructid;//   房屋结构ID,字典表
	private String rebuildstructidcall;//   房屋结构
	private double rebuildarea;//   改造面积	private String startdate;//   动工时间	private String remark;//   简要说明
	private String personname;//   贫困户户主姓名
	
	public String getId() {	    return this.id;	}	public void setId(String id) {	 this.id=id;	}	public String getProjectid() {	    return this.projectid;	}	public void setProjectid(String projectid) {	 this.projectid=projectid;	}	public String getPoorfamilyid() {	    return this.poorfamilyid;	}	public void setPoorfamilyid(String poorfamilyid) {	 this.poorfamilyid=poorfamilyid;	}	public double getYearincome() {	    return this.yearincome;	}	public void setYearincome(double yearincome) {	 this.yearincome=yearincome;	}	public String getFamilytypeid() {	    return this.familytypeid;	}	public void setFamilytypeid(String familytypeid) {	 this.familytypeid=familytypeid;	}	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getApprovestatusid() {	    return this.approvestatusid;	}	public void setApprovestatusid(String approvestatusid) {	 this.approvestatusid=approvestatusid;	}	public String getApprovedate() {	    return this.approvedate;	}	public void setApprovedate(String approvedate) {	 this.approvedate=approvedate;	}	public double getOldarea() {	    return this.oldarea;	}	public void setOldarea(double oldarea) {	 this.oldarea=oldarea;	}	public String getOldstruct() {	    return this.oldstruct;	}	public void setOldstruct(String oldstruct) {	 this.oldstruct=oldstruct;	}	public String getRebuildmodeid() {	    return this.rebuildmodeid;	}	public void setRebuildmodeid(String rebuildmodeid) {	 this.rebuildmodeid=rebuildmodeid;	}	public String getRebuildstructid() {	    return this.rebuildstructid;	}	public void setRebuildstructid(String rebuildstructid) {	 this.rebuildstructid=rebuildstructid;	}	public double getRebuildarea() {	    return this.rebuildarea;	}	public void setRebuildarea(double rebuildarea) {	 this.rebuildarea=rebuildarea;	}	public String getStartdate() {	    return this.startdate;	}	public void setStartdate(String startdate) {	 this.startdate=startdate;	}	public String getRemark() {	    return this.remark;	}	public void setRemark(String remark) {	 this.remark=remark;	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public String getFamilytypeidcall() {
		return familytypeidcall;
	}
	public void setFamilytypeidcall(String familytypeidcall) {
		this.familytypeidcall = familytypeidcall;
	}
	public String getApprovestatusidcall() {
		return approvestatusidcall;
	}
	public void setApprovestatusidcall(String approvestatusidcall) {
		this.approvestatusidcall = approvestatusidcall;
	}
	public String getRebuildmodeidcall() {
		return rebuildmodeidcall;
	}
	public void setRebuildmodeidcall(String rebuildmodeidcall) {
		this.rebuildmodeidcall = rebuildmodeidcall;
	}
	public String getRebuildstructidcall() {
		return rebuildstructidcall;
	}
	public void setRebuildstructidcall(String rebuildstructidcall) {
		this.rebuildstructidcall = rebuildstructidcall;
	}
	public String getOldstructcall() {
		return oldstructcall;
	}
	public void setOldstructcall(String oldstructcall) {
		this.oldstructcall = oldstructcall;
	}
	
	
}
