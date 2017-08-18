package com.roch.fupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.GongZuoRiZhi;
import com.roch.fupin.entity.GongZuoRiZhi_Bean;
import com.roch.fupin.entity.KaoQinXinXi;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.LogUtil;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 工作日志详情页activity
 * @author ZhaoDongShao
 * 2016年5月27日 
 */
@ContentView(R.layout.activity_infomation_detail)
public class GongZuoRiZhiDetailActivity extends MainBaseActivity{

	@ViewInject(R.id.wv_notic)
	WebView wv_notic;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_msg_title)
	TextView tv_msg_title;
	@ViewInject(R.id.tv_date)
	TextView tv_data;
	@ViewInject(R.id.tv_name)
	TextView tv_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initToolbar();
		MyApplication.getInstance().addActivity(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		initData();
	}

	/**
	 * 2016年8月5日
	 * ZhaoDongShao
	 */
	private void initToolbar() {
		toolbar.setTitle("");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowTitleEnabled(false);
		}
		tv_title.setText("帮扶记录详情");
	}
	
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
	 * 2016年5月27日
	 * ZhaoDongShao
	 */
	private void initData() {
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra(Common.INTENT_KEY);
		if (bundle != null) {
			String type = bundle.getString("type");
			if("kaoqin".equals(type)){
				KaoQinXinXi kaoQinXinXi = (KaoQinXinXi) bundle.getSerializable(Common.BUNDEL_KEY);
				requestNetData(kaoQinXinXi);
			}else {
				GongZuoRiZhi gongZuoRiZhi = (GongZuoRiZhi) bundle.getSerializable(Common.BUNDEL_KEY);
//			String title = bundle.getString(Common.TITLE_KEY);
				loadWebView(gongZuoRiZhi);
			}
		}
	}

	private void loadWebView(GongZuoRiZhi gongZuoRiZhi) {
		if (gongZuoRiZhi != null) {
			tv_msg_title.setText("帮扶记录详情");
			tv_name.setText(gongZuoRiZhi.getZhutiname());
			tv_data.setText(gongZuoRiZhi.getHelpdate());
			System.out.print("信息公告栏webview的数据为：-------" + gongZuoRiZhi.getHelpdetail());
			String helpDetail=gongZuoRiZhi.getHelpdetail();

//				WebSettings ws = wv_notic.getSettings();

//				ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//				ws.setUseWideViewPort(true);
//				ws.setLoadWithOverviewMode(true);

			wv_notic.getSettings().setDefaultTextEncodingName("utf-8");// 避免中文乱码
			wv_notic.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

			WebSettings settings=wv_notic.getSettings();
			settings.setJavaScriptEnabled(true);
			settings.setNeedInitialFocus(false);
			settings.setSupportZoom(true);
			settings.setLoadWithOverviewMode(true);//适应屏幕
			settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
			settings.setLoadsImagesAutomatically(true);//自动加载图片
			settings.setCacheMode(WebSettings.LOAD_DEFAULT | WebSettings.LOAD_CACHE_ELSE_NETWORK);

			Document document = Jsoup.parse(helpDetail);
			Elements ele_Img=document.getElementsByTag("img");
			if(ele_Img.size() !=0) {
				for(Element e_Img:ele_Img) {
					e_Img.attr("width","100%");
					//一定要设置 auto 不要控制其高度，让其自己跟随宽度变化情况调整
					e_Img.attr("height","auto");
				}
			}
			String newHtmlContent=document.toString();

			wv_notic.loadDataWithBaseURL(null, newHtmlContent, "text/html", "utf-8", null);
		}
	}

	private void requestNetData(KaoQinXinXi kaoQinXinXi) {
		RequestParams rp = new RequestParams();
		rp.addBodyParameter("helptype", kaoQinXinXi.getHelptype());
		rp.addBodyParameter("id", kaoQinXinXi.getId());
		System.out.println("请求获取考勤信息数据网址：===" + URLs.KaoQinXinXi_Detail_Web + "?id=" + kaoQinXinXi.getId()+"&helptype="+kaoQinXinXi.getHelptype());
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST, URLs.KaoQinXinXi_Detail_Web, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));

	}

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		LogUtil.println("请求考勤帮扶日志成功：===" + str);
		GongZuoRiZhi_Bean gongZuoRiZhi_bean=GongZuoRiZhi_Bean.parseToT(str,GongZuoRiZhi_Bean.class);
		if(StringUtil.isNotEmpty(gongZuoRiZhi_bean)){
			if(gongZuoRiZhi_bean.getSuccess()){
				GongZuoRiZhi gongZuoRiZhi = gongZuoRiZhi_bean.getData();
				if(StringUtil.isNotEmpty(gongZuoRiZhi)){
					loadWebView(gongZuoRiZhi);
				}
			}
		}
	}

	@Override
	public void onFaileResult(String str, int flag) {
		super.onFaileResult(str, flag);
		LogUtil.println("请求考勤帮扶日志成功：===" + str);
		showToast(str);
	}

}
