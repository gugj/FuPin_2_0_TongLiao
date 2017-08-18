/**
 * 
 */
package com.roch.fupin.utils;

import java.util.HashMap;
import java.util.Map;

import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.Cjdl;
import com.roch.fupin.entity.Pkcxxh;
import com.roch.fupin.entity.RenkouInfo;
import com.roch.fupin.entity.SerializableMap;
import com.roch.fupin.entity.ShbzInfo;
import com.roch.fupin.entity.Tscy;
import com.roch.fupin.entity.TudiInfo;
import com.roch.fupin.entity.Wfgz;
import com.roch.fupin.entity.Whjs;
import com.roch.fupin.entity.Wsjhsy;
import com.roch.fupin.entity.Ysaq;

/**
 * 台账详情页工具类，防止重复新建Activity
 * 
 * @author ZhaoDongShao
 *
 * 2016年5月25日 
 *
 */
public class AccountPrintUtils {

	/**
	 * 对应的值
	 */
	private static Map<String, String> vMap = null;
	/**
	 * 对应的标识
	 */
	private static Map<String, Map<String, String>> kMap = null;

	/**
	 * 序列化map
	 */
	private static SerializableMap mSerializableMap = new SerializableMap();
	/**
	 * 
	 */
	/**
	 * 根据ID获取字符串
	 *
	 * @param id
	 * @return
	 *
	 * 2016年5月25日
	 *
	 * ZhaoDongShao
	 *
	 */
	private static String getString(int id)
	{
		return ResourceUtil.getInstance().getStringById(id);
	}


	/**
	 * 返回土地信息的有效map
	 *
	 * @param tudiInfo 土地实体类
	 * @return
	 *
	 * 2016年5月25日
	 *
	 * ZhaoDongShao
	 *
	 */
	public static SerializableMap getTudi(TudiInfo tudiInfo){

		//土地信息值
		vMap = new HashMap<String, String>();
		//标识
		kMap = new HashMap<String, Map<String, String>>();

		vMap.put(getString(R.string.gengdi_aear), tudiInfo.getAreagd());
		vMap.put(getString(R.string.tghl_aear), tudiInfo.getAreatghl());
		vMap.put(getString(R.string.linguo_aear), tudiInfo.getArealg());
		vMap.put(getString(R.string.lindi_aear), tudiInfo.getAreald());
		vMap.put(getString(R.string.yxgg_aear), tudiInfo.getAreayxgg());

		kMap.put(Common.KEY_TUDI, vMap);
		mSerializableMap.setMap(kMap);
		return mSerializableMap;
	}


	/**
	 * 返回人口信息的有效map
	 *
	 * @param renkouInfo
	 * @return
	 *
	 * 2016年5月25日
	 *
	 * ZhaoDongShao
	 *
	 */
	public static SerializableMap getRenkou(RenkouInfo renkouInfo){
		//人口信息值
		vMap = new HashMap<String, String>();
		//标识
		kMap = new HashMap<String, Map<String, String>>();

		vMap.put(getString(R.string.hushu_count), renkouInfo.getPopzh());//总户数
		vMap.put(getString(R.string.zrcs_count), renkouInfo.getPopzrc());//自然村数
		vMap.put(getString(R.string.dbhs_count), renkouInfo.getPopdbh());//低保户数
		vMap.put(getString(R.string.wbhs_count), renkouInfo.getPopwbh());//五保户数
		vMap.put(getString(R.string.people_counts), renkouInfo.getPopzr());//总人口数
		vMap.put(getString(R.string.dibao_people_count), renkouInfo.getPopdbr());//低保人口数
		vMap.put(getString(R.string.wb_people_count_), renkouInfo.getPopwbr());//五保人口数
		vMap.put(getString(R.string.canji_count), renkouInfo.getPopcjr());//残疾人口数
		vMap.put(getString(R.string.laodongli_count), renkouInfo.getPopldr());//劳动力人数
		vMap.put(getString(R.string.wcwg_count), renkouInfo.getPopdwg());//外出务工人数

		kMap.put(Common.KEY_TUDI, vMap);
		mSerializableMap.setMap(kMap);
		return mSerializableMap;
	}

	/**
	 * 社会保障信息有效map
	 *
	 * @param shbzInfo
	 * @return
	 *
	 * 2016年5月25日
	 *
	 * ZhaoDongShao
	 *
	 */
	public static SerializableMap getSheHuiBaoZhang(ShbzInfo shbzInfo){
		//社会保障信息值
		vMap = new HashMap<String, String>();
		//标识
		kMap = new HashMap<String, Map<String, String>>();

		vMap.put(getString(R.string.xnh_people_num), shbzInfo.getComncyl());//参加新型农村合作医疗人数
		vMap.put(getString(R.string.czzgylbx_people_num), shbzInfo.getComczzg());//参加城镇职工基本养老保险人数
		vMap.put(getString(R.string.czjmylbx_people_num), shbzInfo.getComncxjm());//参加城乡居民基本养老保险人数

		kMap.put(Common.KEY_TUDI, vMap);
		mSerializableMap.setMap(kMap);
		return mSerializableMap;
	}

