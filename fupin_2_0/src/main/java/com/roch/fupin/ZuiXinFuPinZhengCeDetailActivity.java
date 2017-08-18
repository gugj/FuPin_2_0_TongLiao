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
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.FuPinXinWen;
import com.roch.fupin.utils.Common;
import com.roch.fupin_2_0.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 最新扶贫政策详情页activity
 * @author ZhaoDongShao
 * 2016年5月27日 
 */
@ContentView(R.layout.activity_infomation_detail)
public class ZuiXinFuPinZhengCeDetailActivity extends MainBaseActivity{

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
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:  
			MyApplication.getInstance().finishActivity(this);
			break;

		default:
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
			FuPinXinWen fuPinXinWen = (FuPinXinWen) bundle.getSerializable(Common.BUNDEL_KEY);
			String title = bundle.getString(Common.TITLE_KEY);
			if (fuPinXinWen != null) {
				tv_title.setText(title);
				tv_msg_title.setText(fuPinXinWen.getTitle());
				tv_name.setText(fuPinXinWen.getHouseholderNm());
				tv_data.setText(fuPinXinWen.getTs());
				System.out.print("信息公告栏webview的数据为：-------"+fuPinXinWen.getDetail());

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

				Document document = Jsoup.parse(fuPinXinWen.getDetail());
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
	}	
}
