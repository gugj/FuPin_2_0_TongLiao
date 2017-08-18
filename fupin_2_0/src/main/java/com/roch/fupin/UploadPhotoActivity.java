package com.roch.fupin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.MyAdapter;
import com.roch.fupin.adapter.UploadPhotoAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.FileRequestList;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.LogUtil;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin_2_0.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 上传照片页面
 * <p/>
 * Created by Administrator on 2016/7/26.
 */
@ContentView(R.layout.activity_upload_photo)
public class UploadPhotoActivity extends MainBaseActivity {

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;

	@ViewInject(R.id.listview)
	ListView lv_upload;

	@ViewInject(R.id.tv_title)
	TextView tv_title;
	private HttpUtils httpUtils = null;

	private UploadPhotoAdapter adapter;

	private List<com.roch.fupin.entity.UploadPhoto> lists = null;
	/**
	 * 贫困户householderID或贫困村id
	 */
	private String id = "";
	/**
	 * 照片上传的服务器地址：URLs.IMAGE_UP_LOAD <tr/>
	 * 即 http://101.200.190.254:9100/poverty/app/poorfamily/upload.do
	 */
	private String url = "";
	
	private int count_photo = 1;
	private int count_photo_file = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		ViewUtils.inject(this);
		tv_title.setVisibility(View.GONE);
		initToolbar();
		initDate();
	}

	@Override
	protected void onResume() {
		toolbar.setTitle("照片上传");
		super.onResume();
	}
	
	/**
	 * 2016年8月6日
	 * ZhaoDongShao
	 */
	private void initToolbar() {
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowTitleEnabled(false);
		}

		toolbar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				switch (arg0.getItemId()) {
				case R.id.upload: //点击上传按钮
					//上传照片
					UploadPhoto(0);
					break;
				}
				return false;
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.upload, menu);
		return true;
	}

	/**
	 * 标志位---如果是"hu"为贫困户选择照片，如果是"cun"为贫困村选择照片
	 */
	String shangchuan_type;
	private void initDate() {
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra(Common.INTENT_KEY);
		if (bundle != null) {
			id = bundle.getString(Common.TITLE_KEY);
			url = bundle.getString(Common.UP_LOAD_PHOTO_KEY);
			shangchuan_type=bundle.getString("shangchuan_type");

			List<String> photos = bundle.getStringArrayList(Common.BUNDEL_KEY);
			lists = new ArrayList<>();
			for (int i = 0; i < photos.size(); i++) {
				com.roch.fupin.entity.UploadPhoto photo = new com.roch.fupin.entity.UploadPhoto();
				photo.setDownload(false);
				photo.setPath(photos.get(i));
				photo.setProgress(0);
				lists.add(photo);
			}
			adapter = new UploadPhotoAdapter(mContext, lists);
			lv_upload.setAdapter(adapter);
		}
	}

	@SuppressLint("HandlerLeak")
	Handler Handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				UploadPhoto(0);
				break;
			}
		};
	};

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
	 * 上传照片
	 * @param i
	 * 2016年7月28日
	 * ZhaoDongShao
	 */
	protected void UploadPhoto(final int i) {
		lists.get(i).setDownload(true);
		if (i >= lv_upload.getFirstVisiblePosition() && i <= lv_upload.getLastVisiblePosition()) {
			int position = i - lv_upload.getFirstVisiblePosition();
			ProgressBar progressBar = (ProgressBar)lv_upload.getChildAt(position).findViewById(R.id.progressbar);
			TextView tv_loading = (TextView)lv_upload.getChildAt(position).findViewById(R.id.tv_loading);
			TextView tv_progress = (TextView)lv_upload.getChildAt(position).findViewById(R.id.tv_progress);
			tv_loading.setVisibility(View.GONE);
			tv_progress.setVisibility(View.VISIBLE);
			progressBar.setVisibility(View.VISIBLE);
		}
		
		if (httpUtils == null) {
			httpUtils = new HttpUtils();
		}

		RequestParams rp = new RequestParams();
		rp.addBodyParameter("householderid", id);
		rp.addBodyParameter("file", new File(lists.get(i).getPath()));
		LogUtil.println("上传照片时照片的路径为：==="+lists.get(i).getPath());
		//		String[] strings = adapter.getList().get(i).split("\\.");
		//		rp.addBodyParameter("fileSuffix", strings[1]);
		//				rp.addBodyParameter("username", "zhaodongshao");
		//				String url = "http://192.168.1.161:8080/upload/UploadServlet";
		//		String url = "http://192.168.1.109:8080/poverty/app/poorfamily/upload.do";
		httpUtils.configCurrentHttpCacheExpiry(1000 * 10);
		httpUtils.send(HttpRequest.HttpMethod.POST, url, rp, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				FileRequestList list = FileRequestList.parseToT(responseInfo.result, FileRequestList.class);
				if (list != null) {
					if (list.getSuccess()) {
						if (adapter.getCount()>0) {
							adapter.remove(i);
							if (adapter.getCount() > 0) {
								count_photo ++;
								Message message = new Message();
								message.what = 1;
								Handler.sendMessage(message);
							}else {
								MyAdapter.mSelectedImage.clear();
								if(StringUtil.isNotEmpty(shangchuan_type)){
									if("hu".equals(shangchuan_type)){
										Intent intent=new Intent();
										intent.setAction("photo_shangchuan_hu");
										sendBroadcast(intent);
									}else if("cun".equals(shangchuan_type)){
										Intent intent=new Intent();
										intent.setAction("photo_shangchuan_cun");
										sendBroadcast(intent);
									}
								}
								showToast("成功上传" + count_photo + "张照片，重复" + count_photo_file +"张");
							}
						}
					}else {
						adapter.remove(i);
						count_photo_file++;
						Message message = new Message();
						message.what = 1;
						Handler.sendMessage(message);
						showToast(list.getMsg());
					}
				}
				System.out.println("上传成功");
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				System.out.println("上传失败");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				int count = (int) ((current * 1.0 / total) * 100);
				lists.get(i).setDownload(true);
				lists.get(i).setProgress(count);
				if(i >= lv_upload.getFirstVisiblePosition() && i <= lv_upload.getLastVisiblePosition()) {
					int positionInListView = i - lv_upload.getFirstVisiblePosition();
					ProgressBar item = (ProgressBar) lv_upload.getChildAt(positionInListView).findViewById(R.id.progressbar);
					TextView tv_progress = (TextView)lv_upload.getChildAt(positionInListView).findViewById(R.id.tv_progress);
					tv_progress.setText(count + "%");
					item.setProgress(count);
				}
				Log.i("上传 Progress>>>>>", "count=" + count + "--" + current + " / " + total);
			}
		});
	}

}
