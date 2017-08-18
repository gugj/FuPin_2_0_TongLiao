package com.roch.fupin.entity;

/**
 * 登陆用户的实体类，继承自BaseEntity，继承了parseToJson()方法，里面包含了用户的所有字段adl_cd、adl_nm、org_code等...
 * @author 
 * 2016年11月3日 
 */
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;
	private String loginname; // 登陆用户输入的用户名，即admin
	private String user_code;// 人员代码
	private String user_name;// 人员姓名
	private String dep_code;// 所属部门编码
	private String sex;// 性别
	private String duty_level;// 职务级别
	private String title;// 技术职称
	private String mtime;// 时间戳
	private String note;// 备注
	private String telnumb;// 电话
	private String moblenumb;// 手机
	private String email;// 电子邮件
	private String birthday;// 生日
	private String highestdegree;// 学历
	private String ordernum;// 顺序号
	private String ifdel;// 删除标志
	private String password;// 用户密码
	private String jobids;// 所属岗位id（可多个）
	private String roleids;// 所属角色id
	private String roleids_app;// 所属角色id
	private String depName;// 只是用于前台显示
	private String sexName;// 只是用于前台显示
	private String highestdegreeName;
	private String dutyLevelName;
	private String rolename;
	private String rolename_app;
	private String cityName;// 城市名称
	private String countryName;// 县级名称
	private String townName;// 乡镇名称
	private String villageName;// 村名
	private String adl_cd;
	private String adl_nm;
	private String org_code;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDep_code() {
		return dep_code;
	}

	public void setDep_code(String dep_code) {
		this.dep_code = dep_code;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDuty_level() {
		return duty_level;
	}

	public void setDuty_level(String duty_level) {
		this.duty_level = duty_level;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTelnumb() {
		return telnumb;
	}

	public void setTelnumb(String telnumb) {
		this.telnumb = telnumb;
	}

	public String getMoblenumb() {
		return moblenumb;
	}

	public void setMoblenumb(String moblenumb) {
		this.moblenumb = moblenumb;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHighestdegree() {
		return highestdegree;
	}

	public void setHighestdegree(String highestdegree) {
		this.highestdegree = highestdegree;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public String getIfdel() {
		return ifdel;
	}

	public void setIfdel(String ifdel) {
		this.ifdel = ifdel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJobids() {
		return jobids;
	}

	public void setJobids(String jobids) {
		this.jobids = jobids;
	}

	public String getRoleids() {
		return roleids;
	}

	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}

	public String getRoleids_app() {
		return roleids_app;
	}

	public void setRoleids_app(String roleids_app) {
		this.roleids_app = roleids_app;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getHighestdegreeName() {
		return highestdegreeName;
	}

	public void setHighestdegreeName(String highestdegreeName) {
		this.highestdegreeName = highestdegreeName;
	}

	public String getDutyLevelName() {
		return dutyLevelName;
	}

	public void setDutyLevelName(String dutyLevelName) {
		this.dutyLevelName = dutyLevelName;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRolename_app() {
		return rolename_app;
	}

	public void setRolename_app(String rolename_app) {
		this.rolename_app = rolename_app;
	}

	public String getAdl_cd() {
		return adl_cd;
	}

	public void setAdl_cd(String adl_cd) {
		this.adl_cd = adl_cd;
	}

	public String getOrg_code() {
		return org_code;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public String getAdl_nm() {
		return adl_nm;
	}

	public void setAdl_nm(String adl_nm) {
		this.adl_nm = adl_nm;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityname(String cityname) {
		this.cityName = cityname;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
}
