package com.roch.fupin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.roch.fupin.adapter.TextAdapter;
import com.roch.fupin.entity.PinKunCun;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的RelativeLayout---进行筛选
 * @author ZhaoDongShao
 * 2017年3月31日 
 */
public class MenuRight_2 extends RelativeLayout implements ViewBaseAction {

	private ListView mListView;
//	private final String[] items = new String[] { "item1", "item2", "item3", "item4", "item5", "item6" };//显示字段
//	private final String[] itemsVaule = new String[] { "1", "2", "3", "4", "5", "6" };//隐藏id
	/**
	 * 筛选时的数据
	 */
	List<String> items;
	/**
	 * 筛选时的数据的索引
	 */
	String[] itemsVaule;
	/**
	 * 自定义的选中时的监听器---在筛选时记录选中的数据和索引
	 */
	private OnSelectListener mOnSelectListener;
	/**
	 * 自定义的ArrayAdapter适配器
	 */
	private TextAdapter adapter;
	/**
	 * 筛选时选中的数据的索引
	 */
	private String mDistance;
	/**
	 * 显示的筛选数据
	 */
	private String showText = "不限";
	Context mContext;

	public String getShowText() {
		return showText;
	}

	public MenuRight_2(Context context) {
		super(context);
		init(context);
	}

	public MenuRight_2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public MenuRight_2(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.menu_right, this, true);
		//设置背景颜色
		setBackgroundDrawable(getResources().getDrawable(R.drawable.choosearea_bg));
		mListView = (ListView) findViewById(R.id.listView);
	}

	/**
	 * 设置筛选时的数据---帮扶措施、致贫原因、性别等筛选条件
	 * @param list
	 * 2016年5月30日
	 * ZhaoDongShao
	 */
	public void setStringArray(List<PinKunCun> list){
		items = new ArrayList<>();
		itemsVaule = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			items.add(list.get(i).getName());
			itemsVaule[i] = String.valueOf(i);
		}
		adapter = new TextAdapter(mContext,list, items, R.drawable.choose_item_right, R.drawable.choose_eara_item_selector);
		adapter.setTextSize(17);
		if (mDistance != null) {
			for (int i = 0; i < itemsVaule.length; i++) {
				if (itemsVaule[i].equals(mDistance)) {
					adapter.setSelectedPositionNoNotify(i);
					showText = items.get(i);
					break;
				}
			}
		}
		mListView.setAdapter(adapter);
		adapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				if (mOnSelectListener != null) {
					showText = items.get(position);
					mOnSelectListener.getValue(itemsVaule[position], items.get(position));
				}
			}
		});
	}

	String[] items2;
	/**
	 * 设置筛选时的数据---帮扶措施、致贫原因、性别等筛选条件
	 * @param list
	 * 2016年5月30日
	 * ZhaoDongShao
	 */
	public void setStringArray(List<String> list,String flag){
		items2 = new String[list.size()];
		itemsVaule = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			items2[i]=list.get(i);
			itemsVaule[i] = String.valueOf(i);
		}
		adapter = new TextAdapter(mContext, items2, R.drawable.choose_item_right, R.drawable.choose_eara_item_selector);
		adapter.setTextSize(17);
		if (mDistance != null) {
			for (int i = 0; i < itemsVaule.length; i++) {
				if (itemsVaule[i].equals(mDistance)) {
					adapter.setSelectedPositionNoNotify(i);
					showText = items2[i];
					break;
				}
			}
		}
		mListView.setAdapter(adapter);
		adapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				if (mOnSelectListener != null) {
					showText = items2[position];
					mOnSelectListener.getValue(itemsVaule[position], items2[position]);
				}
			}
		});
	}

	/**
	 * 设置自定义的选中时的监听器---在筛选时记录选中的数据和索引
	 * @param onSelectListener
	 */
	public void setOnSelectListener(OnSelectListener onSelectListener) {
		mOnSelectListener = onSelectListener;
	}

	/**
	 * 自定义的选中时的监听器---在筛选时记录选中的数据和索引
	 * @author ZhaoDongShao
	 * 2017年4月1日 
	 */
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
