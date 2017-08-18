package com.roch.fupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.PoorHousePhotoAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.Bfjl_Detail;
import com.roch.fupin.entity.Bfjl_Detail_Img;
import com.roch.fupin.entity.Photo;
import com.roch.fupin.entity.ShowBfjl;
import com.roch.fupin.entity.ShowBfjl_ResultList;
import com.roch.fupin.utils.LogUtil;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 帮扶记录的详情页面，点击帮扶记录的ListView后跳转到此页面
 * 2016年11月1日 
 */
public class ActivityBangFuJiLuHelpDetail extends MainBaseActivity{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;

	/**
	 * 帮扶记录的WebView加载内容
	 */
	@ViewInject(R.id.wv_notic)
	WebView wv_notic;
	
	/**
	 * toolbar的标题title
	 */
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	/**
	 * 帮扶记录的标题
	 */
	@ViewInject(R.id.tv_msg_title)
	TextView tv_msg_title;
	/**
	 * 展示帮扶记录时的位置信息
	 */
	@ViewInject(R.id.tv_location)
	TextView tv_location;
	/**
	 * 展示帮扶记录时的name
	 */
	@ViewInject(R.id.tv_name)
	TextView tv_name;
	/**
	 * 帮扶记录的日期时间
	 */
	@ViewInject(R.id.tv_date)
	TextView tv_date;
	/**
	 * 帮扶记录的内容
	 */
	@ViewInject(R.id.et_bfjl_content)
	EditText et_bfjl_content;
	/**
	 * 展示帮扶记录照片的GridView
	 */
	@ViewInject(R.id.gv_photo)
	GridView gv_photo;

	/**
	 * 帮扶记录jsondata中的id，和helptitle、helpdate同级
	 */
	private String id;
	/**
	 * 标志位---如果为"hu"即为贫困户类型，如果为"cun"即为贫困村类型
	 */
	String type_hu_cun;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bangfujilu_detail);
		ViewUtils.inject(this);
		
		MyApplication.getInstance().addActivity(this);
		
		// 初始化toolbar
		initToolbar();
		tv_title.setText("帮扶记录详情");
		
		// 展示帮扶记录的title和时间date
		String helptitle = getIntent().getStringExtra("helptitle");
		tv_msg_title.setText(helptitle);
		String helpdate = getIntent().getStringExtra("helpdate");
		if(StringUtil.isNotEmpty(helpdate)){
			tv_date.setText(helpdate.split(" ")[0]);
		}

		// 获取请求网络数据的参数，即帮扶详情的id
		id = getIntent().getStringExtra("id");
		type_hu_cun = getIntent().getStringExtra("type_hu_cun");
		//获取传过来的name
		String helpdname = getIntent().getStringExtra("helpdname");
		//获取传过来的位置信息
		String location = getIntent().getStringExtra("location");
		if(StringUtil.isNotEmpty(helpdname)){
			tv_name.setText(helpdname);
		}
		if(StringUtil.isNotEmpty(location)){
			tv_location.setText(location);
		}
		// 请求网络数据，展示帮扶详情
		requestHttpNetData();
	}
	
	/**
	 *请求网络数据，展示帮扶详情
	 * 2016年11月1日
	 */
	private void requestHttpNetData() {
		if("hu".equals(type_hu_cun)){
			RequestParams rp = new RequestParams();
			rp.addBodyParameter("id", id);
			// 通过post请求网络数据，请求参数为户IDhouseholderid
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
					URLs.POOR_HOUSE_BangFuJiLu_bfjlID, rp,
					new MyRequestCallBack(this, MyConstans.FIRST));
			LogUtil.println("贫困户请求服务器帮扶记录详情的网址：==="+URLs.POOR_HOUSE_BangFuJiLu_bfjlID+"&?id="+id);
		}else if("cun".equals(type_hu_cun)){
			RequestParams rp = new RequestParams();
			rp.addBodyParameter("id", id);
			// 通过post请求网络数据，请求参数为户IDhouseholderid
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
					URLs.POOR_Village_BangFuJiLu_bfjlID, rp,
					new MyRequestCallBack(this, MyConstans.FIRST));
			LogUtil.println("贫困户请求服务器帮扶记录详情的网址：==="+URLs.POOR_Village_BangFuJiLu_bfjlID+"&?id="+id);
		}
	}

	List<Photo> lists = new ArrayList<Photo>();
	PoorHousePhotoAdapter mAdapter;
	/**
	 * 请求网络数据成功后调用该方法,解析数据并将帮扶详情的detail展示出来
	 */
	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		switch(flag){
		case MyConstans.FIRST:
			LogUtil.println("帮扶记录请求服务器数据成功：===="+str);
			ShowBfjl_ResultList showBfjl_resultList=ShowBfjl_ResultList.parseToT(str,ShowBfjl_ResultList.class);
			if(null!=showBfjl_resultList && showBfjl_resultList.getSuccess()){
				List<ShowBfjl> jsondata = showBfjl_resultList.getJsondata();
				if(null!=jsondata && jsondata.size()>0){
					//只有一条帮扶记录内容
					List<Bfjl_Detail> bfjl_details = jsondata.get(0).getDetail();
					if(null!=bfjl_details &&bfjl_details.size()>0){
						et_bfjl_content.setText(bfjl_details.get(0).getDetailbf());
					}
					//帮扶记录的照片数量不一定
					List<Bfjl_Detail_Img> bfjl_detail_imgs = jsondata.get(0).getImg();
					if(null!=bfjl_detail_imgs &&bfjl_detail_imgs.size()>0){
						for (int i = 0; i < bfjl_detail_imgs.size(); i++) {
							Photo photo = new Photo();
							photo.setUrl(bfjl_detail_imgs.get(i).getImagepath());
//							photo.setId(poorFamilyPhoto.getId());
							lists.add(photo);
						}
					}
					mAdapter = new PoorHousePhotoAdapter(mContext, lists);
					gv_photo.setAdapter(mAdapter);
					gv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
							//点击照片---加载照片详情
							imageBrower(position, lists);
						}
					});
				}
			}
			break;
		}
	}

	/**
	 * @param position
	 * @param urls
	 * 2016年5月10日
	 * ZhaoDongShao
	 */
	protected void imageBrower(int position, List<Photo> urls) {
		Intent intent = new Intent(mContext, ImagePagerActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(ImagePagerActivity.EXTRA_IMAGE_URLS, (Serializable)urls);
		bundle.putInt(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS_KEY, bundle);
		startActivity(intent);
	}

	@Override
	public void onFaileResult(String str, int flag) {
		super.onFaileResult(str, flag);
		switch (flag){
			case MyConstans.FIRST:
				LogUtil.println("帮扶记录请求服务器数据失败：===="+str);
				showToast(str);
				break;
		}
	}

	/**
	 * 初始化toolbar信息
	 * 2016年11月1日
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
	 * 点击toolbar上的返回箭头时，关闭activity
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

}
