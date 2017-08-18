package com.roch.fupin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.roch.fupin.adapter.PopupViewAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 贫困户高级查询页面---增加了很多查询条件
 * @author ZhaoDongShao
 * 2017年4月1日 
 */
@ContentView(R.layout.activity_search_gaoji)
public class SearchActivity extends MainBaseActivity implements OnItemClickListener {

	@ViewInject(R.id.tv_house_name)
	EditText tv_house_name;
	
	@ViewInject(R.id.tv_house_phone)
	EditText tv_house_phone;
	
	@ViewInject(R.id.tv_house_renjunnianshouru_start)
	EditText tv_house_renjunnianshouru_start;

	@ViewInject(R.id.tv_house_renjunnianshouru_end)
	EditText tv_house_renjunnianshouru_end;

	@ViewInject(R.id.tv_bangfuren_name)
	EditText tv_bangfuren_name;

	@ViewInject(R.id.tv_house_tuopinqingkuang)
	TextView tv_house_tuopinqingkuang;
	
	@ViewInject(R.id.tv_house_pkhsx)
	TextView tv_house_pkhsx;
	
	@ViewInject(R.id.tv_house_zaixiaosheng)
	TextView tv_house_zaixiaosheng;
	
	@ViewInject(R.id.tv_house_canjiren)
	TextView tv_house_canjiren;
	
//	@ViewInject(R.id.tv_house_jingshenbing)
//	TextView tv_house_jingshenbing;
	
	@ViewInject(R.id.tv_suozaicun_cunshuxing)
	TextView tv_suozaicun_cunshuxing;
	
	@ViewInject(R.id.tv_teshu_manxingbing)
	TextView tv_teshu_manxingbing;
	
	@ViewInject(R.id.tv_zhuyaozhipin_yuanyin)
	TextView tv_zhuyaozhipin_yuanyin;
	
	@ViewInject(R.id.tv_qitazhipin_yuanyin)
	TextView tv_qitazhipin_yuanyin;
	
	@ViewInject(R.id.tv_changjian_manxingbing)
	TextView tv_changjian_manxingbing;
	
	@ViewInject(R.id.tv_bangfucuoshi)
	TextView tv_bangfucuoshi;

	@ViewInject(R.id.tv_tuopinnianfen)
	TextView tv_tuopinnianfen;
	@ViewInject(R.id.tv_yutuopin_nianfe)
	TextView tv_yutuopin_nianfe;

	@ViewInject(R.id.tv_youwuwgongrenyuan)
	TextView tv_youwuwgongrenyuan;

	@ViewInject(R.id.tv_xiangshou_dibao)
	TextView tv_xiangshou_dibao;

	@ViewInject(R.id.tv_xiangshou_wubao)
	TextView tv_xiangshou_wubao;

	@ViewInject(R.id.tv_zhongda_jibing)
	TextView tv_zhongda_jibing;

	@ViewInject(R.id.tv_xingbie)
	TextView tv_xingbie;

	@ViewInject(R.id.tv_youwu_photo)
	TextView tv_youwu_photo;
	@ViewInject(R.id.tv_youwu_bangfu_jilu)
	TextView tv_youwu_bangfu_jilu;

	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	
	@ViewInject(R.id.btn_quxiao)
	Button btn_quxiao;
	
	@ViewInject(R.id.btn_quding)
	Button btn_quding;
	
	/**
     * 筛选条件的集合---贫困户属性
     */
    private List<String> selectors_pkhsx = new ArrayList<String>();
    /**
     * 筛选条件的集合---有无......
     */
    private List<String> selectors_youwu = new ArrayList<String>();
    /**
     * 筛选条件的集合---性别
     */
    private List<String> selectors_xingbie = new ArrayList<String>();
    /**
     * 筛选条件的集合---脱贫情况
     */
    private List<String> selectors_tpqk = new ArrayList<String>();
    /**
     * 筛选条件的集合---所在村的村属性
     */
    private List<String> selectors_csx = new ArrayList<String>();
    /**
     * 筛选条件的集合---帮扶措施
     */
    private List<String> selectors_bfcs = new ArrayList<String>();
    /**
     * 筛选条件的集合---主要致贫原因
     */
    private List<String> selectors_zyzpyy = new ArrayList<String>();
    /**
     * 筛选条件的集合---主要致贫原因
     */
    private List<String> selectors_qtzpyy = new ArrayList<String>();
    /**
     * 筛选条件的集合---脱贫、收入年份
     */
    private List<String> selectors_tpsr = new ArrayList<String>();
    /**
     * 弹出窗口PopupWindow
     */
    private PopupWindow popupWindow;

