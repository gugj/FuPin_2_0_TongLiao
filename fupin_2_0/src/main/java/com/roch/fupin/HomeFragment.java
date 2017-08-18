package com.roch.fupin;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.MainActivity.MyTouchListener;
import com.roch.fupin.adapter.DragAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.HomeMenu;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.OpenActivityUtil;
import com.roch.fupin.view.DragGrid;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements OnItemClickListener {

	@ViewInject(R.id.userGridView)
	private DragGrid gridView;
	@ViewInject(R.id.rl)
	RelativeLayout layout;

	private DragAdapter adapter;
	Activity activity;

	MyTouchListener myTouchListener;
	// 获取测量后的高度
	int height;

	DbUtils dbUtils;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		this.activity = getActivity();
		this.mContext = getContext();
		ViewUtils.inject(this, view);
		initDate();
		
		myTouchListener = new MyTouchListener() {
			@Override
			public void onTouchEvent(MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					int DownY = (int) event.getY();
					if (DownY - height - MainActivity.StruesHeight > 0 && gridView.isDrag) {
						LogUtils.i("点击的位置在控件之外");
						// Common.isDragfo = false;
						gridView.refresh();
					}
					break;
				}
			}
		};
		((MainActivity) getActivity()).registerMyTouchListener(myTouchListener);

		return view;
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if (!hidden) {
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
	}

	@Override
	public void onResume() {
		super.onResume();
		initDate();
	}

	private void initDate() {
		dbUtils = MyApplication.getInstance().getDbUtilsInstance(Common.LoginName);
		List<HomeMenu> iconInfoList = new ArrayList<HomeMenu>();
		try {
			iconInfoList = dbUtils.findAll(HomeMenu.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
		HomeMenu homeMenu = new HomeMenu();
		homeMenu.setMid(Common.MORE);
		homeMenu.setName("更多");
		homeMenu.setIcon(String.valueOf(R.drawable.tubiao_25));

		iconInfoList.add(homeMenu);
		adapter = new DragAdapter(activity, iconInfoList, gridView);
		gridView.setAdapter(adapter);
		gridView.isDrag = false;
		gridView.setOnItemClickListener(this);
		gridView.setRelativeLayout(layout);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (parent.getId()) {
		case R.id.userGridView:
			HomeMenu dragView = (HomeMenu) parent.getItemAtPosition(position);
			if (dragView != null) {
//				System.out.println("登陆用户名为：：========="+Common.LoginName+"*************"); // 打印出来为：admin
//				System.out.println("登陆用户的ad_cd(判断权限)为：：========="+OpenActivityUtil.getInstance().getisAdl_CD(mContext, Common.LoginName));
//				if (OpenActivityUtil.getInstance().getisAdl_CD(mContext, Common.LoginName)) {
//					startActivity(OpenActivityUtil.getInstance().OpenActivity(mContext, dragView.getName()));
//				} else {
//					ShowToast("请选择您所管辖的城市进行查看");
//				}
				
				// TODO 检查上面的判断条件是否正确****************************************************************
				startActivity(OpenActivityUtil.getInstance().OpenActivity(mContext, dragView.getName()));
				
				// if
				// (dragView.getName().equals(Common.EXTS_HELP_OBJECT_FAMILY_NAME)
				// ||
				// dragView.getName().equals(Common.EXTS_HELP_OBJECT_VILLAGE_NAME))
				// {
				//
				// AdlCode adlCode =
				// MyApplication.getInstance().getSharePreferencesUtilInstance().getNowCity(activity,Common.LoginName);
				// if (adlCode.getAd_cd()==null) {
				// MyApplication.getInstance().getToastUtilsInstance().showNormalToast(activity,
				// "请选择您所要查看的行政区县");
				// }else {
				// if (adlCode.getAd_cd().equals("")) {
				// MyApplication.getInstance().getToastUtilsInstance().showNormalToast(activity,
				// "请选择您所要查看的行政区县");
				// }else {
				//
				// }
				// }
				// }else {
				// startActivity(OpenActivityUtil.getInstance().OpenActivity(activity,
				// dragView.getName()));
				// }
			}
			break;
		}
	}
}
