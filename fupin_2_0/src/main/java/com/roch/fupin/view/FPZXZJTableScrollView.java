package com.roch.fupin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import com.roch.fupin.FuPinZhuanXiangZiJinTongJiActivity;

/**
 * 自定义的扶贫专项资金的HorizontalScroollView
 * @author ZhaoDongShao
 * 2016年8月11日
 */
public class FPZXZJTableScrollView extends HorizontalScrollView{

	FuPinZhuanXiangZiJinTongJiActivity activity;

	public FPZXZJTableScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.activity = (FuPinZhuanXiangZiJinTongJiActivity) context;
	}


	public FPZXZJTableScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.activity = (FuPinZhuanXiangZiJinTongJiActivity) context;
	}

	public FPZXZJTableScrollView(Context context) {
		super(context);
		this.activity = (FuPinZhuanXiangZiJinTongJiActivity) context;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		activity.mTouchView = this;
		return super.onTouchEvent(ev);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		if(activity.mTouchView == this) {
			activity.onScrollChanged(l, t, oldl, oldt);
		}else{
			super.onScrollChanged(l, t, oldl, oldt);
		}
	}
}
