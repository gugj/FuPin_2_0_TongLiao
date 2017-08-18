package com.roch.fupin.entity;

/**
 * 年龄和收入、文化程度、致贫原因
 * <br>
 * <b>功能：</b>BasicPoorpeoplePage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class BasicPoorpeopleModel extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;// ID
	private String householderid;// 户主ID
	private String nationid;// 民族ID,字典表
	private int sex;// 性别
	private String familyrelationid;// 与户主关系ID,字典表
	private String personname;// 成员姓名
	private String culturelevelid;// 文化程度ID,字典表
	private String inschoolstatusid;// 在校生状况ID,字典表
	private java.util.Date birthday;// 出生日期
	private String idno;// 身份证号
	private int ifxnh;// 是否参加新型农村合作医疗（城乡居民基本医疗保险）
	private int ifxnbx;// 是否参加新型农村社会养老保险（城乡居民社会养老保险）
	private int ifjbbx;// 是否参加城镇职工基本养老保险
	private int ifdbbx;// 是否参加大病保险
	private String pp_poorreason;// 贫困人致贫原因
	private String pp_helpplan;// 贫困人帮扶措施
	private String ifwanttrained;// 是否愿意培训
	private String personinfo;// 个人情况
	private String remark;// 备注

	private String householdername;// 户主姓名
	private int housecount;// 家庭人口

	// 特殊参数
	private String housesafe;// 房屋安全
	private String watertrouble;// 水质问题
	private String watersafe;// 饮水安全

	private String location;
	private String adl_cd;

	private int pp_poorreason_pc;// 致贫原因人数

	private int whcd_pc;// 文化程度人数

	private int income;// 年就业收入

	/////////////////////////////////////////////////////////////////////////
	//文化程度统计属性、健康状况、在校生状态、劳动能力类型统计
	public String comchronicdisnum; // 常见慢性病人数--学前教育人数--丧失劳动力人数
	public String disability; // 残疾人数--高中人数--省外务工人数
	public String majordisease; // 重大疾病人数--初中人数--技能劳动力人数--市外(省内)务工人数
	public String rows;
	public String healthname;
	public String benkenum; // 中职人数
	public String len;
	public String adl_nm;
	public String page;
	public String len2;
	public String health;
	public String condition;
	public String wenmanum; //高职人数
	public String goodhealthnumber; // 健康人数--非在校生人数--普通劳动能力人数--旗县内务工人数
	public String adcdparam;
	public String spechronicdisnum; // 特殊慢性病人数--小学人数--无劳动力人数--旗县外(市内)务工人数
	public String populationnumber;
	public String healthcount;
	public String orders;
	private String dazhuannum; // 大专
	private String benkexnum; // 本科

	/////////////////贫困村属性/////////////////////////////////
	private int pkcnum;// 贫困村个数
	private int pkcxznum;//贫困村覆盖乡镇数
	private int brcnum;//经济薄弱村个数
	private int brcxznum; //经济薄弱村覆盖乡镇数
	private int sewpkcnum;// 十二五贫困村个数
	private int sewpkcxznum;//十二五贫困村覆盖乡镇数
	private int sswpkcnum;//十三五贫困村个数
	private int sswpkcxznum;// 十三五贫困村覆盖乡镇数
	private int scswpkcnum;// 三到村三到户项目村个数
	private int scswpkcxznum;// 三到村三到户项目村覆盖乡镇数
	private int nopkcnum;// 非贫困村个数
	private int nopkcxznum;// 非贫困村覆盖乡镇数

	/////////////////////////务工情况统计//////////////////////
	public long disability9;
	public long disability6;
	public long disability10;
	public long disability8;
	public long disability7;
	public long disability12;
	public long disability11;


	public long getDisability9() {
		return disability9;
	}

	public void setDisability9(long disability9) {
		this.disability9 = disability9;
	}

	public long getDisability6() {
		return disability6;
	}

	public void setDisability6(long disability6) {
		this.disability6 = disability6;
	}

	public long getDisability10() {
		return disability10;
	}

	public void setDisability10(long disability10) {
		this.disability10 = disability10;
	}

	public long getDisability8() {
		return disability8;
	}

	public void setDisability8(long disability8) {
		this.disability8 = disability8;
	}

	public long getDisability7() {
		return disability7;
	}

	public void setDisability7(long disability7) {
		this.disability7 = disability7;
	}

	public long getDisability12() {
		return disability12;
	}

	public void setDisability12(long disability12) {
		this.disability12 = disability12;
	}

	public long getDisability11() {
		return disability11;
	}

	public void setDisability11(long disability11) {
		this.disability11 = disability11;
	}

	public int getPkcnum() {
		return pkcnum;
	}

	public void setPkcnum(int pkcnum) {
		this.pkcnum = pkcnum;
	}

	public int getPkcxznum() {
		return pkcxznum;
	}

	public void setPkcxznum(int pkcxznum) {
		this.pkcxznum = pkcxznum;
	}

	public int getBrcnum() {
		return brcnum;
	}

	public void setBrcnum(int brcnum) {
		this.brcnum = brcnum;
	}

	public int getBrcxznum() {
		return brcxznum;
	}

	public void setBrcxznum(int brcxznum) {
		this.brcxznum = brcxznum;
	}

	public int getSewpkcnum() {
		return sewpkcnum;
	}

	public void setSewpkcnum(int sewpkcnum) {
		this.sewpkcnum = sewpkcnum;
	}

	public int getSewpkcxznum() {
		return sewpkcxznum;
	}

	public void setSewpkcxznum(int sewpkcxznum) {
		this.sewpkcxznum = sewpkcxznum;
	}

	public int getSswpkcnum() {
		return sswpkcnum;
	}

	public void setSswpkcnum(int sswpkcnum) {
		this.sswpkcnum = sswpkcnum;
	}

	public int getSswpkcxznum() {
		return sswpkcxznum;
	}

	public void setSswpkcxznum(int sswpkcxznum) {
		this.sswpkcxznum = sswpkcxznum;
	}

	public int getScswpkcnum() {
		return scswpkcnum;
	}

	public void setScswpkcnum(int scswpkcnum) {
		this.scswpkcnum = scswpkcnum;
	}

	public int getScswpkcxznum() {
		return scswpkcxznum;
	}

	public void setScswpkcxznum(int scswpkcxznum) {
		this.scswpkcxznum = scswpkcxznum;
	}

	public int getNopkcnum() {
		return nopkcnum;
	}

	public void setNopkcnum(int nopkcnum) {
		this.nopkcnum = nopkcnum;
	}

	public int getNopkcxznum() {
		return nopkcxznum;
	}

	public void setNopkcxznum(int nopkcxznum) {
		this.nopkcxznum = nopkcxznum;
	}

	public String getDazhuannum() {
		return dazhuannum;
	}

	public void setDazhuannum(String dazhuannum) {
		this.dazhuannum = dazhuannum;
	}

	public String getBenkexnum() {
		return benkexnum;
	}

	public void setBenkexnum(String benkexnum) {
		this.benkexnum = benkexnum;
	}

	public String getComchronicdisnum() {
		return comchronicdisnum;
	}

	public void setComchronicdisnum(String comchronicdisnum) {
		this.comchronicdisnum = comchronicdisnum;
	}

	public String getDisability() {
		return disability;
	}

	public void setDisability(String disability) {
		this.disability = disability;
	}

	public String getMajordisease() {
		return majordisease;
	}

	public void setMajordisease(String majordisease) {
		this.majordisease = majordisease;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getHealthname() {
		return healthname;
	}

	public void setHealthname(String healthname) {
		this.healthname = healthname;
	}

	public String getBenkenum() {
		return benkenum;
	}

	public void setBenkenum(String benkenum) {
		this.benkenum = benkenum;
	}

	public String getLen() {
		return len;
	}

	public void setLen(String len) {
		this.len = len;
	}

	public String getAdl_nm() {
		return adl_nm;
	}

	public void setAdl_nm(String adl_nm) {
		this.adl_nm = adl_nm;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getLen2() {
		return len2;
	}

	public void setLen2(String len2) {
		this.len2 = len2;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getWenmanum() {
		return wenmanum;
	}

	public void setWenmanum(String wenmanum) {
		this.wenmanum = wenmanum;
	}

	public String getGoodhealthnumber() {
		return goodhealthnumber;
	}

	public void setGoodhealthnumber(String goodhealthnumber) {
		this.goodhealthnumber = goodhealthnumber;
	}

	public String getAdcdparam() {
		return adcdparam;
	}

	public void setAdcdparam(String adcdparam) {
		this.adcdparam = adcdparam;
	}

	public String getSpechronicdisnum() {
		return spechronicdisnum;
	}

	public void setSpechronicdisnum(String spechronicdisnum) {
		this.spechronicdisnum = spechronicdisnum;
	}

	public String getPopulationnumber() {
		return populationnumber;
	}

	public void setPopulationnumber(String populationnumber) {
		this.populationnumber = populationnumber;
	}

	public String getHealthcount() {
		return healthcount;
	}

	public void setHealthcount(String healthcount) {
		this.healthcount = healthcount;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public String getAdl_cd() {
		return adl_cd;
	}

	public void setAdl_cd(String adl_cd) {
		this.adl_cd = adl_cd;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHouseholderid() {
		return this.householderid;
	}

	public void setHouseholderid(String householderid) {
		this.householderid = householderid;
	}

	public String getNationid() {
		return this.nationid;
	}

	public void setNationid(String nationid) {
		this.nationid = nationid;
	}

	public int getSex() {
		return this.sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getFamilyrelationid() {
		return this.familyrelationid;
	}

	public void setFamilyrelationid(String familyrelationid) {
		this.familyrelationid = familyrelationid;
	}

	public String getPersonname() {
		return this.personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getCulturelevelid() {
		return this.culturelevelid;
	}

	public void setCulturelevelid(String culturelevelid) {
		this.culturelevelid = culturelevelid;
	}

	public String getInschoolstatusid() {
		return this.inschoolstatusid;
	}

	public void setInschoolstatusid(String inschoolstatusid) {
		this.inschoolstatusid = inschoolstatusid;
	}

	public java.util.Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public int getIfxnh() {
		return this.ifxnh;
	}

	public void setIfxnh(int ifxnh) {
		this.ifxnh = ifxnh;
	}

	public int getIfxnbx() {
		return this.ifxnbx;
	}

	public void setIfxnbx(int ifxnbx) {
		this.ifxnbx = ifxnbx;
	}

	public int getIfjbbx() {
		return this.ifjbbx;
	}

	public void setIfjbbx(int ifjbbx) {
		this.ifjbbx = ifjbbx;
	}

	public int getIfdbbx() {
		return this.ifdbbx;
	}

	public void setIfdbbx(int ifdbbx) {
		this.ifdbbx = ifdbbx;
	}

	public String getPp_poorreason() {
		return pp_poorreason;
	}

	public void setPp_poorreason(String pp_poorreason) {
		this.pp_poorreason = pp_poorreason;
	}

	public String getPp_helpplan() {
		return pp_helpplan;
	}

	public void setPp_helpplan(String pp_helpplan) {
		this.pp_helpplan = pp_helpplan;
	}

	public String getIfwanttrained() {
		return ifwanttrained;
	}

	public void setIfwanttrained(String ifwanttrained) {
		this.ifwanttrained = ifwanttrained;
	}

	public String getPersoninfo() {
		return personinfo;
	}

	public void setPersoninfo(String personinfo) {
		this.personinfo = personinfo;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public int getPp_poorreason_pc() {
		return pp_poorreason_pc;
	}

	public void setPp_poorreason_pc(int pp_poorreason_pc) {
		this.pp_poorreason_pc = pp_poorreason_pc;
	}

	public int getWhcd_pc() {
		return whcd_pc;
	}

	public void setWhcd_pc(int whcd_pc) {
		this.whcd_pc = whcd_pc;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
