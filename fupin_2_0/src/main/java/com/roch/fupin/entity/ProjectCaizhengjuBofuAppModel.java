package com.roch.fupin.entity;

/**
 * 
 * <br>项目拨付情况
 * <b>功能：</b>ProjectCaizhengjuBofuAppPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class ProjectCaizhengjuBofuAppModel extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   ID
	private String officeid;//   局委ID,字典表
	private String projectid;//   项目ID
	private double approveamount;//   申请拨付金额
	private String approvedate;//   申请拨付时间
	private String approveuser;//   申请用户id
	private double factamount;//   实际拨付金额
	private String grantdate;//   拨付时间
	private String grantuser;//   拨付用户id
	private String louser;//   拨付用户名称
	private String projectname;//   项目名称
	private String projectjw;
	private String remark;//   备注
	private String jinebutton;//添加按钮
	private String totalamount;//实际拨付金额之和
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOfficeid() {
		return officeid;
	}
	public void setOfficeid(String officeid) {
		this.officeid = officeid;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public double getApproveamount() {
		return approveamount;
	}
	public void setApproveamount(double approveamount) {
		this.approveamount = approveamount;
	}
	public String getApprovedate() {
		return approvedate;
	}
	public void setApprovedate(String approvedate) {
		this.approvedate = approvedate;
	}
	public String getApproveuser() {
		return approveuser;
	}
	public void setApproveuser(String approveuser) {
		this.approveuser = approveuser;
	}
	public double getFactamount() {
		return factamount;
	}
	public void setFactamount(double factamount) {
		this.factamount = factamount;
	}
	public String getGrantdate() {
		return grantdate;
	}
	public void setGrantdate(String grantdate) {
		this.grantdate = grantdate;
	}
	public String getGrantuser() {
		return grantuser;
	}
	public void setGrantuser(String grantuser) {
		this.grantuser = grantuser;
	}
	public String getLouser() {
		return louser;
	}
	public void setLouser(String louser) {
		this.louser = louser;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getJinebutton() {
		return jinebutton;
	}
	public void setJinebutton(String jinebutton) {
		this.jinebutton = jinebutton;
	}
	public String getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}
	public String getProjectjw() {
		return projectjw;
	}
	public void setProjectjw(String projectjw) {
		this.projectjw = projectjw;
	}
	
		
}
