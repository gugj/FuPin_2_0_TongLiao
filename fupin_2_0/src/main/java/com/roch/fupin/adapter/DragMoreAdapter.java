package com.roch.fupin.adapter;

import android.content.Context;
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
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.HelpObjectMenu;
import com.roch.fupin.entity.HelpSubjectMenu;
import com.roch.fupin.entity.HomeMenu;
import com.roch.fupin.entity.Menu;
import com.roch.fupin.entity.MoreMenu;
import com.roch.fupin.entity.NoPoorProjectMenu;
import com.roch.fupin.entity.NoticeMenu;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.FileUtils;
import com.roch.fupin.utils.GSONUtil;
import com.roch.fupin.view.DragMoreGrid;
import com.roch.fupin_2_0.R;
import java.util.ArrayList;
import java.util.List;

public class DragMoreAdapter extends BaseAdapter {

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
	public List<Menu> channelList;
	/**
	 * 判断是哪个界面
	 */
	private int Type = 0;

	/** TextView 频道内容 */
	class HolderView {
		private TextView item_text;
		private ImageView iv_icon;
		private ImageView iv_add;
	}
	//是否添加
	private boolean isAdd = false;
	/** 要删除的position */
	public int remove_position = -1;

	/**
	 * 隐藏指定item的position
	 */
	private int hidePosition = -1;

	private DragMoreGrid grid;

	private BitmapUtils utils;