    /**
     * 弹出窗口PopupWindow中填充的View
     */
    private View popupView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		tv_title.setText("高级查询");
		initToolbar();

		//初始化数据---将筛选条件添加到集合中
        initDataPKHSX();
        //初始化有无...数据---将筛选条件添加到集合中
        initDataYouWu();
        //初始化脱贫情况数据---将筛选条件添加到集合中
        initDataTPQK();
       //初始化脱贫收入年份数据---将筛选条件添加到集合中
        initDataTPSR();
		//初始化所在村的村属性数据---将筛选条件添加到集合中
		initDataCSX();
		//初始化帮扶措施数据---将筛选条件添加到集合中
		initDataBFCS();
		//初始化主要致贫原因数据---将筛选条件添加到集合中
		initDataZYZPYY();
		//初始化其他致贫原因数据---将筛选条件添加到集合中
		initDataQTZPYY();
		//初始化性别数据---将筛选条件添加到集合中
		initDataXingBie();

        //初始化PopupWindow的数据
//        initPopup();
	}

	/**
	 * 初始化toolbar，并显示返回按钮
	 */
	private void initToolbar() {
		toolbar.setTitle("");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowTitleEnabled(false);
		}
	}

	/**
	 * 当点击返回按钮时关闭activity
	 * @param item
	 * @return
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				MyApplication.getInstance().finishActivity(this);
				break;
		}
		return true;
	}

	/**
	 * 初始化其他致贫原因数据---将筛选条件添加到集合中
	 */
	private void initDataQTZPYY() {
		selectors_qtzpyy.add("因病");
		selectors_qtzpyy.add("因残");
		selectors_qtzpyy.add("因学");
		selectors_qtzpyy.add("因灾");
		selectors_qtzpyy.add("因婚");
		selectors_qtzpyy.add("缺土地");
		selectors_qtzpyy.add("缺水");
		selectors_qtzpyy.add("缺技术");
		selectors_qtzpyy.add("缺劳动力");
		selectors_qtzpyy.add("缺资金");
		selectors_qtzpyy.add("交通条件落后");
		selectors_qtzpyy.add("自身发展动力不足");
	}

	/**
	 * 初始化主要致贫原因数据---将筛选条件添加到集合中
	 */
	private void initDataZYZPYY() {
		selectors_zyzpyy.add("因病");
		selectors_zyzpyy.add("因残");
		selectors_zyzpyy.add("因学");
		selectors_zyzpyy.add("因灾");
		selectors_zyzpyy.add("缺土地");
		selectors_zyzpyy.add("缺水");
		selectors_zyzpyy.add("缺技术");
		selectors_zyzpyy.add("缺劳动力");
		selectors_zyzpyy.add("缺资金");
		selectors_zyzpyy.add("交通条件落后");
		selectors_zyzpyy.add("自身发展动力不足");
	}

	/**
	 * 初始化帮扶措施数据---将筛选条件添加到集合中
	 */
	private void initDataBFCS() {
		selectors_bfcs.add("大病救治");
		selectors_bfcs.add("社会保障兜底");
		selectors_bfcs.add("教育扶贫");
		selectors_bfcs.add("产业发展和转移就业");
		selectors_bfcs.add("异地扶贫搬迁");
		selectors_bfcs.add("生态补偿");
	}

	/**
	 * 初始化所在村的村属性数据---将筛选条件添加到集合中
	 */
	private void initDataCSX() {
		selectors_csx.add("贫困村");
		selectors_csx.add("经济薄弱村");
		selectors_csx.add("十二五贫困村");
		selectors_csx.add("十三五贫困村");
		selectors_csx.add("三到村三到户项目村");
		selectors_csx.add("非贫困村");
	}

	private int year=2020;
	/**
	 */
	private void initDataTPSR() {
		for (int i = 0; i < 7; i++) {
			selectors_tpsr.add(String.valueOf(year--));
		}
	}

	/**
	 * 初始化脱贫情况数据---将筛选条件添加到集合中
	 * 2017年4月1日
	 * ZhaoDongShao
	 */
	private void initDataTPQK() {
		selectors_tpqk.add("未脱贫");
		selectors_tpqk.add("已脱贫");
		selectors_tpqk.add("预脱贫");
		selectors_tpqk.add("返贫");
		selectors_tpqk.add("注销");
	}

	/**
	 * 初始化有无...数据---将筛选条件添加到集合中
	 * 2017年4月1日
	 * ZhaoDongShao
	 */
	private void initDataYouWu() {
		selectors_youwu.add("有");
		selectors_youwu.add("无");
	}

	/**
	 * 初始化性别数据---将筛选条件添加到集合中
	 * 2017年4月1日
	 * ZhaoDongShao
	 */
	private void initDataXingBie() {
		selectors_xingbie.add("男");
		selectors_xingbie.add("女");
	}

	/**
     * 初始化贫困户属性数据---将筛选条件添加到集合中
     */
    private void initDataPKHSX() {
    	selectors_pkhsx.add("一般贫困户");
    	selectors_pkhsx.add("低保贫困户");
    	selectors_pkhsx.add("五保贫困户");
    	selectors_pkhsx.add("一般农户");
    }
    
    /**
     * 初始化PopupWindow的数据
     */
    private void initPopup(List<String> selectors) {
        popupView = View.inflate(this, R.layout.view_popup, null);
        ListView lv_popup = (ListView) popupView.findViewById(R.id.lv_popup);
        PopupViewAdapter popupViewAdapter = new PopupViewAdapter(this, selectors);
        lv_popup.setAdapter(popupViewAdapter);
        lv_popup.setOnItemClickListener(this);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 标志位---记录当前点击的筛选条件是哪个
     */
    private int selectTeye=-1;
    
	@OnClick({R.id.tv_house_name, R.id.tv_house_phone, R.id.tv_house_tuopinqingkuang, R.id.tv_house_pkhsx
		, R.id.tv_house_zaixiaosheng, R.id.tv_house_canjiren, R.id.tv_suozaicun_cunshuxing
		, R.id.tv_teshu_manxingbing, R.id.tv_zhuyaozhipin_yuanyin, R.id.tv_qitazhipin_yuanyin, R.id.tv_changjian_manxingbing, R.id.tv_tuopinnianfen,R.id.tv_yutuopin_nianfe, R.id.tv_bangfucuoshi
		, R.id.tv_youwuwgongrenyuan,R.id.tv_youwu_photo, R.id.tv_youwu_bangfu_jilu,R.id.tv_xiangshou_dibao, R.id.tv_xiangshou_wubao, R.id.tv_zhongda_jibing,R.id.tv_xingbie, R.id.btn_quding, R.id.btn_quxiao})
	public void onClick(View view){ //,R.id.tv_house_shilian
		switch (view.getId()) {
		case R.id.tv_house_tuopinqingkuang:
//			showToast("脱贫情况");
			selectTeye= R.id.tv_house_tuopinqingkuang;
			initPopup(selectors_tpqk);
			showPopupWindow();
			break;
			
		case R.id.tv_house_pkhsx:
//			showToast("贫困户属性");
			selectTeye= R.id.tv_house_pkhsx;
			initPopup(selectors_pkhsx);
			showPopupWindow();
			break;
			
		case R.id.tv_tuopinnianfen:
//			showToast("脱贫年份");
			selectTeye= R.id.tv_tuopinnianfen;
			initPopup(selectors_tpsr);
			showPopupWindow();
			break;

		case R.id.tv_yutuopin_nianfe:
//			showToast("预脱贫年份");
			selectTeye= R.id.tv_yutuopin_nianfe;
			initPopup(selectors_tpsr);
			showPopupWindow();
			break;

		case R.id.tv_bangfucuoshi:
//			showToast("帮扶措施");
			selectTeye= R.id.tv_bangfucuoshi;
			initPopup(selectors_bfcs);
			showPopupWindow();
			break;
			
		case R.id.tv_house_zaixiaosheng:
//			showToast("有无在校生");
			selectTeye= R.id.tv_house_zaixiaosheng;
			initPopup(selectors_youwu);
			showPopupWindow();
			break;
			
		case R.id.tv_house_canjiren:
//			showToast("有无残疾人");
			selectTeye= R.id.tv_house_canjiren;
			initPopup(selectors_youwu);
			showPopupWindow();
			break;
			
//		case R.id.tv_house_jingshenbing:
////			showToast("有无精神病");
//			selectTeye= R.id.tv_house_jingshenbing;
//			initPopup(selectors_youwu);
//			showPopupWindow();
//			break;
			
		case R.id.tv_suozaicun_cunshuxing:
//			showToast("所在村的村属性");
			selectTeye= R.id.tv_suozaicun_cunshuxing;
			initPopup(selectors_csx);
			showPopupWindow();
			break;
			
		case R.id.tv_teshu_manxingbing:
//			showToast("有无特殊慢性病人员");
			selectTeye= R.id.tv_teshu_manxingbing;
			initPopup(selectors_youwu);
			showPopupWindow();
			break;
			
		case R.id.tv_zhuyaozhipin_yuanyin:
//			showToast("主要致贫原因");
			selectTeye= R.id.tv_zhuyaozhipin_yuanyin;
			initPopup(selectors_zyzpyy);
			showPopupWindow();
			break;
			
		case R.id.tv_qitazhipin_yuanyin:
//			showToast("其他致贫原因");
			selectTeye= R.id.tv_qitazhipin_yuanyin;
			initPopup(selectors_qtzpyy);
			showPopupWindow();
			break;
			
		case R.id.tv_changjian_manxingbing:
//			showToast("有无常见慢性病");
			selectTeye= R.id.tv_changjian_manxingbing;
			initPopup(selectors_youwu);
			showPopupWindow();
			break;

		case R.id.tv_youwuwgongrenyuan:
//			showToast("有无务工人员");
				selectTeye= R.id.tv_youwuwgongrenyuan;
				initPopup(selectors_youwu);
				showPopupWindow();
				break;

		case R.id.tv_youwu_photo:
//			showToast("有无照片");
				selectTeye= R.id.tv_youwu_photo;
				initPopup(selectors_youwu);
				showPopupWindow();
				break;

		case R.id.tv_youwu_bangfu_jilu:
//			showToast("有无帮扶记录");
				selectTeye= R.id.tv_youwu_bangfu_jilu;
				initPopup(selectors_youwu);
				showPopupWindow();
				break;

		case R.id.tv_xiangshou_dibao:
//			showToast("有无享受低保人员");
				selectTeye= R.id.tv_xiangshou_dibao;
				initPopup(selectors_youwu);
				showPopupWindow();
				break;

		case R.id.tv_xiangshou_wubao:
//			showToast("有无享受五保人员");
				selectTeye= R.id.tv_xiangshou_wubao;
				initPopup(selectors_youwu);
				showPopupWindow();
				break;

		case R.id.tv_zhongda_jibing:
//			showToast("有无重大疾病");
				selectTeye= R.id.tv_zhongda_jibing;
				initPopup(selectors_youwu);
				showPopupWindow();
				break;

		case R.id.tv_xingbie:
//			showToast("性别");
				selectTeye= R.id.tv_xingbie;
				initPopup(selectors_xingbie);
				showPopupWindow();
				break;

		case R.id.btn_quxiao: //取消筛选
			finish();
			break;
			
		case R.id.btn_quding: //确定
			String houseName=tv_house_name.getText().toString();
			String housePhone=tv_house_phone.getText().toString();
			String tv_renjunnianshouru_start=tv_house_renjunnianshouru_start.getText().toString();
			String tv_renjunnianshouru_end=tv_house_renjunnianshouru_end.getText().toString();
			String tv_bangfuren_xingming=tv_bangfuren_name.getText().toString();
			String houseTPQK=tv_house_tuopinqingkuang.getText().toString(); //脱贫情况
			String housePKUSX=tv_house_pkhsx.getText().toString();
			String houseTouPinNianFen=tv_tuopinnianfen.getText().toString(); //脱贫年份
			String yutuopin_nianfen=tv_yutuopin_nianfe.getText().toString(); //预脱贫年份
			String bangfucuoshi=tv_bangfucuoshi.getText().toString();//收入年份
			String houseZaiXiaoSheng=tv_house_zaixiaosheng.getText().toString();
			String houseCanJiRen=tv_house_canjiren.getText().toString();
//			String houseJingShenBing=tv_house_jingshenbing.getText().toString();
			String suozaicun_cunshuxing=tv_suozaicun_cunshuxing.getText().toString();
//			String houseShiLian=tv_house_shilian.getText().toString();
			String teshu_manxingbing=tv_teshu_manxingbing.getText().toString();
			String zhuyaozhipin_yuanyin=tv_zhuyaozhipin_yuanyin.getText().toString();
			String qitazhipin_yuanyin=tv_qitazhipin_yuanyin.getText().toString();
			String changjian_manxingbing=tv_changjian_manxingbing.getText().toString();
			String youwuwgongrenyuan=tv_youwuwgongrenyuan.getText().toString();
			String youwu_photo=tv_youwu_photo.getText().toString();
			String youwu_bangfu_jilu=tv_youwu_bangfu_jilu.getText().toString();
			String xiangshou_dibao=tv_xiangshou_dibao.getText().toString();
			String xiangshou_wubao=tv_xiangshou_wubao.getText().toString();
			String zhongda_jibing=tv_zhongda_jibing.getText().toString();
			String xingbie=tv_xingbie.getText().toString();
//			showToast("大病未选："+houseDaBing);
			if(!StringUtil.isEmpty(tv_renjunnianshouru_start) && !StringUtil.isEmpty(tv_renjunnianshouru_end)){
				if(Double.parseDouble(tv_renjunnianshouru_start)>Double.parseDouble(tv_renjunnianshouru_end)){
					showToast("人均年收入最小值不能大于最大值");
					return;
				}
			}
			Intent intent=new Intent();
			intent.putExtra("houseName", houseName);
			intent.putExtra("housePhone", housePhone);
			intent.putExtra("tv_renjunnianshouru_start", tv_renjunnianshouru_start);
			intent.putExtra("tv_renjunnianshouru_end", tv_renjunnianshouru_end);
			intent.putExtra("tv_bangfuren_xingming", tv_bangfuren_xingming);
			intent.putExtra("houseTPQK", houseTPQK);
			intent.putExtra("housePKUSX", housePKUSX);
			intent.putExtra("houseTouPinNianFen", houseTouPinNianFen);
			intent.putExtra("bangfucuoshi", bangfucuoshi);
			intent.putExtra("houseZaiXiaoSheng", houseZaiXiaoSheng);
			intent.putExtra("houseCanJiRen", houseCanJiRen);
			intent.putExtra("xiangshou_dibao", xiangshou_dibao);
//			intent.putExtra("houseJingShenBing", houseJingShenBing);
			intent.putExtra("suozaicun_cunshuxing", suozaicun_cunshuxing);
//			intent.putExtra("houseShiLian", houseShiLian);
			intent.putExtra("teshu_manxingbing", teshu_manxingbing);
			intent.putExtra("zhuyaozhipin_yuanyin", zhuyaozhipin_yuanyin);
			intent.putExtra("qitazhipin_yuanyin", qitazhipin_yuanyin);
			intent.putExtra("changjian_manxingbing", changjian_manxingbing);
			intent.putExtra("youwuwgongrenyuan", youwuwgongrenyuan);
			intent.putExtra("youwu_photo", youwu_photo);
			intent.putExtra("youwu_bangfu_jilu", youwu_bangfu_jilu);
			intent.putExtra("zhongda_jibing", zhongda_jibing);
			intent.putExtra("xiangshou_wubao", xiangshou_wubao);
			intent.putExtra("xingbie", xingbie);
			intent.putExtra("yutuopin_nianfen", yutuopin_nianfen);
			setResult(2, intent);
			finish();
			break;
			
		default:
			selectTeye=-1;
			break;
		}
	}
	
	 /**
     * 显示PopupWindow
     */
    private void showPopupWindow() {
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(R.style.PopupWindowTimerAnimation);
        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
	
	/**
     * 当PopupWindow中的ListView上的Item被点击时调用此方法
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    	switch (selectTeye) {
    	case R.id.tv_tuopinnianfen:
//			showToast("脱贫年份");
    		tv_tuopinnianfen.setText(selectors_tpsr.get(position));
			break;

    	case R.id.tv_yutuopin_nianfe:
//			showToast("预脱贫年份");
			tv_yutuopin_nianfe.setText(selectors_tpsr.get(position));
			break;

    	case R.id.tv_bangfucuoshi:
//			showToast("帮扶措施");
    		tv_bangfucuoshi.setText(selectors_bfcs.get(position));
			break;
    	
		case R.id.tv_house_tuopinqingkuang:
//			showToast("脱贫情况");
			tv_house_tuopinqingkuang.setText(selectors_tpqk.get(position));
			break;
			
		case R.id.tv_house_pkhsx:
//			showToast("贫困户属性");
			tv_house_pkhsx.setText(selectors_pkhsx.get(position));
			break;
			
		case R.id.tv_house_zaixiaosheng:
//			showToast("有无在校生");
			tv_house_zaixiaosheng.setText(selectors_youwu.get(position));
			break;
			
		case R.id.tv_house_canjiren:
//			showToast("有无残疾人");
			tv_house_canjiren.setText(selectors_youwu.get(position));
			break;
			
//		case R.id.tv_house_jingshenbing:
////			showToast("有无精神病");
//			tv_house_jingshenbing.setText(selectors_youwu.get(position));
//			break;
			
		case R.id.tv_suozaicun_cunshuxing:
//			showToast("所在村的村属性");
			tv_suozaicun_cunshuxing.setText(selectors_csx.get(position));
			break;
			
		case R.id.tv_teshu_manxingbing:
//			showToast("有无特殊慢性病人员");
			tv_teshu_manxingbing.setText(selectors_youwu.get(position));
			break;
			
		case R.id.tv_zhuyaozhipin_yuanyin:
//			showToast("主要致贫原因");
			tv_zhuyaozhipin_yuanyin.setText(selectors_zyzpyy.get(position));
			break;
			
		case R.id.tv_qitazhipin_yuanyin:
//			showToast("其他致贫原因");
			tv_qitazhipin_yuanyin.setText(selectors_qtzpyy.get(position));
			break;
			
		case R.id.tv_changjian_manxingbing:
//			showToast("有无常见慢性病");
			tv_changjian_manxingbing.setText(selectors_youwu.get(position));
			break;

		case R.id.tv_youwuwgongrenyuan:
//			showToast("有无务工人员");
				tv_youwuwgongrenyuan.setText(selectors_youwu.get(position));
				break;

		case R.id.tv_youwu_photo:
//			showToast("有无照片");
			tv_youwu_photo.setText(selectors_youwu.get(position));
				break;

		case R.id.tv_youwu_bangfu_jilu:
//			showToast("有无照片");
			tv_youwu_bangfu_jilu.setText(selectors_youwu.get(position));
				break;

		case R.id.tv_xiangshou_dibao:
//			showToast("有无享受低保人员");
			tv_xiangshou_dibao.setText(selectors_youwu.get(position));
				break;

		case R.id.tv_xiangshou_wubao:
//			showToast("有无享受五保人员");
			tv_xiangshou_wubao.setText(selectors_youwu.get(position));
				break;

		case R.id.tv_zhongda_jibing:
//			showToast("有无重大疾病");
				tv_zhongda_jibing.setText(selectors_youwu.get(position));
				break;

		case R.id.tv_xingbie:
//			showToast("性别");
			tv_xingbie.setText(selectors_xingbie.get(position));
				break;
		}
//        tv_device_name.setText(deviceNames.get(position));
        popupWindow.setFocusable(false);
        popupWindow.dismiss();
    }
	
}
