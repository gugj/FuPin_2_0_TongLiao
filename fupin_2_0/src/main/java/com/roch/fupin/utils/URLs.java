package com.roch.fupin.utils;

public class URLs {

//    	public static String IP = "192.168.1.10:8080";
//      public static String IP = "101.200.190.254:9005";
        //阿里云服务器---新
      public static String IP = "47.93.120.179:9005";
        //王子松服务器
//	    public static String IP = "192.168.1.153:8080";
       //荣聪服务器
//	    public static String IP = "192.168.1.113:8080";

    /**
     * WebService服务器端接口的BaseUrl <br/>
     * web端---http://101.200.190.254:9005/poverty <br/>
     * 手机端---http://101.200.190.254:9005/poverty/app/
     */
    public static String WEB_SERVICE_URL = "http://" + IP + "/poverty/app/";

    /**
     * image——url服务器端图片的BaseUrl <br/>
     * http://101.200.190.254:9100/poverty
     */
    public static String IMAGE_URL = "http://" + IP + "/poverty/";

//	public URLs(String address) {
//		if (address.equals("")) {
//			IP = "101.200.190.254:8999";
//		}else {
//			IP = address;
//		}
//	}

    /**
     * 登陆方法 <br/>
     * http://101.200.190.254:9100/poverty/app/sys_user/login.do
     */
    public static String LOGIN = WEB_SERVICE_URL + "sys_user/login.do";
    /**
     * 查询签到次数和内容提示 <br/>
     * http://101.200.190.254:9100/poverty/app/user_sign/getCountAndDateByUserid
     */
    public static String QianDao_JiLu = WEB_SERVICE_URL  + "user_sign/getCountAndDateByUserid";
    /**
     * 确认提交签到 <br/>
     * http://101.200.190.254:9100/poverty/app/user_sign/save
     */
    public static String QianDao_Commit = WEB_SERVICE_URL  + "user_sign/save";
    /**
     * 修改密码 <br/>
     * http://101.200.190.254:9100/poverty/app/sys_user/updatepassword.do
     */
    public static String Update_password = WEB_SERVICE_URL + "sys_user/updatepassword.do";

    /**
     * 项目图片 <br/>
     * http://101.200.190.254:9100/poverty/app/project_info/getProjectImages.do
     */
    public static String XiangMu_Photo = WEB_SERVICE_URL + "project_info/getProjectImages.do";

    /**
     * APP版本更新的服务器地址<br/>
     * http://101.200.190.254:9100/poverty/app/bbgx/getBbgxID.do
     */
    public static String APP_Update = WEB_SERVICE_URL + "bbgx/getBbgxID.do";

    /**
     * 获取贫困户数据 <br/>
     * http://101.200.190.254:9100/poverty/app/poorfamily/dataList.do
     */
    public static String POOR_HOUSE_LISE = WEB_SERVICE_URL + "poorfamily/dataList.do";

    /**
     * 贫困户台账信息url <br/>
     * http://101.200.190.254:9100/poverty/app/poorfamily/queryPoorfamilyincomeApp.do
     */
    public static String POOR_HOUSE_ACCENT = WEB_SERVICE_URL + "poorfamily/queryPoorfamilyincomeApp.do";
    
    /**
     * 贫困户帮扶记录--信息传参户IDurl <br/> ------------------------------------------------
     * http://101.200.190.254:9003/poverty/app/poorfamilyhelp/dataList
     */
    public static String POOR_HOUSE_BangFuJiLu_HourseId = WEB_SERVICE_URL + "poorfamilyhelp/dataList";
    /**
     * 贫困村帮扶记录--信息传参户IDurl <br/> ------------------------------------------------
     * http://101.200.190.254:9003/poverty/app/poorvillagehelp/dataList.do
     */
    public static String POOR_Village_BangFuJiLu_VillageId = WEB_SERVICE_URL + "poorvillagehelp/dataList.do";

    /**
     * 贫困户帮扶记录信息--传参帮扶记录IDurl <br/> ------------------------------------------------
     * http://101.200.190.254:9003/poverty/app/poorfamilyhelp/queryBfByid
     */
    public static String POOR_HOUSE_BangFuJiLu_bfjlID = WEB_SERVICE_URL + "poorfamilyhelp/queryBfByid";
    /**
     * 贫困村帮扶记录信息--传参帮扶记录IDurl <br/> ------------------------------------------------
     * http://101.200.190.254:9003/poverty/app/poorvillagehelp/queryBfvilageByid.do
     */
    public static String POOR_Village_BangFuJiLu_bfjlID = WEB_SERVICE_URL + "poorvillagehelp/queryBfvilageByid.do";

