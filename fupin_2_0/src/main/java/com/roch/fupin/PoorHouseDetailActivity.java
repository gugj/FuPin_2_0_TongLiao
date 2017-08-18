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
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.ViewPagerAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.AddPopWindow.ShowMessageListener;
import com.roch.fupin.entity.ListMenu;
import com.roch.fupin.entity.PoorFamily;
import com.roch.fupin.entity.PoorFamilyBase;
import com.roch.fupin.entity.PoorFamilyListDetailResult;
import com.roch.fupin.entity.PoorFamilyPhoto;
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
 * 贫困户详情页面Activity
 * @author ZhaoDongShao
 * 2016年5月9日
 */
@ContentView(R.layout.activity_poorhouse_detail)
public class PoorHouseDetailActivity extends MainBaseActivity implements ShowMessageListener{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.ll_navibar)
	private LinearLayout layout_title_name;
	@ViewInject(R.id.vp_pager)
	ViewPager viewPager;

	//进度条
	private ProgressDialog progressDialog;
	private static final int PRO = 0;
	private static final int MAX_PROGRESS=100;  
	private int progress=10;  
	PoorFamily poorFamily = null;
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

	//照片的路径
	private String photo = null;
	private Uri imageUri = null;

	private static final int CROP = 1;
	/**
	 * 贫困户详情activity中自定义的广播---选择照片上传成功后再次请求服务器
	 */
	mybroadCastReceiver mybroadCastReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		//初始化toolbar
		initToolbar();
		MyApplication.getInstance().addActivity(mActivity);
		//初始化view，并从PoorHouseActivity中获取intent，然后获取Bundle，在获取贫困户所有信息的对象，通过户ID请求网络数据
		initView();
		//注册广播---选择照片上传成功后再次请求服务器
		registerBroadCast();
	}

	/**
	 * 注册广播---选择照片上传成功后再次请求服务器
	 */
	private void registerBroadCast() {
		IntentFilter intentFilter=new IntentFilter();
		intentFilter.addAction("photo_shangchuan_hu");
		mybroadCastReceiver=new mybroadCastReceiver();
		registerReceiver(mybroadCastReceiver, intentFilter);
	}

	/**
	 * 贫困户详情activity中自定义的广播---选择照片上传成功后再次请求服务器
	 */
	class mybroadCastReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			RequestParams rp = new RequestParams();
			rp.addBodyParameter("householderid", poorHouse.getHouseholderid());
			// 通过post请求网络数据，请求参数为户IDhouseholderid
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
					URLs.POOR_HOUSE_DETAIL, rp,
					new MyRequestCallBack(PoorHouseDetailActivity.this, MyConstans.SECOND));
		}
	}

	/**
	 * 初始化toolbar
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
//					window.setShowMessageListener(PoorHouseDetailActivity.this);
//					window.showPopupWindow(toolbar, xPox);
					//点击 + 号直接进入条件帮扶记录页面
					Intent intent=new Intent(mContext,AddBangFuJiLuActivity.class);
					intent.putExtra("house_village_id",poorHouse.getHouseholderid());
					intent.putExtra("shangchuan_type", "hu");
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
	 * 当点击了toolbar的返回箭头时，关闭该activity回到上一级的activity
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
	 * 当fragment的索引是4，即为帮扶记录的fragment时，把上传照片的+号显示出来，来上传照片 <br/> <br/>
	 * 在onCreateOptionsMenu执行后，菜单被显示前调用；如果菜单已经被创建，则在菜单显示前被调用。 同样的，
	 * 返回true则显示该menu,false 则不显示; （可以通过此方法动态的改变菜单的状态，比如加载不同的菜单等）
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (currPage == 4) {
			menu.findItem(R.id.photo).setVisible(true);	 
		}else {
			menu.findItem(R.id.photo).setVisible(false);
		}
		invalidateOptionsMenu();
		return super.onPrepareOptionsMenu(menu);
	}
	
	/**
	 * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
	 * (只会在第一次初始化菜单时调用) 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.poor_house_menu, menu);
		return true;
	}

	/**
	 * 初始化view，并从PoorHouseActivity中获取intent，然后获取Bundle，在获取贫困户所有信息的对象，通过户ID请求网络数据
	 * 2016年5月9日
	 * ZhaoDongShao
	 */
	private void initView() {
		Intent intent = getIntent();
		bundle = intent.getBundleExtra(Common.INTENT_KEY);
		poorHouse = (PoorFamilyBase) bundle.getSerializable(Common.BUNDEL_KEY);
		if (!StringUtil.isEmpty(poorHouse)) {
			tv_title.setText(poorHouse.getPersonname());
			RequestParams rp = new RequestParams();
			rp.addBodyParameter("householderid", poorHouse.getHouseholderid());
			// 通过post请求网络数据，请求参数为户IDhouseholderid
			System.out.println("请求服务的贫困户详情的网址：=="+URLs.POOR_HOUSE_DETAIL+"?householderid="+poorHouse.getHouseholderid());
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST, URLs.POOR_HOUSE_DETAIL, rp,
					new MyRequestCallBack(this, MyConstans.FIRST));
		}

		title_names = ResourceUtil.getInstance().getStringArrayById(R.array.tab_family_title);
		for (int i = 0; i < title_names.length; i++) {
			final TextView tv_title_name = new TextView(mContext);
			tv_title_name.setGravity(Gravity.CENTER);
			tv_title_name.setPadding(5, 5, 5, 5);
			int text_size = Common.TEXT_SIZE;
			tv_title_name.setTextSize(text_size);
			tv_title_name.setId(i);
			tv_title_name.setText(title_names[i]);
			layout_title_name.addView(tv_title_name, Common.Width / 5,LinearLayout.LayoutParams.WRAP_CONTENT);
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

	/**
	 * 当通过户ID请求网络数据成功时调用该方法，这个是Success接口类里面的方法，而当前的父类实现了该接口，
	 * 当前类请求了网络数据，所以就一定会调用该方法
	 * @param str
	 * @param flag
	 * 2016年10月31日
	 */
	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		switch (flag) {
		case MyConstans.FIRST:
			System.out.println("获取到贫困户详情数据成功：==="+str);
			PoorFamilyListDetailResult poorFamilyListResult = PoorFamilyListDetailResult.parseToT(str, PoorFamilyListDetailResult.class);
			// 贫困户总信息页的对象不为空
			if (poorFamilyListResult != null) { 
				// 贫困户总信息页的success字段为true
				if (poorFamilyListResult.getSuccess()) { 
					List<PoorFamily> lFamilies = poorFamilyListResult.getJsondata();
					// 贫困户对象的List集合不为空并且size大于0
					if (!StringUtil.isEmpty(lFamilies) && lFamilies.size() > 0) { 
						for (PoorFamily poorFamily : lFamilies) {
							//	PoorHouseBaseFragment.poorFamily = poorFamily;
							//	PoorFamilyBase lBases = poorFamily.getPn();
							// 贫困户对象不为空
							if (!StringUtil.isEmpty(poorFamily)) {
								this.poorFamily = poorFamily;
								initPage(poorFamily);
							}
						}
					}
				} else {
					// 贫困户总信息页的success字段为false时给出网络错误提示
					ShowNoticDialog();
				}
			}
			break;

			case MyConstans.SECOND:
				System.out.println("贫困户详情页面tab页的数据成功：=="+str);
				PoorFamilyListDetailResult poorFamilyListResult1 = PoorFamilyListDetailResult.parseToT(str, PoorFamilyListDetailResult.class);
				// 贫困户总信息页的对象不为空
				if (poorFamilyListResult1 != null) {
					// 贫困户总信息页的success字段为true
					if (poorFamilyListResult1.getSuccess()) {
						List<PoorFamily> lFamilies = poorFamilyListResult1.getJsondata();
						// 贫困户对象的List集合不为空并且size大于0
						if (!StringUtil.isEmpty(lFamilies) && lFamilies.size() > 0) {
							for (PoorFamily poorFamily : lFamilies) {
								//	PoorHouseBaseFragment.poorFamily = poorFamily;
								//	PoorFamilyBase lBases = poorFamily.getPn();
								// 贫困户对象不为空
								if (!StringUtil.isEmpty(poorFamily)) {
									List<PoorFamilyPhoto> poorFamilyPhotos=poorFamily.getLe();
									Intent intent=new Intent();
									intent.setAction("photo_shangchuan");
									intent.putExtra("poorFamilyPhotos", (Serializable) poorFamilyPhotos);
									sendBroadcast(intent);
								}
							}
						}
					} else {
						// 贫困户总信息页的success字段为false时给出网络错误提示
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
		System.out.println("获取到贫困户详情数据失败：===" + str);
	}

	/**
	 * 初始化五个fragment，并设置默认显示第一个fragment
	 * @param poorFamily
	 * 2016年6月12日
	 * ZhaoDongShao
	 */
	@SuppressWarnings("deprecation")
	private void initPage(PoorFamily poorFamily) {
		// 1.贫困户基本情况的fragment
		PoorHouseBaseFragment baseFragment = new PoorHouseBaseFragment();
		bundle = new Bundle();
		bundle.putSerializable(Common.BUNDEL_KEY, poorFamily.getPn());
		baseFragment.setArguments(bundle);
		
		// 2.家庭成员的fragment
		PoorHouseFamilyPeopleFragment peopleFragment = new PoorHouseFamilyPeopleFragment();
		bundle = new Bundle();
		bundle.putSerializable(Common.BUNDEL_KEY, (Serializable) poorFamily.getPa());
		peopleFragment.setArguments(bundle);
		
		// 3.贫困户台账信息的fragment
		PoorHouseAccountPrintFragment printFragment = new PoorHouseAccountPrintFragment();
		bundle = new Bundle();
		bundle.putSerializable(Common.BUNDEL_KEY, (Serializable) poorFamily.getPia());
		bundle.putString(Common.TITLE_KEY, poorFamily.getPn().getHouseholderid()); //获取户主ID
		printFragment.setArguments(bundle);
		
		// 4.贫困户照片的fragment
		PoorHousePhotoFragment photoFragment = new PoorHousePhotoFragment();
		bundle = new Bundle();
		bundle.putSerializable(Common.BUNDEL_KEY, (Serializable) poorFamily.getLe());
		photoFragment.setArguments(bundle);
		
		// 5.增加帮扶记录的fragment----------------------------------------------------------
		poorHourseBangFuJiLuFragment = new PoorHourseBangFuJiLuFragment();
		bundle = new Bundle();
		// 将householderid传到帮扶记录的poorHourseBangFuJiLuFragment中------------------------
		bundle.putSerializable(Common.BUNDEL_KEY, poorHouse.getHouseholderid());
		bundle.putString("type_hu_cun","hu");
		poorHourseBangFuJiLuFragment.setArguments(bundle);
		
		// 将五个fragment添加到集合中
		list.add(baseFragment);
		list.add(peopleFragment);
		list.add(printFragment);
		list.add(photoFragment);
		list.add(poorHourseBangFuJiLuFragment);
		
		viewPagerAdapter = new ViewPagerAdapter(list, PoorHouseDetailActivity.this);
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

	/**
	 * 当点击toolbar上的+号时，弹出照片和拍照的选项
	 */
	@Override
	public void Message(Object object) {
		ListMenu menu = (ListMenu)object;
		if (menu.getName().equals("照片")) {
			// 打开图片上传的Activity
			Intent intent = new Intent(mContext, SelectPhotoActivity.class);
			intent.putExtra(Common.INTENT_KEY, poorFamily.getPn().getHouseholderid());
			intent.putExtra("shangchuan_type", "hu");
			intent.putExtra(Common.UP_LOAD_PHOTO_KEY, URLs.IMAGE_UP_LOAD);
			startActivity(intent);
//			startActivityForResult(intent,0);
		}else if (menu.getName().equals("拍照")) {
			// 打开照相机
			Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			photo = Common.CACHE_DIR + "/" + UUID.randomUUID().toString() + ".jpg";
			imageUri = Uri.fromFile(new File(photo));
//			Log.i("imageuri", imageUri.getHost());
			intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			startActivityForResult(intentFromCapture, CROP);
		}else if(menu.getName().equals("帮扶记录")){
//			showToast("添加帮扶记录");
			Intent intent=new Intent(mContext,AddBangFuJiLuActivity.class);
			startActivity(intent);
		}
	}

	@SuppressLint("HandlerLeak")
	Handler handler=new Handler(){  
		@Override  
		public void handleMessage(Message msg) {  
			super.handleMessage(msg);  
			switch (msg.what) {  
			case PRO:
				if (!StringUtil.isEmpty(poorHouse)) {
					RequestParams rp = new RequestParams();
					rp.addBodyParameter("householderid", poorHouse.getHouseholderid());
					// 通过post请求网络数据，请求参数为户IDhouseholderid
					MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
							URLs.POOR_HOUSE_DETAIL, rp,
							new MyRequestCallBack(PoorHouseDetailActivity.this, MyConstans.SECOND));
					System.out.println("贫困户详情页面请求的网址为："+URLs.POOR_HOUSE_DETAIL+"?&householderid="+poorHouse.getHouseholderid());
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
	private PoorHourseBangFuJiLuFragment poorHourseBangFuJiLuFragment;
	
	/**
	 *  该poorhouse里面含有householderid 
	 */
	private PoorFamilyBase poorHouse;  

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
					rp.addBodyParameter("householderid", poorFamily.getPn().getHouseholderid());
					rp.addBodyParameter("file", new File(photo));
					//					String[] strings = photo.split("\\.");
					//					rp.addBodyParameter("fileSuffix", strings[1]);
					//					rp.addBodyParameter("username", "zhaodongshao");
					//					String url = "http://192.168.1.161:8080/upload/UploadServlet";

					httpUtils.configCurrentHttpCacheExpiry(1000 * 10);
					httpUtils.send(HttpRequest.HttpMethod.POST, URLs.IMAGE_UP_LOAD, rp, new RequestCallBack<String>() {
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
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mybroadCastReceiver);
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
