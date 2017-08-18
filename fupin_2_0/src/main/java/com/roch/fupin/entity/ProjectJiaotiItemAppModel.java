package com.roch.fupin.entity;

/**
 * 
 * <br>
 * <b>功能：</b>ProjectJiaotiItemPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class ProjectJiaotiItemAppModel extends BaseEntity {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   ID	private String recordid;//   发放项目ID	private String poorpeopleid;//   贫困人ID	private double grantamount;//   发放金额（元）
	//贫困人表中的数据
	private String personname;//   成员姓名
	
	private String sexName;//性别
	private String location;
	private String birthday;
	
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
	public double getGrantamount() {
		return grantamount;
	}
	public void setGrantamount(double grantamount) {
		this.grantamount = grantamount;
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