    /**
     * 获取贫困户详细数据 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamily/queryPoorfamilyApp.do
     */
    public static String POOR_HOUSE_DETAIL = WEB_SERVICE_URL + "poorfamily/queryPoorfamilyApp.do";
    /**
     * 贫困户添加保存帮扶记录---内容部分和照片部分 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamilyhelp/upload.do
     */
    public static String POOR_HOUSE_Bfjl_Save = WEB_SERVICE_URL + "poorfamilyhelp/upload.do";
    /**
     * 贫困户添加保存帮扶记录---内容部分无照片 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamilyhelp/save.do
     */
    public static String POOR_HOUSE_Bfjl_Save_NoPhoto = WEB_SERVICE_URL + "poorfamilyhelp/save.do";
    /**
     * 贫困村添加保存帮扶记录---内容部分无照片 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorvillagehelp/save.do
     */
    public static String POOR_Village_Bfjl_Save_NoPhoto = WEB_SERVICE_URL + "poorvillagehelp/save.do";
    /**
     * 贫困村添加保存帮扶记录---内容部分有照片 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorvillagehelp/upload.do
     */
    public static String POOR_Village_Bfjl_Save = WEB_SERVICE_URL + "poorvillagehelp/upload.do";
    /**
     * 贫困户修改保存帮扶记录---内容部分和照片部分 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamilyhelp/updateandro
     */
    public static String POOR_HOUSE_Bfjl_Xiugai = WEB_SERVICE_URL + "poorfamilyhelp/updateandro.do";
    /**
     * 贫困户修改保存帮扶记录---内容部分无照片 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamilyhelp/edit.do
     */
    public static String POOR_HOUSE_Bfjl_Xiugai_NoPhoto = WEB_SERVICE_URL + "poorfamilyhelp/edit.do";
    /**
     * 贫困户修改保存帮扶记录---内容部分有照片--都是老照片 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamilyhelp/updatedel.do
     */
    public static String POOR_HOUSE_Bfjl_Xiugai_Only_Old_Photo = WEB_SERVICE_URL + "poorfamilyhelp/updatedel.do";
    /**
     * 贫困村修改保存帮扶记录---内容部分和照片部分 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorvillagehelp/updateapp
     */
    public static String POOR_Village_Bfjl_Xiugai = WEB_SERVICE_URL + "poorvillagehelp/updateandro.do";
    /**
     * 贫困村修改保存帮扶记录---内容部分无照片 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorvillagehelp/edit.do
     */
    public static String POOR_Village_Bfjl_Xiugai_NoPhoto = WEB_SERVICE_URL + "poorvillagehelp/edit.do";
    /**
     * 贫困村修改保存帮扶记录---内容部分有照片---都是老照片 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorvillagehelp/updatedel.do
     */
    public static String POOR_Village_Bfjl_Xiugai_Only_old_Photo = WEB_SERVICE_URL + "poorvillagehelp/updatedel.do";
    /**
     * 获取贫困户基本信息数据 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamily/queryPoorfamilyApp.do
     */
    public static String POOR_HOUSE_BaseInfo = WEB_SERVICE_URL + "poorfamily/getBaseByHouseholderid.do";
    /**
     * 获取贫困户家庭成员数据 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamily/queryPoorfamilyApp.do
     */
    public static String POOR_HOUSE_ChengYuan = WEB_SERVICE_URL + "poorfamily/getPoorfaminlyCyByHouseholderid.do";
    /**
     * 获取贫困户图片数据 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamily/queryPoorfamilyApp.do
     */
    public static String POOR_HOUSE_TuPian = WEB_SERVICE_URL + "poorfamily/getPictureByHouseholderid.do";

    /**
     * 获取贫困村数据 <br/>
     *   http://101.200.190.254:9100/poverty/app/poorvillage/dataList.do
     */
    public static String POOR_VILLAGE_LIST = WEB_SERVICE_URL + "poorvillage/dataList.do";

    /**
     * 获取贫困村详细数据 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorvillage/queryPoorvillageApp.do
     */
    public static String POOR_VILLAGE_DETAIL = WEB_SERVICE_URL + "poorvillage/queryPoorvillageApp.do";

    /**
     * 贫困村删除照片 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorvillage/deleteImg.do
     */
    public static String POOR_Village_Delete_Photo = WEB_SERVICE_URL + "poorvillage/deleteImg.do";
    /**
     * 贫困户删除帮扶记录 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamilyhelp/delete.do
     */
    public static String POOR_House_Delete_Bfjl = WEB_SERVICE_URL + "poorfamilyhelp/delete.do";
    /**
     * 贫困村删除帮扶记录 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorvillagehelp/delete.do
     */
    public static String POOR_Village_Delete_Bfjl = WEB_SERVICE_URL + "poorvillagehelp/delete.do";

