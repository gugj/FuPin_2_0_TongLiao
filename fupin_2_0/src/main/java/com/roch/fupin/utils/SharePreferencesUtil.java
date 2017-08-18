package com.roch.fupin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.roch.fupin.entity.AdlCode;
import com.roch.fupin.entity.Sroles;
import com.roch.fupin.entity.User;

/**
 * Sharepreferences 工具
 * @author zds
 */
public class SharePreferencesUtil {
	
	private static final String CONFIG = "config";
	// 登录用户
	// private static final String LOGIN = "login";
	// 权限
	private static final String SROLES = "sroles";
	// 当前城市名字
	public static final String NOW_CITY = "now_city";
	// 是否显示引导页
	public static final String GUIDE_FLAG = "sp_config";
	// 历史搜索记录
	public static final String SEARCH_HISTORY = "history";
	// 服务器地址
	public static final String SERVER_ADDRESS = "server";

	public static final String LOCTION = "loction";
	
	/**
	 * 保存登陆时的用户名和密码
	 * @param context
	 * @param loginName
	 * @param loginPassword
	 * @param checked
	 * 2016年11月8日
	 */
	public void saveNameAndPassword(Context context,String loginName,String loginPassword,boolean checked){
		SharedPreferences sp = context.getSharedPreferences(Common.SP_NameAndPassword, Context.MODE_APPEND);
		Editor ed = sp.edit();

		ed.putString("loginName", loginName);
		ed.putString("loginPassword", loginPassword);
		ed.putBoolean("isChecked", checked);
		ed.commit();
		System.out.println("保存成功用户名和密码！");
	}
	
	/**
	 * 获取保存的登陆时的用户名
	 * @param context
	 * @return
	 * 2016年11月8日
	 */
	public String getLonginName(Context context){
		SharedPreferences sp = context.getSharedPreferences(Common.SP_NameAndPassword, Context.MODE_APPEND);
		return sp.getString("loginName", "");
	}
	
	/**
	 * 获取保存的登陆时的密码
	 * @param context
	 * @return
	 * 2016年11月8日
	 */
	public String getLonginPassword(Context context){
		SharedPreferences sp = context.getSharedPreferences(Common.SP_NameAndPassword, Context.MODE_APPEND);
		return sp.getString("loginPassword", "");
	}
	
	/**
	 * 获取保存的登陆时是否保存登陆用户名和密码
	 * @param context
	 * @return
	 * 2016年11月8日
	 */
	public boolean getLonginChecked(Context context){
		SharedPreferences sp = context.getSharedPreferences(Common.SP_NameAndPassword, Context.MODE_APPEND);
		return sp.getBoolean("isChecked", false);
	}

