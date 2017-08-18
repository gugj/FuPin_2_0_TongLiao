package com.roch.fupin.entity;

/**
 * 
 * <br>
 * <b>功能：</b>ProjectFupinbanTrainItemPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class ProjectFupinbanTrainItemAppModel extends BaseEntity {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   ID	private String recordid;//   培训ID	private String poorpeopleid;//   贫困人ID	private int isfinish;//   是否已结业
	private String isfinishtext;	private String workstatusid;//   陪训后就业情况ID,字典表
	private double salarymonth;//   月工资（元）	private String workcompany;//   就业单位名称	private String workposition;//   工作岗位	private String personname;//   就业单位负责人	private String personphone;//   就业单位负责人联系方式	private String entrydate;//   入职日期	private int workmonth;//   预计持续工作时间
	
	private String sexName;
	private String location;
	private String idno;//身份证号
	private String finishName;//是否结业
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
	public String getIsfinishtext() {
		return isfinishtext;
	}
	public void setIsfinishtext(String isfinishtext) {
		this.isfinishtext = isfinishtext;
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
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}	
}
