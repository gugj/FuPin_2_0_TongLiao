package com.roch.fupin.entity;


/**
 * 就业培训item
 * <br>
 * <b>功能：</b>ProjectCanlianTrainitemPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class ProjectCanlianTrainitemAppModel extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   ID	private String recordid;//   培训ID	private String poorpeopleid;//   贫困人ID	private int isfinish;//   是否已结业	private String workstatusid;//   陪训后就业情况ID,字典表
	private double salarymonth;//   月工资（元）	private String workcompany;//   就业单位名称	private String workposition;//   工作岗位	private String personname;//   就业单位负责人	private String personphone;//   就业单位负责人联系方式	private String entrydate;//   入职日期	private int workmonth;//   预计持续工作时间

	private String peoplename;
	private String finishName;
	private String sexName;
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
