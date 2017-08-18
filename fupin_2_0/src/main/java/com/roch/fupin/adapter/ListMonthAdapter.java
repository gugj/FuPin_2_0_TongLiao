/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.roch.fupin.utils.ResourceUtil;
import com.roch.fupin_2_0.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author ZhaoDongShao
 *
 * 2016年7月20日 
 *
 */
public class ListMonthAdapter extends ArrayAdapter<String>{

	private Context mContext;
	private List<String> mListData;
	private String[] mArrayData;
	private int selectedPos = 0;
	private String selectedText = "";
	private int normalDrawbleId;
	private Drawable selectedDrawble;
	private float textSize;
	private OnClickListener onClickListener;
	private OnItemClickListener mOnItemClickListener;

	public ListMonthAdapter(Context context, List<String> listData, int sId, int nId) {
		super(context, R.string.no_data, listData);
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
				
				ViewHouder viewHouder = (ViewHouder) view.getTag();
				selectedPos = (Integer) viewHouder.tv_month.getTag();
				setSelectedPosition(selectedPos);
				if (mOnItemClickListener != null) {
					mOnItemClickListener.onItemClick(viewHouder.tv_month.getText().toString().trim(), selectedPos);
				}
			}
		};
	}

	public ListMonthAdapter(Context context, String[] arrayData, int sId, int nId) {
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
			selectedText = mListData.get(pos);
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
			selectedText = mListData.get(pos);
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
		View view = convertView;
		ViewHouder viewHouder = null;
		if (view == null) {
			viewHouder = new ViewHouder();
			view = LayoutInflater.from(mContext).inflate(R.layout.listview_fragment_poorhouse_accountprint_month_item, parent, false);
			viewHouder.tv_month = (TextView)view.findViewById(R.id.tv_month);
			view.setTag(viewHouder);
		} else {
			viewHouder = (ViewHouder) convertView.getTag();
		}
		viewHouder.tv_month.setTag(position);
		String mString = "";
		if (mListData != null) {
			if (position < mListData.size()) {
				mString = mListData.get(position);
			}
		} else if (mArrayData != null) {
			if (position < mArrayData.length) {
				mString = mArrayData[position];
			}
		}
		//		if (mString.contains("不限"))
		//			view.setText("不限");
		//		else
		viewHouder.tv_month.setText(mString);
		if (textSize != 0.0) {
			viewHouder.tv_month.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
		}
		if (selectedText != null && selectedText.equals(mString)) {
			if (Build.VERSION.SDK_INT >= 16) {

				view.setBackgroundDrawable(selectedDrawble);//设置选中的背景图片
				viewHouder.tv_month.setBackgroundColor(ResourceUtil.getInstance().getColorById(R.color.color_d1d1d1));
			}
			else {

				view.setBackgroundDrawable(selectedDrawble);//设置选中的背景图片
				viewHouder.tv_month.setBackgroundColor(ResourceUtil.getInstance().getColorById(R.color.color_d1d1d1));
			}
		} else {
			if (Build.VERSION.SDK_INT >= 16) {
				view.setBackgroundDrawable(mContext.getResources().getDrawable(normalDrawbleId));//设置未选中状态背景图片
				viewHouder.tv_month.setBackgroundColor(Color.WHITE);
			}
			else {
				view.setBackgroundDrawable(mContext.getResources().getDrawable(normalDrawbleId));//设置未选中状态背景图片
				viewHouder.tv_month.setBackgroundColor(Color.WHITE);
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
		public void onItemClick(String month, int position);
	}

	class ViewHouder{
		
		TextView tv_month;
		
	}

}
