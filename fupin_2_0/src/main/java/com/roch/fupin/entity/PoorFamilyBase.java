package com.roch.fupin.entity;

import java.util.List;

/**
 * 贫困户的全部的基本信息，包括户ID <br>
 * <b>功能：</b>BasicPoorfamilyPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class PoorFamilyBase extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String householderid;//   户主ID
	private String povertystatusid;//   脱贫属性ID，字典表，新识别贫困户，未脱贫，已脱贫，预脱贫，返贫，注销
	private String helpplan;//   扶贫计划
	private int housecount;//   家庭人口
	private String phone;//   联系电话
	private String housearea;//   住房面积
	private String iselectricity;//   是否通生活用电
	private String watertrouble;//   饮水是否困难
	private String watersafe;//   饮水是否安全
	private double dibao;//
	private double yanglao;//
	private double xinnonghe;//
	private double huanlin;//
	private double shengtailin;//
	private double jiaoyu;//
	private double liangshi;//
	private double yangzhi;//
	private double guopin;//
	private double shumu;//
	private double jiuye;//
	private double linggong;//
	private double juanzeng;//
	private double weiwen;//
	private double qita;//
	private List<DutyPerson> dutyPerson;//帮扶责任人
	private String picturePath;//图片地址
	private int jyrs;//就业人数
	//贫困户-帮扶责任人关系表字段
	private String map_id;//   ID
	private String map_householderid;//   户主ID
	private String helpdutypersonid;//   帮扶责任人ID
	//贫困人表字段
	private String id;//   ID
	private String p_householderid;//   户主ID
	private String nationid;//   民族ID,字典表
	private int sex;//   性别
	private String sextext;//   性别-----新
	private String familyrelationid;//   与户主关系ID,字典表
	private String personname;//   成员姓名
	private String culturelevelid;//   文化程度ID,字典表
	private String inschoolstatusid;//   在校生状况ID,字典表
	private String birthday;//   出生日期
	private String idno;//   身份证号
	private int ifxnh;//   是否参加新型农村合作医疗（城乡居民基本医疗保险）
	private int ifxnbx;//   是否参加新型农村社会养老保险（城乡居民社会养老保险）
	private int ifjbbx;//   是否参加城镇职工基本养老保险
	private int ifdbbx;//   是否参加大病保险
	private String pp_helpplan;//贫困人帮扶措施
	private String ifwanttrained;//是否愿意培训
	private String personinfo;//   个人情况
	private String remark;//   备注
	private String householdername;//关联查询出户主姓名
	private String pp_poorreason;//主要致贫原因
	private String se_poorreason;//次要致贫原因
	private String ifjunlieshu;//是否军烈属
	private String grassland;//牧草地面积
	private String waterland;//水面面积
	private String ifrelocate;//是否搬迁户
	private String relocatetype;//搬迁方式
	private String emplacetype;//安置方式
	private String relocateplace;//安置地
	private String relocatediff;//搬迁后可能存在的困难
	//就业情况和收入
	private int ifhasjob;
	private String job_householderid;
	private String job_poorpeopleid;
	private double total_income;//总收入

	//村镇转化
	private String groupname;//组名称
	private String sexName;//性别
//	private String havejob;//有无家人就业

	private int intpoor;//參加培训和
	private String  ifpeixun;//是否培训
	private String tpname;//脱贫情况
	private String groupno;// 所在组
	private String adl_cd;// 行政区划代码
	private String helpPersonName;//帮扶责任人名称
	private String location;//地址

	private String poorfamilypropert;// 贫困户属性
	private String poorfamilyproperttest;
	private String ifooperative;// 是否参与农民专业合作社
	private String farmland;// 耕地面积（亩）
	private String irrigation;// 有效灌溉面积（亩）
	private String woodland;// 林地面积（亩）
	private String reusefarmland;// 退耕还林面积（亩）
	private String timbertrees;// 林果面积（亩）
	private String distance;// 与村主干路距离（公里）
	private String productionpower;// 是否通生产用电
	private String fueltype;// 主要燃料类型

	private String housesecurity;// 房屋安全属性
	private String ifagreetomove;// 是否同意搬迁
	private String housingtype;// 房屋类型
	private String ifnoroom;// 是否无房
	private String pkhsxname;//贫困户属性
	private String housesecurityname;//房屋安全属性
	private String housingtypename;//房屋类型

	private String housingtypetext;
	private String housesecuritytext;
	private String fueltypetext;
	private String relocatetypetext;
	private String emplacetypetext;
	private String relocateplacetext;
	//就业、培训情况和收入
	private String job_info;
	private String zaixiao_info;
	private String jb1_info;
	private String jb2_info;
	private String jb3_info;
	private String canji_info;
	private String db_info;
	private String wb_info;
	private double year_income_perp;//人均年收入
	private double month_income_perp;//人均月收入
	private double housearea_perp;
	private double farmland_perp;

	//筛选条件
	private String housecount_start;
	private String housecount_end;
	private String income_start;
	private String income_end;
	private String housearea_start;
	private String housearea_end;
	private String farmland_start;
	private String farmland_end;
	private String irrigation_start;
	private String irrigation_end;
	private String woodland_start;
	private String woodland_end;
	private String reusefarmland_start;
	private String reusefarmland_end;
	private String timbertrees_start;
	private String timbertrees_end;
	private String grassland_start;
	private String grassland_end;
	private String waterland_start;
	private String waterland_end;
	private String distance_start;
	private String distance_end;
	private String housearea_perp_start;
	private String housearea_perp_end;
	private String farmland_perp_start;
	private String farmland_perp_end;

	private String povertystatustext;//   脱贫属性
//	private String helpplan;//   扶贫计划
//	private String pp_poorreason;//   致贫原因
//	private String se_poorreason;//   其他致贫原因
//	private int housecount;//   家庭人口
//	private String phone;//   联系电话
//	private String poorfamilyproperttest;// 贫困户属性
	private String ifjunlieshutext;//是否军烈属
//	private String location; //地址
//	private String helpplan;//  帮扶措施


	public String getPovertystatustext() {
		return povertystatustext;
	}

	public void setPovertystatustext(String povertystatustext) {
		this.povertystatustext = povertystatustext;
	}

	public String getIfjunlieshutext() {
		return ifjunlieshutext;
	}

	public void setIfjunlieshutext(String ifjunlieshutext) {
		this.ifjunlieshutext = ifjunlieshutext;
	}

	public String getSextext() {
		return sextext;
	}

	public void setSextext(String sextext) {
		this.sextext = sextext;
	}

	public String getPkhsxname() {
		return pkhsxname;
	}
	public void setPkhsxname(String pkhsxname) {
		this.pkhsxname = pkhsxname;
	}
	public String getHousesecurityname() {
		return housesecurityname;
	}
	public void setHousesecurityname(String housesecurityname) {
		this.housesecurityname = housesecurityname;
	}
	public String getHousingtypename() {
		return housingtypename;
	}
	public void setHousingtypename(String housingtypename) {
		this.housingtypename = housingtypename;
	}
	public String getPoorfamilypropert() {
		return poorfamilypropert;
	}
	public void setPoorfamilypropert(String poorfamilypropert) {
		this.poorfamilypropert = poorfamilypropert;
	}
	public String getPoorfamilyproperttest() {
		return poorfamilyproperttest;
	}
	public void setPoorfamilyproperttest(String poorfamilyproperttest) {
		this.poorfamilyproperttest = poorfamilyproperttest;
	}
	public String getIfooperative() {
		return ifooperative;
	}
	public void setIfooperative(String ifooperative) {
		this.ifooperative = ifooperative;
	}
	public String getFarmland() {
		return farmland;
	}
	public void setFarmland(String farmland) {
		this.farmland = farmland;
	}
	public String getIrrigation() {
		return irrigation;
	}
	public void setIrrigation(String irrigation) {
		this.irrigation = irrigation;
	}
	public String getWoodland() {
		return woodland;
	}
	public void setWoodland(String woodland) {
		this.woodland = woodland;
	}
	public String getReusefarmland() {
		return reusefarmland;
	}
	public void setReusefarmland(String reusefarmland) {
		this.reusefarmland = reusefarmland;
	}
	public String getTimbertrees() {
		return timbertrees;
	}
	public void setTimbertrees(String timbertrees) {
		this.timbertrees = timbertrees;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getProductionpower() {
		return productionpower;
	}
	public void setProductionpower(String productionpower) {
		this.productionpower = productionpower;
	}
	public String getFueltype() {
		return fueltype;
	}
	public void setFueltype(String fueltype) {
		this.fueltype = fueltype;
	}
	public String getHousesecurity() {
		return housesecurity;
	}
	public void setHousesecurity(String housesecurity) {
		this.housesecurity = housesecurity;
	}
	public String getIfagreetomove() {
		return ifagreetomove;
	}
	public void setIfagreetomove(String ifagreetomove) {
		this.ifagreetomove = ifagreetomove;
	}
	public String getHousingtype() {
		return housingtype;
	}
	public void setHousingtype(String housingtype) {
		this.housingtype = housingtype;
	}
	public String getIfnoroom() {
		return ifnoroom;
	}
	public void setIfnoroom(String ifnoroom) {
		this.ifnoroom = ifnoroom;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getHelpPersonName() {
		return helpPersonName;
	}
	public void setHelpPersonName(String helpPersonName) {
		this.helpPersonName = helpPersonName;
	}
	public String getGroupno() {
		return groupno;
	}
	public void setGroupno(String groupno) {
		this.groupno = groupno;
	}
	public String getAdl_cd() {
		return adl_cd;
	}
	public void setAdl_cd(String adl_cd) {
		this.adl_cd = adl_cd;
	}
	public String getTpname() {
		return tpname;
	}
	public void setTpname(String tpname) {
		this.tpname = tpname;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public int getIntpoor() {
		return intpoor;
	}
	public void setIntpoor(int intpoor) {
		this.intpoor = intpoor;
	}
	public String getIfpeixun() {
		return ifpeixun;
	}
	public void setIfpeixun(String ifpeixun) {
		this.ifpeixun = ifpeixun;
	}
	public List<DutyPerson> getDutyPerson() {
		return this.dutyPerson;
	}
	public void setDutyPerson(List<DutyPerson> dutyPerson) {
		this.dutyPerson = dutyPerson;
	}

	public String getSexName() {
		return this.sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getGroupname() {
		return this.groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getMap_id() {
		return this.map_id;
	}
	public void setMap_id(String map_id) {
		this.map_id = map_id;
	}
	public String getMap_householderid() {
		return this.map_householderid;
	}
	public void setMap_householderid(String map_householderid) {
		this.map_householderid = map_householderid;
	}
	public String getHelpdutypersonid() {
		return this.helpdutypersonid;
	}
	public void setHelpdutypersonid(String helpdutypersonid) {
		this.helpdutypersonid = helpdutypersonid;
	}
	public String getHouseholdername() {
		return this.householdername;
	}
	public void setHouseholdername(String householdername) {
		this.householdername = householdername;
	}
	public int getIfhasjob() {
		return this.ifhasjob;
	}
	public void setIfhasjob(int ifhasjob) {
		this.ifhasjob = ifhasjob;
	}
	public String getJob_poorpeopleid() {
		return this.job_poorpeopleid;
	}
	public void setJob_poorpeopleid(String job_poorpeopleid) {
		this.job_poorpeopleid = job_poorpeopleid;
	}
	public double getTotal_income() {
		return this.total_income;
	}
	public void setTotal_income(double total_income) {
		this.total_income = total_income;
	}
	public String getIncome_start() {
		return this.income_start;
	}
	public void setIncome_start(String income_start) {
		this.income_start = income_start;
	}
	public String getIncome_end() {
		return this.income_end;
	}
	public void setIncome_end(String income_end) {
		this.income_end = income_end;
	}
	public String getJob_householderid() {
		return this.job_householderid;
	}
	public void setJob_householderid(String job_householderid) {
		this.job_householderid = job_householderid;
	}
	public String getHouseholderid() {
		return this.householderid;
	}
	public void setHouseholderid(String householderid) {
		this.householderid = householderid;
	}
	public String getPovertystatusid() {
		return this.povertystatusid;
	}
	public void setPovertystatusid(String povertystatusid) {
		this.povertystatusid = povertystatusid;
	}
	public String getHelpplan() {
		return this.helpplan;
	}
	public void setHelpplan(String helpplan) {
		this.helpplan = helpplan;
	}
	public int getHousecount() {
		return this.housecount;
	}
	public void setHousecount(int housecount) {
		this.housecount = housecount;
	}
	public String getPhone() {
		return this.phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHousearea() {
		return this.housearea;
	}
	public void setHousearea(String housearea) {
		this.housearea = housearea;
	}
	public String getIselectricity() {
		return this.iselectricity;
	}
	public void setIselectricity(String iselectricity) {
		this.iselectricity = iselectricity;
	}
	public String getWatertrouble() {
		return this.watertrouble;
	}
	public void setWatertrouble(String watertrouble) {
		this.watertrouble = watertrouble;
	}
	public String getWatersafe() {
		return this.watersafe;
	}
	public void setWatersafe(String watersafe) {
		this.watersafe = watersafe;
	}
	public double getDibao() {
		return this.dibao;
	}
	public void setDibao(double dibao) {
		this.dibao = dibao;
	}
	public double getYanglao() {
		return this.yanglao;
	}
	public void setYanglao(double yanglao) {
		this.yanglao = yanglao;
	}
	public double getXinnonghe() {
		return this.xinnonghe;
	}
	public void setXinnonghe(double xinnonghe) {
		this.xinnonghe = xinnonghe;
	}
	public double getHuanlin() {
		return this.huanlin;
	}
	public void setHuanlin(double huanlin) {
		this.huanlin = huanlin;
	}
	public double getShengtailin() {
		return this.shengtailin;
	}
	public void setShengtailin(double shengtailin) {
		this.shengtailin = shengtailin;
	}
	public double getJiaoyu() {
		return this.jiaoyu;
	}
	public void setJiaoyu(double jiaoyu) {
		this.jiaoyu = jiaoyu;
	}
	public double getLiangshi() {
		return this.liangshi;
	}
	public void setLiangshi(double liangshi) {
		this.liangshi = liangshi;
	}
	public double getYangzhi() {
		return this.yangzhi;
	}
	public void setYangzhi(double yangzhi) {
		this.yangzhi = yangzhi;
	}
	public double getGuopin() {
		return this.guopin;
	}
	public void setGuopin(double guopin) {
		this.guopin = guopin;
	}
	public double getShumu() {
		return this.shumu;
	}
	public void setShumu(double shumu) {
		this.shumu = shumu;
	}
	public double getJiuye() {
		return this.jiuye;
	}
	public void setJiuye(double jiuye) {
		this.jiuye = jiuye;
	}
	public double getLinggong() {
		return this.linggong;
	}
	public void setLinggong(double linggong) {
		this.linggong = linggong;
	}
	public double getJuanzeng() {
		return this.juanzeng;
	}
	public void setJuanzeng(double juanzeng) {
		this.juanzeng = juanzeng;
	}
	public double getWeiwen() {
		return this.weiwen;
	}
	public void setWeiwen(double weiwen) {
		this.weiwen = weiwen;
	}
	public double getQita() {
		return this.qita;
	}
	public void setQita(double qita) {
		this.qita = qita;
	}

	public int getJyrs() {
		return this.jyrs;
	}
	public void setJyrs(int jyrs) {
		this.jyrs = jyrs;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getP_householderid() {
		return this.p_householderid;
	}
	public void setP_householderid(String p_householderid) {
		this.p_householderid = p_householderid;
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
	public String getBirthday() {
		return this.birthday;
	}
	public void setBirthday(String birthday) {
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
		return this.pp_poorreason;
	}
	public void setPp_poorreason(String pp_poorreason) {
		this.pp_poorreason = pp_poorreason;
	}
	public String getPp_helpplan() {
		return this.pp_helpplan;
	}
	public void setPp_helpplan(String pp_helpplan) {
		this.pp_helpplan = pp_helpplan;
	}
	public String getIfwanttrained() {
		return this.ifwanttrained;
	}
	public void setIfwanttrained(String ifwanttrained) {
		this.ifwanttrained = ifwanttrained;
	}
	public String getPersoninfo() {
		return this.personinfo;
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
	public String getSe_poorreason() {
		return se_poorreason;
	}
	public void setSe_poorreason(String se_poorreason) {
		this.se_poorreason = se_poorreason;
	}
	public String getIfjunlieshu() {
		return ifjunlieshu;
	}
	public void setIfjunlieshu(String ifjunlieshu) {
		this.ifjunlieshu = ifjunlieshu;
	}
	public String getGrassland() {
		return grassland;
	}
	public void setGrassland(String grassland) {
		this.grassland = grassland;
	}
	public String getWaterland() {
		return waterland;
	}
	public void setWaterland(String waterland) {
		this.waterland = waterland;
	}
	public String getIfrelocate() {
		return ifrelocate;
	}
	public void setIfrelocate(String ifrelocate) {
		this.ifrelocate = ifrelocate;
	}
	public String getRelocatetype() {
		return relocatetype;
	}
	public void setRelocatetype(String relocatetype) {
		this.relocatetype = relocatetype;
	}
	public String getEmplacetype() {
		return emplacetype;
	}
	public void setEmplacetype(String emplacetype) {
		this.emplacetype = emplacetype;
	}
	public String getRelocateplace() {
		return relocateplace;
	}
	public void setRelocateplace(String relocateplace) {
		this.relocateplace = relocateplace;
	}
	public String getRelocatediff() {
		return relocatediff;
	}
	public void setRelocatediff(String relocatediff) {
		this.relocatediff = relocatediff;
	}
	public String getHousingtypetext() {
		return housingtypetext;
	}
	public void setHousingtypetext(String housingtypetext) {
		this.housingtypetext = housingtypetext;
	}
	public String getHousesecuritytext() {
		return housesecuritytext;
	}
	public void setHousesecuritytext(String housesecuritytext) {
		this.housesecuritytext = housesecuritytext;
	}
	public String getFueltypetext() {
		return fueltypetext;
	}
	public void setFueltypetext(String fueltypetext) {
		this.fueltypetext = fueltypetext;
	}
	public String getRelocatetypetext() {
		return relocatetypetext;
	}
	public void setRelocatetypetext(String relocatetypetext) {
		this.relocatetypetext = relocatetypetext;
	}
	public String getEmplacetypetext() {
		return emplacetypetext;
	}
	public void setEmplacetypetext(String emplacetypetext) {
		this.emplacetypetext = emplacetypetext;
	}
	public String getRelocateplacetext() {
		return relocateplacetext;
	}
	public void setRelocateplacetext(String relocateplacetext) {
		this.relocateplacetext = relocateplacetext;
	}
	public String getJob_info() {
		return job_info;
	}
	public void setJob_info(String job_info) {
		this.job_info = job_info;
	}
	public String getZaixiao_info() {
		return zaixiao_info;
	}
	public void setZaixiao_info(String zaixiao_info) {
		this.zaixiao_info = zaixiao_info;
	}
	public String getJb1_info() {
		return jb1_info;
	}
	public void setJb1_info(String jb1_info) {
		this.jb1_info = jb1_info;
	}
	public String getJb2_info() {
		return jb2_info;
	}
	public void setJb2_info(String jb2_info) {
		this.jb2_info = jb2_info;
	}
	public String getJb3_info() {
		return jb3_info;
	}
	public void setJb3_info(String jb3_info) {
		this.jb3_info = jb3_info;
	}
	public String getCanji_info() {
		return canji_info;
	}
	public void setCanji_info(String canji_info) {
		this.canji_info = canji_info;
	}
	public String getDb_info() {
		return db_info;
	}
	public void setDb_info(String db_info) {
		this.db_info = db_info;
	}
	public String getWb_info() {
		return wb_info;
	}
	public void setWb_info(String wb_info) {
		this.wb_info = wb_info;
	}
	public double getYear_income_perp() {
		return year_income_perp;
	}
	public void setYear_income_perp(double year_income_perp) {
		this.year_income_perp = year_income_perp;
	}
	public double getMonth_income_perp() {
		return month_income_perp;
	}
	public void setMonth_income_perp(double month_income_perp) {
		this.month_income_perp = month_income_perp;
	}
	public double getHousearea_perp() {
		return housearea_perp;
	}
	public void setHousearea_perp(double housearea_perp) {
		this.housearea_perp = housearea_perp;
	}
	public double getFarmland_perp() {
		return farmland_perp;
	}
	public void setFarmland_perp(double farmland_perp) {
		this.farmland_perp = farmland_perp;
	}
	public String getHousecount_start() {
		return housecount_start;
	}
	public void setHousecount_start(String housecount_start) {
		this.housecount_start = housecount_start;
	}
	public String getHousecount_end() {
		return housecount_end;
	}
	public void setHousecount_end(String housecount_end) {
		this.housecount_end = housecount_end;
	}
	public String getHousearea_start() {
		return housearea_start;
	}
	public void setHousearea_start(String housearea_start) {
		this.housearea_start = housearea_start;
	}
	public String getHousearea_end() {
		return housearea_end;
	}
	public void setHousearea_end(String housearea_end) {
		this.housearea_end = housearea_end;
	}
	public String getFarmland_start() {
		return farmland_start;
	}
	public void setFarmland_start(String farmland_start) {
		this.farmland_start = farmland_start;
	}
	public String getFarmland_end() {
		return farmland_end;
	}
	public void setFarmland_end(String farmland_end) {
		this.farmland_end = farmland_end;
	}
	public String getIrrigation_start() {
		return irrigation_start;
	}
	public void setIrrigation_start(String irrigation_start) {
		this.irrigation_start = irrigation_start;
	}
	public String getIrrigation_end() {
		return irrigation_end;
	}
	public void setIrrigation_end(String irrigation_end) {
		this.irrigation_end = irrigation_end;
	}
	public String getWoodland_start() {
		return woodland_start;
	}
	public void setWoodland_start(String woodland_start) {
		this.woodland_start = woodland_start;
	}
	public String getWoodland_end() {
		return woodland_end;
	}
	public void setWoodland_end(String woodland_end) {
		this.woodland_end = woodland_end;
	}
	public String getReusefarmland_start() {
		return reusefarmland_start;
	}
	public void setReusefarmland_start(String reusefarmland_start) {
		this.reusefarmland_start = reusefarmland_start;
	}
	public String getReusefarmland_end() {
		return reusefarmland_end;
	}
	public void setReusefarmland_end(String reusefarmland_end) {
		this.reusefarmland_end = reusefarmland_end;
	}
	public String getTimbertrees_start() {
		return timbertrees_start;
	}
	public void setTimbertrees_start(String timbertrees_start) {
		this.timbertrees_start = timbertrees_start;
	}
	public String getTimbertrees_end() {
		return timbertrees_end;
	}
	public void setTimbertrees_end(String timbertrees_end) {
		this.timbertrees_end = timbertrees_end;
	}
	public String getGrassland_start() {
		return grassland_start;
	}
	public void setGrassland_start(String grassland_start) {
		this.grassland_start = grassland_start;
	}
	public String getGrassland_end() {
		return grassland_end;
	}
	public void setGrassland_end(String grassland_end) {
		this.grassland_end = grassland_end;
	}
	public String getWaterland_start() {
		return waterland_start;
	}
	public void setWaterland_start(String waterland_start) {
		this.waterland_start = waterland_start;
	}
	public String getWaterland_end() {
		return waterland_end;
	}
	public void setWaterland_end(String waterland_end) {
		this.waterland_end = waterland_end;
	}
	public String getDistance_start() {
		return distance_start;
	}
	public void setDistance_start(String distance_start) {
		this.distance_start = distance_start;
	}
	public String getDistance_end() {
		return distance_end;
	}
	public void setDistance_end(String distance_end) {
		this.distance_end = distance_end;
	}
	public String getHousearea_perp_start() {
		return housearea_perp_start;
	}
	public void setHousearea_perp_start(String housearea_perp_start) {
		this.housearea_perp_start = housearea_perp_start;
	}
	public String getHousearea_perp_end() {
		return housearea_perp_end;
	}
	public void setHousearea_perp_end(String housearea_perp_end) {
		this.housearea_perp_end = housearea_perp_end;
	}
	public String getFarmland_perp_start() {
		return farmland_perp_start;
	}
	public void setFarmland_perp_start(String farmland_perp_start) {
		this.farmland_perp_start = farmland_perp_start;
	}
	public String getFarmland_perp_end() {
		return farmland_perp_end;
	}
	public void setFarmland_perp_end(String farmland_perp_end) {
		this.farmland_perp_end = farmland_perp_end;
	}
}