    /**
     * 贫困户删除照片 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamily/deleteImg.do
     */
    public static String POOR_House_Delete_Photo = WEB_SERVICE_URL + "poorfamily/deleteImg.do";
    /**
     * 贫困户删除帮扶记录中的老照片 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamily/deleteEditImg.do
     */
    public static String POOR_House_Delete_Photo_Bfjl = WEB_SERVICE_URL + "poorfamily/updatedel.do";

    /**
     * 帮扶责任人 <br/>
     *  http://101.200.190.254:9100/poverty/app/helpdutypersonapp/dataList.do
     */
    public static String HELP_PEOPLE_LIST = WEB_SERVICE_URL + "helpdutypersonapp/dataList.do";

    /**
     * 帮扶责任人负责的贫困户 <br/>
     *  http://101.200.190.254:9100/poverty/app/helpdutypersonapp/queryPoorfamily.do
     */
    public static String HELP_PEOPLE_POOR_FAMILY_LIST = WEB_SERVICE_URL + "helpdutypersonapp/queryPoorfamily.do";

    /**
     * 帮扶单位 <br/>
     *  http://101.200.190.254:9100/poverty/app/helpdutycompanyapp/dataList.do
     */
    public static String HELP_COMPANY_LIST = WEB_SERVICE_URL + "helpdutycompanyapp/dataList.do";

    /**
     * 帮扶单位负责的贫困村 <br/>
     *  http://101.200.190.254:9100/poverty/app/helpdutycompanyapp/queryPoorvillage.do
     */
    public static String HELP_COMPANY_POOR_VILLAGE_LIST = WEB_SERVICE_URL + "helpdutycompanyapp/queryPoorvillage.do";

    /**
     * 信息宣传 <br/>
     *  http://101.200.190.254:9100/poverty/app/info_propaganda/dataList.do
     */
    public static String INFORMATION_LIST = WEB_SERVICE_URL + "info_propaganda/dataList.do";
    /**
     * 信息反馈 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamily_feedback/dataList.do
     */
    public static String XinXiFanKui_LIST = WEB_SERVICE_URL + "poorfamily_feedback/dataList.do";
    /**
     * 反馈管理 <br/>
     *  http://101.200.190.254:9100/poverty/app/helpdutyperson_feedback/dataList
     */
    public static String FanKuiGuanLi_LIST = WEB_SERVICE_URL + "helpdutyperson_feedback/dataList";
    /**
     * 经验交流 <br/>
     *  http://101.200.190.254:9100/poverty/app/helpdutypersonsymposiu/dataList
     */
    public static String JingYanJiaoLiu_LIST = WEB_SERVICE_URL + "helpdutypersonsymposiu/dataList";
    /**
     * 政策咨询 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamily_policyadvice/dataList.do
     */
    public static String ZhengCeZiXun_LIST = WEB_SERVICE_URL + "poorfamily_policyadvice/dataList.do";
    /**
     * 查看工作日志 <br/>
     *  http://101.200.190.254:9100/poverty/app/helprecords/dataList
     */
    public static String GongZuoRiZhi_LIST = WEB_SERVICE_URL + "helprecords/dataList";
    /**
     * 获取信息反馈中所有的回复信息列表 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamily_feedback/getReplyByFeedbackapp.do
     */
    public static String HuiFuXinXi_LIST = WEB_SERVICE_URL + "poorfamily_feedback/getReplyByFeedbackapp.do";
    /**
     * 获取反馈管理中所有的回复信息列表 <br/>
     *  http://101.200.190.254:9100/poverty/app/helpdutyperson_feedback/replayList
     */
    public static String FanKuiGuanLi_HuiFu_LIST = WEB_SERVICE_URL + "helpdutyperson_feedback/replayList";
    /**
     * 获取经验交流中所有的回复信息列表 <br/>
     *  http://101.200.190.254:9100/poverty/app/helpdutypersonsymposiu/getReplyByFeedback
     */
    public static String JingYanJiaoLiu_HuiFu_LIST = WEB_SERVICE_URL + "helpdutypersonsymposiu/getReplyByFeedback";
    /**
     * 获取政策咨询中所有的回复信息列表 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamily_policyadvice/getReplyByFeedbackapp.do
     */
    public static String ZhengCeZiXun_HuiFu_LIST = WEB_SERVICE_URL + "poorfamily_policyadvice/getReplyByFeedbackapp.do";
    /**
     * 发送评论 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamily_feedback/replay
     */
    public static String SendComment = WEB_SERVICE_URL + "poorfamily_feedback/replay";
    /**
     * 反馈管理发送评论 <br/>
     *  http://101.200.190.254:9100/poverty/app/helpdutyperson_feedback/save.do
     */
    public static String FanKuiGuanLi_SendComment = WEB_SERVICE_URL + "helpdutyperson_feedback/save.do";
    /**
     * 经验交流发送评论 <br/>
     *  http://101.200.190.254:9100/poverty/app/helpdutypersonsymposiu/save.do
     */
    public static String JingYanJiaoLiu_SendComment = WEB_SERVICE_URL + "helpdutypersonsymposiu/save.do";
    /**
     * 政策咨询发送评论 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamily_policyadvice/replay.do
     */
    public static String ZhengCeZiXun_SendComment = WEB_SERVICE_URL + "poorfamily_policyadvice/replay.do";
    /**
     * 回复并展示所有回复列表 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamily_feedback/replay
     */
    public static String HuiFuLieBiao = WEB_SERVICE_URL + "poorfamily_feedback/replay";
    /**
     * 扶贫新闻 <br/>
     *  http://101.200.190.254:9100/poverty/app/news_povertyinfo/dataList.do
     */
    public static String FuPinXinWen_LIST = WEB_SERVICE_URL + "news_povertyinfo/dataList.do";
    /**
     * 最新扶贫政策 <br/>
     *  http://101.200.190.254:9100/poverty/app/news_PovertyinfoOfZhengCe/dataList.do
     */
    public static String ZuiXinFuPinZhengCe_LIST = WEB_SERVICE_URL + "news_PovertyinfoOfZhengCe/dataList.do";
    /**
     * 全市扶贫活动记载 <br/>
     *  http://101.200.190.254:9100/poverty/app/news_PovertyinfoOfHuoDong/dataList.do
     */
    public static String FuPinHuoDongJiZai_LIST = WEB_SERVICE_URL + "news_PovertyinfoOfHuoDong/dataList.do";
    /**
     * 社会扶贫信息 <br/>
     *  http://101.200.190.254:9100/poverty/app/society_povertyinfo/dataList.do
     */
    public static String SheHuiFuPinXinXi_LIST = WEB_SERVICE_URL + "society_povertyinfo/dataList.do";
    /**
     * 行业扶贫信息 <br/>
     *  http://101.200.190.254:9100/poverty/app/industries_povertyinfo/dataList.do
     */
    public static String HangYeFuPinXinXi_LIST = WEB_SERVICE_URL + "industries_povertyinfo/dataList.do";
    /**
     * 贫困户供给信息 <br/>
     *  http://101.200.190.254:9100/poverty/app/supply_povertyinfo/dataList.do
     */
    public static String PinKunHuGongJi_LIST = WEB_SERVICE_URL + "supply_povertyinfo/dataList.do";

