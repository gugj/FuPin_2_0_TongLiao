package com.roch.fupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.DragMoreAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.Menu;
import com.roch.fupin.entity.MoreMenu;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.GSONUtil;
import com.roch.fupin.utils.OpenActivityUtil;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.view.DragMoreGrid;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 九宫格中更多页面
 * @author Administrator
 *
 */
@ContentView(R.layout.activity_more)
public class MoreActivity extends MainBaseActivity implements OnItemClickListener{

	@ViewInject(R.id.tv_title)
	TextView tv_title;

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.gv_menu)
	DragMoreGrid gridView;
	@ViewInject(R.id.rl)
	RelativeLayout rLayout;
	DragMoreAdapter adapter;
	DbUtils dbUtils;

	//获取测量后的gridview高度
	int height;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		MyApplication.getInstance().addActivity(this);
		initToolbar();
		initDate();

		ViewTreeObserver vto = gridView.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onGlobalLayout() {
				gridView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				height = gridView.getHeight();
			}
		});
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
			MyApplication.getInstance().finishActivity(mActivity);
			break;
		}
		return true;
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//获取当前按下的点
			int DownY = (int)event.getY();
			//获取当前GridView的高度
			if (DownY - height - MainActivity.StruesHeight> 0 && gridView.isDrag) {
				LogUtils.i("点击的位置在控件之外");
				gridView.refresh();
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Intent intent = getIntent();
		String title_name = intent.getStringExtra(Common.INTENT_KEY);
		if (title_name != null && !title_name.equals("")) {
			tv_title.setText(title_name);
		}
	}

	/**
	 * 初始化数据
	 */
	private void initDate() {
		dbUtils = MyApplication.getInstance().getDbUtilsInstance(Common.LoginName);
		List<Menu> list2 = new ArrayList<Menu>();
		try {
			//查找更多表中的数据
			List<MoreMenu> iconInfoList = dbUtils.findAll(MoreMenu.class);
			if (!StringUtil.isEmpty(iconInfoList) && iconInfoList.size() > 0) {
				for (int i = 0; i < iconInfoList.size(); i++) {
					Menu dragIconInfo = new Menu();
					String morejson = GSONUtil.objectToJson(iconInfoList.get(i));
					dragIconInfo = (Menu) GSONUtil.fromJson(morejson, Menu.class);
					//					dragIconInfo.setId(iconInfoList.get(i).getId());
					//					dragIconInfo.setName(iconInfoList.get(i).getName());
					//					dragIconInfo.setResid(iconInfoList.get(i).getResid());
					list2.add(dragIconInfo);
				}
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		adapter = new DragMoreAdapter(mActivity, list2,Common.EXTS_MORE,gridView);
		gridView.setAdapter(adapter);
		gridView.setRelativeLayout(rLayout);
		gridView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (parent.getId()) {
		case R.id.gv_menu:
			if (!Common.isAnimaEnd) {
				return;
			}
			Menu dragView = (Menu)parent.getItemAtPosition(position);
			if (dragView != null) {
//				if (OpenActivityUtil.getInstance().getisAdl_CD(mContext, Common.LoginName)) {
//					startActivity(OpenActivityUtil.getInstance().OpenActivity(mContext, dragView.getName()));
//				}else {
//					showToast("请选择您所管辖的城市进行查看");
//				}
				// TODO 检查上面的判断条件是否正确****************************************************************
				startActivity(OpenActivityUtil.getInstance().OpenActivity(mContext, dragView.getName()));
				//				if (dragView.getName().equals(Common.EXTS_HELP_OBJECT_FAMILY_NAME) || dragView.getName().equals(Common.EXTS_HELP_OBJECT_VILLAGE_NAME)) {
				//
				//					AdlCode adlCode = MyApplication.getInstance().getSharePreferencesUtilInstance().getNowCity(mActivity,Common.LoginName);
				//					if (adlCode.getAd_cd()==null) {
				//						MyApplication.getInstance().getToastUtilsInstance().showNormalToast(mActivity, "请选择您所要查看的行政区县");
				//					}else {
				//						if (adlCode.getAd_cd().equals("")) {
				//							MyApplication.getInstance().getToastUtilsInstance().showNormalToast(mActivity, "请选择您所要查看的行政区县");
				//						}else {
				//							startActivity(OpenActivityUtil.getInstance().OpenActivity(mActivity, dragView.getName()));
				//						}
				//					}
				//
				//				}else {
				//					startActivity(OpenActivityUtil.getInstance().OpenActivity(mActivity, dragView.getName()));
				//				}

				//				startActivity(OpenActivityUtil.getInstance().OpenActivity(mActivity, dragView.getName()));
				//				MyApplication.getInstance().getToastUtilsInstance().showNormalToast(mActivity, dragView.getName());
			}
			break;
		}
	}

}
