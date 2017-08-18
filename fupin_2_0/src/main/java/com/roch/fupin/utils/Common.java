package com.roch.fupin.utils;

import android.os.Environment;

import java.util.UUID;

/**
 * 常量类，保存了各种数据的常量
 */
public class Common {

	/**
	 * 登陆页面在登陆成功时输入的登陆用户名，例如admin
	 */
	public static String LoginName = "";
	
	/**
	 * 保存登陆界面输入的登陆用户名和密码的sp的名字
	 */
	public static String SP_NameAndPassword = "SP_NameAndPassword";

	public static int TEXT_SIZE = 15;

	/**
	 * 定位action
	 */
	public static final String ADRESS_ACTION = "com.roch.action.address";
	/**
	 * 土地信息
	 */
	public static final String KEY_TUDI = "tudi";

	/**
	 * 标题
	 */
	public static final String TITLE_KEY = "title";

	/**
	 * Bundle传参key
	 */
	public static final String BUNDEL_KEY = "bundle_key";

	/**
	 * Bundle传参筛选条件key
	 */
	public static final String BUNDEL_FILTER_KEY_1 = "bundle_filter_key_1";
	public static final String BUNDEL_FILTER_KEY_2 = "bundle_filter_key_2";

	/**
	 * Intent传参key
	 */
	public static final String INTENT_KEY = "intent_key";
	public static final String UP_LOAD_PHOTO_KEY = "image_url";

	/**
	 * 查询条件提示
	 */
	public static final String TEXT_HINI = "text_hini";

	/**
	 * 登陆失效
	 */
	public static final int LOGIN_FILE = -2;

	/**
	 * 致贫原因
	 */
	public static final String EXTS_ZPYY = "pp_poorreason";

	/**
	 * 帮扶措施
	 */
	public static final String EXTS_BFCS = "pp_helpplan";

	/**
	 * 帮扶主体
	 */
	public static final int EXTS_HELP_SUBJECT = 1;

	/**
	 * 帮扶主体name
	 */
	public static final String EXTS_HELP_SUBJECT_NAME = "帮扶主体";

	/**
	 * 帮扶责任人
	 */
	public static final String EXTS_HELP_SUBJECT_PROPLE_NAME = "帮扶责任人";

	/**
	 * 帮扶单位
	 */
	public static final String EXTS_HELP_SUBJECT_COMPANY_NAME = "帮扶单位";

	/**
	 * 扶贫对象
	 */
	public static final int EXTS_HELP_OBJECT = 2;

	/**
	 * 扶贫对象name
	 */
	public static final String EXTS_HELP_OBJECT_NAME = "扶贫对象";

	/**
	 * 贫困村
	 */
	public static final String EXTS_HELP_OBJECT_VILLAGE_NAME = "贫困村";

	/**
	 * 贫困户
	 */
	public static final String EXTS_HELP_OBJECT_FAMILY_NAME = "贫困户";

	/**
	 * 通知公告name
	 */
	public static final String EXTS_NOTIC_NAME = "通知公告";

	/**
	 * 
	 */
	public static final int EXTS_NOTIC = 4;

	/**
	 * 公告栏
	 */
	public static final String EXTS_NOTIC_BOARD_NAME = "公告栏";

	/**
	 * 信息宣传
	 */
	public static final String EXTS_NOTIC_INFOMATION_NAME = "信息宣传";

	/**
	 * 脱贫攻坚项目
	 */
	public static final int EXTS_NO_POOR_PROJECT = 3;

	/**
	 * 脱贫攻坚项目name
	 */
	public static final String EXTS_NO_POOR_PROJECT_NAME = "脱贫攻坚项目";

	/**
	 * 水利局
	 */
	public static final String EXTS_NO_POOR_PROJECT_WATER = "水利局";

	/**
	 * 交通局
	 */
	public static final String EXTS_NO_POOR_PROJECT_JAOTONG = "交通局";

	/**
	 * 新村办
	 */
	public static final String EXTS_NO_POOR_PROJECT_XINCUNBAN = "新村办";

