package com.roch.fupin.entity;

import java.util.List;

/**
 * 贫困村基本信息
 * @author ZhaoDongShao
 * 2016年5月24日
 */
public class PoorVillageBase extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String id;//   ID
	private String povertystatusid;//   脱贫属性ID，字典表，未脱贫，已脱贫，返贫
	private String personname;//   村负责人姓名
	private String persontitle;//   村负责人职务
	private String personphone;//   村负责人电话
	private int populationnumber;//   总人口数
	private String povertypercent;//   贫困发生率
	private String livelihoodinfo;//   生产生活情况
	private String sanitationinfo;//   卫生计划生育情况
	private String informationize;//   基础设施
	private String cultureinfo;//   公共服务
	private String industryinfo;//   产业发展
	private String adl_cd; //行政层级代码
	private String countryname;//城市名称
	private String cityName;
	private String townname;//乡镇名称
	private String villagename;//村名称
	private String location;
	private String groupname;//组名称
	private String tpName;//脱贫状态
	private String companyName; //帮扶单位
	private List<HelpCompany> dutycompany;//帮扶单位列表
	private String picturePath;//图片
	//新添加字段
	private int poorhousenm;//	贫困户数目
	private int poorpeoplenm;//	贫困人数目
	private String secretaryname;//	村支书名称
	private String secretaryphone;// 村支书电话
	private String firsecretaryname;// 第一书记名称
	private String firsecretaryphone;// 第一书记电话
	private String captainname;// 队长姓名
	private String captainphone;// 队长电话

	public String getCaptainname() {
		return captainname;
	}

	public void setCaptainname(String captainname) {
		this.captainname = captainname;
	}

	public String getCaptainphone() {
		return captainphone;
	}

	public void setCaptainphone(String captainphone) {
		this.captainphone = captainphone;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPovertystatusid() {
		return povertystatusid;
	}
	public void setPovertystatusid(String povertystatusid) {
		this.povertystatusid = povertystatusid;
	}
	public String getVillagename() {
		return villagename;
	}
	public void setVillagename(String villagename) {
		this.villagename = villagename;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public String getPersontitle() {
		return persontitle;
	}
	public void setPersontitle(String persontitle) {
		this.persontitle = persontitle;
	}
	public String getPersonphone() {
		return personphone;
	}
	public void setPersonphone(String personphone) {
		this.personphone = personphone;
	}
	public int getPopulationnumber() {
		return populationnumber;
	}
	public void setPopulationnumber(int populationnumber) {
		this.populationnumber = populationnumber;
	}
	public String getPovertypercent() {
		return povertypercent;
	}
	public void setPovertypercent(String povertypercent) {
		this.povertypercent = povertypercent;
	}
	public String getLivelihoodinfo() {
		return livelihoodinfo;
	}
	public void setLivelihoodinfo(String livelihoodinfo) {
		this.livelihoodinfo = livelihoodinfo;
	}
	public String getSanitationinfo() {
		return sanitationinfo;
	}
	public void setSanitationinfo(String sanitationinfo) {
		this.sanitationinfo = sanitationinfo;
	}
	public String getInformationize() {
		return informationize;
	}
	public void setInformationize(String informationize) {
		this.informationize = informationize;
	}
	public String getCultureinfo() {
		return cultureinfo;
	}
	public void setCultureinfo(String cultureinfo) {
		this.cultureinfo = cultureinfo;
	}
	public String getIndustryinfo() {
		return industryinfo;
	}
	public void setIndustryinfo(String industryinfo) {
		this.industryinfo = industryinfo;
	}
	public int getPoorhousenm() {
		return poorhousenm;
	}
	public void setPoorhousenm(int poorhousenm) {
		this.poorhousenm = poorhousenm;
	}
	public int getPoorpeoplenm() {
		return poorpeoplenm;
	}
	public void setPoorpeoplenm(int poorpeoplenm) {
		this.poorpeoplenm = poorpeoplenm;
	}
	public String getSecretaryname() {
		return secretaryname;
	}
	public void setSecretaryname(String secretaryname) {
		this.secretaryname = secretaryname;
	}
	public String getSecretaryphone() {
		return secretaryphone;
	}
	public void setSecretaryphone(String secretaryphone) {
		this.secretaryphone = secretaryphone;
	}
	public String getFirsecretaryname() {
		return firsecretaryname;
	}
	public void setFirsecretaryname(String firsecretaryname) {
		this.firsecretaryname = firsecretaryname;
	}
	public String getFirsecretaryphone() {
		return firsecretaryphone;
	}
	public void setFirsecretaryphone(String firsecretaryphone) {
		this.firsecretaryphone = firsecretaryphone;
	}
	public String getCountryname() {
		return countryname;
	}
	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	public String getTpName() {
		return tpName;
	}
	public void setTpName(String tpName) {
		this.tpName = tpName;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String getAdl_cd() {
		return adl_cd;
	}
	public void setAdl_cd(String adl_cd) {
		this.adl_cd = adl_cd;
	}
	public String getTownName() {
		return townname;
	}
	public void setTownName(String townName) {
		this.townname = townName;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<HelpCompany> getDutycompany() {
		return dutycompany;
	}
	public void setDutycompany(List<HelpCompany> dutycompany) {
		this.dutycompany = dutycompany;
	}
}
