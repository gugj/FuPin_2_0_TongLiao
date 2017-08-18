package com.roch.fupin.entity;

/**
 * 贫困户的家庭成员信息，含有家庭成员的全部字段
 * 
 * @author wf
 */
public class PoorFamilyPeople extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String id;// ID
	private String householderid;// 户主ID
	private String location;
	private String groupname;
	private String nationid;// 民族ID,字典表
	private String nationtext;
	private int sex;// 性别
	private String sextext;
	private String familyrelationid;// 与户主关系ID,字典表
	private String familyrelationtext;
	private String personname;// 成员姓名
	private String culturelevelid;// 文化程度ID,字典表
	private String cultureleveltext;
	private String inschoolstatusid;// 在校生状况ID,字典表
	private String inschoolstatustext;
	private String inschoolplace;
	private String inschoolplacetext;
	private String birthday;// 出生日期
	private String birthdaytext;
	private String age;
	private String idno;// 身份证号
	private int ifxnh;// 是否参加新型农村合作医疗（城乡居民基本医疗保险）
	private String ifxnhtext;
	private String ifxnhname;
	private int ifxnbx;// 是否参加新型农村社会养老保险（城乡居民社会养老保险）
	private String ifxnbxtext;
	private String ifxnbxname;
	private int ifjbbx;// 是否参加城镇职工基本养老保险
	private String ifjbbxtext;
	private String ifjbbxname;
	private int ifdbbx;// 是否参加大病保险
	private String ifdbbxtext;
	private String personinfo;// 个人情况
	private String remark;// 备注
	private String pp_phone;
	private String pp_health;// 健康状况
	private String diseasetype;// 疾病类型
	private String pp_laborskill;// 劳动技能
	private String pp_onsoldier;// 是否现役军人
	private String pp_politics;// 政治面貌
	private String pp_deformityno;// 残疾证号
	private String pp_healthtext;
//	private String diseasetypetext;
	private String pp_laborskilltext;
	private String pp_politicstext;
	private String pp_onsoldiertext;
	private String householdername;// 户主姓名
	private int housecount;// 家庭人口
	private String ifhasjob;
	private String ifdborwb;// 是否参加低保或五保
	private String ifdborwbtext;
	// 特殊参数
	private String housesafe;// 房屋安全
	private String watertrouble;// 水质问题
	private String watersafe;// 饮水安全
	private String adl_cd;
	private String groupno;
	// 查询参数
	private int age_start;
	private int age_end;
	private String if_hanorshaoshu;
	private String if_zaixiao;
	private String ifcanjizheng;
	private int pp_poorreason_pc;// 致贫原因人数
	private String pp_poorreason;
	private int whcd_pc;// 文化程度人数
	private int school_pc;
	private String illness;
	private int illness_pc;
	private int income;// 年就业收入
	private String ts; // 最后更新时间***********
	private String changefield;
	private String fieldbeforeval;
	private String helpdutypersonid;
	private String ifxncylbxname; //是否参加新型农村社会养老保险
	private String ifxinnhname; //是否参加新农合
	private String diseasetypetext;//疾病类型

