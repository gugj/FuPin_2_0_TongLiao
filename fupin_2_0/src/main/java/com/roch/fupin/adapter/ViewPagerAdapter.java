package com.roch.fupin.adapter;

import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * 自定义的ViewPager的适配器adapter，继承自FragmentStatePagerAdapter
 * @author ZhaoDongShao
 *
 * 2016年5月9日
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

	private List<Fragment> list;
	
	/**
	 * 标题
	 */
	private String[] title;

	/**
	 * @param fm
	 */
	public ViewPagerAdapter(List<Fragment> list, FragmentActivity fm) {
		super(fm.getSupportFragmentManager());
		this.list = list;
	}

	@Override
	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return title[position];
	}

	/**
	 * 设置导航栏的标题
	 *
	 * @param title
	 * 2016年5月9日
	 * ZhaoDongShao
	 */
	public void setTitle(String[] title) {
		this.title = title;
	}
}
