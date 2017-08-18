/**
 * 
 */
package com.roch.fupin.view;

import com.roch.fupin.BaseActivity;
import com.roch.fupin.PoorPeopleBFCSActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * @author ZhaoDongShao
 *
 * 2016年8月11日 
 *
 */
public class TableScrollView extends HorizontalScrollView{

	PoorPeopleBFCSActivity activity;

	public TableScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.activity = (PoorPeopleBFCSActivity) context;
	}


	public TableScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.activity = (PoorPeopleBFCSActivity) context;
	}

	public TableScrollView(Context context) {
		super(context);
		this.activity = (PoorPeopleBFCSActivity) context;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		activity.mTouchView = this;
		return super.onTouchEvent(ev);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		if(activity.mTouchView == this) {
			activity.onScrollChanged(l, t, oldl, oldt);
		}else{
			super.onScrollChanged(l, t, oldl, oldt);
		}
	}

}