//	private String pp_laborskilltext;//劳动能力
//	private String pp_onsoldiertext;//是否现役军人
//	private String pp_politicstext;//政治面貌
//	private String inschoolstatustext;//在校生状况
//	private String pp_healthtext;//健康状况
//	private String diseasetypetext;//疾病类型
//	private String inschoolplacetext;//在校地区


	public String getIfjbbxname() {
		return ifjbbxname;
	}

	public void setIfjbbxname(String ifjbbxname) {
		this.ifjbbxname = ifjbbxname;
	}

	public String getIfxnbxname() {
		return ifxnbxname;
	}

	public void setIfxnbxname(String ifxnbxname) {
		this.ifxnbxname = ifxnbxname;
	}

	public String getIfxnhname() {
		return ifxnhname;
	}

	public void setIfxnhname(String ifxnhname) {
		this.ifxnhname = ifxnhname;
	}

	public String getIfxncylbxname() {
		return ifxncylbxname;
	}

	public void setIfxncylbxname(String ifxncylbxname) {
		this.ifxncylbxname = ifxncylbxname;
	}

	public String getIfxinnhname() {
		return ifxinnhname;
	}

	public void setIfxinnhname(String ifxinnhname) {
		this.ifxinnhname = ifxinnhname;
	}

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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getNationid() {
		return nationid;
	}
	public void setNationid(String nationid) {
		this.nationid = nationid;
	}
	public String getNationtext() {
		return nationtext;
	}
	public void setNationtext(String nationtext) {
		this.nationtext = nationtext;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getSextext() {
		return sextext;
	}
	public void setSextext(String sextext) {
		this.sextext = sextext;
	}
	public String getFamilyrelationid() {
		return familyrelationid;
	}
	public void setFamilyrelationid(String familyrelationid) {
		this.familyrelationid = familyrelationid;
	}
	public String getFamilyrelationtext() {
		return familyrelationtext;
	}
	public void setFamilyrelationtext(String familyrelationtext) {
		this.familyrelationtext = familyrelationtext;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public String getCulturelevelid() {
		return culturelevelid;
	}
	public void setCulturelevelid(String culturelevelid) {
		this.culturelevelid = culturelevelid;
	}
	public String getCultureleveltext() {
		return cultureleveltext;
	}
	public void setCultureleveltext(String cultureleveltext) {
		this.cultureleveltext = cultureleveltext;
	}
	public String getInschoolstatusid() {
		return inschoolstatusid;
	}
	public void setInschoolstatusid(String inschoolstatusid) {
		this.inschoolstatusid = inschoolstatusid;
	}
	public String getInschoolstatustext() {
		return inschoolstatustext;
	}
	public void setInschoolstatustext(String inschoolstatustext) {
		this.inschoolstatustext = inschoolstatustext;
	}
	public String getInschoolplace() {
		return inschoolplace;
	}
	public void setInschoolplace(String inschoolplace) {
		this.inschoolplace = inschoolplace;
	}
	public String getInschoolplacetext() {
		return inschoolplacetext;
	}
	public void setInschoolplacetext(String inschoolplacetext) {
		this.inschoolplacetext = inschoolplacetext;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getBirthdaytext() {
		return birthdaytext;
	}
	public void setBirthdaytext(String birthdaytext) {
		this.birthdaytext = birthdaytext;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public int getIfxnh() {
		return ifxnh;
	}
	public void setIfxnh(int ifxnh) {
		this.ifxnh = ifxnh;
	}
	public String getIfxnhtext() {
		return ifxnhtext;
	}
	public void setIfxnhtext(String ifxnhtext) {
		this.ifxnhtext = ifxnhtext;
	}
	public int getIfxnbx() {
		return ifxnbx;
	}
	public void setIfxnbx(int ifxnbx) {
		this.ifxnbx = ifxnbx;
	}
	public String getIfxnbxtext() {
		return ifxnbxtext;
	}
	public void setIfxnbxtext(String ifxnbxtext) {
		this.ifxnbxtext = ifxnbxtext;
	}
	public int getIfjbbx() {
		return ifjbbx;
	}
	public void setIfjbbx(int ifjbbx) {
		this.ifjbbx = ifjbbx;
	}
	public String getIfjbbxtext() {
		return ifjbbxtext;
	}
	public void setIfjbbxtext(String ifjbbxtext) {
		this.ifjbbxtext = ifjbbxtext;
	}
	public int getIfdbbx() {
		return ifdbbx;
	}
	public void setIfdbbx(int ifdbbx) {
		this.ifdbbx = ifdbbx;
	}
	public String getIfdbbxtext() {
		return ifdbbxtext;
	}
	public void setIfdbbxtext(String ifdbbxtext) {
		this.ifdbbxtext = ifdbbxtext;
	}
	public String getPersoninfo() {
		return personinfo;
	}
	public void setPersoninfo(String personinfo) {
		this.personinfo = personinfo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPp_phone() {
		return pp_phone;
	}
	public void setPp_phone(String pp_phone) {
		this.pp_phone = pp_phone;
	}
	public String getPp_health() {
		return pp_health;
	}
	public void setPp_health(String pp_health) {
		this.pp_health = pp_health;
	}
	public String getDiseasetype() {
		return diseasetype;
	}
	public void setDiseasetype(String diseasetype) {
		this.diseasetype = diseasetype;
	}
	public String getPp_laborskill() {
		return pp_laborskill;
	}
	public void setPp_laborskill(String pp_laborskill) {
		this.pp_laborskill = pp_laborskill;
	}
	public String getPp_onsoldier() {
		return pp_onsoldier;
	}
	public void setPp_onsoldier(String pp_onsoldier) {
		this.pp_onsoldier = pp_onsoldier;
	}
	public String getPp_politics() {
		return pp_politics;
	}
	public void setPp_politics(String pp_politics) {
		this.pp_politics = pp_politics;
	}
	public String getPp_deformityno() {
		return pp_deformityno;
	}
	public void setPp_deformityno(String pp_deformityno) {
		this.pp_deformityno = pp_deformityno;
	}
	public String getPp_healthtext() {
		return pp_healthtext;
	}
	public void setPp_healthtext(String pp_healthtext) {
		this.pp_healthtext = pp_healthtext;
	}
	public String getDiseasetypetext() {
		return diseasetypetext;
	}
	public void setDiseasetypetext(String diseasetypetext) {
		this.diseasetypetext = diseasetypetext;
	}
	public String getPp_laborskilltext() {
		return pp_laborskilltext;
	}
	public void setPp_laborskilltext(String pp_laborskilltext) {
		this.pp_laborskilltext = pp_laborskilltext;
	}
	public String getPp_politicstext() {
		return pp_politicstext;
	}
	public void setPp_politicstext(String pp_politicstext) {
		this.pp_politicstext = pp_politicstext;
	}
	public String getPp_onsoldiertext() {
		return pp_onsoldiertext;
	}
	public void setPp_onsoldiertext(String pp_onsoldiertext) {
		this.pp_onsoldiertext = pp_onsoldiertext;
	}
	public String getHouseholdername() {
		return householdername;
	}
	public void setHouseholdername(String householdername) {
		this.householdername = householdername;
	}
	public int getHousecount() {
		return housecount;
	}
	public void setHousecount(int housecount) {
		this.housecount = housecount;
	}
	public String getIfhasjob() {
		return ifhasjob;
	}
	public void setIfhasjob(String ifhasjob) {
		this.ifhasjob = ifhasjob;
	}
	public String getIfdborwb() {
		return ifdborwb;
	}
	public void setIfdborwb(String ifdborwb) {
		this.ifdborwb = ifdborwb;
	}
	public String getIfdborwbtext() {
		return ifdborwbtext;
	}
	public void setIfdborwbtext(String ifdborwbtext) {
		this.ifdborwbtext = ifdborwbtext;
	}
	public String getHousesafe() {
		return housesafe;
	}
	public void setHousesafe(String housesafe) {
		this.housesafe = housesafe;
	}
	public String getWatertrouble() {
		return watertrouble;
	}
	public void setWatertrouble(String watertrouble) {
		this.watertrouble = watertrouble;
	}
	public String getWatersafe() {
		return watersafe;
	}
	public void setWatersafe(String watersafe) {
		this.watersafe = watersafe;
	}
	public String getAdl_cd() {
		return adl_cd;
	}
	public void setAdl_cd(String adl_cd) {
		this.adl_cd = adl_cd;
	}
	public String getGroupno() {
		return groupno;
	}
	public void setGroupno(String groupno) {
		this.groupno = groupno;
	}
	public int getAge_start() {
		return age_start;
	}
	public void setAge_start(int age_start) {
		this.age_start = age_start;
	}
	public int getAge_end() {
		return age_end;
	}
	public void setAge_end(int age_end) {
		this.age_end = age_end;
	}
	public String getIf_hanorshaoshu() {
		return if_hanorshaoshu;
	}
	public void setIf_hanorshaoshu(String if_hanorshaoshu) {
		this.if_hanorshaoshu = if_hanorshaoshu;
	}
	public String getIf_zaixiao() {
		return if_zaixiao;
	}
	public void setIf_zaixiao(String if_zaixiao) {
		this.if_zaixiao = if_zaixiao;
	}
	public String getIfcanjizheng() {
		return ifcanjizheng;
	}
	public void setIfcanjizheng(String ifcanjizheng) {
		this.ifcanjizheng = ifcanjizheng;
	}
	public int getPp_poorreason_pc() {
		return pp_poorreason_pc;
	}
	public void setPp_poorreason_pc(int pp_poorreason_pc) {
		this.pp_poorreason_pc = pp_poorreason_pc;
	}
	public String getPp_poorreason() {
		return pp_poorreason;
	}
	public void setPp_poorreason(String pp_poorreason) {
		this.pp_poorreason = pp_poorreason;
	}
	public int getWhcd_pc() {
		return whcd_pc;
	}
	public void setWhcd_pc(int whcd_pc) {
		this.whcd_pc = whcd_pc;
	}
	public int getSchool_pc() {
		return school_pc;
	}
	public void setSchool_pc(int school_pc) {
		this.school_pc = school_pc;
	}
	public String getIllness() {
		return illness;
	}
	public void setIllness(String illness) {
		this.illness = illness;
	}
	public int getIllness_pc() {
		return illness_pc;
	}
	public void setIllness_pc(int illness_pc) {
		this.illness_pc = illness_pc;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getChangefield() {
		return changefield;
	}
	public void setChangefield(String changefield) {
		this.changefield = changefield;
	}
	public String getFieldbeforeval() {
		return fieldbeforeval;
	}
	public void setFieldbeforeval(String fieldbeforeval) {
		this.fieldbeforeval = fieldbeforeval;
	}
	public String getHelpdutypersonid() {
		return helpdutypersonid;
	}
	public void setHelpdutypersonid(String helpdutypersonid) {
		this.helpdutypersonid = helpdutypersonid;
	}
}
