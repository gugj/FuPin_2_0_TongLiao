package com.roch.fupin.entity;

/**
 * @author ZhaoDongShao
 * 2016年6月3日
 */
public class BaseProject extends BaseEntity{

	private static final long serialVersionUID = 1L;

	//项目信息
	private String projectname;//   项目名称
	private String planstartdate;//   计划开始时间
	private String planenddate;//   计划结束时间
	private double zszj;//   中省资金（万元）
	private double sjzj;//   市级资金（万元）
	private double zcpt;//   镇村配套（万元）
	private double qzzc;//   群众自筹
	private double ywctz;//已完成投资
	private double investtotal;//   投资合计（万元）,中省+市级+镇村配套
	private double approveamount;//   市财政批准金额
	private double paidamount;//   市财政已拨付金额合计,需记录拨付明细
	private String projectcategoryid;//   项目类型ID,字典表
	private String projectscheduleid;//   项目进度ID,字典表
	private String projectstatusid;//   项目状态ID,字典表
	private String dutydeptname;//   项目责任单位
	private String dutyperson;//   项目责任人
	private String dutypersonphone;//   项目责任人联系方式
	private String buildcontent;//   建设内容
	private String projecteffect;//   项目效益
	private int islixiang;//   是否已立项
	private String lixiangdate;//   立项日期
	private int isbaobei;//   是否已报备
	private String baobeidate;//   报备日期
	private int iszhaobiao;//   是否已招标
	private String zhaobiaodate;//   招标日期
	private String zhongbiaocompany;//   中标单位名称
	private int iskaigong;//   是否已开工
	private String kaigongdate;//   开工日期
	private int isjungong;//   是否已竣工
	private String jungongdate;//   竣工日期
	private int isyanshou;//   是否已验收
	private String yanshoudate;//   验收日期
	
	private String projectscheduleidcall; //进度
	private String projectstatusidcall; //状态
	private String projectcategoryidcall; //类型

	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getPlanstartdate() {
		return planstartdate;
	}
	public void setPlanstartdate(String planstartdate) {
		this.planstartdate = planstartdate;
	}
	public String getPlanenddate() {
		return planenddate;
	}
	public void setPlanenddate(String planenddate) {
		this.planenddate = planenddate;
	}
	public double getZszj() {
		return zszj;
	}
	public void setZszj(double zszj) {
		this.zszj = zszj;
	}
	public double getSjzj() {
		return sjzj;
	}
	public void setSjzj(double sjzj) {
		this.sjzj = sjzj;
	}
	public double getZcpt() {
		return zcpt;
	}
	public void setZcpt(double zcpt) {
		this.zcpt = zcpt;
	}
	public double getInvesttotal() {
		return investtotal;
	}
	public void setInvesttotal(double investtotal) {
		this.investtotal = investtotal;
	}
	public double getApproveamount() {
		return approveamount;
	}
	public void setApproveamount(double approveamount) {
		this.approveamount = approveamount;
	}
	public double getPaidamount() {
		return paidamount;
	}
	public void setPaidamount(double paidamount) {
		this.paidamount = paidamount;
	}
	public String getProjectcategoryid() {
		return projectcategoryid;
	}
	public void setProjectcategoryid(String projectcategoryid) {
		this.projectcategoryid = projectcategoryid;
	}
	public String getProjectscheduleid() {
		return projectscheduleid;
	}
	public void setProjectscheduleid(String projectscheduleid) {
		this.projectscheduleid = projectscheduleid;
	}
	public String getProjectstatusid() {
		return projectstatusid;
	}
	public void setProjectstatusid(String projectstatusid) {
		this.projectstatusid = projectstatusid;
	}
	public String getDutydeptname() {
		return dutydeptname;
	}
	public void setDutydeptname(String dutydeptname) {
		this.dutydeptname = dutydeptname;
	}
	public String getDutyperson() {
		return dutyperson;
	}
	public void setDutyperson(String dutyperson) {
		this.dutyperson = dutyperson;
	}
	public String getDutypersonphone() {
		return dutypersonphone;
	}
	public void setDutypersonphone(String dutypersonphone) {
		this.dutypersonphone = dutypersonphone;
	}
	public String getBuildcontent() {
		return buildcontent;
	}
	public void setBuildcontent(String buildcontent) {
		this.buildcontent = buildcontent;
	}
	public String getProjecteffect() {
		return projecteffect;
	}
	public void setProjecteffect(String projecteffect) {
		this.projecteffect = projecteffect;
	}
	public int getIslixiang() {
		return islixiang;
	}
	public void setIslixiang(int islixiang) {
		this.islixiang = islixiang;
	}
	public String getLixiangdate() {
		return lixiangdate;
	}
	public void setLixiangdate(String lixiangdate) {
		this.lixiangdate = lixiangdate;
	}
	public int getIsbaobei() {
		return isbaobei;
	}
	public void setIsbaobei(int isbaobei) {
		this.isbaobei = isbaobei;
	}
	public String getBaobeidate() {
		return baobeidate;
	}
	public void setBaobeidate(String baobeidate) {
		this.baobeidate = baobeidate;
	}
	public int getIszhaobiao() {
		return iszhaobiao;
	}
	public void setIszhaobiao(int iszhaobiao) {
		this.iszhaobiao = iszhaobiao;
	}
	public String getZhaobiaodate() {
		return zhaobiaodate;
	}
	public void setZhaobiaodate(String zhaobiaodate) {
		this.zhaobiaodate = zhaobiaodate;
	}
	public String getZhongbiaocompany() {
		return zhongbiaocompany;
	}
	public void setZhongbiaocompany(String zhongbiaocompany) {
		this.zhongbiaocompany = zhongbiaocompany;
	}
	public int getIskaigong() {
		return iskaigong;
	}
	public void setIskaigong(int iskaigong) {
		this.iskaigong = iskaigong;
	}
	public String getKaigongdate() {
		return kaigongdate;
	}
	public void setKaigongdate(String kaigongdate) {
		this.kaigongdate = kaigongdate;
	}
	public int getIsjungong() {
		return isjungong;
	}
	public void setIsjungong(int isjungong) {
		this.isjungong = isjungong;
	}
	public String getJungongdate() {
		return jungongdate;
	}
	public void setJungongdate(String jungongdate) {
		this.jungongdate = jungongdate;
	}
	public int getIsyanshou() {
		return isyanshou;
	}
	public void setIsyanshou(int isyanshou) {
		this.isyanshou = isyanshou;
	}
	public String getYanshoudate() {
		return yanshoudate;
	}
	public void setYanshoudate(String yanshoudate) {
		this.yanshoudate = yanshoudate;
	}
	public String getProjectscheduleidcall() {
		return projectscheduleidcall;
	}
	public void setProjectscheduleidcall(String projectscheduleidcall) {
		this.projectscheduleidcall = projectscheduleidcall;
	}
	public String getProjectstatusidcall() {
		return projectstatusidcall;
	}
	public void setProjectstatusidcall(String projectstatusidcall) {
		this.projectstatusidcall = projectstatusidcall;
	}
	public String getProjectcategoryidcall() {
		return projectcategoryidcall;
	}
	public void setProjectcategoryidcall(String projectcategoryidcall) {
		this.projectcategoryidcall = projectcategoryidcall;
	}
	public double getQzzc() {
		return qzzc;
	}
	public void setQzzc(double qzzc) {
		this.qzzc = qzzc;
	}
	public double getYwctz() {
		return ywctz;
	}
	public void setYwctz(double ywctz) {
		this.ywctz = ywctz;
	}

}
