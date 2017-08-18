package com.roch.fupin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.roch.fupin.MainActivity.MyTouchListener;
import com.roch.fupin.adapter.DragMoreAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.Menu;
import com.roch.fupin.entity.XinXiFuWuMenu;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.GSONUtil;
import com.roch.fupin.utils.OpenActivityUtil;
import com.roch.fupin.view.DragMoreGrid;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 信息服务
 * @author ZhaoDongShao
 * 2016年5月11日
 */
public class XinXiFuWuFragment extends BaseFragment{

	private List<Menu> listmenus;

	private DragMoreAdapter mAdapter;
	@ViewInject(R.id.gv_menu)
	private DragMoreGrid gridView;


	MyTouchListener myTouchListener;
	//获取测量后的高度
	int height;

	DbUtils dbUtils;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_more_gridview, container);
		ViewUtils.inject(this,view);

		mContext = getContext();

		myTouchListener = new MyTouchListener() {

			@Override
			public void onTouchEvent(MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					int DownY = (int)event.getY();
					if (DownY - height - MainActivity.StruesHeight> 0 && gridView.isDrag) {
						LogUtils.i("点击的位置在控件之外");
//						Common.isDragfo = false;
						gridView.refresh();
					}
					break;
				default:
					break;
				}
			}
		}; 
		((MainActivity)getActivity()).registerMyTouchListener(myTouchListener);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		initData();
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if (!hidden) {
			initData();
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

	/**
	 *初始化数据
	 * 2016年5月16日
	 * ZhaoDongShao
	 */
	private void initData(){
		dbUtil = MyApplication.getInstance().getDbUtilsInstance(Common.LoginName);
		listmenus = new ArrayList<Menu>();
		List<XinXiFuWuMenu> menus;
		try {
			menus = dbUtil.findAll(XinXiFuWuMenu.class);
			if(menus!=null){ //-----------------*************null**************-----------------
				for (int i = 0; i < menus.size(); i++) {
					String noticjson = GSONUtil.objectToJson(menus.get(i));
					XinXiFuWuMenu mXinXiFuWuMenu = (XinXiFuWuMenu) GSONUtil.fromJson(noticjson, XinXiFuWuMenu.class);
					listmenus.add(mXinXiFuWuMenu);
				}
			}
			mAdapter = new DragMoreAdapter(mContext, listmenus, Common.EXTS_NOTIC, gridView);
			gridView.setAdapter(mAdapter);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	@OnItemClick(R.id.gv_menu)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		XinXiFuWuMenu dragView = (XinXiFuWuMenu)parent.getItemAtPosition(position);
		if (dragView != null) {
			startActivity(OpenActivityUtil.getInstance().OpenActivity(mContext, dragView.getName()));
		}
	}
}
