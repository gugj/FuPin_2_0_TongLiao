package com.roch.fupin.utils;

import android.content.Context;
import android.content.Intent;

import com.roch.fupin.FanKuiGuanLiActivity;
import com.roch.fupin.FuPinXinWenActivity;
import com.roch.fupin.FuPinZhuanXiangZiJinTongJiActivity;
import com.roch.fupin.GongZuoRiZhiActivity;
import com.roch.fupin.HangYeFuPinXinXiActivity;
import com.roch.fupin.HelpCompanyActivity;
import com.roch.fupin.HelpPeopleActivity;
import com.roch.fupin.InformationActivity;
import com.roch.fupin.JingYanJiaoLiuActivity;
import com.roch.fupin.KaoQinXinXiActivity;
import com.roch.fupin.LiuGeYiPiTongJiActivity;
import com.roch.fupin.MoreActivity;
import com.roch.fupin.NoPoorProjectActivity;
import com.roch.fupin.NoPoorProjectCaiZhengJuActivity;
import com.roch.fupin.NoPoorProjectCanLianActivity;
import com.roch.fupin.NoPoorProjectFuLianActivity;
import com.roch.fupin.NoPoorProjectFuPinBanActivity;
import com.roch.fupin.NoPoorProjectJiaoTiJuActivity;
import com.roch.fupin.NoPoorProjectJinDuTiXingActivity;
import com.roch.fupin.NoPoorProjectLinYeJuActivity;
import com.roch.fupin.NoPoorProjectMinZhengJuActivity;
import com.roch.fupin.NoPoorProjectNongWeiActivity;
import com.roch.fupin.NoPoorProjectRenLaoJuActivity;
import com.roch.fupin.NoPoorProjectWeiJiWeiActivity;
import com.roch.fupin.NoPoorProjectZhuJianJuActivity;
import com.roch.fupin.NoticBoardActivity;
import com.roch.fupin.PinKunHuGongJiActivity;
import com.roch.fupin.PoorHouseActivity;
import com.roch.fupin.PoorPeopleBFCSActivity;
import com.roch.fupin.PoorPeopleCaseActivity;
import com.roch.fupin.PoorPeopleStatisticsActivity;
import com.roch.fupin.PoorPeopleTPQKActivity;
import com.roch.fupin.PoorPeopleWHCDActivity;
import com.roch.fupin.PoorQiXianActivity;
import com.roch.fupin.PoorVillageActivity;
import com.roch.fupin.QuanShiFuPinDaXingHuoDongActivity;
import com.roch.fupin.SheHuiFuPinXinXiActivity;
import com.roch.fupin.XinXiFanKuiActivity;
import com.roch.fupin.ZhengCeZiXunActivity;
import com.roch.fupin.ZhuanXiangZiJinGuanLiActivity;
import com.roch.fupin.ZuiXinFuPinZhengCeActivity;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.AdlCode;
import com.roch.fupin.entity.User;

/**
 * 打开Activity的工具类
 * @author ZhaoDongShao
 * 2016年5月26日
 */
public class OpenActivityUtil implements IOpenActivity {

	static OpenActivityUtil util;

	/**
	 * 获取打开Activity的工具类OpenActivityUtil的对象
	 * @return
	 * 2016年11月3日
	 */
	public static OpenActivityUtil getInstance() {
		if (util == null) {
			util = new OpenActivityUtil();
		}
		return util;
	}

