package com.roch.fupin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import com.roch.fupin.PoorPeopleTPQKActivity;

/**
 * 自定义的脱贫情况的HorizontalScrollView
 * @author ZhaoDongShao
 * 2016年8月11日
 */
public class TPQKTableScrollView extends HorizontalScrollView{

	PoorPeopleTPQKActivity activity;

	public TPQKTableScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.activity = (PoorPeopleTPQKActivity) context;
	}


	public TPQKTableScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.activity = (PoorPeopleTPQKActivity) context;
	}

	public TPQKTableScrollView(Context context) {
		super(context);
		this.activity = (PoorPeopleTPQKActivity) context;
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
