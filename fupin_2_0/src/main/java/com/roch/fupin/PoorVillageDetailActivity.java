package com.roch.fupin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.ViewPagerAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.AddPopWindow.ShowMessageListener;
import com.roch.fupin.entity.ListMenu;
import com.roch.fupin.entity.PoorFamilyPhoto;
import com.roch.fupin.entity.PoorVillage;
import com.roch.fupin.entity.PoorVillageBase;
import com.roch.fupin.entity.PoorVillageListDetailResult;
import com.roch.fupin.utils.BitmapUtil;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.ResourceUtil;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ZhaoDongShao
 * 2016年5月24日
 */
@ContentView(R.layout.activity_poorhouse_detail)
public class PoorVillageDetailActivity extends MainBaseActivity implements ShowMessageListener{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.ll_navibar)
	private LinearLayout layout_title_name;
	@ViewInject(R.id.vp_pager)
	ViewPager viewPager;
	List<Fragment> list = new ArrayList<Fragment>();
	ViewPagerAdapter viewPagerAdapter;
	/**
	 * 当前fragment索引
	 */
	private int currPage = 0;
	/**
	 * table页标题
	 */
	private String[] title_names;
	/**
	 * 所有标题
	 */
	List<View> listviews = new ArrayList<View>();
	Bundle bundle = null;

	//进度条
	private ProgressDialog progressDialog;
	private static final int PRO = 0;
	private static final int MAX_PROGRESS=100;  
	private int progress=10;  

	//照片的路径
	private String photo = null;
	private Uri imageUri = null;

	private static final int CROP = 1;
	PoorVillageBase poorVillageBase = null;
	/**
	 * 贫困村详情activity中自定义的广播---选择照片上传成功后再次请求服务器
	 */
	mybroadCastReceiver mybroadCastReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		//初始化toolbar，并设置 + 号的点击监听
		initToolbar();
		MyApplication.getInstance().addActivity(mActivity);
		//初始化贫困村page页面，并请求服务的贫困村详情数据
		initView();
		//注册广播---选择照片上传成功后再次请求服务器
		registerBroadCast();
	}

	/**
	 * 注册广播---选择照片上传成功后再次请求服务器
	 */
	private void registerBroadCast() {
		IntentFilter intentFilter=new IntentFilter();
		intentFilter.addAction("photo_shangchuan_cun");
		mybroadCastReceiver=new mybroadCastReceiver();
		registerReceiver(mybroadCastReceiver, intentFilter);
	}

	/**
	 * 贫困村详情activity中自定义的广播---选择照片上传成功后再次请求服务器
	 */
	class mybroadCastReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			RequestParams rp = new RequestParams();
			rp.addBodyParameter("id", poorVillageBase.getId());
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
					URLs.POOR_VILLAGE_DETAIL, rp,
					new MyRequestCallBack(PoorVillageDetailActivity.this, MyConstans.THIRD));
		}
	}

	/**
	 * 初始化toolbar，并设置 + 号的点击监听
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
		toolbar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem menuItem) {
				switch (menuItem.getItemId()) {
				case R.id.photo:
//					int xPox = (int)(Common.Width * 0.7 - 10);
//					AddPopWindow window = new AddPopWindow(mActivity,1);
//					window.setShowMessageListener(PoorVillageDetailActivity.this);
//					window.showPopupWindow(toolbar,xPox);
					//点击 + 号直接进入条件帮扶记录页面
					Intent intent=new Intent(mContext,AddBangFuJiLuActivity.class);
					intent.putExtra("house_village_id",poorVillageBase.getId());
					intent.putExtra("shangchuan_type", "cun");
					startActivity(intent);
					break;

				default:
					break;
				}
				return false;
			}
		});
	}

	/**
	 * 点击返回键时关闭activity
	 * @param item
	 * @return
	 */
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
	 * 当fragment显示索引为2时显示 + 号
	 * @param menu
	 * @return
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (currPage == 3) {
			menu.findItem(R.id.photo).setVisible(true);	
		}else {
			menu.findItem(R.id.photo).setVisible(false);
		}
		invalidateOptionsMenu();
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.poor_house_menu, menu);
		return true;
	}

	/**
	 * 初始化贫困村page页面，并请求服务的贫困村详情数据
	 * 2016年5月9日
	 * ZhaoDongShao
	 */
	private void initView() {
		Intent intent = getIntent();
		bundle = intent.getBundleExtra(Common.INTENT_KEY);
		poorVillageBase = (PoorVillageBase) bundle.getSerializable(Common.BUNDEL_KEY);
		if (!StringUtil.isEmpty(poorVillageBase)) {
			tv_title.setText(poorVillageBase.getVillagename());
			RequestParams rp = new RequestParams();
			rp.addBodyParameter("id", poorVillageBase.getId());
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
					URLs.POOR_VILLAGE_DETAIL, rp,
					new MyRequestCallBack(this, MyConstans.FIRST));
			System.out.println("贫困村页面请求服务器照片的网址为：===" + URLs.POOR_VILLAGE_DETAIL+"&?id="+poorVillageBase.getId());
		}

		title_names = ResourceUtil.getInstance().getStringArrayById(R.array.tab_village_title);
		for (int i = 0; i < title_names.length; i++) {
			final TextView tv_title_name = new TextView(mContext);
			tv_title_name.setGravity(Gravity.CENTER);
			tv_title_name.setPadding(5, 5, 5, 5);
			tv_title_name.setTextSize(Common.TEXT_SIZE);
			tv_title_name.setId(i);
			tv_title_name.setText(title_names[i]);
			layout_title_name.addView(tv_title_name, Common.Width / 4,LinearLayout.LayoutParams.WRAP_CONTENT);
			listviews.add(tv_title_name);

			tv_title_name.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (currPage == v.getId()){
					}else {
						viewPager.setCurrentItem(v.getId());
					}
				}
			});
		}
	}

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		switch (flag) {
		case MyConstans.FIRST:
			System.out.println("贫困村页面请求服务器照片成功：==="+str);
			PoorVillageListDetailResult poorVillageListDetailResult = PoorVillageListDetailResult.parseToT(str, PoorVillageListDetailResult.class);
			if (poorVillageListDetailResult != null) {
				if (poorVillageListDetailResult.getSuccess()) {
					List<PoorVillage> lVillages = poorVillageListDetailResult.getJsondata();
					if (!StringUtil.isEmpty(lVillages) && lVillages.size() > 0) {
						for (PoorVillage poorVillage : lVillages) {
							if (!StringUtil.isEmpty(poorVillage)) {
								initPage(poorVillage);
							}
						}
					}
				}
				else {
					ShowNoticDialog();
				}
			}
			break;

		case MyConstans.THIRD:
			System.out.println("贫困村请求服务器选择照片上传成功：==="+str);
			PoorVillageListDetailResult poorVillageListDetailResult1 = PoorVillageListDetailResult.parseToT(str, PoorVillageListDetailResult.class);
			if (poorVillageListDetailResult1 != null) {
				if (poorVillageListDetailResult1.getSuccess()) {
					List<PoorVillage> lVillages = poorVillageListDetailResult1.getJsondata();
					if (!StringUtil.isEmpty(lVillages) && lVillages.size() > 0) {
						List<PoorFamilyPhoto> poorFamilyPhotos=lVillages.get(0).getLe();

						Intent intent=new Intent();
						intent.setAction("photo_shangchuan2");
						intent.putExtra("poorFamilyPhotos", (Serializable) poorFamilyPhotos);
						sendBroadcast(intent);
					}
				} else {
					ShowNoticDialog();
				}
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onFaileResult(String str, int flag) {
		super.onFaileResult(str, flag);
		showToast(str);
		System.out.println("贫困村页面请求服务器照片失败：===" + str);
	}

	private PoorHourseBangFuJiLuFragment poorHourseBangFuJiLuFragment;
	/**
	 * @param poorVillage
	 * 2016年6月12日
	 * ZhaoDongShao
	 */
	@SuppressWarnings("deprecation")
	private void initPage(PoorVillage poorVillage) {
		//1.基本情况fragment
		PoorVillageBaseFragment baseFragment = new PoorVillageBaseFragment();
		bundle = new Bundle();
		bundle.putSerializable(Common.BUNDEL_KEY, poorVillage.getPn());
		baseFragment.setArguments(bundle);

		//2.台账fragment
		PoorVillageAccountPrintFragment printFragment = new PoorVillageAccountPrintFragment();
		bundle = new Bundle();
		bundle.putSerializable(Common.BUNDEL_KEY, poorVillage.getPt());
		printFragment.setArguments(bundle);

		//3.照片fragment
		PoorVillagePhotoFragment photoFragment = new PoorVillagePhotoFragment();
		bundle = new Bundle();
		bundle.putSerializable(Common.BUNDEL_KEY, (Serializable) poorVillage.getLe());
		photoFragment.setArguments(bundle);

		// 4.增加帮扶记录的fragment----------------------------------------------------------
		poorHourseBangFuJiLuFragment = new PoorHourseBangFuJiLuFragment();
		bundle = new Bundle();
		// 将id传到帮扶记录的poorHourseBangFuJiLuFragment中------------------------
		bundle.putSerializable(Common.BUNDEL_KEY, poorVillageBase.getId());
		bundle.putString("type_hu_cun","cun");
		poorHourseBangFuJiLuFragment.setArguments(bundle);

		list.add(baseFragment);
		list.add(printFragment);
		list.add(photoFragment);
		list.add(poorHourseBangFuJiLuFragment);
		viewPagerAdapter = new ViewPagerAdapter(list, PoorVillageDetailActivity.this);
		viewPagerAdapter.setTitle(title_names);
		//默认显示第一页
		viewPager.setOffscreenPageLimit(0);
		viewPager.setAdapter(viewPagerAdapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				for (int i = 0; i < listviews.size(); i++) {
					if (arg0 == i) {
						listviews.get(arg0).setSelected(true);
						TextView textView = (TextView)listviews.get(arg0);
						textView.setTextColor(ResourceUtil.getInstance().getColorById(R.color.bule_155bbb));
						Drawable drawable = ResourceUtil.getInstance().getDrawableByID(R.drawable.blueyes_03);
						textView.setBackgroundDrawable(drawable);
						currPage = arg0;
					}else {
						listviews.get(i).setSelected(false);
						TextView textView = (TextView)listviews.get(i);
						textView.setTextColor(ResourceUtil.getInstance().getColorById(R.color.black));
						Drawable drawable = ResourceUtil.getInstance().getDrawableByID(R.drawable.blusno_03);
						textView.setBackgroundDrawable(drawable);
					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		for (int i = 0; i < listviews.size(); i++) {
			if (currPage == i) {
				listviews.get(currPage).setSelected(true);
				TextView textView = (TextView)listviews.get(currPage);
				textView.setTextColor(ResourceUtil.getInstance().getColorById(R.color.bule_155bbb));
				Drawable drawable = ResourceUtil.getInstance().getDrawableByID(R.drawable.blueyes_03);
				textView.setBackgroundDrawable(drawable);
				viewPager.setCurrentItem(currPage);
			}else {
				listviews.get(i).setSelected(false);
				TextView textView = (TextView)listviews.get(i);
				textView.setTextColor(ResourceUtil.getInstance().getColorById(R.color.black));
				Drawable drawable = ResourceUtil.getInstance().getDrawableByID(R.drawable.blusno_03);
				textView.setBackgroundDrawable(drawable);
			}
		}

	}

	@Override
	public void Message(Object object) {
		ListMenu menu = (ListMenu)object;
		if (menu.getName().equals("照片")) {  //点击选则照片上传
			Intent intent = new Intent(mContext, SelectPhotoActivity.class);
			intent.putExtra(Common.INTENT_KEY, poorVillageBase.getId());
			intent.putExtra("shangchuan_type", "cun");
			intent.putExtra(Common.UP_LOAD_PHOTO_KEY, URLs.VILLAGE_IMAGE_UP_LOAD);
			startActivity(intent);
		}else if (menu.getName().equals("拍照")) { //点击拍照上传
			Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			photo = Common.CACHE_DIR + "/" + UUID.randomUUID().toString() + ".jpg";
			Log.i("photo", photo);
			imageUri = Uri.fromFile(new File(photo));
			Log.i("imageuri", imageUri.getHost());
			intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			startActivityForResult(intentFromCapture, CROP);
		}
	}

	@SuppressLint("HandlerLeak")
	Handler handler=new Handler(){  
		@Override  
		public void handleMessage(Message msg) {  
			super.handleMessage(msg);  
			switch (msg.what) {  
			case PRO: //拍照并上传服务器成功后，再次请求服务器中的贫困村照片
				if (!StringUtil.isEmpty(poorVillageBase)) {
					RequestParams rp = new RequestParams();
					rp.addBodyParameter("id", poorVillageBase.getId());
					MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
							URLs.POOR_VILLAGE_DETAIL, rp,
							new MyRequestCallBack(PoorVillageDetailActivity.this, MyConstans.THIRD));
				}

				if(progress>=MAX_PROGRESS){  
					//重新设置  
					progress=0;  
					progressDialog.dismiss();//销毁对话框  
					showToast("照片上传成功");
				} 
				break;

				case 1:
					progressDialog.dismiss();//销毁对话框
					showToast("照片上传失败");
					break;

			default:  
				break;  
			}
		}  
	};  

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case CROP:
				if (imageUri == null) {
					imageUri = data.getData();
				}
				if (MyApplication.getInstance().getNetConnectInstance().ischeackNet(mContext)) {
					getImageToView(data);
					if (TextUtils.isEmpty(photo)) {
						showToast("剪切照片失败...");
						return;
					}

					progressDialog = new ProgressDialog(this);
					progressDialog.setIcon(R.drawable.ic_launcher);
					progressDialog.setTitle("提示");
					progressDialog.setMessage("正在上传照片...");
					progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					progressDialog.setMax(100);
					progressDialog.show();
					HttpUtils httpUtils = new HttpUtils();
					RequestParams rp = new RequestParams();
					rp.addBodyParameter("householderid", poorVillageBase.getId());
					rp.addBodyParameter("file", new File(photo));

					httpUtils.configCurrentHttpCacheExpiry(1000 * 10);
					httpUtils.send(HttpRequest.HttpMethod.POST, URLs.VILLAGE_IMAGE_UP_LOAD,
							rp, new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							progress = 100;
							handler.sendEmptyMessage(PRO);
							System.out.println("上传成功");
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							handler.sendEmptyMessage(1);
							System.out.println("上传失败");
						}

						@Override
						public void onLoading(long total, long current, boolean isUploading) {
							int count = (int) ((current * 1.0 / total) * 100);
							//必须设置到show之后
							progressDialog.setProgress(count);
							Log.i("上传 Progress>>>>>", "count=" + count + "--" + current + " / " + total);
						}
					});
				}
				break;

			default:
				break;
			}
		}
	}

	/**
	 * 保存照片
	 * @param data
	 * 2016年6月21日
	 * ZhaoDongShao
	 */
	private void getImageToView(Intent data) {
		try {
			Bundle extras = data.getExtras();
			if (extras != null) {
				if (Build.MODEL.equals("M9")) {
					@SuppressWarnings("unused")
					Drawable drawable = Drawable.createFromPath(photo);
				} else {
					Bitmap drawable = extras.getParcelable("data");
					File faceFile = BitmapUtil.saveBitmapTofile(drawable, photo);
					photo = faceFile.getAbsolutePath();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
//			showToast("抱歉，您的手机没有本地图库，无法保存图片!");
		}
	}

}
