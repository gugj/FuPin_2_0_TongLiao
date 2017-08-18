/**
 * 
 */
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
import com.roch.fupin.entity.NoPoorProjectMenu;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.GSONUtil;
import com.roch.fupin.utils.OpenActivityUtil;
import com.roch.fupin.view.DragMoreGrid;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 脱贫攻坚项目---改为项目跟踪
 * @author ZhaoDongShao
 * 2016年6月1日
 */
public class NoPoorProjectFragment extends BaseFragment{

	@ViewInject(R.id.gv_menu)
	DragMoreGrid mDragMoreGrid;

	DragMoreAdapter mDragMoreAdapter;

	List<Menu> dragviews;

	MyTouchListener myHomeTouchListener;
	//获取测量后的gridview高度
	int height;

	DbUtils dbUtils;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_more_gridview, container);
		ViewUtils.inject(this, view);
		this.mContext = getContext();

		myHomeTouchListener = new MyTouchListener() {
			@Override
			public void onTouchEvent(MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					//获取当前按下的点
					int DownY = (int)event.getY();
					//获取当前GridView的高
					if (DownY - height - MainActivity.StruesHeight> 0) {
						LogUtils.i("点击的位置在控件之外");
						mDragMoreGrid.refresh();
					}
					break;

				default:
					break;
				}
			}
		};
		((MainActivity)getActivity()).registerMyTouchListener(myHomeTouchListener);

		return view;
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if (!hidden) {
			initDate();
			ViewTreeObserver vto = mDragMoreGrid.getViewTreeObserver();
			vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

				@SuppressWarnings("deprecation")
				@Override
				public void onGlobalLayout() {
					mDragMoreGrid.getViewTreeObserver().removeGlobalOnLayoutListener(this);
					height = mDragMoreGrid.getHeight();
				}
			});
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		initDate();
	}

	/**
	 * 加载数据
	 */
	private void initDate() {
		dragviews = new ArrayList<Menu>();
		dbUtils = MyApplication.getInstance().getDbUtilsInstance(Common.LoginName);
		try {
			List<NoPoorProjectMenu> list = dbUtils.findAll(NoPoorProjectMenu.class);
			if(null!=list){
				for (int i = 0; i < list.size(); i++) {
					String noprroprojectjson = GSONUtil.objectToJson(list.get(i));
					Menu dragIconInfo = (Menu) GSONUtil.fromJson(noprroprojectjson, Menu.class);
					dragviews.add(dragIconInfo);
				}
			}
			mDragMoreAdapter = new DragMoreAdapter(mContext, dragviews, Common.EXTS_NO_POOR_PROJECT,mDragMoreGrid);
			mDragMoreGrid.setAdapter(mDragMoreAdapter);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	@OnItemClick(R.id.gv_menu)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Menu dragView = (Menu)parent.getItemAtPosition(position);
		if (dragView != null) {
//			if (OpenActivityUtil.getInstance().getisAdl_CD(mContext, Common.LoginName)) {
//				startActivity(OpenActivityUtil.getInstance().OpenActivity(mContext, dragView.getName()));
//			}else {
//				ShowToast("请选择您所管辖的城市进行查看");
//			}

			// TODO 检查上面的判断条件是否正确****************************************************************
			startActivity(OpenActivityUtil.getInstance().OpenActivity(mContext, dragView.getName()));
		}
	}

}