	/**
	 * 民政局
	 */
	public static final String EXTS_NO_POOR_PROJECT_MINZHENGJU = "民政局";
	/**
	 * 农牧业局
	 */
	public static final String EXTS_NO_POOR_PROJECT_NONGWEI = "农牧业局";
	/**
	 * 教体局
	 */
	public static final String EXTS_NO_POOR_PROJECT_JIAOTIJU = "教体局";
	/**
	 * 残联
	 */
	public static final String EXTS_NO_POOR_PROJECT_CANLIAN = "残联";
	/**
	 * 危房改造
	 */
	public static final String EXTS_NO_POOR_PROJECT_CANLIAN_WFGZ = "危房改造";
	/**
	 * 就业培训
	 */
	public static final String EXTS_NO_POOR_PROJECT_CANLIAN_JYPX = "就业培训";
	/**
	 * 妇联
	 */
	public static final String EXTS_NO_POOR_PROJECT_FULIAN = "妇联";
	/**
	 * 进度提醒
	 */
	public static final String EXTS_NO_POOR_PROJECT_JINDUTIXING = "进度提醒";
	/**
	 * 财政局
	 */
	public static final String EXTS_NO_POOR_PROJECT_CAIZHENGJU = "财政局";
	/**
	 * 住建局
	 */
	public static final String EXTS_NO_POOR_PROJECT_ZHUJIANJU = "住建局";
	/**
	 * 卫计委
	 */
	public static final String EXTS_NO_POOR_PROJECT_WEIJIWEI = "卫计委";
	/**
	 * 人劳局
	 */
	public static final String EXTS_NO_POOR_PROJECT_RENLAO = "人劳局";
	/**
	 * 扶贫办
	 */
	public static final String EXTS_NO_POOR_PROJECT_FUPUNBAN = "扶贫办";
	/**
	 * 林业局
	 */
	public static final String EXTS_NO_POOR_PROJECT_LINYEJU = "林业局";
	/**
	 * 道路绿化
	 */
	public static final String EXTS_NO_POOR_PROJECT_LINYEJU_DLLH = "道路绿化";
	/**
	 * 林下经济
	 */
	public static final String EXTS_NO_POOR_PROJECT_LINYEJU_LXJJ = "林下经济";
	/**
	 * 更多
	 */
	public static final int EXTS_MORE = 0;
	/**
	 * 项目详情
	 */
	public static final String PROJECT_DETAIL = "项目详情";
	/**
	 * 更多Name
	 */
	public static final String EXTS_MORE_NAME = "更多";

	/**
	 * 数据分析
	 */
	public static final String EXTS_STATISTIC = "数据分析";
	/**
	 * 贫困人口统计报表
	 */
	public static final String EXTS_STATISTIC_POOR_PEOPLE = "贫困人口统计";
	/**
	 * 文化程度
	 */
	public static final String EXTS_WHCD = "文化程度";
	/**
	 * 致贫原因信息
	 */
	public static final String EXTS_POOR_CASE = "致贫原因统计";
	/**
	 * 年龄
	 */
	public static final String EXTS_AGE = "年龄构成统计";
	/**
	 * 就业收入
	 */
	public static final String EXTS_WORK = "就业收入统计";
	/**
	 * 项目资金使用情况
	 */
	public static final String EXTS_PROJECT_MONEY = "项目资金统计";
	/**
	 * 项目进度分析
	 */
	public static final String EXTS_XMJD = "项目进度统计";
	/**
	 * 脱贫情况统计
	 */
	public static final String EXTS_TPQK = "脱贫情况统计";
	/**
	 * 帮扶措施统计
	 */
	public static final String EXTS_BFCS_NAME = "帮扶措施统计";

	/**
	 * 滞后
	 */
	public static final String EXTS_ZH = "滞后";
	/**
	 * 贫困户列表当前显示页数
	 */
	public static String PAGR = "1";
	/**
	 * 页码参数
	 */
	public static final String EXTS_PAGE = "page";
	/**
	 * 菜单
	 */
	public static final String MENU_BUNDEL_KEY = "menu";
	public static final String USER_BUNDEL_KEY = "user";
	/**
	 * 菜单key
	 */
	public static final String MENU_INTENT_KEY = "menu_key";
	/**
	 * ----------------------------------------------------------
	 */
	/**
	 * 是否进行拖拽
	 */
	public static boolean isDragfo = false;

	/**
	 * 判断动画是否结束
	 */
	public static boolean isAnimaEnd = true;

	/**
	 * 是否有更多item
	 */
	public static String MORE = "99999";

	/**
	 * 在登陆页面登陆成功后保存的flag标志---登录状态
	 */
	public static boolean isLogin = false;
	/**
	 * 城市字段
	 */
	public static String CITY_NT = "city_nt";
	public static String CITY_AD_NM = "city_ad_nm";
	public static String CITY_TS = "city_ts";
	public static String CITY_ORDERS = "city_orders";
	public static String CITY_AD_CD = "city_ad_cd";
	public static String CITY_PINYI = "city_pinyi";

