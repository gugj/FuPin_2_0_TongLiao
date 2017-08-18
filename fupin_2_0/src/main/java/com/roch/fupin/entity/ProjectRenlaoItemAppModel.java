package com.roch.fupin.entity;

/**
 * 
 * <br>
 * <b>功能：</b>ProjectRenlaoItemAppPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class ProjectRenlaoItemAppModel extends BaseEntity{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   ID	private String recordid;//   培训ID	private String poorpeopleid;//   贫困人ID	private int isfinish;//   是否已结业	private String workstatusid;//   陪训后就业情况ID,字典表
	private double salarymonth;//   月工资（元）	private String workcompany;//   就业单位名称	private String workposition;//   工作岗位	private String personname;//   就业单位负责人	private String sexName;//性别
	private String location;
	private String finishName;//是否结业
	private String idno;//身份证号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public String getPoorpeopleid() {
		return poorpeopleid;
	}
	public void setPoorpeopleid(String poorpeopleid) {
		this.poorpeopleid = poorpeopleid;
	}
	public int getIsfinish() {
		return isfinish;
	}
	public void setIsfinish(int isfinish) {
		this.isfinish = isfinish;
	}
	public String getWorkstatusid() {
		return workstatusid;
	}
	public void setWorkstatusid(String workstatusid) {
		this.workstatusid = workstatusid;
	}
	public double getSalarymonth() {
		return salarymonth;
	}
	public void setSalarymonth(double salarymonth) {
		this.salarymonth = salarymonth;
	}
	public String getWorkcompany() {
		return workcompany;
	}
	public void setWorkcompany(String workcompany) {
		this.workcompany = workcompany;
	}
	public String getWorkposition() {
		return workposition;
	}
	public void setWorkposition(String workposition) {
		this.workposition = workposition;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public String getFinishName() {
		return finishName;
	}
	public void setFinishName(String finishName) {
		this.finishName = finishName;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