	// ---------------登录用户------------------------
	/**
	 * 保存登陆用户---根据登陆用户名获取登陆用户信息，并存到sp中
	 * @param context
	 * @param login
	 * @return
	 */
	public void saveLoginUser(Context context, User login) {
		SharedPreferences sp = context.getSharedPreferences(login.getLoginname(), Context.MODE_APPEND);
		Editor ed = sp.edit();
		ed.putString(Common.USER_ID, login.getId()); // id
		ed.putString(Common.USER_LOGINNAME, login.getLoginname());
		ed.putString(Common.USER_USERNAME, login.getUser_name()); // 人员姓名
		ed.putString(Common.USER_PASSWORD, login.getPassword());// 密码
		ed.putString(Common.USER_EMAIL, login.getEmail()); // 邮箱
		ed.putString(Common.USER_CODE, login.getUser_code()); // 人员代码
		ed.putString(Common.USER_DEP_CODE, login.getDep_code()); // 所属部门编码
		ed.putString(Common.USER_DUTY_LEVEL, login.getDuty_level()); // 职务级别
		ed.putString(Common.USER_SEX, login.getSex()); // 性别
		ed.putString(Common.USER_BIRTHDAY, login.getBirthday()); // 生日
		ed.putString(Common.USER_HIGHESTDEGREE, login.getHighestdegree()); // 学历
		ed.putString(Common.USER_TITLE, login.getTitle()); // 技术职称
		ed.putString(Common.USER_TELNUMB, login.getTelnumb());// 电话
		ed.putString(Common.USER_MOBLENUMB, login.getMoblenumb());// 手机
		ed.putString(Common.USER_DEPNAME, login.getDepName());// 部门名称

		ed.putString(Common.USER_MTIME, login.getMtime()); // 时间戳
		ed.putString(Common.USER_NODE, login.getNote()); // 备注
		ed.putString(Common.USER_ORDERNUM, login.getOrdernum());
		ed.putString(Common.USER_IFDEL, login.getIfdel());
		ed.putString(Common.USER_JOBIDS, login.getJobids());
		ed.putString(Common.USER_ROLEIDS, login.getRoleids());
		ed.putString(Common.USER_ROLEIDS_APP, login.getRoleids_app());
		ed.putString(Common.USER_SEXNAME, login.getSexName()); // 性别
		ed.putString(Common.USER_DUTYLEVELNAME, login.getDutyLevelName());
		ed.putString(Common.USER_HIGHESTDEGREENAME, login.getHighestdegreeName()); // 学历
		ed.putString(Common.USER_ROLENAME, login.getRolename()); // 技术职称
		ed.putString(Common.USER_ROLENAME_APP, login.getRolename_app());
		ed.putString(Common.USER_COUNTRYNAME, login.getCountryName());// 县级市
		ed.putString(Common.USER_CITYNAME, login.getCityName());// 城市
		ed.putString(Common.USER_TOWNNAME, login.getTownName());// 乡镇
		ed.putString(Common.USER_VILLAGENAME, login.getVillageName());// 村
		ed.putString(Common.USER_ORGCODE, login.getOrg_code());
		ed.putString(Common.USER_ADLCD, login.getAdl_cd());
		ed.putString(Common.USER_ADLNAME, login.getAdl_nm());
		ed.commit();
	}

	/**
	 * 获取登陆用户---根据登陆用户名获取登陆用户信息，并存到sp中，此方法再从sp中获取到该登陆用户
	 * @param context
	 * @param username
	 * @return
	 */
	public User getLoginUser(Context context, String username) {
		SharedPreferences sp = context.getSharedPreferences(username, Context.MODE_APPEND);
		User login = new User();
		login.setId(sp.getString(Common.USER_ID, login.getId()));
		login.setUser_name(sp.getString(Common.USER_USERNAME, login.getUser_name()));
		login.setLoginname(sp.getString(Common.USER_LOGINNAME, login.getLoginname()));
		login.setPassword(sp.getString(Common.USER_PASSWORD, login.getPassword()));
		login.setEmail(sp.getString(Common.USER_EMAIL, login.getEmail()));
		login.setUser_code(sp.getString(Common.USER_CODE, login.getUser_code()));
		login.setDep_code(sp.getString(Common.USER_DEP_CODE, login.getDep_code()));
		login.setDuty_level(sp.getString(Common.USER_DUTY_LEVEL, login.getDuty_level()));
		login.setSex(sp.getString(Common.USER_SEX, login.getSex()));
		login.setBirthday(sp.getString(Common.USER_BIRTHDAY, login.getBirthday()));
		login.setHighestdegree(sp.getString(Common.USER_HIGHESTDEGREE, login.getHighestdegree()));
		login.setTitle(sp.getString(Common.USER_TITLE, login.getTitle()));
		login.setTelnumb(sp.getString(Common.USER_TELNUMB, login.getTelnumb()));
		login.setMoblenumb(sp.getString(Common.USER_MOBLENUMB, login.getMoblenumb()));
		login.setDepName(sp.getString(Common.USER_DEPNAME, login.getDepName()));

		login.setMtime(sp.getString(Common.USER_MTIME, login.getMtime())); // 时间戳
		login.setNote(sp.getString(Common.USER_NODE, login.getNote())); // 备注
		login.setOrdernum(sp.getString(Common.USER_ORDERNUM, login.getOrdernum()));
		login.setIfdel(sp.getString(Common.USER_IFDEL, login.getIfdel()));
		login.setJobids(sp.getString(Common.USER_JOBIDS, login.getJobids()));
		login.setRoleids(sp.getString(Common.USER_ROLEIDS, login.getRoleids()));
		login.setRoleids_app(sp.getString(Common.USER_ROLEIDS_APP, login.getRoleids_app()));
		login.setSexName(sp.getString(Common.USER_SEXNAME, login.getSexName())); // 性别
		login.setDutyLevelName(sp.getString(Common.USER_DUTYLEVELNAME, login.getDutyLevelName()));
		login.setHighestdegreeName(sp.getString(Common.USER_HIGHESTDEGREENAME, login.getHighestdegreeName())); // 学历
		login.setRolename(sp.getString(Common.USER_ROLENAME, login.getRolename())); // 技术职称
		login.setRolename_app(sp.getString(Common.USER_ROLENAME_APP, login.getRolename_app()));
		login.setCountryName(sp.getString(Common.USER_COUNTRYNAME, login.getCountryName()));// 县级市
		login.setCityname(sp.getString(Common.USER_CITYNAME, login.getCityName()));// 城市
		login.setTownName(sp.getString(Common.USER_TOWNNAME, login.getTownName()));// 乡镇
		login.setVillageName(sp.getString(Common.USER_VILLAGENAME, login.getVillageName()));// 村
		login.setAdl_cd(sp.getString(Common.USER_ADLCD, login.getAdl_cd())); //-------------------adl_cd--------------------
		login.setOrg_code(sp.getString(Common.USER_ORGCODE, login.getOrg_code()));
		login.setAdl_nm(sp.getString(Common.USER_ADLNAME, login.getAdl_nm()));
		return login;
	}

