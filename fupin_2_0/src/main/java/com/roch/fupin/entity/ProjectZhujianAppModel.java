package com.roch.fupin.entity;


/**
 * 住建局
 * <br>
 * <b>功能：</b>ProjectZhujianAppPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class ProjectZhujianAppModel extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   ID
	private String projectid;//   项目ID
	private String poorfamilyid;//   贫困户户主ID
	private String oldbuildyear;//   旧房建造年代
	private String oldstructid;//   旧房房屋结构类型,字典表
	private double oldarea;//   旧房建筑面积
	private String rebuildreasonid;//   改造原因,字典表
	private String rebuildmodeid;//   改造方式,字典表
	private double newarea;//   改造后建筑面积
	private String newstructid;//   改造房屋后结构类型,字典表
	private double totalamount;//   总投资金额（元）
	private double selfloan;//   农户贷款（元）
	private double selfotheramount;//   农户其他自筹资金（元）
	private String approvestatusid;//   申请进度,字典表
	private String approvedate;//   申请日期
	private double approveamount;//   申请补助资金（元）
	private double factamount;//   实际补助资金（元）
	//内连表
	private String personname;//   贫困户户主姓名
	private String projectname;
	//关联表
	private String projectnamecall;//   项目名称
	private String oldstructidcall;//   旧房房屋结构类型,字典表
	private String rebuildreasonidcall;//   改造原因,字典表
	private String rebuildmodeidcall;//   改造方式,字典表
	private String newstructidcall;//   改造房屋后结构类型,字典表
	private String approvestatusidcall;//   申请进度,字典表

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getPoorfamilyid() {
		return poorfamilyid;
	}
	public void setPoorfamilyid(String poorfamilyid) {
		this.poorfamilyid = poorfamilyid;
	}
	public String getOldbuildyear() {
		return oldbuildyear;
	}
	public void setOldbuildyear(String oldbuildyear) {
		this.oldbuildyear = oldbuildyear;
	}
	public String getOldstructid() {
		return oldstructid;
	}
	public void setOldstructid(String oldstructid) {
		this.oldstructid = oldstructid;
	}
	public double getOldarea() {
		return oldarea;
	}
	public void setOldarea(double oldarea) {
		this.oldarea = oldarea;
	}
	public String getRebuildreasonid() {
		return rebuildreasonid;
	}
	public void setRebuildreasonid(String rebuildreasonid) {
		this.rebuildreasonid = rebuildreasonid;
	}
	public String getRebuildmodeid() {
		return rebuildmodeid;
	}
	public void setRebuildmodeid(String rebuildmodeid) {
		this.rebuildmodeid = rebuildmodeid;
	}
	public double getNewarea() {
		return newarea;
	}
	public void setNewarea(double newarea) {
		this.newarea = newarea;
	}
	public String getNewstructid() {
		return newstructid;
	}
	public void setNewstructid(String newstructid) {
		this.newstructid = newstructid;
	}
	public double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}
	public double getSelfloan() {
		return selfloan;
	}
	public void setSelfloan(double selfloan) {
		this.selfloan = selfloan;
	}
	public double getSelfotheramount() {
		return selfotheramount;
	}
	public void setSelfotheramount(double selfotheramount) {
		this.selfotheramount = selfotheramount;
	}
	public String getApprovestatusid() {
		return approvestatusid;
	}
	public void setApprovestatusid(String approvestatusid) {
		this.approvestatusid = approvestatusid;
	}
	public String getApprovedate() {
		return approvedate;
	}
	public void setApprovedate(String approvedate) {
		this.approvedate = approvedate;
	}
	public double getApproveamount() {
		return approveamount;
	}
	public void setApproveamount(double approveamount) {
		this.approveamount = approveamount;
	}
	public double getFactamount() {
		return factamount;
	}
	public void setFactamount(double factamount) {
		this.factamount = factamount;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public String getProjectnamecall() {
		return projectnamecall;
	}
	public void setProjectnamecall(String projectnamecall) {
		this.projectnamecall = projectnamecall;
	}
	public String getOldstructidcall() {
		return oldstructidcall;
	}
	public void setOldstructidcall(String oldstructidcall) {
		this.oldstructidcall = oldstructidcall;
	}
	public String getRebuildreasonidcall() {
		return rebuildreasonidcall;
	}
	public void setRebuildreasonidcall(String rebuildreasonidcall) {
		this.rebuildreasonidcall = rebuildreasonidcall;
	}
	public String getRebuildmodeidcall() {
		return rebuildmodeidcall;
	}
	public void setRebuildmodeidcall(String rebuildmodeidcall) {
		this.rebuildmodeidcall = rebuildmodeidcall;
	}
	public String getNewstructidcall() {
		return newstructidcall;
	}
	public void setNewstructidcall(String newstructidcall) {
		this.newstructidcall = newstructidcall;
	}
	public String getApprovestatusidcall() {
		return approvestatusidcall;
	}
	public void setApprovestatusidcall(String approvestatusidcall) {
		this.approvestatusidcall = approvestatusidcall;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}


}
