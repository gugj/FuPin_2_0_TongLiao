/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.BitmapUtils;
import com.roch.fupin.entity.UploadPhoto;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.FileUtils;
import com.roch.fupin_2_0.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author ZhaoDongShao
 *
 * 2016年7月27日 
 *
 */
public class UploadPhotoAdapter extends BaseAdapter{

	
	ViewHolder holder = null;
	private List<UploadPhoto> list = new ArrayList<UploadPhoto>();

	private Context mContext;
	private BitmapUtils utils;

	public UploadPhotoAdapter(Context mContext, List<UploadPhoto> list) {
		this.list = list;
		this.mContext = mContext;
		int maxMemory = (int)Runtime.getRuntime().maxMemory();
		int chcheSize = maxMemory / 8;
		FileUtils fileUtil = new FileUtils(mContext, Common.CACHE_DIR);
		utils = new BitmapUtils(mContext, fileUtil.getCacheDir(), chcheSize);
	}


	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (view == null) {

			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.upload_photo_list_item, parent, false);
			holder = new ViewHolder();
			holder.tv_name = (TextView) view.findViewById(R.id.tv_photo_name);
			holder.tv_loading = (TextView) view.findViewById(R.id.tv_loading);
			holder.iv_photo = (ImageView) view.findViewById(R.id.iv_photo);
			holder.tv_progress = (TextView) view.findViewById(R.id.tv_progress);
			holder.progressbar = (ProgressBar) view.findViewById(R.id.progressbar);
			
			LayoutParams mLayoutParams = holder.iv_photo.getLayoutParams();
			mLayoutParams.width = (int) (Common.Width / 5);
			mLayoutParams.height = (int) (Common.Width / 5);
			holder.iv_photo.setLayoutParams(mLayoutParams);
			
			view.setTag(holder);
			
		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		UploadPhoto item = (UploadPhoto) getItem(position);

		if (item != null) {
			
			utils.display(holder.iv_photo, item.getPath());
			String name = item.getPath().substring(item.getPath().lastIndexOf("/") + 1, item.getPath().lastIndexOf("."));
			holder.tv_name.setText(name);
			
			if (item.isDownload()) {
				
				holder.progressbar.setVisibility(View.VISIBLE);
				holder.tv_loading.setVisibility(View.GONE);
				holder.tv_progress.setVisibility(View.VISIBLE);
				
			}else {
				
				holder.progressbar.setVisibility(View.GONE);
				holder.tv_loading.setVisibility(View.VISIBLE);
				holder.tv_progress.setVisibility(View.GONE);
				
			}
		
		}

		return view;
	}

	public class ViewHolder {
		public TextView tv_name;
		public ImageView iv_photo;
		public ProgressBar progressbar;
		public TextView tv_loading;
		public TextView tv_progress;
	}

	/**
	 *
	 * @param i
	 *
	 * 2016年7月28日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void remove(int i) {
		// TODO Auto-generated method stub
		list.remove(i);
		notifyDataSetChanged();
	}
}
