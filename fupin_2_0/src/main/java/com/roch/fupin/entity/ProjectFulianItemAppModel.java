package com.roch.fupin.entity;
/**
 * 
 * <br>
 * <b>功能：</b>ProjectFulianItemPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class ProjectFulianItemAppModel extends BaseEntity {
	
	
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   ID
	private double salarymonth;//   月工资（元）
	

	private String peoplename;
	private String finishName;
	private String sexName;
	private String birthday;
	private String workstatusidcall;
	private String location;
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
	public String getPersonphone() {
		return personphone;
	}
	public void setPersonphone(String personphone) {
		this.personphone = personphone;
	}
	public String getEntrydate() {
		return entrydate;
	}
	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}
	public int getWorkmonth() {
		return workmonth;
	}
	public void setWorkmonth(int workmonth) {
		this.workmonth = workmonth;
	}
	public String getFinishName() {
		return finishName;
	}
	public void setFinishName(String finishName) {
		this.finishName = finishName;
	}
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getWorkstatusidcall() {
		return workstatusidcall;
	}
	public void setWorkstatusidcall(String workstatusidcall) {
		this.workstatusidcall = workstatusidcall;
	}
	public String getPeoplename() {
		return peoplename;
	}
	public void setPeoplename(String peoplename) {
		this.peoplename = peoplename;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}