    /**
     * 公告栏收件箱 <br/>
     *  http://101.200.190.254:9100/poverty/app/oa_notice/dataList.do
     */
    public static String NOTIC_BOARD_INBOX = WEB_SERVICE_URL + "oa_notice/dataList.do";

    /**
     * 公告栏发件箱 <br/>
     *  http://101.200.190.254:9100/poverty/app/oa_notice/dataList_post.do
     */
    public static String NOTIC_BOARD_OUTBOX = WEB_SERVICE_URL + "oa_notice/dataList_post.do";

//	/**
//	 * 获取乡镇
//	 */
//	public static String TOWNLET_LIST = WEB_SERVICE_URL + "zidian/queryZidian.do";
//	/**
//	 * 根据乡镇去查找村
//	 */
//	public static String VILLIGE_LIST = WEB_SERVICE_URL + "zidian/queryXcun.do";

    /**
     * 交通局项目 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_jiaotong/dataList.do
     */
    public static String NO_POOR_PROJECT_JAOTONG = WEB_SERVICE_URL + "project_jiaotong/dataList.do";

    /**
     * 水利局 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_shuili/dataList.do
     */
    public static String NO_POOR_PROJECT_WATER = WEB_SERVICE_URL + "project_shuili/dataList.do";

    /**
     * 新村办 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_xincunban/dataList.do
     */
    public static String NO_POOR_PROJECT_XINCUNBAN = WEB_SERVICE_URL + "project_xincunban/dataList.do";

    /**
     * 民政局 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_minzhengapp/dataList.do
     */
    public static String NO_POOR_PROJECT_MINZHENGJU = WEB_SERVICE_URL + "project_minzhengapp/dataList.do";

    /**
     * 民政局详情 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_minzhengapp/queryminzhengApp.do
     */
    public static String NO_POOR_PROJECT_MINZHENGJU_DETAIL = WEB_SERVICE_URL + "project_minzhengapp/queryminzhengApp.do";

