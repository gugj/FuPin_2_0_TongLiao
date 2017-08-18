package com.roch.fupin.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.HomeMenu;
import com.roch.fupin.entity.MoreMenu;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.FileUtils;
import com.roch.fupin.utils.GSONUtil;
import com.roch.fupin.view.DragGrid;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

public class DragAdapter extends BaseAdapter {

	/** TAG*/
	private final static String TAG = "DragAdapter";
	/** 是否显示底部的ITEM */
	private boolean isItemShow = false;
	private Context context;
	/** 控制的postion */
	private int holdPosition;
	/** 是否改变 */
	private boolean isChanged = false;
	/** 是否可见 */
	boolean isVisible = true;
	/** 可以拖动的列表（即用户选择的频道列表） */
	public List<HomeMenu> channelList;

	/** TextView 频道内容 */
	class HolderView {
		private TextView item_text;
		private ImageView iv_icon;
		private ImageView iv_delete;
	}

	private boolean isDelete = false;
	/** 要删除的position */
	public int remove_position = -1;

	private Handler mHandler = new Handler();

	private DragGrid grid;

	/**
	 * 指定隐藏的position
	 */
	private int hideposition = -1;

	private BitmapUtils utils;

	public DragAdapter(Context context, List<HomeMenu> channelList,DragGrid grid) {
		this.context = context;
		this.channelList = channelList;
		this.grid = grid;
		
		int maxMemory = (int)Runtime.getRuntime().maxMemory();
		int chcheSize = maxMemory / 8;
		FileUtils fileUtil = new FileUtils(context, Common.CACHE_DIR);
		utils = new BitmapUtils(context, fileUtil.getCacheDir(), chcheSize);
	}

	@Override
	public int getCount() {
		return channelList == null ? 0 : channelList.size();
	}

	@Override
	public Object getItem(int position) {
		if (channelList != null && channelList.size() != 0) {
			return channelList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		HolderView holderView = null;
		View view = null;
		if (view == null) {
			holderView = new HolderView();
			view = LayoutInflater.from(context).inflate(R.layout.gridview_itemview, parent,false);
			holderView.iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
			holderView.item_text = (TextView) view.findViewById(R.id.tv_name);
			holderView.iv_delete = (ImageView) view.findViewById(R.id.iv_delet);

			LayoutParams mLayoutParams = holderView.iv_icon.getLayoutParams();
			mLayoutParams.width = (int) (Common.Width / 8);
			mLayoutParams.height = (int) (Common.Width / 8);
			holderView.iv_icon.setLayoutParams(mLayoutParams);

			view.setTag(holderView);
		}
		holderView = (HolderView)view.getTag();
		final HomeMenu iconInfo = (HomeMenu) getItem(position);
		utils.display(holderView.iv_icon, iconInfo.getIcon());
//		holderView.iv_icon.setImageResource(iconInfo.getIcon());
		if (iconInfo.getName().equals("更多")) {
			holderView.iv_icon.setImageResource(Integer.parseInt(iconInfo.getIcon()));
		}
		holderView.item_text.setText(iconInfo.getName());
		holderView.iv_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						if (!Common.isAnimaEnd) {
							return;
						}
						notifyDataSetChanged();
						grid.deleteInfo(position);
					}
				});
			}
		});

		if (position == getCount()-1){
			if (convertView == null) {
				convertView = view;
			}
			convertView.setEnabled(false);
			convertView.setFocusable(false);
			convertView.setClickable(false);
		}
		//		DragView channel = getItem(position);
		//		item_text.setText(channel.getName());
		if (isChanged && (position == holdPosition) && !isItemShow) {
			holderView.item_text.setText("");
			holderView.item_text.setSelected(true);
			holderView.item_text.setEnabled(true);
			isChanged = false;
		}
		if (!isVisible && (position == -1 + channelList.size())) {
			holderView.item_text.setText("");
			holderView.item_text.setSelected(true);
			holderView.item_text.setEnabled(true);
		}
		if(remove_position == position){
			deletInfo(position);
		}
		if (!isDelete) {
			holderView.iv_delete.setVisibility(View.GONE);
		}else {
			if (!iconInfo.getName().equals("更多")) {
				holderView.iv_delete.setVisibility(View.VISIBLE);
			}
		}
		if (hideposition == position) {
			view.setVisibility(View.INVISIBLE);
		}else {
			view.setVisibility(View.VISIBLE);
		}
		return view;
	}

	public void setisDelete(boolean isDelete)
	{
		this.isDelete = isDelete;
	}

	/**
	 * 删除某个position
	 * @param position
	 */
	public void deletInfo(int position) {
		HomeMenu iconInfo = channelList.get(position);
		DbUtils dbUtils = MyApplication.getInstance().getDbUtilsInstance(Common.LoginName);
		try {
			dbUtils.delete(iconInfo);
			String homejson = GSONUtil.objectToJson(iconInfo);

			MoreMenu more = (MoreMenu) GSONUtil.fromJson(homejson, MoreMenu.class);
			//			more.setName(iconInfo.getName());
			//			more.setResid(iconInfo.getResid());
			//			more.setId(iconInfo.getId());
			dbUtils.save(more);
			MyApplication.getInstance().getToastUtilsInstance().showNormalToast(context, iconInfo.getName()+"已删除移动至更多，再次长按可拖动");
		} catch (DbException e) {
			e.printStackTrace();
		}
		channelList.remove(position);
		hideposition = -1;
		notifyDataSetChanged();
	}

	/** 添加频道列表 */
	public void addItem(HomeMenu channel) {
		channelList.add(channel);
		notifyDataSetChanged();
	}

	/** 拖动变更频道排序 */
	public void exchange(int dragPostion, int dropPostion) {
		holdPosition = dropPostion;
		HomeMenu homeMenu = (HomeMenu) getItem(dragPostion);
		Log.d(TAG, "startPostion=" + dragPostion + ";endPosition=" + dropPostion);
		if (dragPostion < dropPostion) {
			channelList.add(dropPostion + 1, homeMenu);
			channelList.remove(dragPostion);
		} else {
			channelList.add(dropPostion, homeMenu);
			channelList.remove(dragPostion + 1);
		}
		isChanged = true;
		if (!isDelete) {
			DbUtils dbUtils = MyApplication.getInstance().getDbUtilsInstance(Common.LoginName);
			try {
				dbUtils.deleteAll(HomeMenu.class);
				List<HomeMenu> list = new ArrayList<HomeMenu>();
				for (int i = 0; i < channelList.size(); i++) {

					String menujson = GSONUtil.objectToJson(channelList.get(i));

					HomeMenu more = (HomeMenu) GSONUtil.fromJson(menujson, HomeMenu.class);
					//					more.setName(channelList.get(i).getName());
					//					more.setId(channelList.get(i).getId());
					//					more.setResid(channelList.get(i).getResid());
					list.add(more);
				}
				dbUtils.saveAll(list);
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		hideposition = dropPostion;
		notifyDataSetChanged();
	}

	/** 获取是否可见 */
	public boolean isVisible() {
		return isVisible;
	}

	/** 设置是否可见 */
	public void setVisible(boolean visible) {
		isVisible = visible;
	}
	/** 显示放下的ITEM */
	public void setShowDropItem(boolean show) {
		isItemShow = show;
	}
	/**
	 * 设置隐藏指定item
	 * @param position
	 */
	public void setHidePosition(int position) {
		this.hideposition = position;
		notifyDataSetChanged();
	}
}