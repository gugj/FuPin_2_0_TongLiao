package com.roch.fupin.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单控件头部，封装了下拉动画，动态生成头部按钮个数
 * @author yueyueniao
 */
public class ExpandTabView extends LinearLayout implements OnDismissListener {

	private ToggleButton selectedButton;
	private List<String> mTextArray = new ArrayList<String>();
	private List<RelativeLayout> mViewArray = new ArrayList<RelativeLayout>();
	private List<ToggleButton> mToggleButton = new ArrayList<ToggleButton>();
	private Context mContext;
	private final int SMALL = 0;
	private int displayWidth;
	private int displayHeight;
	private PopupWindow popupWindow;
	private int selectPosition;
	private Resources resources;

	public ExpandTabView(Context context) {
		super(context);
		init(context);
	}

	public ExpandTabView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * 根据选择的位置设置tabitem显示的值
	 */
	public void setTitle(String valueText, int position) {
		if (position < mToggleButton.size()) {
			mToggleButton.get(position).setText(valueText);
		}
	}

	/**
	 * 根据选择的位置获取tabitem显示的值
	 */
	public String getTitle(int position) {
		if (position < mToggleButton.size() && mToggleButton.get(position).getText() != null) {
			return mToggleButton.get(position).getText().toString();
		}
		return "";
	}

	ToggleButton tButton;
	/**
	 * 设置tabitem的个数和初始值
	 */
	@SuppressWarnings("deprecation")
	public void setValue(List<String> textArray, List<View> viewArray) {
		if (mContext == null) {
			return;
		}
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		mTextArray = textArray;
		for (int i = 0; i < viewArray.size(); i++) {
			final RelativeLayout r = new RelativeLayout(mContext);
			int maxHeight = (int) (displayHeight * 0.7);
			RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
					maxHeight);
			rl.topMargin = 1;
			rl.bottomMargin = 150;
			r.addView(viewArray.get(i), rl);
			mViewArray.add(r);
			r.setTag(SMALL);
			tButton = (ToggleButton) inflater.inflate(R.layout.toggle_button, this, false);
			addView(tButton);
			View line = new TextView(mContext);
			line.setBackgroundResource(R.drawable.choosebar_line);
			if (i < viewArray.size() - 1) {
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
				addView(line, lp);
			}
			mToggleButton.add(tButton);
			tButton.setTag(i);
			tButton.setText(mTextArray.get(i));

			r.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					onPressBack();
				}
			});

			r.setBackgroundColor(mContext.getResources().getColor(R.color.popup_main_background));
			tButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					// initPopupWindow();
					ToggleButton tButton = (ToggleButton) view;

					if (selectedButton != null && selectedButton != tButton) {
						selectedButton.setChecked(false);
						selectedButton.setTextColor(resources.getColor(R.color.black));
					}
					selectedButton = tButton;
					selectPosition = (Integer) selectedButton.getTag();
					startAnimation();
					if (mOnButtonClickListener != null && tButton.isChecked()) {
						mOnButtonClickListener.onClick(selectPosition);
					}
				}
			});
		}
	}

	/**
	 * 初始化筛选条件的文字导航显示---初始化后两个
	 */
	public void initSelectText2(){
		mToggleButton.get(1).setText(mTextArray.get(1));
		mToggleButton.get(2).setText(mTextArray.get(2));
	}
	/**
	 * 初始化筛选条件的文字导航显示---初始化最后个
	 */
	public void initSelectText1(){
		mToggleButton.get(2).setText(mTextArray.get(2));
	}

	/**
	 * 初始化筛选条件的文字导航显示
	 * @param index
	 */
	public void initSecetText(int index){
		tButton.setText(mTextArray.get(index));
	}

	@SuppressWarnings("deprecation")
	private void startAnimation() {
		if (popupWindow == null) {
			popupWindow = new PopupWindow(mViewArray.get(selectPosition), displayWidth, displayHeight);
			popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
			popupWindow.setFocusable(false);
			popupWindow.setOutsideTouchable(true);
		}

		if (selectedButton.isChecked()) {
			if (!popupWindow.isShowing()) {
				showPopup(selectPosition);
				selectedButton.setTextColor(resources.getColor(R.color.color_00aff0));
			} else {
				popupWindow.setOnDismissListener(this);
				popupWindow.dismiss();
				selectedButton.setTextColor(resources.getColor(R.color.color_00aff0));
				String tString = (String) selectedButton.getText();
				System.out.println(tString);
				hideView();
			}
		} else {
			if (popupWindow.isShowing()) {
				popupWindow.dismiss();
				selectedButton.setTextColor(resources.getColor(R.color.black));
				hideView();
			}
		}
	}

	private void showPopup(int position) {
		View tView = mViewArray.get(selectPosition).getChildAt(0);
		if (tView instanceof ViewBaseAction) {
			ViewBaseAction f = (ViewBaseAction) tView;
			f.show();
		}
		if (popupWindow.getContentView() != mViewArray.get(position)) {
			popupWindow.setContentView(mViewArray.get(position));
		}
		popupWindow.showAsDropDown(this, 0, 0);
	}

	/**
	 * 如果菜单成展开状态，则让菜单收回去
	 */
	@SuppressWarnings("deprecation")
	public boolean onPressBack() {
		if (popupWindow != null && popupWindow.isShowing()) {
			popupWindow.dismiss();
			hideView();
			if (selectedButton != null) {
				selectedButton.setChecked(false);
				selectedButton.setTextColor(resources.getColor(R.color.black));
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断菜单是否展开，如展开返回true，否则返回false
	 * 
	 * @return
	 */
	public boolean isShowing() {
		if (popupWindow != null && popupWindow.isShowing()) {
			return true;
		} else {
			return false;
		}
	}

	private void hideView() {
		View tView = mViewArray.get(selectPosition).getChildAt(0);
		if (tView instanceof ViewBaseAction) {
			ViewBaseAction f = (ViewBaseAction) tView;
			f.hide();
		}
	}

	@SuppressWarnings("deprecation")
	private void init(Context context) {
		mContext = context;
		displayWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
		displayHeight = ((Activity) mContext).getWindowManager().getDefaultDisplay().getHeight();
		setOrientation(LinearLayout.HORIZONTAL);
		resources = getResources();
	}

	@Override
	public void onDismiss() {
		showPopup(selectPosition);
		popupWindow.setOnDismissListener(null);
	}

	private OnButtonClickListener mOnButtonClickListener;

	/**
	 * 设置tabitem的点击监听事件
	 */
	public void setOnButtonClickListener(OnButtonClickListener l) {
		mOnButtonClickListener = l;
	}

	/**
	 * 自定义tabitem点击回调接口
	 */
	public interface OnButtonClickListener {
		void onClick(int selectPosition);
	}
}
