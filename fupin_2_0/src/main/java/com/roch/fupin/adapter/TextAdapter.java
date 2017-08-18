package com.roch.fupin.adapter;

import java.util.List;

import com.roch.fupin.entity.PinKunCun;
import com.roch.fupin_2_0.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TextAdapter extends ArrayAdapter<String> {

	private Context mContext;
	private List<PinKunCun> mListData;
	private String[] mArrayData;
	private int selectedPos = -1;
	private String selectedText = "";
	private int normalDrawbleId;
	private Drawable selectedDrawble;
	private float textSize;
	private OnClickListener onClickListener;
	private OnItemClickListener mOnItemClickListener;

	public TextAdapter(Context context, List<PinKunCun> listData,List<String> super_datas, int sId, int nId) {
		super(context, R.string.no_data, super_datas);
		mContext = context;
		mListData = listData;
		selectedDrawble = mContext.getResources().getDrawable(sId);
		normalDrawbleId = nId;

		init();
	}

	private void init() {
		onClickListener = new OnClickListener() {

			@Override
			public void onClick(View view) {
				selectedPos = (Integer) view.getTag();
				setSelectedPosition(selectedPos);
				if (mOnItemClickListener != null) {
					mOnItemClickListener.onItemClick(view, selectedPos);
				}
			}
		};
	}

	public TextAdapter(Context context, String[] arrayData, int sId, int nId) {
		super(context, R.string.no_data, arrayData);
		mContext = context;
		mArrayData = arrayData;
		selectedDrawble = mContext.getResources().getDrawable(sId);
		normalDrawbleId = nId;
		init();
	}

	/**
	 * 设置选中的position,并通知列表刷新
	 */
	public void setSelectedPosition(int pos) {
		if (mListData != null && pos < mListData.size()) {
			selectedPos = pos;
			if("01".equals(mListData.get(pos).getPoor_type())){
				selectedText = mListData.get(pos).getName()+"(贫)";
			}else {
				selectedText = mListData.get(pos).getName();
			}
			notifyDataSetChanged();
		} else if (mArrayData != null && pos < mArrayData.length) {
			selectedPos = pos;
			selectedText = mArrayData[pos];
			notifyDataSetChanged();
		}

	}

	/**
	 * 设置选中的position,但不通知刷新
	 */
	public void setSelectedPositionNoNotify(int pos) {
		selectedPos = pos;
		if (mListData != null && pos < mListData.size()) {
			if("01".equals(mListData.get(pos).getPoor_type())){
				selectedText = mListData.get(pos).getName()+"(贫)";
			}else {
				selectedText = mListData.get(pos).getName();
			}
		} else if (mArrayData != null && pos < mArrayData.length) {
			selectedText = mArrayData[pos];
		}
	}

	/**
	 * 获取选中的position
	 */
	public int getSelectedPosition() {
		if (mArrayData != null && selectedPos < mArrayData.length) {
			return selectedPos;
		}
		if (mListData != null && selectedPos < mListData.size()) {
			return selectedPos;
		}

		return -1;
	}

	/**
	 * 设置列表字体大小
	 */
	public void setTextSize(float tSize) {
		textSize = tSize;
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view;
		if (convertView == null) {
			view = (TextView) LayoutInflater.from(mContext).inflate(R.layout.choose_item, parent, false);
		} else {
			view = (TextView) convertView;
		}
		view.setTag(position);
		String mString = "";
		if (mListData != null) {
			if (position < mListData.size()) {
				if("01".equals(mListData.get(position).getPoor_type())){
					mString = mListData.get(position).getName()+"(贫)";
				}else {
					mString = mListData.get(position).getName();
				}
			}
		} else if (mArrayData != null) {
			if (position < mArrayData.length) {
				mString = mArrayData[position];
			}
		}
		if (mString.contains("不限"))
			view.setText("不限");
		else
		if(mString.contains("(贫)")){
			view.setText(mString);
			view.setTextColor(Color.RED);
			view.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
		}else {
			view.setText(mString);
			view.setTextColor(Color.BLACK);
			view.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
		}

		if (selectedText != null && selectedText.equals(mString)) {
			if (Build.VERSION.SDK_INT >= 16) {
				view.setBackgroundDrawable(selectedDrawble);//设置选中的背景图片
			}
			else {
				view.setBackgroundDrawable(selectedDrawble);//设置选中的背景图片
			}
		} else {
			if (Build.VERSION.SDK_INT >= 16) {
				view.setBackgroundDrawable(mContext.getResources().getDrawable(normalDrawbleId));//设置未选中状态背景图片
			}
			else {
				view.setBackgroundDrawable(mContext.getResources().getDrawable(normalDrawbleId));//设置未选中状态背景图片
			}
		}
		view.setPadding(20, 0, 0, 0);
		view.setOnClickListener(onClickListener);
		return view;
	}

	public void setOnItemClickListener(OnItemClickListener l) {
		mOnItemClickListener = l;
	}

	/**
	 * 重新定义菜单选项单击接口
	 */
	public interface OnItemClickListener {
		public void onItemClick(View view, int position);
	}

}