	/**
	 * 用户字段
	 */
	public static final String USER_ID = "user_id"; // 会员id
	public static final String USER_LOGINNAME = "user_loginname";// 用户名
	public static final String USER_USERNAME = "user_user_name";
	public static final String USER_DEPTCODE = "user_dep_code";
	public static final String USER_PASSWORD = "user_password";
	public static final String USER_CODE = "user_code";
	public static final String USER_DEP_CODE = "user_dep_code";
	public static final String USER_SEX = "user_sex";
	public static final String USER_DUTY_LEVEL = "user_duty_level";
	public static final String USER_TITLE = "user_title";
	public static final String USER_MTIME = "user_mtime";
	public static final String USER_NODE = "user_note";
	public static final String USER_EMAIL = "user_email";
	public static final String USER_TELNUMB = "user_telnumb"; // 电话
	public static final String USER_MOBLENUMB = "user_moblenumb";
	public static final String USER_BIRTHDAY = "user_birthday";// 手机
	public static final String USER_HIGHESTDEGREE = "user_highestdegree";
	public static final String USER_DEPNAME = "user_depName";
	public static final String USER_ORDERNUM = "user_ordernum";
	public static final String USER_IFDEL = "user_ifdel";
	public static final String USER_JOBIDS = "user_jobids";
	public static final String USER_ROLEIDS = "user_roleids";
	public static final String USER_ROLEIDS_APP = "user_roleids_app";
	public static final String USER_SEXNAME = "user_sexName";
	public static final String USER_DUTYLEVELNAME = "user_dutyLevelName";
	public static final String USER_HIGHESTDEGREENAME = "user_highestdegreeName";
	public static final String USER_ROLENAME = "user_rolename";
	public static final String USER_ROLENAME_APP = "user_rolename_app";

	public static final String USER_COUNTRYNAME = "user_countryName";
	public static final String USER_CITYNAME = "user_cityname";
	public static final String USER_TOWNNAME = "user_townName";
	public static final String USER_VILLAGENAME = "user_villageName";
	public static final String USER_ADLCD = "user_adl_cd";
	public static final String USER_ADLNAME = "user_adl_nm";
	public static final String USER_ORGCODE = "user_org_code";

	/**
	 * 权限字段
	 */
	public static final String SROLES_id = "id";
	public static final String SROLES_departmentid = "departmentid";
	public static final String SROLES_oid = "oid";
	public static final String SROLES_pdatafilter = "pdatafilter";
	public static final String SROLES_name = "name";
	public static final String SROLES_createdate = "createdate";
	public static final String SROLES_descript = "descript";
	public static final String SROLES_systemtype = "systemtype";

	/**
	 * 是否定位
	 */
	public static final String LOCTION = "isLoction";
	
	/**
	 * 保存当前手机DPI
	 */
	public static int densityDpi = 0;
	
	/**
	 * 保存手机的宽度
	 */
	public static int Width = 0;
	
	/**
	 * 保存手机的高度
	 */
	public static int Hight = 0;
	
	/**
	 * 缓存路径
	 */
	public static final String CACHE_PATHE = "/Android/data/com.roch.fupin_2_0";
	
	/**
	 * 数据库版本
	 */
	public static final int DB_VERSION = 3;

	/**
	 * 数据库名称
	 */
	public static final String DB_NAME = "fupin_2_0_DB";
	/**
	 * 定时进行网络判断action
	 */
	public static final String MESSAGE_RECEIVED_ACTION = "com.roch.fupin_2_0.MESSAGE_RECEIVED_INTERNET";
	/**
	 * 发送的信息
	 */
	public static final String KEY_MESSAGE = "message";
	/**
	 * APP更新action
	 */
	public static final String UPDATA_APP = "com.roch.fupin_2_0.update";
	/**
	 * 取消更新
	 */
	public static final String CANCEL_BROADCAST = "com.roch.fupin_2_0.update_cancel";
	/**
	 * 存储卡路径
	 */
	public static final String SDCARD_DIR = Environment.getExternalStorageDirectory().getPath();
	public static final String CACHE_DIR = Environment.getExternalStorageDirectory().getPath() + CACHE_PATHE + "/cache";
	public static final String DOWNLOAD_DIR = Environment.getExternalStorageDirectory().getPath() + CACHE_PATHE + "/download";

	/**
	 * 返回uuid
	 * @param str
	 * @return
	 */
	public static String uuidToStr(String str) {
		String objectKey = null;
		if (null != str && str.length() > 0) {
			int index = str.lastIndexOf(".");
			int len = str.length();
			objectKey = UUID.randomUUID().toString().replaceAll("-", "") + str.substring(index, len);
		}
		return objectKey;
	}
}
