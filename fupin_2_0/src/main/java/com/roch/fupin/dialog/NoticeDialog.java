package com.roch.fupin.dialog;

import com.roch.fupin.LoginActivity;
import com.roch.fupin_2_0.R;
import com.roch.fupin.app.MyApplication;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * @author ZhaoDongShao
 *
 * 2016年5月19日 
 * 创建连接服务器失败时的自定义样式的dialog对话框
 */
@SuppressLint({ "InflateParams", "NewApi" })
public class NoticeDialog extends DialogFragment{

	Button btn_ok;
	
	/* (non-Javadoc)
	 * @see android.app.DialogFragment#onCreateDialog(android.os.Bundle)
	 */
	/**
	 * 创建连接服务器失败时的自定义样式的dialog对话框
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new Builder(getActivity(),R.style.popup_dialog_style);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.notic_dialog, null);
		builder.setCancelable(false);
		builder.setView(view).setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				Intent intent = new Intent(getActivity(), LoginActivity.class);
				startActivity(intent);
				MyApplication.getInstance().finishAllActivity();
			}
		});
		return builder.create();
	}
}
