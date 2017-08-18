package com.roch.fupin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.PoorHousePhotoAdapter;
import com.roch.fupin.entity.Photo;
import com.roch.fupin.entity.PoorFamilyPhoto;
import com.roch.fupin.entity.PoorFamilyPhoto_ResultList;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin_2_0.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 贫困户照片
 * @author ZhaoDongShao
 * 2016年5月9日
 */
public class PoorHousePhotoFragment_SaoYiSao extends BaseFragment {

	@ViewInject(R.id.gv_photo)
	GridView gv_photo;
	@ViewInject(R.id.rl)
	RelativeLayout rl;
	List<Photo> lists = new ArrayList<Photo>();

	PoorHousePhotoAdapter mAdapter;
	Context mContext;

	/**
	 * 标志位，标志初始化已经完成
	 */
	private boolean isPrepared;

	/**
	 * 标识当前fragment是否可见
	 */
	private boolean isVisible;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_poorhouse_photo, container, false);
		ViewUtils.inject(this,view);
		this.mContext = getActivity();
//		isPrepared = true;
//		lazyLoad();
		initData();
		return view;
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
//		if (isVisibleToUser) {
//			isVisible = true;
//			lazyLoad();
//		}else {
//			isVisible = false;
//		}
	}

//	private void lazyLoad() {
//		if (!isPrepared || !isVisible) {
//			return;
//		}
//		initData();
//	}


	/**
	 * 2016年5月10日
	 * ZhaoDongShao
	 */
	@SuppressWarnings("unchecked")
	private void initData() {
		Photo photo = null;
		Bundle bundle = getArguments();
		if (!StringUtil.isEmpty(bundle)) {
			String str3= bundle.getString("str3");
			System.out.println("str3==="+str3);
			PoorFamilyPhoto_ResultList poorFamilyPhoto_resultList=PoorFamilyPhoto_ResultList.parseToT(str3,PoorFamilyPhoto_ResultList.class);
			if (poorFamilyPhoto_resultList.getSuccess()){
				List<PoorFamilyPhoto> lFamilyIncomes = poorFamilyPhoto_resultList.getJsondata();
				if (lFamilyIncomes != null && lFamilyIncomes.size() <= 0) {
					gv_photo.setVisibility(View.GONE);
					rl.setVisibility(View.VISIBLE);
				}else {
					if (lists != null && lists.size() > 0) {
						lists.clear();
					}
					for (PoorFamilyPhoto poorFamilyPhoto : lFamilyIncomes) {
						photo = new Photo();
						photo.setUrl(poorFamilyPhoto.getImagepath());
						lists.add(photo);
					}
					mAdapter = new PoorHousePhotoAdapter(mContext, lists);
					gv_photo.setAdapter(mAdapter);
					gv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
							imageBrower(position,lists);
						}
					});
				}
			}
		}
	}

	/**
	 * @param position
	 * @param urls
	 * 2016年5月10日
	 * ZhaoDongShao
	 */
	protected void imageBrower(int position, List<Photo> urls) {
		Intent intent = new Intent(mContext, ImagePagerActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(ImagePagerActivity.EXTRA_IMAGE_URLS, (Serializable)urls);
		bundle.putInt(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS_KEY, bundle);
		startActivity(intent);
	}
}
