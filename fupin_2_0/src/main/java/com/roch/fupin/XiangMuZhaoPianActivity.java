package com.roch.fupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.PoorHousePhotoAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.Photo;
import com.roch.fupin.entity.PoorFamilyPhoto;
import com.roch.fupin.entity.XiangMuZhaoPian_ResultList;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目照片页面
 * @author ZhaoDongShao
 * 2017年3月6日 
 */
@ContentView(R.layout.activity_xiangmu_zhaopian)
public class XiangMuZhaoPianActivity extends MainBaseActivity{
	
	@ViewInject(R.id.gv_photo)
	GridView gv_photo;
	@ViewInject(R.id.rl)
	RelativeLayout rl;
	@ViewInject(R.id.tv_notic)
	TextView tv_notic;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	List<Photo> lists = new ArrayList<Photo>();
	
	PoorHousePhotoAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		ViewUtils.inject(this);
		initToolbar();
		initDate();   
	}
	
	/**
	 * 2016年8月5日
	 * ZhaoDongShao
	 */
	private void initToolbar() {
		toolbar.setTitle("");
		tv_title.setText("项目图片");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowTitleEnabled(false);
		}
	}
	
	/**
	 * 初始化数据，请求项目图片数据
	 * 2017年3月6日
	 * ZhaoDongShao
	 */
	private void initDate() {
		String xiangmu_id = getIntent().getStringExtra("xiangmu_id");
		RequestParams rp = new RequestParams();
		rp.addBodyParameter("id", xiangmu_id);
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST, URLs.XiangMu_Photo, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
		System.out.println("请求项目图片的服务器地址为：==="+URLs.XiangMu_Photo+"&?id="+xiangmu_id);
	}

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		System.out.println("请求项目图片的服务器地址成功：==="+str);
		switch (flag) {
		case MyConstans.FIRST:
			XiangMuZhaoPian_ResultList xiangMuZhaoPian_ResultList=XiangMuZhaoPian_ResultList.parseToT(str, XiangMuZhaoPian_ResultList.class);
			if(xiangMuZhaoPian_ResultList.getSuccess()){
				Photo photo = null;
				List<PoorFamilyPhoto> poorFamilyPhotos = xiangMuZhaoPian_ResultList.getJsondata();
				if(null != poorFamilyPhotos && poorFamilyPhotos.size()>0){
					if (lists != null && lists.size() > 0) {
						lists.clear();
					}
					for (PoorFamilyPhoto poorFamilyPhoto : poorFamilyPhotos) {
						photo = new Photo();
						photo.setUrl(poorFamilyPhoto.getImagepath());
						lists.add(photo);
					}
					mAdapter = new PoorHousePhotoAdapter(mContext, lists);
					gv_photo.setAdapter(mAdapter);
					gv_photo.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
							//点击某个图片时进入图片查看器
							imageBrower(position,lists);
						}
					});
				}else {
					gv_photo.setVisibility(View.GONE);
					rl.setVisibility(View.VISIBLE);
					tv_notic.setText("还未上传项目照片");
					showToast("暂无照片");
				}
			}
			break;

		default:
			break;
		}
	}
	
	/**
	 * 点击某个图片时进入图片查看器
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
		showToast(str);
		System.out.println("请求项目图片的服务器地址失败：==="+str);
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
}