	/**
	 * 清空登录信息
	 * @param context
	 * @param loginname 用户名
	 * 2016年6月2日 ZhaoDongShao
	 */
	public void clearLoginUser(Context context, String loginname) {
		SharedPreferences sp = context.getSharedPreferences(loginname, Context.MODE_PRIVATE);
		Editor ed = sp.edit();
		ed.clear();
		ed.commit();
	}

	// ----------当前城市-------------
	public void saveNowCity(Context context, AdlCode str, String loginname) {
		SharedPreferences sp = context.getSharedPreferences(loginname + "_" + NOW_CITY, Context.MODE_PRIVATE);
		Editor ed = sp.edit();
		ed.putString(Common.CITY_AD_CD, str.getAd_cd());
		ed.putString(Common.CITY_AD_NM, str.getAd_nm());
		ed.putString(Common.CITY_NT, str.getNt());
		ed.putString(Common.CITY_ORDERS, str.getOrders());
		ed.putString(Common.CITY_PINYI, str.getPinyi());
		ed.putString(Common.CITY_TS, str.getTs());
		ed.commit();
	}

	/**
	 * 获取当前选择的行政区代码
	 * @param context    上下文
	 * @param loginname  登陆的城市名
	 * @return 返回行政区城市对象
	 * 2016年10月27日
	 */
	public AdlCode getNowCity(Context context, String loginname) {
		SharedPreferences sp = context.getSharedPreferences(loginname + "_" + NOW_CITY, Context.MODE_PRIVATE);
		AdlCode code = new AdlCode();
		code.setAd_cd(sp.getString(Common.CITY_AD_CD, code.getAd_cd()));
		code.setAd_nm(sp.getString(Common.CITY_AD_NM, code.getAd_nm()));
		code.setNt(sp.getString(Common.CITY_NT, code.getNt()));
		code.setOrders(sp.getString(Common.CITY_ORDERS, code.getOrders()));
		code.setPinyi(sp.getString(Common.CITY_PINYI, code.getPinyi()));
		code.setTs(sp.getString(Common.CITY_TS, code.getTs()));
		return code;
	}

	// ----------当前时间-------------
	public static void saveIsShowGuide(Context context, int isUpdate) {
		SharedPreferences sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
		Editor ed = sp.edit();
		ed.putInt(GUIDE_FLAG, isUpdate);
		ed.commit();
	}

	/**
	 * 是否第一次运行
	 * @param context
	 * @return
	 */
	public static int getGuide(Context context) {
		SharedPreferences sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
		return sp.getInt(GUIDE_FLAG, -1);
	}

