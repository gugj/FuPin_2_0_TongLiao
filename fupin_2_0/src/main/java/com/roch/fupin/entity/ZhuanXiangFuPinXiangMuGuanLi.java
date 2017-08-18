package com.roch.fupin.entity;

/**
 * 专项扶贫项目管理实体类
 * 作者：ZDS
 * 时间：2016/12/21/021 16:38
 */
public class ZhuanXiangFuPinXiangMuGuanLi extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;
    private String projectname;//   项目名称
    private String planstartdate;//   计划开始时间
    private String planenddate;//   计划结束时间
    private String zszj;//   中省资金（万元）
    private String sjzj;//   市级资金（万元）
    private String zcpt;//   镇村配套（万元）
    private String qzzc;//   群众自筹（万元）
    private String investtotal;//   投资合计（万元）,中省+市级+镇村配套+群众自筹
    private String approveamount;//   市财政批准金额
    private String paidamount;//   市财政已拨付金额合计,需记录拨付明细
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
    private String remark;//   备注
    private String projectcategoryidcall;//   项目类型ID,字典表
    private String descript_text;//   项目类型描述
    private String projectscheduleidcall;//   项目进度ID,字典表
    private String projectstatusidcall;//   项目状态ID,字典表
    private String fpdx;//项目对应的帮扶对象--扶贫对象
    private String completepercent;//项目完成进度百分比
    private String scheinfo;//项目进度填报详情--最新进度简报

    private String adl_cd;
    private String fpdx_adcd;
    private String countryName;
    private String townName;
    private String villageName;
    private String org_code;//组织机构代码
    private String ywctz;//已完成投资（万元）
    private String zjbl;//资金比例%比（已完成投资/总投资金额）

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getZszj() {
        return zszj;
    }

    public void setZszj(String zszj) {
        this.zszj = zszj;
    }

    public String getSjzj() {
        return sjzj;
    }

    public void setSjzj(String sjzj) {
        this.sjzj = sjzj;
    }

    public String getZcpt() {
        return zcpt;
    }

    public void setZcpt(String zcpt) {
        this.zcpt = zcpt;
    }

    public String getQzzc() {
        return qzzc;
    }

    public void setQzzc(String qzzc) {
        this.qzzc = qzzc;
    }

    public String getInvesttotal() {
        return investtotal;
    }

    public void setInvesttotal(String investtotal) {
        this.investtotal = investtotal;
    }

    public String getApproveamount() {
        return approveamount;
    }

    public void setApproveamount(String approveamount) {
        this.approveamount = approveamount;
    }

    public String getPaidamount() {
        return paidamount;
    }

    public void setPaidamount(String paidamount) {
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProjectcategoryidcall() {
        return projectcategoryidcall;
    }

    public void setProjectcategoryidcall(String projectcategoryidcall) {
        this.projectcategoryidcall = projectcategoryidcall;
    }

    public String getDescript_text() {
        return descript_text;
    }

    public void setDescript_text(String descript_text) {
        this.descript_text = descript_text;
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

    public String getFpdx() {
        return fpdx;
    }

    public void setFpdx(String fpdx) {
        this.fpdx = fpdx;
    }

    public String getCompletepercent() {
        return completepercent;
    }

    public void setCompletepercent(String completepercent) {
        this.completepercent = completepercent;
    }

    public String getScheinfo() {
        return scheinfo;
    }

    public void setScheinfo(String scheinfo) {
        this.scheinfo = scheinfo;
    }

    public String getAdl_cd() {
        return adl_cd;
    }

    public void setAdl_cd(String adl_cd) {
        this.adl_cd = adl_cd;
    }

    public String getFpdx_adcd() {
        return fpdx_adcd;
    }

    public void setFpdx_adcd(String fpdx_adcd) {
        this.fpdx_adcd = fpdx_adcd;
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

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getYwctz() {
        return ywctz;
    }

    public void setYwctz(String ywctz) {
        this.ywctz = ywctz;
    }

    public String getZjbl() {
        return zjbl;
    }

    public void setZjbl(String zjbl) {
        this.zjbl = zjbl;
    }
}
