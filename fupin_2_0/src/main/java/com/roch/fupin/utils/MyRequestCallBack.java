package com.roch.fupin.utils;

import android.text.TextUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.roch.fupin.BaseActivity;
import com.roch.fupin.BaseFragment;
import com.roch.fupin.app.MyApplication;

/**
 * 
 *自定义的发起访问网络请求后的回调类，继承自xUtils的回调类RequestCallBack<String>
 */
public class MyRequestCallBack extends RequestCallBack<String> {

	private BaseActivity context;
	private SuccessResult sr;
	public int flag = 0;
	public boolean showProgress = true;

	/**
	 * 2016年10月27日  <br/>
	 * 
	 *自定义的发起访问网络请求后的回调类MyRequestCallBack的内部接口类，
	 *需要实现两个方法onSuccessResult(String str, int flag); onFaileResult(String str, int flag);
	 *
	 */
	public interface SuccessResult {
		void onSuccessResult(String str, int flag);
		void onFaileResult(String str, int flag);
	}

	public MyRequestCallBack(BaseActivity context, int flag, boolean showProgressDialog) {
		this(context, flag);
		showProgress = showProgressDialog;
	}

	public MyRequestCallBack(BaseFragment context, int flag, boolean showProgressDialog) {
		this(context, flag);
		showProgress = showProgressDialog;
	}

	public MyRequestCallBack(BaseActivity context, int flag) {
		super();
		this.context = context;
		this.flag = flag;
//		if (flag == MyConstans.REFERSH || flag == MyConstans.SEVEN || flag == MyConstans.EIGHT || flag == MyConstans.NINE) {
//			showProgress = false;
//		}
		try {
			sr = (SuccessResult) context;
		} catch (Exception e) {
			throw new ClassCastException(context.toString() + " Must implent SuccessResult");
		}
	}

	public MyRequestCallBack(BaseFragment context, int flag) {
		super();
		this.context = (BaseActivity) context.getActivity();
		this.flag = flag;
//		if (flag == MyConstans.REFERSH || flag == MyConstans.SEVEN || flag == MyConstans.EIGHT || flag == MyConstans.NINE) {
//			showProgress = false;
//		}
		try {
			sr = (SuccessResult) context;
		} catch (Exception e) {
			throw new ClassCastException(context.toString() + " Must implent SuccessResult");
		}
	}

	@Override
	public void onStart() {
		if (!MyApplication.getInstance().getNetConnectInstance().ischeackNet(context)) {
			return;
		}
		if (showProgress) {
			context.showProgressDialog("正在加载...", true);
		}
		super.onStart();
	}

	@Override
	public void onSuccess(ResponseInfo<String> responseInfo) {
		String str = responseInfo.result;
		if (sr != null && !(TextUtils.isEmpty(str))) {
			sr.onSuccessResult(str, flag);
		} else {

		}
		context.cancelProgressDialog();
	}

	@Override
	public void onFailure(HttpException error, String msg) {
		if (!MyApplication.getInstance().getNetConnectInstance().ischeackNet(context)) {
			MyApplication.getInstance().getToastUtilsInstance().showNormalToast(context, "网络无连接，请检查网络");
			return;
		}
		if (sr != null){
			sr.onFaileResult("网络或服务异常", flag);
		}
		context.cancelProgressDialog();
	}

	@Override
	public void onCancelled() {
		super.onCancelled();
		context.cancelProgressDialog();
		context.finish();
	}
}