    /**
     * 农委 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_nongwei/dataList.do
     */
    public static String NO_POOR_PROJECT_NONGWEI = WEB_SERVICE_URL + "project_nongwei/dataList.do";

    /**
     * 农委详情 <br/>
     *   http://101.200.190.254:9100/poverty/app/project_nongwei/querynongweiApp.do
     */
    public static String NO_POOR_PROJECT_NONGWEI_DETAIL = WEB_SERVICE_URL + "project_nongwei/querynongweiApp.do";

    /**
     * 教体局 <br/>
     *   http://101.200.190.254:9100/poverty/app/project_jiaoti/dataList.do
     */
    public static String NO_POOR_PROJECT_JIAOTIJU = WEB_SERVICE_URL + "project_jiaoti/dataList.do";

    /**
     * 教体局详情 <br/>
     *   http://101.200.190.254:9100/poverty/app/project_jiaoti/queryjiaotiApp.do
     */
    public static String NO_POOR_PROJECT_JIAOTIJU_DETAIL = WEB_SERVICE_URL + "project_jiaoti/queryjiaotiApp.do";

    /**
     * 残联——就业培训详情 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_canlian/querycljyApp.do
     */
    public static String NO_POOR_PROJECT_CANLIAN_JYPX_DETAIL = WEB_SERVICE_URL + "project_canlian/querycljyApp.do";

    /**
     * 残联——危房改造 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_canlian/dataList.do
     */
    public static String NO_POOR_PROJECT_CANLIAN_WFGZ = WEB_SERVICE_URL + "project_canlian/dataList.do";

    /**
     * 残联——就业培训 <br/>
     *   http://101.200.190.254:9100/poverty/app/project_canlian/dataList1.do
     */
    public static String NO_POOR_PROJECT_CANLIAN_JYPX = WEB_SERVICE_URL + "project_canlian/dataList1.do";

    /**
     * 妇联 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_fulian/dataList.do
     */
    public static String NO_POOR_PROJECT_FULIAN = WEB_SERVICE_URL + "project_fulian/dataList.do";

    /**
     * 妇联详情 <br/>
     *   http://101.200.190.254:9100/poverty/app/project_fulian/queryfulianApp.do
     */
    public static String NO_POOR_PROJECT_FULIAN_DETAIL = WEB_SERVICE_URL + "project_fulian/queryfulianApp.do";

    /**
     * 进度提醒 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_jindutixing/dataList.do
     */
    public static String NO_POOR_PROJECT_JINDUTIXING = WEB_SERVICE_URL + "project_jindutixing/dataList.do";

    /**
     * 财政局 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_caizhengjuapp/dataList.do
     */
    public static String NO_POOR_PROJECT_CAIZHENGJU = WEB_SERVICE_URL + "project_caizhengjuapp/dataList.do";

    /**
     * 财政局详情页 <br/>
     *   http://101.200.190.254:9100/poverty/app/project_caizhengjuapp/queryxingmuApp.do
     */
    public static String NO_POOR_PROJECT_CAIZHENGJU_DETAIL = WEB_SERVICE_URL + "project_caizhengjuapp/queryxingmuApp.do";

    /**
     * 住建局 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_zhujianapp/dataList.do
     */
    public static String NO_POOR_PROJECT_ZHUJIANJU = WEB_SERVICE_URL + "project_zhujianapp/dataList.do";

    /**
     * 住建局详情页 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_zhujianapp/queryxingmuApp.do
     */
    public static String NO_POOR_PROJECT_ZHUJIANJU_DETAIL = WEB_SERVICE_URL + "project_zhujianapp/queryxingmuApp.do";

    /**
     * 人劳局 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_renlao/dataList.do
     */
    public static String NO_POOR_PROJECT_RENLAOJU = WEB_SERVICE_URL + "project_renlao/dataList.do";

    /**
     * 人劳局详情页 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_renlao/queryrenlaoApp.do
     */
    public static String NO_POOR_PROJECT_RENLAOJU_DETAIL = WEB_SERVICE_URL + "project_renlao/queryrenlaoApp.do";

    /**
     * 扶贫办 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_fupinban_train/dataList.do
     */
    public static String NO_POOR_PROJECT_FUPINBAN = WEB_SERVICE_URL + "project_fupinban_train/dataList.do";

    /**
     * 扶贫办详情 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_fupinban_train/queryfupinbanApp.do
     */
    public static String NO_POOR_PROJECT_FUPINBAN_DETAIL = WEB_SERVICE_URL + "project_fupinban_train/queryfupinbanApp.do";

