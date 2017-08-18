package com.roch.fupin.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.roch.fupin.entity.ImageItem;
import com.roch.fupin.photo.ViewHolder;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends CommonAdapter<ImageItem> {

	/**
	 * 用户选择的图片，存储为图片的完整路径
	 */
	public static List<ImageItem> mSelectedImage = new ArrayList<ImageItem>();

	/**
	 * 文件夹路径
	 */
	private String mDirPath;

	public MyAdapter(Context context, List<ImageItem> mDatas, int itemLayoutId, String dirPath) {
		super(context, mDatas, itemLayoutId);
		this.mDirPath = dirPath;
	}

	@Override
	public void convert(final ViewHolder helper, final ImageItem item, final int position) {
		//设置no_pic
		helper.setImageResource(R.id.id_item_image, R.drawable.pictures_no);
		//设置no_selected
		helper.setImageResource(R.id.id_item_select, R.drawable.checkbox_unselected);
		//设置图片
		helper.setImageByUrl(R.id.id_item_image, mDirPath + "/" + item.getPath());

		final ImageView mImageView = helper.getView(R.id.id_item_image);
		final ImageView mSelect = helper.getView(R.id.id_item_select);

		mImageView.setColorFilter(null);
		//设置ImageView的点击事件
		mImageView.setOnClickListener(new OnClickListener() {
			//选择，则将图片变暗，反之则反之
			@Override
			public void onClick(View v) {
				if (mSelectedImage.size() <= 0) {
					mSelectedImage.add(new ImageItem(mDirPath + "/" + item.getPath(), true));
					mSelect.setImageResource(R.drawable.checkbox_selected);
					mImageView.setColorFilter(Color.parseColor("#77000000"));
				}else if(mSelectedImage.size() < 15){
					ImageItem item1 = new ImageItem();
					item1.setPath(mDirPath + "/" + item.getPath());
					item1.setSelected(true);
					if (mSelectedImage.contains(item1)) {
						mSelectedImage.remove(item1);
						mSelect.setImageResource(R.drawable.checkbox_unselected);
						mImageView.setColorFilter(null);
					}else {
						mSelectedImage.add(item1);
						mSelect.setImageResource(R.drawable.checkbox_selected);
						mImageView.setColorFilter(Color.parseColor("#77000000"));
					}
				}else {
					ImageItem item1 = new ImageItem();
					item1.setPath(mDirPath + "/" + item.getPath());
					item1.setSelected(true);
					if (mSelectedImage.contains(item1)) {
						mSelectedImage.remove(item1);
						mSelect.setImageResource(R.drawable.checkbox_unselected);
						mImageView.setColorFilter(null);
					}else {
						if (mOnItemClickListener != null) {
							mOnItemClickListener.onItemClick(position);
						}
					}
				}
				if (mOnItemClickListener != null) {
					mOnItemClickListener.onItemClick(position);
				}
			}
		});

		//已经选择过的图片，显示出选择过的效果
		for (int i = 0; i < mSelectedImage.size(); i++) {
			if (mSelectedImage.get(i).getPath().equals(mDirPath + "/" + item.getPath())) {
				mSelect.setImageResource(R.drawable.checkbox_selected);
				mImageView.setColorFilter(Color.parseColor("#77000000"));
			}
		}
	}

	private OnItemClickListener mOnItemClickListener;

	public void setOnItemClickListener(OnItemClickListener l) {
		mOnItemClickListener = l;
	}

	public interface OnItemClickListener {
		void onItemClick(int position);
	}

}
