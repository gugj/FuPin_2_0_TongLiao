package com.roch.fupin.entity;

/**
 * 
 * <br>
 * <b>功能：</b>ProjectNongweiAppPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class ProjectNongweiItemAppModel extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   ID
	private String recordid;//   企业ID
	private String poorpeopleid;//   贫困人ID
	private String personname;//   贫困人姓名
	private String entrydate;//   入职日期
	private String workposition;//   工作岗位
	private double salarymonth;//   每月工资（元）
	private int workmonth;//   预计持续工作时间(月)
	private String sexName;
	private String birthday;
	private String location;
	
	private String remark;//   备注
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
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public String getEntrydate() {
		return entrydate;
	}
	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}
	public String getWorkposition() {
		return workposition;
	}
	public void setWorkposition(String workposition) {
		this.workposition = workposition;
	}
	public double getSalarymonth() {
		return salarymonth;
	}
	public void setSalarymonth(double salarymonth) {
		this.salarymonth = salarymonth;
	}
	public int getWorkmonth() {
		return workmonth;
	}
	public void setWorkmonth(int workmonth) {
		this.workmonth = workmonth;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