	/**
	 * 打开activity的方法，返回一个intent对象，将点击时的名字传到打开的activity中，里面带有参数activity的名字和打开的activity的对象
	 */
	@Override
	public Intent OpenActivity(Context mContext, String ActivityName) {
		Intent intent = null;
		if (ActivityName == null || ActivityName.equals("")) {
			return intent;
		}

		if (ActivityName.equals(Common.EXTS_HELP_OBJECT_FAMILY_NAME)) { // 贫困户
			intent = new Intent(mContext, PoorHouseActivity.class);
		} else if (ActivityName.equals(Common.EXTS_HELP_OBJECT_VILLAGE_NAME)
				   ||ActivityName.equals("行政村")) { // 贫困村---改为行政村
			intent = new Intent(mContext, PoorVillageActivity.class);
		} else if (ActivityName.equals("贫困旗县")) { // 贫困旗县
			intent = new Intent(mContext, PoorQiXianActivity.class);
		}else if (ActivityName.equals("专项扶贫项目管理")) { //专项扶贫项目管理
			intent = new Intent(mContext, PoorQiXianActivity.class);
		}else if (ActivityName.equals(Common.EXTS_HELP_SUBJECT_PROPLE_NAME)) { // 帮扶责任人
			intent = new Intent(mContext, HelpPeopleActivity.class);
		} else if (ActivityName.equals(Common.EXTS_HELP_SUBJECT_COMPANY_NAME)) { // 帮扶单位
			intent = new Intent(mContext, HelpCompanyActivity.class);
		} else if (ActivityName.equals(Common.EXTS_MORE_NAME)) { // 更多
			intent = new Intent(mContext, MoreActivity.class);
		} else if (ActivityName.equals("通知栏")) { // 通知公告----改为通知栏
			intent = new Intent(mContext, NoticBoardActivity.class);
		} else if (ActivityName.equals("公告栏")) { // 信息宣传----改为公告栏
			intent = new Intent(mContext, InformationActivity.class);
		} else if (ActivityName.equals(Common.EXTS_NO_POOR_PROJECT_JAOTONG)
				  || ActivityName.equals(Common.EXTS_NO_POOR_PROJECT_WATER)
				  || ActivityName.equals(Common.EXTS_NO_POOR_PROJECT_XINCUNBAN)) { // 交通局、水利局、新村办
			intent = new Intent(mContext, NoPoorProjectActivity.class);
		} else if (ActivityName.equals(Common.EXTS_NO_POOR_PROJECT_MINZHENGJU)) { // 民政局
			intent = new Intent(mContext, NoPoorProjectMinZhengJuActivity.class);
		} else if (ActivityName.equals(Common.EXTS_NO_POOR_PROJECT_NONGWEI)) { // 农委---改为农牧业局
			intent = new Intent(mContext, NoPoorProjectNongWeiActivity.class);
		} else if (ActivityName.equals(Common.EXTS_NO_POOR_PROJECT_JIAOTIJU)) { // 教体局
			intent = new Intent(mContext, NoPoorProjectJiaoTiJuActivity.class);
		} else if (ActivityName.equals(Common.EXTS_NO_POOR_PROJECT_CANLIAN)) { // 残联
			intent = new Intent(mContext, NoPoorProjectCanLianActivity.class);
		} else if (ActivityName.equals(Common.EXTS_NO_POOR_PROJECT_FULIAN)) { // 妇联
			intent = new Intent(mContext, NoPoorProjectFuLianActivity.class);
		} else if (ActivityName.equals(Common.EXTS_NO_POOR_PROJECT_JINDUTIXING)) { // 进度提醒
			intent = new Intent(mContext, NoPoorProjectJinDuTiXingActivity.class);
		} else if (ActivityName.equals(Common.EXTS_NO_POOR_PROJECT_CAIZHENGJU)) { // 财政局
			intent = new Intent(mContext, NoPoorProjectCaiZhengJuActivity.class);
		} else if (ActivityName.equals(Common.EXTS_NO_POOR_PROJECT_ZHUJIANJU)) { // 财政局
			intent = new Intent(mContext, NoPoorProjectZhuJianJuActivity.class);
		} else if (ActivityName.equals(Common.EXTS_NO_POOR_PROJECT_RENLAO)) { //人劳局
			intent = new Intent(mContext, NoPoorProjectRenLaoJuActivity.class);
		} else if (ActivityName.equals(Common.EXTS_NO_POOR_PROJECT_FUPUNBAN)) { //扶贫办
			intent = new Intent(mContext, NoPoorProjectFuPinBanActivity.class);
		} else if (ActivityName.equals(Common.EXTS_NO_POOR_PROJECT_LINYEJU)) { //林业局
			intent = new Intent(mContext, NoPoorProjectLinYeJuActivity.class);
		} else if (ActivityName.equals(Common.EXTS_NO_POOR_PROJECT_WEIJIWEI)) { //卫计委
			intent = new Intent(mContext, NoPoorProjectWeiJiWeiActivity.class);
		} else if (ActivityName.equals(Common.EXTS_STATISTIC_POOR_PEOPLE)) { // 贫困人口统计报表
			intent = new Intent(mContext, PoorPeopleStatisticsActivity.class);
		} else if (ActivityName.equals(Common.EXTS_WHCD)) { // 文化程度统计
			intent = new Intent(mContext, PoorPeopleWHCDActivity.class);
		} else if (ActivityName.equals("主要致贫原因统计")) { // 主要致贫原因统计
			intent = new Intent(mContext, PoorPeopleCaseActivity.class);
		} else if (ActivityName.equals(Common.EXTS_AGE)) { // 年龄构成统计
			intent = new Intent(mContext, PoorPeopleWHCDActivity.class);
		} else if (ActivityName.equals(Common.EXTS_WORK)) { // 就业收入统计
			intent = new Intent(mContext, PoorPeopleWHCDActivity.class);
		} else if (ActivityName.equals(Common.EXTS_PROJECT_MONEY)) { // 项目资金统计
			intent = new Intent(mContext, PoorPeopleWHCDActivity.class);
		} else if (ActivityName.equals(Common.EXTS_XMJD)) { // 项目进度统计
			intent = new Intent(mContext, PoorPeopleWHCDActivity.class);
		} else if (ActivityName.equals(Common.EXTS_BFCS_NAME)) { // 帮扶措施
			intent = new Intent(mContext, PoorPeopleBFCSActivity.class);
		} else if (ActivityName.equals(Common.EXTS_TPQK)) { // 脱贫情况
			intent = new Intent(mContext, PoorPeopleTPQKActivity.class);
		}else if (ActivityName.equals("扶贫新闻")) { // 扶贫新闻
			intent = new Intent(mContext, FuPinXinWenActivity.class);
		}else if (ActivityName.equals("最新扶贫政策")) { // 最新扶贫政策
			intent = new Intent(mContext, ZuiXinFuPinZhengCeActivity.class);
		}else if (ActivityName.equals("全市扶贫大型活动记载")) { // 全市扶贫大型活动记载
			intent = new Intent(mContext, QuanShiFuPinDaXingHuoDongActivity.class);
		}else if (ActivityName.equals("社会扶贫信息")) { // 社会扶贫信息
			intent = new Intent(mContext, SheHuiFuPinXinXiActivity.class);
		}else if (ActivityName.equals("行业扶贫信息")) { // 行业扶贫信息
			intent = new Intent(mContext, HangYeFuPinXinXiActivity.class);
		}else if (ActivityName.equals("贫困户供给信息")) { // 贫困户供给信息
			intent = new Intent(mContext, PinKunHuGongJiActivity.class);
		}else if (ActivityName.equals("专项资金到账")
				 ||ActivityName.equals("专项资金下拨")
				 ||ActivityName.equals("本级项目管理费")
				 ||ActivityName.equals("专项资金流向")) { // 专项资金管理--改为专项资金到账
			intent = new Intent(mContext, ZhuanXiangZiJinGuanLiActivity.class);
		}else if (ActivityName.equals("信息反馈")) { // 信息反馈
			intent = new Intent(mContext, XinXiFanKuiActivity.class);
		}else if (ActivityName.equals("政策咨询")) { // 政策咨询
			intent = new Intent(mContext, ZhengCeZiXunActivity.class);
		}else if (ActivityName.equals("六个一批统计")) { // 六个一批统计
			intent = new Intent(mContext, LiuGeYiPiTongJiActivity.class);
		}else if (ActivityName.equals("工作日志")) { // 工作日志
			intent = new Intent(mContext, GongZuoRiZhiActivity.class);
		}else if (ActivityName.equals("反馈管理")) { // 反馈管理
			intent = new Intent(mContext, FanKuiGuanLiActivity.class);
		}else if (ActivityName.equals("经验交流")) { // 经验交流
			intent = new Intent(mContext, JingYanJiaoLiuActivity.class);
		}else if (ActivityName.equals("考勤信息")
				 || ActivityName.equals("日志考勤")) { // 考勤信息---改为日志考勤
			intent = new Intent(mContext, KaoQinXinXiActivity.class);
		}else if (ActivityName.equals("健康状况统计")) { // 健康状况统计
			intent = new Intent(mContext, PoorPeopleWHCDActivity.class);
		}else if (ActivityName.equals("在校生状态统计")) { // 在校生状态统计
			intent = new Intent(mContext, PoorPeopleWHCDActivity.class);
		}else if (ActivityName.equals("劳动能力类型统计")) { // 劳动能力类型统计
			intent = new Intent(mContext, LiuGeYiPiTongJiActivity.class);
		}else if (ActivityName.equals("贫困户属性统计")) { // 贫困户属性统计
			intent = new Intent(mContext, LiuGeYiPiTongJiActivity.class);
		}else if (ActivityName.equals("贫困村属性统计")) { // 贫困村属性统计
			intent = new Intent(mContext, LiuGeYiPiTongJiActivity.class);
		}else if (ActivityName.equals("务工情况统计")) { // 务工情况统计
			intent = new Intent(mContext, LiuGeYiPiTongJiActivity.class);
		}else if (ActivityName.equals("扶贫专项资金统计")) { // 扶贫专项资金统计
			intent = new Intent(mContext, FuPinZhuanXiangZiJinTongJiActivity.class);
		}else if (ActivityName.equals("低保五保情况统计")) { // 低保五保情况统计
			intent = new Intent(mContext, PoorPeopleWHCDActivity.class);
		}else if (ActivityName.equals("行业项目资金统计")) { // 行业项目资金统计
			intent = new Intent(mContext, PoorPeopleWHCDActivity.class);
		}else {
			intent = new Intent();
		}
		intent.putExtra(Common.INTENT_KEY, ActivityName);
		return intent;
	}