	public DragMoreAdapter(Context context, List<Menu> channelList,int type, DragMoreGrid grid) {
		this.context = context;
		this.channelList = channelList;
		this.Type = type;
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
	public Menu getItem(int position) {
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
	public View getView(final int position, final View convertView, ViewGroup parent) {
		HolderView holderView = null;
		View view = null;
		if (view == null) {
			holderView = new HolderView();
			view = LayoutInflater.from(context).inflate(R.layout.gridview_add_itemview, parent,false);
			holderView.iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
			holderView.item_text = (TextView) view.findViewById(R.id.tv_name);
			holderView.iv_add = (ImageView) view.findViewById(R.id.iv_add);

			//重新计算icon的大小
			LayoutParams mLayoutParams = holderView.iv_icon.getLayoutParams();
			mLayoutParams.width = (int) (Common.Width / 8);
			mLayoutParams.height = (int) (Common.Width / 8);
			holderView.iv_icon.setLayoutParams(mLayoutParams);
			view.setTag(holderView);
		}
		holderView = (HolderView)view.getTag();
		final Menu iconInfo = getItem(position);
		utils.display(holderView.iv_icon, iconInfo.getIcon());
		holderView.item_text.setText(iconInfo.getName());
		holderView.iv_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DbUtils dbUtils = MyApplication.getInstance().getDbUtilsInstance(Common.LoginName);
				if (Type == Common.EXTS_MORE) {
					if (!Common.isAnimaEnd) {
						return;
					}
					notifyDataSetChanged();
					grid.deleteInfo(position);
				}else{
					try {
						HomeMenu homeMenu = dbUtils.findFirst(Selector.from(HomeMenu.class).where(WhereBuilder.b("name", "=", iconInfo.getName())));
						if (homeMenu != null) {
							MyApplication.getInstance().getToastUtilsInstance().showNormalToast(context, iconInfo.getName()+"已存在首页，无需再次添加");
							return;
						}
						String helpjson = GSONUtil.objectToJson(iconInfo);
						HomeMenu view = (HomeMenu) GSONUtil.fromJson(helpjson, HomeMenu.class);
						dbUtils.save(view);
						dbUtils.delete(MoreMenu.class, WhereBuilder.b("name", "=", iconInfo.getName()));
						MyApplication.getInstance().getToastUtilsInstance().showNormalToast(context, view.getName()+"已添加至首页[末位]，再次长按可拖动");
					} catch (DbException e) {
						e.printStackTrace();
					}
				}
			}
		});

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
		if (!isAdd) {
			holderView.iv_add.setVisibility(View.GONE);
		}else {
			holderView.iv_add.setVisibility(View.VISIBLE);
		}
		if (isAdd) {
		}
		if (hidePosition == position) {
			view.setVisibility(View.INVISIBLE);
		}else {
			view.setVisibility(View.VISIBLE);
		}
		return view;
	}

	/**
	 * 隐藏指定position
	 * @param position
	 */
	public void setHidePosition(int position) {
		this.hidePosition = position;
		notifyDataSetChanged();
	}

	public void setisDelete(boolean isDelete)
	{
		this.isAdd = isDelete;
	}

	/**
	 * 删除某个position
	 * @param position
	 */
	public void deletInfo(int position) {
		Menu iconInfo = channelList.get(position);
		DbUtils dbUtils = MyApplication.getInstance().getDbUtilsInstance(Common.LoginName);
		try {
			String menujson = GSONUtil.objectToJson(iconInfo);
			HomeMenu menu = (HomeMenu)GSONUtil.fromJson(menujson, HomeMenu.class);
			dbUtils.save(menu);
			dbUtils.delete(MoreMenu.class, WhereBuilder.b("name", "=", iconInfo.getName()));
			MyApplication.getInstance().getToastUtilsInstance().showNormalToast(context, iconInfo.getName()+"已添加至首页[末位]，再次长按可拖动");
		} catch (DbException e) {
			e.printStackTrace();
		}
		channelList.remove(position);
		hidePosition = -1;
		notifyDataSetChanged();
	}

	/** 添加列表 */
	public void addItem(Menu channel) {
		channelList.add(channel);
		notifyDataSetChanged();
	}

	/** 拖动变更item排序 */
	public void exchange(int dragPostion, int dropPostion) {
		holdPosition = dropPostion;
		Menu dragItem = getItem(dragPostion);
		Log.d(TAG, "startPostion=" + dragPostion + ";endPosition=" + dropPostion);
		if (dragPostion < dropPostion) {
			channelList.add(dropPostion + 1, dragItem);
			channelList.remove(dragPostion);
		} else {
			channelList.add(dropPostion, dragItem);
			channelList.remove(dragPostion + 1);
		}
		isChanged = true;
		DbUtils dbUtils = MyApplication.getInstance().getDbUtilsInstance(Common.LoginName);
		if (Type == Common.EXTS_MORE) { //更多
			try {
				dbUtils.deleteAll(MoreMenu.class);
				List<MoreMenu> list = new ArrayList<MoreMenu>();
				for (int i = 0; i < channelList.size(); i++) {
					String menujson = GSONUtil.objectToJson(channelList.get(i));
					MoreMenu more = (MoreMenu)GSONUtil.fromJson(menujson, MoreMenu.class);
					list.add(more);
				}
				dbUtils.saveAll(list);
			} catch (DbException e) {
				e.printStackTrace();
			}
		}else if(Type == Common.EXTS_HELP_SUBJECT){ //帮扶主体
			try {
				dbUtils.deleteAll(HelpSubjectMenu.class);
				List<HelpSubjectMenu> list = new ArrayList<HelpSubjectMenu>();
				for (int i = 0; i < channelList.size(); i++) {
					String menujson = GSONUtil.objectToJson(channelList.get(i));
					HelpSubjectMenu helpSubject = (HelpSubjectMenu)GSONUtil.fromJson(menujson, HelpSubjectMenu.class);
					list.add(helpSubject);
				}
				dbUtils.saveAll(list);
			} catch (DbException e) {
				e.printStackTrace();
			}
		}else if (Type == Common.EXTS_NO_POOR_PROJECT) { //脱贫攻坚项目
			try {
				dbUtils.deleteAll(NoPoorProjectMenu.class);
				List<NoPoorProjectMenu> list = new ArrayList<NoPoorProjectMenu>();
				for (int i = 0; i < channelList.size(); i++) {
					String menujson = GSONUtil.objectToJson(channelList.get(i));
					NoPoorProjectMenu noPoorProject = (NoPoorProjectMenu)GSONUtil.fromJson(menujson, NoPoorProjectMenu.class);
					list.add(noPoorProject);
				}
				dbUtils.saveAll(list);
			} catch (DbException e) {
				e.printStackTrace();
			}
		}else if (Type == Common.EXTS_HELP_OBJECT) { //扶贫对象
			try {
				dbUtils.deleteAll(HelpObjectMenu.class);
				List<HelpObjectMenu> list = new ArrayList<HelpObjectMenu>();
				for (int i = 0; i < channelList.size(); i++) {
					String menujson = GSONUtil.objectToJson(channelList.get(i));
					HelpObjectMenu helpObject = (HelpObjectMenu)GSONUtil.fromJson(menujson, HelpObjectMenu.class);
					list.add(helpObject);
				}
				dbUtils.saveAll(list);
			} catch (DbException e) {
				e.printStackTrace();
			}
		}else if (Type == Common.EXTS_NOTIC) { //通知公告
			try {
				dbUtils.deleteAll(NoticeMenu.class);
				List<NoticeMenu> list = new ArrayList<NoticeMenu>();
				for (int i = 0; i < channelList.size(); i++) {
					String menujson = GSONUtil.objectToJson(channelList.get(i));
					NoticeMenu noticeMenu = (NoticeMenu)GSONUtil.fromJson(menujson, NoticeMenu.class);
					list.add(noticeMenu);
				}
				dbUtils.saveAll(list);
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		hidePosition = dropPostion;
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
}