	/**
	 * 村级道路有效map
	 *
	 * @param cjdl
	 * @return
	 *
	 * 2016年5月25日
	 *
	 * ZhaoDongShao
	 *
	 */
	public static SerializableMap getCunjiDaolu(Cjdl cjdl) {
		//社会保障信息值
		vMap = new HashMap<String, String>();
		//标识
		kMap = new HashMap<String, Map<String, String>>();

		vMap.put(getString(R.string.sfsn_rode), cjdl.getLoadlqname());//到乡镇是否通沥青（水泥）路
		vMap.put(getString(R.string.sft_bus), cjdl.getLoadkyname());//是否通客运班车

		kMap.put(Common.KEY_TUDI, vMap);
		mSerializableMap.setMap(kMap);
		return mSerializableMap;
	}

	/**
	 * 饮水安全有效信息map
	 *
	 * @param ysaq
	 * @return
	 *
	 * 2016年5月25日
	 *
	 * ZhaoDongShao
	 *
	 */
	public static SerializableMap getYinshui(Ysaq ysaq) {
		//饮水安全信息值
		vMap = new HashMap<String, String>();
		//标识
		kMap = new HashMap<String, Map<String, String>>();

		vMap.put(getString(R.string.no_ysaq), ysaq.getWaterwsx());//未实现饮水安全户数
		vMap.put(getString(R.string.yskn_family), ysaq.getWaterkn());//饮水困难户数

		kMap.put(Common.KEY_TUDI, vMap);
		mSerializableMap.setMap(kMap);
		return mSerializableMap;
	}

	/**
	 * 危房改造信息有效map
	 *
	 * @param wfgz
	 * @return
	 *
	 * 2016年5月25日
	 *
	 * ZhaoDongShao
	 *
	 */
	public static SerializableMap getWeiFang(Wfgz wfgz) {
		//危房改造信息值
		vMap = new HashMap<String, String>();
		//标识
		kMap = new HashMap<String, Map<String, String>>();

		vMap.put(getString(R.string.wfgz), wfgz.getDanwf());//危房户数

		kMap.put(Common.KEY_TUDI, vMap);
		mSerializableMap.setMap(kMap);
		return mSerializableMap;
	}

	/**
	 * 特色产业信息有效map
	 *
	 * @param tscy
	 * @return
	 *
	 * 2016年5月25日
	 *
	 * ZhaoDongShao
	 *
	 */
	public static SerializableMap getTeseChanye(Tscy tscy) {

		//特色产业信息值
		vMap = new HashMap<String, String>();
		//标识
		kMap = new HashMap<String, Map<String, String>>();

		vMap.put(getString(R.string.hezuoshe_count), tscy.getSpecialhzs());//危房户数
		vMap.put(getString(R.string.canjia_hezuoshe_count), tscy.getSpecialcj());
		
		kMap.put(Common.KEY_TUDI, vMap);
		mSerializableMap.setMap(kMap);
		return mSerializableMap;

	}
	
	/**
	 * 卫生和计划生育有效map
	 *
	 * @param wsjhsy
	 * @return
	 *
	 * 2016年5月25日
	 *
	 * ZhaoDongShao
	 *
	 */
	public static SerializableMap getWeiSheng(Wsjhsy wsjhsy) {
		
		vMap = new HashMap<String, String>();
		//标识
		kMap = new HashMap<String, Map<String, String>>();

		vMap.put(getString(R.string.ws_house), wsjhsy.getWsgs());//卫生室个数
		vMap.put(getString(R.string.ws_gonggong_wc_num), wsjhsy.getWswc());//公共厕所个数
		vMap.put(getString(R.string.ws_zhiyeyishi_num), wsjhsy.getWsys());//执业医师数
		vMap.put(getString(R.string.ws_lajiduifang), wsjhsy.getWslj());//垃圾集中堆放点个数
		
		kMap.put(Common.KEY_TUDI, vMap);
		mSerializableMap.setMap(kMap);
		return mSerializableMap;
		
	}
	
	
	
	/**
	 * 文化建设有效map
	 *
	 * @return
	 *
	 * 2016年5月25日
	 *
	 * ZhaoDongShao
	 *
	 */
	public static SerializableMap getWenhua(Whjs whjs){
		
		vMap = new HashMap<String, String>();
		//标识
		kMap = new HashMap<String, Map<String, String>>();

		vMap.put(getString(R.string.tushushi), whjs.getCultss());//行政村文化室个数
		
		kMap.put(Common.KEY_TUDI, vMap);
		mSerializableMap.setMap(kMap);
		return mSerializableMap;
		
	}
	
	/**
	 * 贫困村信息化
	 *
	 * @return
	 *
	 * 2016年5月25日
	 *
	 * ZhaoDongShao
	 *
	 */
	public static SerializableMap getPkcXinxihua(Pkcxxh pkcxxh) {
		
		vMap = new HashMap<String, String>();
		//标识
		kMap = new HashMap<String, Map<String, String>>();

		vMap.put(getString(R.string.tkd_family_num), pkcxxh.getInfokdh());//通宽带户数
		vMap.put(getString(R.string.ysjsw_family_num), pkcxxh.getInfosj());//能用手机上网的户数
		vMap.put(getString(R.string.ytd_tkd_family_count), pkcxxh.getInfotd());//已通电自然村（20户以上）中通宽带的村数
		vMap.put(getString(R.string.tkd_small_school_num), pkcxxh.getInfokdxx());//通宽带的小学个数
		vMap.put(getString(R.string.xzc_xxy_num), pkcxxh.getInfoxxy());//行政村信息员
		
		kMap.put(Common.KEY_TUDI, vMap);
		mSerializableMap.setMap(kMap);
		return mSerializableMap;
		
	}
}