    /**
     * 林业局---道路绿化 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_linyeju/dataList.do
     */
    public static String NO_POOR_PROJECT_LINYEJU_DLLH = WEB_SERVICE_URL + "project_linyeju/dataList.do";

    /**
     * 林业局---林下经济  <br/>
     *  http://101.200.190.254:9100/poverty/app/project_linyeeconomyapp/dataList.do
     */
    public static String NO_POOR_PROJECT_LINYEJU_LXJJ = WEB_SERVICE_URL + "project_linyeeconomyapp/dataList.do";

    /**
     * 林业局---林下经济详情 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_linyeeconomyapp/queryxingmuApp.do
     */
    public static String NO_POOR_PROJECT_LINYEJU_LXJJ_DETAIL = WEB_SERVICE_URL + "project_linyeeconomyapp/queryxingmuApp.do";

    /**
     * 卫计委 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_weijiweiapp/dataList.do
     */
    public static String NO_POOR_PROJECT_WEIJIWEI = WEB_SERVICE_URL + "project_weijiweiapp/dataList.do";

    /**
     * 卫计委详情 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_weijiweiapp/queryxingmuApp.do
     */
    public static String NO_POOR_PROJECT_WEIJIWEI_DETAIL = WEB_SERVICE_URL + "project_weijiweiapp/queryxingmuApp.do";

    /**
     * 县 <br/>
     *  http://101.200.190.254:9100/poverty/app/sys_user/getCountryAdcd.do
     */
    public static String COUNTY = WEB_SERVICE_URL + "sys_user/getCountryAdcd.do";

    /**
     * 镇 <br/>
     *  http://101.200.190.254:9100/poverty/app/sys_user/getTownAdcd.do
     */
    public static String TOWN = WEB_SERVICE_URL + "sys_user/getTownAdcd.do";
    /**
     * 贫困旗县的接口 <br/>
     *  http://101.200.190.254:9100/poverty/app/povertyarea/dataList.do
     */
    public static String Poor_QiXian = WEB_SERVICE_URL + "povertyarea/dataList.do";
    /**
     * 专项扶贫项目管理的接口 <br/>
     *  http://101.200.190.254:9100/poverty/app/projectspecialinfo/dataList.do
     */
    public static String ZhuanXiangFuPinXiangMuGuanLi = WEB_SERVICE_URL + "projectspecialinfo/dataList.do";
    /**
     * 考勤信息的接口 <br/>
     *  http://101.200.190.254:9100/poverty/app/attendance/dataList.do
     */
    public static String KaoQinXinXi = WEB_SERVICE_URL + "attendance/dataList.do";
    /**
     * 考勤信息详情---即帮扶详细信息列表的接口 <br/>
     *  http://101.200.190.254:9100/poverty/app/attendance/getHelprecord
     */
    public static String KaoQinXinXi_Detail = WEB_SERVICE_URL + "attendance/getHelprecord";
    /**
     * 考勤信息详情---即帮扶详细信息webview的接口 <br/>
     *  http://101.200.190.254:9100/poverty/app/helprecords/getModelByIdAndType
     */
    public static String KaoQinXinXi_Detail_Web = WEB_SERVICE_URL + "helprecords/getModelByIdAndType";
    /**
     * 专项资金到账的接口 <br/>
     *  http://101.200.190.254:9100/poverty/app/fundsspecialinfolj/dataList.do
     */
    public static String ZhuanXiangZiJinGongLi = WEB_SERVICE_URL + "fundsspecialinfolj/dataList.do";
    /**
     * 专项资金下拨的接口 <br/>
     *  http://101.200.190.254:9100/poverty/app/fundsspecialinfolj/dataList.do
     */
    public static String ZhuanXiangZiJinXiaBo = WEB_SERVICE_URL + "fundsallocated/dataList.do";
    /**
     * 本级项目管理的接口 <br/>
     *  http://101.200.190.254:9100/poverty/app/fundsbenji/dataList.do
     */
    public static String BenJiXiangMuGuanLi = WEB_SERVICE_URL + "fundsbenji/dataList.do";
    /**
     *  专项资金流向的接口 <br/>
     *  http://101.200.190.254:9100/poverty/app/fundflow/fundflowTree.do
     */
    public static String ZhuanXiangZiJingLiuXiang = WEB_SERVICE_URL + "fundflow/fundflowTree.do";
    /**
     *  专项资金流向二级页面详情的接口 <br/>
     *  http://101.200.190.254:9100/poverty/app/fundflow/fundflowTree2.do
     */
    public static String ZhuanXiangZiJingLiuXiang_2Ji = WEB_SERVICE_URL + "fundflow/fundflowTree2.do";
    /**
     *  专项资金流向三级页面详情的接口 <br/>
     *  http://101.200.190.254:9100/poverty/app/fundflow/fundflowTree3.do
     */
    public static String ZhuanXiangZiJingLiuXiang_3Ji = WEB_SERVICE_URL + "fundflow/fundflowTree3.do";