	/**
	 * 保存历史搜索记录
	 * @param msg
	 */
	public static void saveHistory(String msg, Context context) {
		SharedPreferences sp = context.getSharedPreferences(SEARCH_HISTORY, Context.MODE_APPEND);
		Editor editor = sp.edit();
		editor.putString("history", msg);
		editor.commit();
	}

	/**
	 * 获取历史记录
	 * @param context
	 * @return
	 */
	public static String getHistory(Context context) {
		SharedPreferences sp = context.getSharedPreferences(SEARCH_HISTORY, Context.MODE_PRIVATE);
		return sp.getString("history", "");
	}

	/**
	 * 保存服务地址
	 * @param serveraddress
	 * @param context
	 */
	public static void saveServerAddress(String serveraddress, Context context) {
		SharedPreferences sp = context.getSharedPreferences(SERVER_ADDRESS, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("server", serveraddress);
		editor.commit();
	}

	/**
	 * 获取服务地址
	 * @param context
	 */
	public static String getServerAddress(Context context) {
		SharedPreferences sp = context.getSharedPreferences(SERVER_ADDRESS, Context.MODE_PRIVATE);
		return sp.getString("server", "");
	}

	/**
	 * 将登陆用户的权限保存到sp中
	 * @param
	 * @param sroles
	 * 2016年7月2日  ZhaoDongShao
	 */
	public void saveSroles(Context context, Sroles sroles) {
		SharedPreferences sp = context.getSharedPreferences(SROLES, Context.MODE_APPEND);
		Editor ed = sp.edit();
		ed.putString(Common.SROLES_createdate, sroles.getCreatedate());
		ed.putString(Common.SROLES_departmentid, sroles.getDepartmentid());
		ed.putString(Common.SROLES_descript, sroles.getDescript());
		ed.putString(Common.SROLES_id, sroles.getId());
		ed.putString(Common.SROLES_name, sroles.getName());
		ed.putInt(Common.SROLES_pdatafilter, sroles.getPdatafilter()); // 1可以选行政区、0不能选
		ed.putString(Common.SROLES_oid, sroles.getOid());
		ed.putString(Common.SROLES_systemtype, sroles.getSystemtype());
		ed.commit();
	}

	/**
	 * 获取权限信息
	 * @return
	 * 2016年7月2日  ZhaoDongShao
	 */
	public Sroles getSroles(Context context) {
		SharedPreferences sp = context.getSharedPreferences(SROLES, Context.MODE_APPEND);
		Sroles sroles = new Sroles();
		sroles.setCreatedate(sp.getString(Common.SROLES_createdate, sroles.getCreatedate()));
		sroles.setDepartmentid(sp.getString(Common.SROLES_departmentid, sroles.getDepartmentid()));
		sroles.setDescript(sp.getString(Common.SROLES_descript, sroles.getDescript()));
		sroles.setId(sp.getString(Common.SROLES_id, sroles.getId()));
		sroles.setName(sp.getString(Common.SROLES_name, sroles.getName()));
		sroles.setOid(sp.getString(Common.SROLES_oid, sroles.getOid()));
		sroles.setSystemtype(sp.getString(Common.SROLES_systemtype, sroles.getSystemtype()));
		sroles.setPdatafilter(sp.getInt(Common.SROLES_pdatafilter, sroles.getPdatafilter()));
		return sroles;
	}

	/**
	 * 保存定位标识
	 * 2016年8月8日
	 * ZhaoDongShao
	 */
	public void saveLoction(Context context, boolean loction) {
		SharedPreferences sp = context.getSharedPreferences(LOCTION, Context.MODE_APPEND);
		Editor ed = sp.edit();
		ed.putBoolean(Common.LOCTION, loction);
		ed.commit();
	}

	/**
	 * 获取是否已经定位
	 * @param context
	 * @return
	 * 2016年8月8日  ZhaoDongShao
	 */
	public boolean getisLoction(Context context) {
		SharedPreferences sp = context.getSharedPreferences(LOCTION, Context.MODE_APPEND);
		return sp.getBoolean(Common.LOCTION, false);
	}
}
