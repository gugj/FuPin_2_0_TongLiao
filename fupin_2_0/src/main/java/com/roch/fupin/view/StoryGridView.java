package com.roch.fupin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 自定义的GridView，里面只是重新制定了一下高度的测量规则，模式为at_most，继承自GridView
 * @author ZhaoDongShao
 * 2016年11月4日 
 */
public class StoryGridView extends GridView {

	public StoryGridView(Context context) {
		super(context);
	}

	public StoryGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public StoryGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