    /**
     * 村 <br/>
     *  http://101.200.190.254:9100/poverty/app/sys_user/getVillageAdcd.do
     */
    public static String VILLAGE = WEB_SERVICE_URL + "sys_user/getVillageAdcd.do";

    /**
     * 贫困人口统计报表 <br/>
     *  http://101.200.190.254:9100/poverty/app/pkrk/dataList.do
     */
    public static String POOR_PEOPLE_STATISTIC = WEB_SERVICE_URL + "pkrk/dataList.do";

    /**
     * 文化程度统计 <br/>
     *  http://101.200.190.254:9100/poverty/app/wenhua/initEduTabData.do
     */
    public static String POOR_PEOPLE_WHCD = WEB_SERVICE_URL + "wenhua/initEduTabData.do";

    /**
     * 年龄统计 <br/>
     *  http://101.200.190.254:9100/poverty/app/anage/initAgeTabData.do
     */
    public static String POOR_PEOPLE_AGE = WEB_SERVICE_URL + "anage/initAgeTabData.do";

    /**
     * 就业收入统计 <br/>
     *  http://101.200.190.254:9100/poverty/app/jysr/initIncomeTabData.do
     */
    public static String POOR_PEOPLE_WORK = WEB_SERVICE_URL + "jysr/initIncomeTabData.do";

    /**
     * 致贫原因 <br/>
     *  http://101.200.190.254:9100/poverty/app/zpyy/initPieData.do
     */
    public static String POOR_PEOPLE_CASE = WEB_SERVICE_URL + "zpyy/initPieData.do";
    /**
     * 主要致贫原因 <br/>
     *  http://101.200.190.254:9100/poverty/app/zhuyaozhipinyuanyin/initPieData.do
     */
    public static String ZhuYao_POOR_PEOPLE_CASE = WEB_SERVICE_URL + "zhuyaozhipinyuanyin/initPieData.do";
    /**
     * 六个一批饼图 <br/>
     *  http://101.200.190.254:9100/poverty/app/sixbatch/initPieData.do
     */
    public static String LiuGeYiPiTongJi_BingTu = WEB_SERVICE_URL + "sixbatch/initPieData.do";
    /**
     * 劳动能力类型统计饼图 <br/>
     *  http://101.200.190.254:9100/poverty/app/basic_peoplelaborskill/initPieData.do
     */
    public static String LaoDongNengLiLeiXingTongJi_BingTu = WEB_SERVICE_URL + "basic_peoplelaborskill/initPieData.do";
    /**
     * 贫困户属性统计饼图 <br/>
     *  http://101.200.190.254:9100/poverty/app/basic_peopleattribute/initPieData.do
     */
    public static String PinKunHuShuXingTongJi_BingTu = WEB_SERVICE_URL + "basic_peopleattribute/initPieData.do";
    /**
     * 贫困村属性统计饼图 <br/>
     *  http://101.200.190.254:9100/poverty/app/basic_peoplevillage/initPieData.do
     */
    public static String PinKunCunShuXingTongJi_BingTu = WEB_SERVICE_URL + "basic_peoplevillage/initPieData.do";
    /**
     * 务工情况统计饼图 <br/>
     *  http://101.200.190.254:9100/poverty/app/basic_peoplewugong/initPieData.do
     */
    public static String WuGongQingKuangTongJi_BingTu = WEB_SERVICE_URL + "basic_peoplewugong/initPieData.do";

    /**
     * 项目资金使用 <br/>
     *  http://101.200.190.254:9100/poverty/app/xmzjsy/charts.do
     */
    public static String POOR_PEOPEL_PROJECT_MONEY = WEB_SERVICE_URL + "xmzjsy/charts.do";

    /**
     * 项目进度分析 <br/>
     *  http://101.200.190.254:9100/poverty/app/xmjd/chartstu.do
     */
    public static String POOR_PEOPEL_XMJD = WEB_SERVICE_URL + "xmjd/chartstu.do";
    /**
     * 健康状况统计 <br/>
     *  http://101.200.190.254:9100/poverty/app/basic_peoplehealth/initBarData.do
     */
    public static String JianKangZhuangKuangTongJi = WEB_SERVICE_URL + "basic_peoplehealth/initBarData.do";
    /**
     * 在校生状态统计 <br/>
     *  http://101.200.190.254:9100/poverty/app/basic_peoplestudents/initBarData.do
     */
    public static String ZaiXiaoShengZhuangTaiTongJi = WEB_SERVICE_URL + "basic_peoplestudents/initBarData.do";
    /**
     * 低保五保情况统计 <br/>
     *  http://101.200.190.254:9100/poverty/app/dbwb/initBarData.do
     */
    public static String DiBaoWuBaoQingKuangTongJi = WEB_SERVICE_URL + "dbwb/initBarData.do";
    /**
     * 行业项目资金统计 <br/>
     *  http://101.200.190.254:9100/app/usepay/charts.do
     */
    public static String HangYeXiangMuZiJinTongJi = WEB_SERVICE_URL + "usepay/charts.do";