	/**
	 * 根据登陆用户的行政区划代码和选择城市后的行政区划代码，进行对比，判断选择的adl_cd是否属于自己管辖范围
	 * @return
	 * 2016年8月9日
	 * ZhaoDongShao
	 */
	public boolean getisAdl_CD(Context mContext, String username) {
		boolean is = false;
		//获取登陆用户---根据登陆用户名获取登陆用户信息，并存到sp中，此方法再从sp中获取到该登陆用户
		User user = MyApplication.getInstance().getSharePreferencesUtilInstance().getLoginUser(mContext, username);
		AdlCode adlCode = MyApplication.getInstance().getSharePreferencesUtilInstance().getNowCity(mContext, username);

		System.out.println("打开界面时登录用户的adl_cd为：********************"+user.getAdl_cd());
		System.out.println("打开界面时的ad_cd为：********************"+adlCode.getAd_cd());

		if (user == null || user.getAdl_cd() == null || user.getAdl_cd().equals("")) {
			is = false;
		}
		if (adlCode == null || adlCode.getAd_cd() == null || adlCode.getAd_cd().equals("")) {
			is = false;
		}

		// 获取当前登录用户的adl_cd
		String adl_cd = user.getAdl_cd(); // 登陆用户的行政区划代码
		// 判断当前登录用户是否为市级------市级
		boolean isCity = AdlcdUtil.isCity(adl_cd);
		// 判断当前登录用户是否为区县级------县级
		boolean isCountry = AdlcdUtil.isCountry(adl_cd);
		// 判断当前登录用户是否为乡镇级------镇级
		boolean isTown = AdlcdUtil.isTown(adl_cd);
		// 判断当前登录用户是否为村级------村级
		boolean isVillage = AdlcdUtil.isVillage(adl_cd);

		//获取选择后的城市ad_cd
		String select_adl_cd = adlCode.getAd_cd(); // 选择城市后的行政区划代码
		if (isCity) { // 如果当前登录用户是市级------市级
			String city_cd = AdlcdUtil.generateCityCode(select_adl_cd);// 获取选择城市的市级行政区划代码
			if (adl_cd.equals(city_cd)) {
				is = true;
			}
		} else if (isCountry) { // 如果当前登录用户是县级------县级
			String country_cd = AdlcdUtil.generateCountryCode(select_adl_cd); // 获取选择城市的区县级行政区代码
			if (adl_cd.equals(country_cd)) {
				is = true;
			}
		} else if (isTown) { // 如果当前登录用户是镇级------镇级
			String town_cd = AdlcdUtil.generateTownCode(select_adl_cd); // 获取选择城市的乡镇级行政区代码
			if (adl_cd.equals(town_cd)) {
				is = true;
			}
		}else if (isVillage) { // 如果当前登录用户是村级------村级
			if (adl_cd.equals(select_adl_cd)) {
				is = true;
			}
		}
		return is;
	}

	/**
	 * 根据点击的item的名字返回对应的url
	 * @param titlename
	 * @return
	 * 2016年6月1日
	 * ZhaoDongShao
	 */
	public static String getUrlString(String titlename) {
		String url = "";
		if (titlename == null || titlename.equals("")) {
			return url;
		}
		if (titlename.equals(Common.EXTS_NO_POOR_PROJECT_JAOTONG)) {
			url = URLs.NO_POOR_PROJECT_JAOTONG;
		} else if (titlename.equals(Common.EXTS_NO_POOR_PROJECT_WATER)) {
			url = URLs.NO_POOR_PROJECT_WATER;
		} else if (titlename.equals(Common.EXTS_NO_POOR_PROJECT_XINCUNBAN)) {
			url = URLs.NO_POOR_PROJECT_XINCUNBAN;
		}
		return url;
	}
}
