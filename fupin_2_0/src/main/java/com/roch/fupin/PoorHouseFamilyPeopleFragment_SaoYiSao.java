package com.roch.fupin;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.PoorFamilyPeopleAdapter_SaoYiSao;
import com.roch.fupin.entity.PoorFamilyPeople;
import com.roch.fupin.entity.PoorFamilyPeople_ResultList;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 家庭成员Fragment---3个tab页中的第二个
 * @author ZhaoDongShao
 * 2016年5月9日
 */
public class PoorHouseFamilyPeopleFragment_SaoYiSao extends BaseFragment {

	/**
	 * 标志位，标志初始化已经完成
	 */
	private boolean isPrepared;

	/**
	 * 标识当前fragment是否可见
	 */
	private boolean isVisible;
	
	@ViewInject(R.id.ll)
	private LinearLayout ll;

	PoorFamilyPeopleAdapter_SaoYiSao mPoorFamilyPeopleAdapter;
	@ViewInject(R.id.lv_poor)
	ListView lv_poor;
	
	Context mContext;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_poorhouse_familypeople, container, false);
		ViewUtils.inject(this,view);
//		isPrepared = true;
		mContext = getActivity();
//		lazyLoad();
		initData();
		return view;
	}

//	@Override
//	public void setUserVisibleHint(boolean isVisibleToUser) {
//		super.setUserVisibleHint(isVisibleToUser);
//		if (isVisibleToUser) {
//			isVisible = true;
//			lazyLoad();
//		}else {
//			isVisible = false;
//		}
//	}

//	private void lazyLoad() {
//		if (!isPrepared || !isVisible) {
//			return;
//		}
//		initData();
//	}

	/**
	 * 获取数据
	 * 2016年5月18日
	 * ZhaoDongShao
	 */
	@SuppressWarnings("unchecked")
	private void initData(){
		Bundle bundle = getArguments();
		if (!StringUtil.isEmpty(bundle)) {
			String str2= bundle.getString("str2");
			System.out.println("str2==="+str2);
			PoorFamilyPeople_ResultList poorFamilyPeople_resultList=PoorFamilyPeople_ResultList.parseToT(str2,PoorFamilyPeople_ResultList.class);
			if (poorFamilyPeople_resultList.getSuccess()){
				List<PoorFamilyPeople> lFamilyPeoples = poorFamilyPeople_resultList.getJsondata();
				if (!StringUtil.isEmpty(lFamilyPeoples)&&lFamilyPeoples.size() > 0) {
					mPoorFamilyPeopleAdapter = new PoorFamilyPeopleAdapter_SaoYiSao(mContext, lFamilyPeoples);
					lv_poor.setAdapter(mPoorFamilyPeopleAdapter);
				}

//				lv_poor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//					@Override
//					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//						PoorFamilyPeople people = (PoorFamilyPeople)parent.getItemAtPosition(position);
//						Intent intent = new Intent(mContext, PoorFamilyPeopleDetailActivity.class);
//						Bundle bundle = new Bundle();
//						bundle.putSerializable(Common.BUNDEL_KEY, people);
//						bundle.putString("type","2");
//						intent.putExtra(Common.INTENT_KEY, bundle);
//						startActivity(intent);
//					}
//				});
			}
		}
	}
}