    /**
     * 图片上传 <br/>
     *  http://101.200.190.254:9100/poverty/app/poorfamily/upload.do
     */
    public static String IMAGE_UP_LOAD = WEB_SERVICE_URL + "poorfamily/upload.do";

    public static String VILLAGE_IMAGE_UP_LOAD = WEB_SERVICE_URL + "poorvillage/uploadvillage.do";

    /**
     * 获取行政层级代码 <br/>
     *  http://101.200.190.254:9100/poverty/app/tsad/queryadlcd.do
     */
    public static String CITY_CODE = WEB_SERVICE_URL + "tsad/queryadlcd.do";

    /**
     * 致贫原因详情 <br/>
     *   http://101.200.190.254:9100/poverty/app/zhuyaozhipinyuanyin/dataList.do
     */
    public static String CASE_DETAIL = WEB_SERVICE_URL + "zhuyaozhipinyuanyin/dataList.do";
    /**
     * 六个一批统计详情 <br/>
     *   http://101.200.190.254:9100/poverty/app/sixbatch/dataList.do
     */
    public static String LiuGeYiPiTongJi_DETAIL = WEB_SERVICE_URL + "sixbatch/dataList.do";
    /**
     * 贫困户属性统计详情 <br/>
     *   http://101.200.190.254:9100/poverty/app/basic_peopleattribute/dataList.do
     */
    public static String PinKunHuShuXingTongJi_DETAIL = WEB_SERVICE_URL + "basic_peopleattribute/dataList.do";
    /**
     * 贫困村属性统计详情 <br/>
     *   http://101.200.190.254:9100/poverty/app/basic_peoplevillage/dataList.do
     */
    public static String PinKunCunShuXingTongJi_DETAIL = WEB_SERVICE_URL + "basic_peoplevillage/dataList.do";
    /**
     * 劳动能力类型统计详情 <br/>
     *   http://101.200.190.254:9100/poverty/app/basic_peoplelaborskill/dataList.do
     */
    public static String LaoDongNengLiLeiXingTongJi_DETAIL = WEB_SERVICE_URL + "basic_peoplelaborskill/dataList.do";
    /**
     *  务工情况统计详情 <br/>
     *   http://101.200.190.254:9100/poverty/app/basic_peoplewugong/dataList.do
     */
    public static String WuGongQingKuangTongJi_DETAIL = WEB_SERVICE_URL + "basic_peoplewugong/dataList.do";

    /**
     * 帮扶措施统计 <br/>
     *   http://101.200.190.254:9100/poverty/app/bangfucuoshi/dataList.do
     */
    public static String POOR_PEOPLE_BFCS = WEB_SERVICE_URL + "bangfucuoshi/dataList.do";

    /**
     * 脱贫情况 <br/>
     *  http://101.200.190.254:9100/poverty/app/tuopinqingkuang/dataList.do
     */
    public static String POOR_PROPLE_TPQK = WEB_SERVICE_URL + "tuopinqingkuang/dataList.do";
    /**
     * 扶贫专项资金统计 <br/>
     *  http://101.200.190.254:9100/poverty/app/fundsspecialinfo/dataList.do
     */
    public static String FuPinZhuanXiangZiJing_TongJi = WEB_SERVICE_URL + "fundsspecialinfo/dataList.do";

    /**
     * 民政局 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_minzhengapp/queryminzhenghz.do
     */
    public static String MINZHENGJU_STISTIC = WEB_SERVICE_URL + "project_minzhengapp/queryminzhenghz.do";

    /**
     * 卫计委 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_weijiweiapp/wjwbbhz.do
     */
    public static String WEIJIWEI_STISTIC = WEB_SERVICE_URL + "project_weijiweiapp/wjwbbhz.do";

    /**
     * 教体局 <br/>
     *  http://101.200.190.254:9100/poverty/app/project_jiaoti/queryjiaotihz.do
     */
    public static String JIAOTIJU_STISTIC = WEB_SERVICE_URL + "project_jiaoti/queryjiaotihz.do";
}
