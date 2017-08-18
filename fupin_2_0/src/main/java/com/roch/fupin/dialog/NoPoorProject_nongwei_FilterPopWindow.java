package com.roch.fupin.dialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import com.roch.fupin_2_0.R;
import com.roch.fupin.adapter.FilterPopuGridViewAdapter;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.utils.ResourceUtil;
import com.roch.time.JudgeDate;
import com.roch.time.ScreenInfo;
import com.roch.time.WheelMain;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 攻坚脱贫项目筛选弹出框
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月2日 
 *
 */
public class NoPoorProject_nongwei_FilterPopWindow extends PopupWindow {

	private View conentView;  
	FilterPopuGridViewAdapter popuGridViewAdapter_jd; //进度
	FilterPopuGridViewAdapter popuGridViewAdapter_lx; //类别
	String string_qylx = "",string_spjd = "";

	private View timeView;
	private WheelMain wheelMain; // TimePicker
	private ScreenInfo screenInfo;
	private String srBirthDay = "";
	private SimpleDateFormat dateFormat;
	private TextView timeCancle;
	private TextView timeSure;
	private TextView tv_time;
	private PopupWindow timePop;// 时间选择器popup
	
	
	
	private ShowMessageListener showMessageListener;
	public void setShowMessageListener(ShowMessageListener showMessageListener) {
		this.showMessageListener = showMessageListener;
	}

	@SuppressLint("InflateParams")
	public NoPoorProject_nongwei_FilterPopWindow(final Context context) {  
		super(context);
		
		LayoutInflater inflater = (LayoutInflater) context  
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
		conentView = inflater.inflate(R.layout.no_poor_project_nongwei_filter_popwindow, null,false);  
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
		//项目进度
		GridView gridView_lx = (GridView) conentView  
				.findViewById(R.id.gv_menu_lx);
		//项目状态
		GridView gridView_jd = (GridView) conentView  
				.findViewById(R.id.gv_menu_jd);
		
		
		RelativeLayout rl_time = (RelativeLayout)conentView.findViewById(R.id.rl_time);
		tv_time = (TextView)conentView.findViewById(R.id.tv_time);
		dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		screenInfo = new ScreenInfo((Activity)context);
		timeView = LayoutInflater.from(context).inflate(R.layout.item_odertime, null);
		timeCancle = (TextView) timeView.findViewById(R.id.time_cancle);
		timeSure = (TextView) timeView.findViewById(R.id.time_sure);
		timePop = new PopupWindow(timeView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		
		rl_time.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initTimePicker(timeView);
				initTimePop();
			}
		});
		
		
		String[] qylx = ResourceUtil.getInstance().getStringArrayById(R.array.qylx);
		if (qylx.length <= 0) {
			return;
		}
		List<String> list_qylx = new ArrayList<String>();
		Collections.addAll(list_qylx, qylx);
		popuGridViewAdapter_lx = new FilterPopuGridViewAdapter(list_qylx, context);
		gridView_lx.setAdapter(popuGridViewAdapter_lx);

		gridView_lx.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				popuGridViewAdapter_lx.setSelection(position);
				popuGridViewAdapter_lx.notifyDataSetChanged();
				string_qylx = (String)parent.getItemAtPosition(position);
			}
		});

		String[] spjd = ResourceUtil.getInstance().getStringArrayById(R.array.spjd);
		if (spjd.length <= 0) {
			return;
		}
		List<String> list_spjd = new ArrayList<String>();
		Collections.addAll(list_spjd, spjd);
		popuGridViewAdapter_jd = new FilterPopuGridViewAdapter(list_spjd, context);
		gridView_jd.setAdapter(popuGridViewAdapter_jd);

		gridView_jd.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				popuGridViewAdapter_jd.setSelection(position);
				popuGridViewAdapter_jd.notifyDataSetChanged();
				string_spjd = (String)parent.getItemAtPosition(position);
			}
		});

		
		Button btn_repeat = (Button)conentView.findViewById(R.id.btn_repeat);
		Button btn_submit = (Button)conentView.findViewById(R.id.btn_submit);

		btn_repeat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popuGridViewAdapter_jd.setSelection(-1);
				popuGridViewAdapter_jd.notifyDataSetChanged();
				popuGridViewAdapter_lx.setSelection(-1);
				popuGridViewAdapter_lx.notifyDataSetChanged();
				string_qylx = "";string_spjd = "";
			}
		});

		btn_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showMessageListener.Message(string_qylx.replace("不限", ""),string_spjd.replace("不限", ""),tv_time.getText().toString().trim().replace("时间不限", ""));
			}
		});
	}
	/** 
	 * 显示popupWindow 
	 *  
	 * @param parent 
	 */  
	public void showPopupWindow(View parent,int pox) {  
		if (!this.isShowing()) {  
			// 以下拉方式显示popupwindow  
			this.showAsDropDown(parent, pox, 10); 

		} else {  
			this.dismiss();  
		}  
	}  

	/**
	 * 更新选中item
	 *
	 * @param list
	 *
	 * 2016年6月14日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void setSelectionAdapter(List<MapEntity> list){

		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.qylx))) {

					string_qylx = list.get(i).getValue(); 
					popuGridViewAdapter_lx.setSelectionPosition(list.get(i).getValue());

				}else if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.spjd))) {
					string_spjd = list.get(i).getValue();
					popuGridViewAdapter_jd.setSelectionPosition(list.get(i).getValue());

				}
			}
		}
	}
	
	/**
	 * 选择日期
	 */
	private void initTimePop() {
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
				tv_time.setText(wheelMain.getTime());
				srBirthDay = wheelMain.getTime();
				timePop.setFocusable(false);
				timePop.dismiss();
			}
		});
	}

	/**
	 * 初始化时间选择器
	 * @param v
	 */
	private void initTimePicker(View v) {
		// TODO Auto-generated method stub
		wheelMain = new WheelMain(v);
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
		wheelMain.initBirthdayPicker(year, month, day);
//		wheelMain.initDateTimePickerYear(year);
	}
	
	
	public interface ShowMessageListener
	{
		/**
		 * 
		 *
		 * @param string_xmjd 项目进度
		 * @param string_xmzt 项目状态
		 *
		 * 2016年6月2日
		 *
		 * ZhaoDongShao
		 *
		 */
		void Message(String string_ffjd, String string_fflb, String time);
	}

}
