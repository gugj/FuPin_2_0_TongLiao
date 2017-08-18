package com.roch.fupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.HuiFuXinXiAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.FanKuiGuanLi;
import com.roch.fupin.entity.HuiFuXinXi;
import com.roch.fupin.entity.HuiFuXinXi_ResultList;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * 经验交流详情页activity
 * @author ZhaoDongShao
 * 2016年5月27日 
 */
@ContentView(R.layout.activity_xinxifankui_detail)
public class JingYanJiaoLiuDetailActivity extends MainBaseActivity implements View.OnClickListener {

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
	@ViewInject(R.id.tv_replaycount)
	TextView tv_replaycount;
	@ViewInject(R.id.lv_comment_list)
	ListView lv_comment_list;
	@ViewInject(R.id.et_comment)
	EditText et_comment;
	@ViewInject(R.id.tv_send_comment)
	TextView tv_send_comment;
	/**
	 * 点击列表中的条目的id
	 */
	private String id;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initToolbar();
		MyApplication.getInstance().addActivity(this);
		//给发送评论的按钮设置点击事件
		initView();
	}

	/**
	 * 给发送评论的按钮设置点击事件
	 */
	private void initView() {
		tv_send_comment.setOnClickListener(this);
	}

	/**
	 * 请求评论列表数据
	 * @param id 条目的id
	 */
	private void requestCommentListData(String id) {
		RequestParams rp = new RequestParams();
		rp.addBodyParameter("recordid", id);
		System.out.println("经验交流中请求评论列的网址为："+URLs.JingYanJiaoLiu_HuiFu_LIST+"?recordid="+ id);
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
				URLs.JingYanJiaoLiu_HuiFu_LIST, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
	}

	HuiFuXinXi_ResultList huiFuXinXi_resultList;
	HuiFuXinXiAdapter huiFuXinXiAdapter;
	List<HuiFuXinXi> huiFuXinXis;
	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		switch (flag){
			case MyConstans.FIRST:
				System.out.println("获取经验交流中请求评论列的数据成功：==="+str);
				huiFuXinXi_resultList = HuiFuXinXi_ResultList.parseToT(str, HuiFuXinXi_ResultList.class);
				if (!StringUtil.isEmpty(huiFuXinXi_resultList)) {
					if (huiFuXinXi_resultList.getSuccess()) {
						huiFuXinXis = huiFuXinXi_resultList.getJsondata();
						if (!StringUtil.isEmpty(huiFuXinXis)) {
							huiFuXinXiAdapter=new HuiFuXinXiAdapter(mContext,huiFuXinXis);
							lv_comment_list.setAdapter(huiFuXinXiAdapter);
						}
					}
				}
				break;

			case MyConstans.SECOND:
				showToast("发送评论成功");
				System.out.println("发送评论成功：==="+str);
				currentCommentCount++;
				tv_replaycount.setText("共有" + currentCommentCount + "条评论：");
				//发送评论成功以后--请求服务器更新评论列表
				requestCommentListData(id);
				break;

			default:
				break;
		}
	}

	@Override
	public void onFaileResult(String str, int flag) {
		super.onFaileResult(str, flag);
		switch (flag){
			case MyConstans.FIRST:
				System.out.println("获取经验交流中评论列的数据失败：==" + str);
			break;

			case MyConstans.SECOND:
				System.out.println("发送评论失败：==" + str);
			break;

			default:
				break;
		}
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
		}
		return true;
	}

	/**
	 * 当前评论的数量
	 */
	private int currentCommentCount;
	/**
	 * 2016年5月27日
	 * ZhaoDongShao
	 */
	private void initData() {
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra(Common.INTENT_KEY);
		if (bundle != null) {
			FanKuiGuanLi fanKuiGuanLi = (FanKuiGuanLi) bundle.getSerializable(Common.BUNDEL_KEY);
			String title = bundle.getString(Common.TITLE_KEY);
			id = bundle.getString("recordid");
			if (fanKuiGuanLi != null) {
				tv_title.setText(title);
				tv_msg_title.setText(fanKuiGuanLi.getTitle());
				tv_name.setText(fanKuiGuanLi.getPersonname());
				tv_data.setText(fanKuiGuanLi.getTs());

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

				Document document = Jsoup.parse(fanKuiGuanLi.getDetail());
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
				currentCommentCount=fanKuiGuanLi.getReplycount();
				tv_replaycount.setText("共有" + fanKuiGuanLi.getReplycount() + "条评论：");
				//请求评论列表数据
				requestCommentListData(id);
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.tv_send_comment: //点击了发送评论
				showToast("发送评论");
				String comment=et_comment.getText().toString();
				if(StringUtil.isNotEmpty(comment)){
					RequestParams rp = new RequestParams();
					rp.addBodyParameter("recordid", id);
					rp.addBodyParameter("detail", comment);
					System.out.println("发送评论的网址为："+URLs.JingYanJiaoLiu_SendComment+"?recordid="+comment+"&detail="+comment);
					MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
							URLs.JingYanJiaoLiu_SendComment, rp,
							new MyRequestCallBack(this, MyConstans.SECOND));
				}
				et_comment.setText("");
				break;

			default:
				break;
		}
	}
}
