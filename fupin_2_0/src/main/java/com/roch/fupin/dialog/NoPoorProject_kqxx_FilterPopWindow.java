package com.roch.fupin.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.utils.ResourceUtil;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin_2_0.R;
import com.roch.time.JudgeDate;
import com.roch.time.ScreenInfo;
import com.roch.time.WheelMain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * 攻坚脱贫项目筛选弹出框
 * @author ZhaoDongShao
 * 2016年6月2日
 */
public class NoPoorProject_kqxx_FilterPopWindow extends PopupWindow {

	private View conentView;
	/**
	 * 时间选择滚轮的布局View
	 */
	private View timeView;
	private WheelMain wheelMain; // TimePicker
	private ScreenInfo screenInfo;
	private String srBirthDay = "";
	private SimpleDateFormat dateFormat;
	private TextView timeCancle;
	private TextView timeSure;
	private PopupWindow timePop;// 时间选择器popup
	/**
	 * 显示的选择后的时间
	 */
	private TextView tv_time_end;
	private ShowMessageListener showMessageListener;
	/**
	 * 输入的考勤对象
	 */
	private  EditText et_shouru;
	/**
	 * 输入的考勤对象电话
	 */
	private  EditText et_phone;

	private boolean showShouRu=true;
	private RelativeLayout rl_shouru;
	private RelativeLayout rl_phone;

	public void setShowShouRu(boolean show){
		showShouRu=show;
	}

	@SuppressLint("InflateParams")
	public NoPoorProject_kqxx_FilterPopWindow(final Context context) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.no_poor_project_kqxx_filter_popwindow, null,false);
		//		int h = context.getWindowManager().getDefaultDisplay().getHeight();  
		//		int w = context.getWindowManager().getDefaultDisplay().getWidth();  
		// 设置SelectPicPopupWindow的View  
		this.setContentView(conentView);  
		// 设置SelectPicPopupWindow弹出窗体的宽  
		this.setWidth(LayoutParams.MATCH_PARENT);  
		// 设置SelectPicPopupWindow弹出窗体的高  
		this.setHeight(LayoutParams.MATCH_PARENT);  
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);  
		this.setOutsideTouchable(true);  
		// 刷新状态
		this.update();  
		// 实例化一个ColorDrawable颜色为半透明  
		//		ColorDrawable dw = new ColorDrawable(ResourceUtil.getInstance().getColorById(R.color.black));  
		//		dw.setAlpha(50);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作  
		//		this.setBackgroundDrawable(dw);  
		this.setBackgroundDrawable(ResourceUtil.getInstance().getDrawableByID(R.drawable.pop_info_window_1));  
		// mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);  
		// 设置SelectPicPopupWindow弹出窗体动画效果  
		this.setAnimationStyle(R.style.PopupWindowAnimation);

		//输入的人均纯收入标准
		et_shouru = (EditText) conentView.findViewById(R.id.et_shouru);
		et_phone = (EditText) conentView.findViewById(R.id.et_phone);

		//选择时间的容器
		RelativeLayout rl_time_end = (RelativeLayout)conentView.findViewById(R.id.rl_time_end);
		//显示的时间
		tv_time_end = (TextView)conentView.findViewById(R.id.tv_time_end);
		dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		screenInfo = new ScreenInfo((Activity)context);
		//时间选择滚轮的布局View
		timeView = LayoutInflater.from(context).inflate(R.layout.item_odertime, null);
		timeCancle = (TextView) timeView.findViewById(R.id.time_cancle);
		timeSure = (TextView) timeView.findViewById(R.id.time_sure);
		timePop = new PopupWindow(timeView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		//给时间选择设置点击监听
		rl_time_end.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initTimePicker(timeView);
				initTimePop(1);
			}
		});

		Button btn_repeat = (Button)conentView.findViewById(R.id.btn_repeat);
		Button btn_submit = (Button)conentView.findViewById(R.id.btn_submit);

		//重置时间
		btn_repeat.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_time_end.setText("时间不限");
				et_shouru.setText("");
				et_phone.setText("");
			}
		});

		//提交时间
		btn_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showMessageListener.Message(tv_time_end.getText().toString().trim().replace("时间不限", "")
						, et_shouru.getText().toString().trim(),et_phone.getText().toString().trim());
			}
		});
	}
	/** 
	 * 显示popupWindow
	 * @param parent 
	 */  
	public void showPopupWindow(View parent,int pox) {  
		if (!this.isShowing()) {
			rl_shouru = (RelativeLayout) conentView.findViewById(R.id.rl_shouru);
			rl_phone = (RelativeLayout) conentView.findViewById(R.id.rl_phone);
			if(showShouRu){
				rl_shouru.setVisibility(View.VISIBLE);
				rl_phone.setVisibility(View.VISIBLE);
			}else {
				rl_shouru.setVisibility(View.GONE);
				rl_phone.setVisibility(View.GONE);
			}
			// 以下拉方式显示popupwindow  
			this.showAsDropDown(parent, pox, 1);
		} else {  
			this.dismiss();  
		}  
	}

	/**
	 * 设置筛选过之后的时间
	 * @param list
	 */
	public void setSelectionAdapter(List<MapEntity> list,String selectShouRu,String selectPhone){
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getKey().equals("wejend")) {
					tv_time_end.setText(list.get(i).getValue());
				}
			}
		}
		et_shouru.setText(selectShouRu);
		et_phone.setText(selectPhone);
	}

	/**
	 * 监听者模式，将监听者传过来
	 * @param showMessageListener
	 */
	public void setShowMessageListener(ShowMessageListener showMessageListener) {
		this.showMessageListener = showMessageListener;
	}

	public interface ShowMessageListener {
		/**
		 * @param time
		 * @param shouru 截止时间 2016年6月2日
		 */
		void Message(String time, String shouru, String phone);
	}
	
	/**
	 * 选择日期
	 */
	private void initTimePop(final int i) {
		timePop.setFocusable(true);
		timePop.setOutsideTouchable(false);
		timePop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		timePop.setAnimationStyle(R.style.PopupWindowTimerAnimation);
		timePop.showAtLocation(timeView,Gravity.BOTTOM, 0, LayoutParams.WRAP_CONTENT);
		timeCancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (null != timePop && timePop.isShowing()) {
					timePop.setFocusable(false);
					timePop.dismiss();
				}
			}
		});
		timeSure.setOnClickListener(new OnClickListener() {// 要求到货时间
			@Override
			public void onClick(View v) {
				String times = wheelMain.getYearAndQuarter();
				if (!StringUtil.isEmpty(times)) {
					String month = times.split("-")[1];
					int months = Integer.parseInt(month) + 1;
//					String time = times.split("-")[0] + "-" + months;
					String time = times.split("-")[0] + "";

					if (i == 1) {
						tv_time_end.setText(time);
					}
					srBirthDay = wheelMain.getYear();
					timePop.setFocusable(false);
					timePop.dismiss();
				}
			}
		});
	}

	/**
	 * 初始化时间选择器
	 * @param v
	 */
	private void initTimePicker(View v) {
		wheelMain = new WheelMain(v, false, false, false);
		wheelMain.screenheight = screenInfo.getHeight();
		Calendar calendar = Calendar.getInstance();
		WheelMain.setSTART_YEAR(calendar.get(Calendar.YEAR)-60);
		WheelMain.setEND_YEAR(calendar.get(Calendar.YEAR));
		if (JudgeDate.isDate(srBirthDay, "yyyy-MM-dd")) {
			try {
				calendar.setTime(dateFormat.parse(srBirthDay));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		wheelMain.initDateTimePicker(year, 0, 0);
	}
}
