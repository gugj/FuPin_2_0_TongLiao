package com.roch.fupin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import com.roch.fupin.PoorPeopleCaseYXActivity;

/**
 * 自定义的HorizonTalScrollView
 * @author ZhaoDongShao
 * 2016年8月11日
 */
public class CaseXueTableScrollView extends HorizontalScrollView{

	PoorPeopleCaseYXActivity activity;

	public CaseXueTableScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.activity = (PoorPeopleCaseYXActivity) context;
	}


	public CaseXueTableScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.activity = (PoorPeopleCaseYXActivity) context;
	}

	public CaseXueTableScrollView(Context context) {
		super(context);
		this.activity = (PoorPeopleCaseYXActivity) context;
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
