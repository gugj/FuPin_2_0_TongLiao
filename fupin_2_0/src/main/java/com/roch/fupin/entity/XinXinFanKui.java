package com.roch.fupin.entity;

/**
 * 信息反馈实体类，里面包含15个字段：id、householderid、title、detail、ts、industryid、orgName、adl_cd、location、personname
 * 、replycount、helpdutypersonid、queryname、querystarttime、queryendtime
 * @author ZhaoDongShao 2016年5月26日
 */
public class XinXinFanKui extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;//   id
	private String householderid;//   户主ID
	private String title;//   标题
	private String detail;//   反馈内容
	private String ts;//   时间
	private String industryid;//行业部门

	private String orgName;
	private String adl_cd;
	private String location;
	private String personname;
	private int replycount;
	private String helpdutypersonid;

	private String queryname;
	private String querystarttime;
	private String queryendtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHouseholderid() {
		return householderid;
	}

	public void setHouseholderid(String householderid) {
		this.householderid = householderid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public String getIndustryid() {
		return industryid;
	}

	public void setIndustryid(String industryid) {
		this.industryid = industryid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAdl_cd() {
		return adl_cd;
	}

	public void setAdl_cd(String adl_cd) {
		this.adl_cd = adl_cd;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public int getReplycount() {
		return replycount;
	}

	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}

	public String getHelpdutypersonid() {
		return helpdutypersonid;
	}

	public void setHelpdutypersonid(String helpdutypersonid) {
		this.helpdutypersonid = helpdutypersonid;
	}

	public String getQueryname() {
		return queryname;
	}

	public void setQueryname(String queryname) {
		this.queryname = queryname;
	}

	public String getQuerystarttime() {
		return querystarttime;
	}

	public void setQuerystarttime(String querystarttime) {
		this.querystarttime = querystarttime;
	}

	public String getQueryendtime() {
		return queryendtime;
	}

	public void setQueryendtime(String queryendtime) {
		this.queryendtime = queryendtime;
	}
}
