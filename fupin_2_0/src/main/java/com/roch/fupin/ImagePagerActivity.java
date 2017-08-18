/**
 * 
 */
package com.roch.fupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.entity.Photo;
import com.roch.fupin.view.ImageViewPager;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 照片查看器Activity
 * @author ZhaoDongShao
 * 2016年5月10日
 */
@ContentView(R.layout.activity_image_detail_page)
public class ImagePagerActivity extends BaseActivity {

	private static final String STATE_POSITION = "STATE_POSITION";
	public static final String EXTRA_IMAGE_INDEX = "image_index";
	public static final String EXTRA_IMAGE_URLS = "image_urls";
	public static final String EXTRA_IMAGE_URLS_KEY = "image_urls_key";
	/**
	 * 照片GridView的点击位置
	 */
	private int position;
	
	@ViewInject(R.id.pager)
	ImageViewPager viewPager;
	@ViewInject(R.id.indicator)
	TextView indicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(mActivity);
		initDate(savedInstanceState);
	}

	/**
	 * 加载的是否是本地图片---默认不是，即加载的是网络图片
	 */
	boolean mIfLoadLocalPhoto=false;
	/**
	 * 2016年5月10日
	 * ZhaoDongShao
	 * @param savedInstanceState
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	private void initDate(Bundle savedInstanceState) {
		//照片GridView的适配器的数据源
		List<Photo> lists = null;
		Intent intent = getIntent();
		//获取bundle
		Bundle bundle = intent.getBundleExtra(EXTRA_IMAGE_URLS_KEY);
		if (bundle != null) {
			//照片GridView的点击位置
			position = bundle.getInt(EXTRA_IMAGE_INDEX);
			lists = (List<Photo>)bundle.getSerializable(EXTRA_IMAGE_URLS);
			mIfLoadLocalPhoto=bundle.getBoolean("ifLoadLocalPhoto");
		}
		ImageViewAdapter mAdapter = new ImageViewAdapter(getSupportFragmentManager(), lists,mIfLoadLocalPhoto);
		viewPager.setAdapter(mAdapter);
		CharSequence text = getString(R.string.viewpager_indicator, 1, viewPager.getAdapter().getCount());
		indicator.setText(text);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				CharSequence text = getString(R.string.viewpager_indicator, arg0 + 1, viewPager.getAdapter().getCount());
				indicator.setText(text);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
		if (savedInstanceState != null) {
			position = savedInstanceState.getInt(STATE_POSITION);
		}
		viewPager.setCurrentItem(position);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_POSITION, viewPager.getCurrentItem());
	}

	/**
	 * 图片加载器Activity中ViewPager的适配器
	 */
	private class ImageViewAdapter extends FragmentStatePagerAdapter {

		/**
		 * 照片GridView的适配器的数据源
		 */
		List<Photo> list;
		/**
		 * 加载的是否是本地图片---默认不是，即加载的是网络图片
		 */
		boolean mIfLoadLocalPhoto=false;

		/**
		 * @param fm
		 * @param list 照片GridView的适配器的数据源
		 */
		public ImageViewAdapter(FragmentManager fm, List<Photo> list,boolean mIfLoadLocalPhoto) {
			super(fm);
			this.list = list;
			this.mIfLoadLocalPhoto=mIfLoadLocalPhoto;
		}

		@Override
		public Fragment getItem(int arg0) {
			//获取该位置的照片对象
			Photo photo = list.get(arg0);
			return ImageDetailFragment.newInstance(photo, mIfLoadLocalPhoto);
		}

		@Override
		public int getCount() {
			return list != null ? list.size() : 0;
		}
	}
	
}
