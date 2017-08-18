package com.roch.fupin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.roch.fupin.adapter.TextAdapter;
import com.roch.fupin.entity.Basic_DistrictAppModel;
import com.roch.fupin.entity.PinKunCun;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 乡镇、村进行筛选
 * @author ZhaoDongShao
 * 2016年5月30日
 */
public class MenuLeft extends RelativeLayout implements ViewBaseAction{

	private ListView regionListView;
	private ListView plateListView;
	private List<PinKunCun> groups = new ArrayList<>();
	private LinkedList<PinKunCun> childrenItem = new LinkedList<>();
	private SparseArray<LinkedList<PinKunCun>> children = new SparseArray<LinkedList<PinKunCun>>();
	private TextAdapter plateListViewAdapter;
	private TextAdapter earaListViewAdapter;
	private OnSelectListener mOnSelectListener;
	private int tEaraPosition = 0;
	private int tBlockPosition = 0;
	private String showString = "不限";

	Context mContext;

	public MenuLeft(Context context) {
		super(context);
		init(context);
	}

	public MenuLeft(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public void updateShowText(String showArea, String showBlock) {
		if (showArea == null || showBlock == null) {
			return;
		}
		for (int i = 0; i < groups.size(); i++) {
			if (groups.get(i).equals(showArea)) {
				earaListViewAdapter.setSelectedPosition(i);
				childrenItem.clear();
				if (i < children.size()) {
					childrenItem.addAll(children.get(i));
				}
				tEaraPosition = i;
				break;
			}
		}
		for (int j = 0; j < childrenItem.size(); j++) {
			if (childrenItem.get(j).getName().replace("不限", "").equals(showBlock.trim())) {
				plateListViewAdapter.setSelectedPosition(j);
				tBlockPosition = j;
				break;
			}
		}
		setDefaultSelect();
	}

	@SuppressWarnings("deprecation")
	private void init(Context context) {
		mContext = context;
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.menu_left, this, true);
		regionListView = (ListView) findViewById(R.id.listView);
		plateListView = (ListView) findViewById(R.id.listView2);
		setBackgroundDrawable((getResources().getDrawable(R.drawable.choosearea_bg)));
	}

	List<String> super_datas=new ArrayList<>();
	/**
	 * 获取乡镇
	 * @param list
	 * 2016年5月31日
	 * ZhaoDongShao
	 */
	public void setStringArray(final List<Basic_DistrictAppModel> list){
		//先获取父数据
		List<String> super_datas1=new ArrayList<>();
		if(null!=list){
			for (int i = 0; i < list.size(); i++) {
				super_datas1.add(list.get(i).getAd_nm());
			}
		}

		LinkedList<PinKunCun> tItem = new LinkedList<>();
//		groups.add("不限");
//		tItem.add("不限");
		if(null!=list){
			for (int i = 0; i < list.size(); i++) {
				PinKunCun pinKunCun=new PinKunCun();
				pinKunCun.setName(list.get(i).getAd_nm());
				pinKunCun.setPoor_type(list.get(i).getPovertystatusid());
				//将乡镇信息保存在list里面
//			groups.add(list.get(i).getAd_nm());
				groups.add(pinKunCun);
				//循环调用接口，获取村庄的数据
				//			mOnSelectListener.getArray(list.get(i).getAd_cd());
			}
		}
		children.put(0, tItem);

		earaListViewAdapter = new TextAdapter(mContext, groups,super_datas1,
				R.drawable.choose_item_selected_1,
				R.drawable.choose_eara_item_selector);
		earaListViewAdapter.setTextSize(17);
		earaListViewAdapter.setSelectedPositionNoNotify(tEaraPosition);
		regionListView.setAdapter(earaListViewAdapter);
		earaListViewAdapter
		.setOnItemClickListener(new TextAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {

				String name = groups.get(position).getName();
				String adl_cd = "";
				if (name != null && !name.equals("")) {
					if (name.equals("不限")) {
						List<Basic_DistrictAppModel> list = new ArrayList<Basic_DistrictAppModel>();
						Basic_DistrictAppModel basic_DistrictAppModel = new Basic_DistrictAppModel();
						basic_DistrictAppModel.setAd_nm("不限");
						list.add(basic_DistrictAppModel);
						setVillage(list);
					}else {
						for (int j = 0; j < list.size(); j++) {
							if (name.equals(list.get(j).getAd_nm())) {
								adl_cd = list.get(j).getAd_cd();
							}
						}
					}
				}
				if (!adl_cd.equals("")) {
					mOnSelectListener.getArray(adl_cd,position);
				}

//				if (position < children.size()) {
//					childrenItem.clear();
//					childrenItem.addAll(children.get(position));
//					plateListViewAdapter.notifyDataSetChanged();
//				}else {
//					childrenItem.clear();
//					plateListViewAdapter.notifyDataSetChanged();
//				}
			}
		});
		//如果当前索引小于链表总数，就进行添加到子列表
		if (tEaraPosition < children.size())
			childrenItem.addAll(children.get(tEaraPosition));
		plateListViewAdapter = new TextAdapter(mContext, childrenItem,super_datas,
				R.drawable.choose_item_right,
				R.drawable.choose_plate_item_selector);
		plateListViewAdapter.setTextSize(15);
		plateListViewAdapter.setSelectedPositionNoNotify(tBlockPosition);
		plateListView.setAdapter(plateListViewAdapter);
		plateListViewAdapter
		.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

			@Override
			public void onItemClick(View view, final int position) {
				showString = childrenItem.get(position).getName();
				if (mOnSelectListener != null) {

					mOnSelectListener.getValue(showString);
				}

			}
		});
		if (tBlockPosition < childrenItem.size())
			showString = childrenItem.get(tBlockPosition).getName();
		if (showString.contains("不限")) {
			showString = showString.replace("不限", "");
		}
		setDefaultSelect();
	}

	int j = 1;
	public void setVillage(List<Basic_DistrictAppModel> list) {
//		List<String> aList = new ArrayList<String>();
//		for (int i = 0; i < list.size(); i++) {
//			aList.add(list.get(i).getAd_nm());
//		}

		List<PinKunCun> pinKunCuns=new ArrayList<>();
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				PinKunCun pinKunCun=new PinKunCun();
				pinKunCun.setName(list.get(i).getAd_nm());
				pinKunCun.setPoor_type(list.get(i).getPovertystatusid());
				pinKunCuns.add(pinKunCun);
//				aList.add(list.get(i).getAd_nm());
			}
		}

		childrenItem.clear();
		childrenItem.addAll(pinKunCuns);

		if(null!=childrenItem){
			super_datas.clear();
			for (int i = 0; i < childrenItem.size(); i++) {
				super_datas.add(childrenItem.get(i).getName());
			}
		}
		plateListViewAdapter.notifyDataSetChanged();
	}

	/**
	 * 选择默认数据
	 * 2016年5月31日
	 * ZhaoDongShao
	 */
	public void setDefaultSelect() {
		regionListView.setSelection(tEaraPosition);
		plateListView.setSelection(tBlockPosition);
	}

	public String getShowText() {
		return showString;
	}

	public void setOnSelectListener(OnSelectListener onSelectListener) {
		mOnSelectListener = onSelectListener;
	}

	public interface OnSelectListener {
		void getValue(String showText);
		void getArray(String id,int position);
	}

	@Override
	public void hide() {
	}

	@Override
	public void show() {
	}
}
