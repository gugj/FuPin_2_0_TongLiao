package com.roch.fupin.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.entity.Photo;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.FileUtils;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * @author ZhaoDongShao
 * 2016年5月10日
 */
public class PoorHousePhotoAdapter extends BaseAdapter {

	Context mContext;
	List<Photo> lists;
	
	private BitmapDisplayConfig mBitmapDisplayConfig;
	private BitmapUtils utils;

	public PoorHousePhotoAdapter(Context mContext, List<Photo> lists) {
		this.lists = lists;
		this.mContext = mContext;
		//获取应用程序的最大可用内存
		int maxMemory = (int)Runtime.getRuntime().maxMemory();
		int catchSize = maxMemory / 8;
		FileUtils fileUtils = new FileUtils(mContext, Common.CACHE_DIR);
		utils = new BitmapUtils(mContext, fileUtils.getCacheDir(), catchSize);
//		utils.configDefaultLoadingImage(R.drawable.empty_photo);//默认背景图片
//		utils.configDefaultLoadFailedImage(R.drawable.empty_photo);//加载失败图片
		utils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);//设置图片压缩类型

		mBitmapDisplayConfig = new BitmapDisplayConfig();
//		mBitmapDisplayConfig.setShowOriginal(true);
		mBitmapDisplayConfig.setBitmapConfig(Bitmap.Config.RGB_565); //设置图片的最大尺寸, 不设置时根据控件属性自适应
		mBitmapDisplayConfig.setBitmapMaxSize(BitmapCommonUtils.getScreenSize(mContext));
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 0.1f);
		alphaAnimation.setDuration(500);
		mBitmapDisplayConfig.setAnimation(alphaAnimation);
	}

	@Override
	public int getCount() {
		if (lists != null) {
			return lists.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (lists != null) {
			return lists.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		View view = convertView;
		if (view == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.gridview_photo_item, parent, false);
			ViewUtils.inject(viewHolder,view);
			view.setTag(viewHolder);
		}
		else {
			viewHolder = (ViewHolder)view.getTag();
		}

		Photo item = (Photo)getItem(position);
		if (item != null) {
//			String aString = "http://192.168.1.103:8080/poverty/upload/1467179805452.jpg";
//			utils.display(viewHolder.iv_photo, item.getUrl());
			utils.display(viewHolder.iv_photo, item.getUrl(), mBitmapDisplayConfig);
		}
		return view;
	}

	class ViewHolder {
		@ViewInject(R.id.iv_photo)
		ImageView iv_photo;
	}
}
