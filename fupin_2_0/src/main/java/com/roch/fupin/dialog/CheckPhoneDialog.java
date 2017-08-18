package com.roch.fupin.dialog;

import com.roch.fupin.utils.StringUtil;
import com.roch.fupin_2_0.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 自定义的对电话号码进行拨打、保存操作的dialog对话框
 */
public class CheckPhoneDialog extends Dialog {
	
	private android.view.View.OnClickListener mOnClick;
	private LinearLayout call_Layout, save_Layout, cancelLayout;
	private TextView tv_notic;

	public CheckPhoneDialog(Context context) {
		super(context);
	}

	public CheckPhoneDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_check_sex_dialog);
		// 使dialog全局
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		initViews();
	}

	/**
	 * 初始化view控件
	 */
	private void initViews() {
		tv_notic = (TextView) findViewById(R.id.tv_notic);
		call_Layout = (LinearLayout) findViewById(R.id.check_call_phone_dialog);
		save_Layout = (LinearLayout) findViewById(R.id.check_save_phone_dialog);
		cancelLayout = (LinearLayout) findViewById(R.id.check_phone_dialog_cancel);
	}

	/**
	 * 设置点击监听
	 * 
	 * @param l
	 */
	public void setOnClickListener(android.view.View.OnClickListener l) {
		this.mOnClick = l;
		call_Layout.setOnClickListener(mOnClick);
		save_Layout.setOnClickListener(mOnClick);
		cancelLayout.setOnClickListener(mOnClick);
	}

	/**
	 * 增加lable标签
	 * 
	 * @param phone
	 *
	 * 2016年7月15日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void setLable(String phone) {
		if (!StringUtil.isEmpty(phone)) {
			tv_notic.setText(phone + "是一个电话号码，你可以");
		}
	}
}
