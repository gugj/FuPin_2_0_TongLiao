package com.roch.fupin.view;

import java.util.List;

import com.roch.fupin_2_0.R;
import com.roch.fupin.adapter.TextAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MenuRight extends RelativeLayout implements ViewBaseAction{

	private ListView mListView;
//	private final String[] items = new String[] { "item1", "item2", "item3", "item4", "item5", "item6" };//显示字段
//	private final String[] itemsVaule = new String[] { "1", "2", "3", "4", "5", "6" };//隐藏id
		
	String[] items;
	String[] itemsVaule;
	private OnSelectListener mOnSelectListener;
	private TextAdapter adapter;
	private String mDistance;
	private String showText = "不限";
		
	Context mContext;
	public String getShowText() {
		return showText;
	}

	public MenuRight(Context context) {
		super(context);
		init(context);
	}

	public MenuRight(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public MenuRight(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	@SuppressWarnings("deprecation")
	private void init(Context context) {
		mContext = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.menu_right, this, true);
		setBackgroundDrawable(getResources().getDrawable(R.drawable.choosearea_bg));
		mListView = (ListView) findViewById(R.id.listView);
	}

	/**
	 * 获取菜单筛选数据
	 *
	 * @param list
	 *
	 * 2016年5月30日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void setStringArray(List<String> list){
		items = new String[list.size()];
		itemsVaule = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			items[i] = list.get(i);
			itemsVaule[i] = String.valueOf(i);
		}
		adapter = new TextAdapter(mContext, items, R.drawable.choose_item_right, R.drawable.choose_eara_item_selector);
		adapter.setTextSize(17);
		if (mDistance != null) {
			for (int i = 0; i < itemsVaule.length; i++) {
				if (itemsVaule[i].equals(mDistance)) {
					adapter.setSelectedPositionNoNotify(i);
					showText = items[i];
					break;
				}
			}
		}
		mListView.setAdapter(adapter);
		adapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

			@Override
			public void onItemClick(View view, int position) {

				if (mOnSelectListener != null) {
					showText = items[position];
					mOnSelectListener.getValue(itemsVaule[position], items[position]);
				}
			}
		});
	}
	
	public void setOnSelectListener(OnSelectListener onSelectListener) {
		mOnSelectListener = onSelectListener;
	}

	public interface OnSelectListener {
		public void getValue(String distance, String showText);
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void show() {
		
	}

}
