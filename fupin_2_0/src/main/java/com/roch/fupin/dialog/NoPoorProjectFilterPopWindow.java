package com.roch.fupin.dialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import com.roch.fupin.NoPoorProjectActivity;
import com.roch.fupin.NoPoorProjectCaiZhengJuActivity;
import com.roch.fupin.NoPoorProjectCanLianActivity;
import com.roch.fupin.NoPoorProjectFuLianActivity;
import com.roch.fupin.NoPoorProjectFuPinBanActivity;
import com.roch.fupin.NoPoorProjectJinDuTiXingActivity;
import com.roch.fupin.NoPoorProjectLinYeJuActivity;
import com.roch.fupin.NoPoorProjectRenLaoJuActivity;
import com.roch.fupin.NoPoorProjectZhuJianJuActivity;
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
import android.widget.LinearLayout;
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
public class NoPoorProjectFilterPopWindow extends PopupWindow {

	private View conentView;  
	FilterPopuGridViewAdapter popuGridViewAdapter_jd; //进度
	FilterPopuGridViewAdapter popuGridViewAdapter_gzyy; //原因
	FilterPopuGridViewAdapter popuGridViewAdapter_gzfs; //改造方式
	FilterPopuGridViewAdapter popuGridViewAdapter_fl; //妇联
	FilterPopuGridViewAdapter popuGridViewAdapter_zt; //状态
	FilterPopuGridViewAdapter popuGridViewAdapter_pz; //养殖品种
	FilterPopuGridViewAdapter popuGridViewAdapter_lb; //培训类别
	FilterPopuGridViewAdapter popuGridViewAdapter_lx; //项目类型
	FilterPopuGridViewAdapter popuGridViewAdapter_xgznbm; //相关职能部门
	String string_xmjd = "",string_xmzt = "",string_xm_time = "", string_yzpz = "";
	String string_pxlb = ""; //培训类别

	private View timeView;
	private WheelMain wheelMain; // TimePicker
	private ScreenInfo screenInfo;
	private String srBirthDay = "";
	private SimpleDateFormat dateFormat;
	private TextView timeCancle;
	private TextView timeSure;
	private TextView tv_time;
	private PopupWindow timePop;// 时间选择器popup
	
	
	/**
	 * 是否显示养殖品种筛选菜单
	 */
	boolean is_show_yzpz = false;
	/**
	 * 项目进度
	 */
	boolean is_show_xmjd = false;
	/**
	 * 项目状态
	 */
	boolean is_show_xmzt = false;
	/**
	 * 养殖品种
	 */
	boolean is_show_pxlb = false;
	/**
	 * 改造原因
	 */
	boolean is_show_gzyy = false;
	/**
	 * 改造方式
	 */
	boolean is_show_gzfs = false;
	/**
	 * 妇联培训类别
	 */
	boolean is_show_fl_pxlb = false;
	/**
	 * 项目类型
	 */
	boolean is_show_xmlx = false;
	/**
	 * 相关职能部门
	 */
	boolean is_show_xgznbm = false;
	private ShowMessageListener showMessageListener;
	public void setShowMessageListener(ShowMessageListener showMessageListener) {
		this.showMessageListener = showMessageListener;
	}

	private ShowOneMessageListener showOneMessageListener;
	public void setShowOneMessageListener(ShowOneMessageListener showOneMessageListener) {
		this.showOneMessageListener = showOneMessageListener;
	}
	
	
	@SuppressLint("InflateParams")
	public NoPoorProjectFilterPopWindow(Context context) {  
		super(context);
		
		LayoutInflater inflater = (LayoutInflater) context  
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
		conentView = inflater.inflate(R.layout.no_poor_project_filter_popwindow, null,true);  
		//		int h = context.getWindowManager().getDefaultDisplay().getHeight();  
		//		int w = context.getWindowManager().getDefaultDisplay().getWidth();  
		// 设置SelectPicPopupWindow的View  
		this.setContentView(conentView);  
		// 设置SelectPicPopupWindow弹出窗体的宽  
		this.setWidth(LayoutParams.MATCH_PARENT);  
		// 设置SelectPicPopupWindow弹出窗体的高  
		this.setHeight(LayoutParams.MATCH_PARENT);  
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setTouchable(true);
		this.setOutsideTouchable(true);
		this.setFocusable(true);
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
		
		
		//项目进度
		final GridView gridView_jd = (GridView) conentView  
				.findViewById(R.id.gv_menu_jd);
		//项目状态
		GridView gridView_zt = (GridView) conentView  
				.findViewById(R.id.gv_menu_zt);
		//养殖品种
		GridView gridView_pz = (GridView) conentView  
				.findViewById(R.id.gv_menu_yzpz);
		//培训类别
		GridView gridView_lb = (GridView) conentView
				.findViewById(R.id.gv_menu_pxlb);
		//项目类型
		GridView gridView_xmlx = (GridView) conentView
				.findViewById(R.id.gv_menu_xmlx);
		//改造原因
		GridView gridView_gzyy = (GridView) conentView
				.findViewById(R.id.gv_menu_gzyy);

		//改造方式
		GridView gridView_gzfs = (GridView) conentView
				.findViewById(R.id.gv_menu_gzfs);

		//妇联培训类别
		GridView gridView_fl_pxlb = (GridView) conentView
				.findViewById(R.id.gv_menu_fl_pxlb);

		//财政局相关职能部门
		GridView gridView_xgznbm = (GridView) conentView
				.findViewById(R.id.gv_menu_xgznbm);



		//相关职能部门
		String[] xgznbm = ResourceUtil.getInstance().getStringArrayById(R.array.xgznbm);
		if (xgznbm.length <= 0) {
			return;
		}
		List<String> list_xgznbm = new ArrayList<String>();
		Collections.addAll(list_xgznbm, xgznbm);
		popuGridViewAdapter_xgznbm = new FilterPopuGridViewAdapter(list_xgznbm, context);
		gridView_xgznbm.setAdapter(popuGridViewAdapter_xgznbm);

		gridView_xgznbm.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				popuGridViewAdapter_xgznbm.setSelection(position);
				popuGridViewAdapter_xgznbm.notifyDataSetChanged();
				string_xmzt = (String)parent.getItemAtPosition(position);
			}
		});

		//妇联培训类别
		String[] fl_pxlb = ResourceUtil.getInstance().getStringArrayById(R.array.fl_pxlb);
		if (fl_pxlb.length <= 0) {
			return;
		}
		List<String> list_fl_pxlb = new ArrayList<String>();
		Collections.addAll(list_fl_pxlb, fl_pxlb);
		popuGridViewAdapter_fl = new FilterPopuGridViewAdapter(list_fl_pxlb, context);
		gridView_fl_pxlb.setAdapter(popuGridViewAdapter_fl);

		gridView_fl_pxlb.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				popuGridViewAdapter_fl.setSelection(position);
				popuGridViewAdapter_fl.notifyDataSetChanged();
				string_pxlb = (String)parent.getItemAtPosition(position);
			}
		});

		//改造方式
		String[] gzfs = ResourceUtil.getInstance().getStringArrayById(R.array.gzfs);
		if (gzfs.length <= 0) {
			return;
		}
		List<String> list_gzfs = new ArrayList<String>();
		Collections.addAll(list_gzfs, gzfs);
		popuGridViewAdapter_gzfs = new FilterPopuGridViewAdapter(list_gzfs, context);
		gridView_gzfs.setAdapter(popuGridViewAdapter_gzfs);

		gridView_gzfs.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				popuGridViewAdapter_gzfs.setSelection(position);
				popuGridViewAdapter_gzfs.notifyDataSetChanged();
				string_pxlb = (String)parent.getItemAtPosition(position);
			}
		});

		//改造原因
		String[] gzyy = ResourceUtil.getInstance().getStringArrayById(R.array.gzyy);
		if (gzyy.length <= 0) {
			return;
		}
		List<String> list_gzyy = new ArrayList<String>();
		Collections.addAll(list_gzyy, gzyy);
		popuGridViewAdapter_gzyy = new FilterPopuGridViewAdapter(list_gzyy, context);
		gridView_gzyy.setAdapter(popuGridViewAdapter_gzyy);

		gridView_gzyy.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				popuGridViewAdapter_gzyy.setSelection(position);
				popuGridViewAdapter_gzyy.notifyDataSetChanged();
				string_pxlb = (String)parent.getItemAtPosition(position);
			}
		});
		//项目进度
		String[] xmjd = ResourceUtil.getInstance().getStringArrayById(R.array.xmjd);
		if (xmjd.length <= 0) {
			return;
		}
		List<String> list_xmjd = new ArrayList<String>();
		Collections.addAll(list_xmjd, xmjd);
		popuGridViewAdapter_jd = new FilterPopuGridViewAdapter(list_xmjd, context);
		gridView_jd.setAdapter(popuGridViewAdapter_jd);

		gridView_jd.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				popuGridViewAdapter_jd.setSelection(position);
				popuGridViewAdapter_jd.notifyDataSetChanged();
				string_xmjd = (String)parent.getItemAtPosition(position);
			}
		});
		//项目状态
		String[] xmzt = ResourceUtil.getInstance().getStringArrayById(R.array.xmzt);
		if (xmzt.length <= 0) {
			return;
		}
		List<String> list_xmzt = new ArrayList<String>();
		Collections.addAll(list_xmzt, xmzt);
		popuGridViewAdapter_zt = new FilterPopuGridViewAdapter(list_xmzt, context);
		gridView_zt.setAdapter(popuGridViewAdapter_zt);

		gridView_zt.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				popuGridViewAdapter_zt.setSelection(position);
				popuGridViewAdapter_zt.notifyDataSetChanged();
				string_xmzt = (String)parent.getItemAtPosition(position);
			}
		});

		//养殖品种
		String[] yzpz = ResourceUtil.getInstance().getStringArrayById(R.array.yzpz);
		if (yzpz.length <= 0) {
			return;
		}
		List<String> list_yzpz = new ArrayList<String>();
		Collections.addAll(list_yzpz, yzpz);
		popuGridViewAdapter_pz = new FilterPopuGridViewAdapter(list_yzpz, context);
		gridView_pz.setAdapter(popuGridViewAdapter_pz);

		gridView_pz.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				popuGridViewAdapter_pz.setSelection(position);
				popuGridViewAdapter_pz.notifyDataSetChanged();
				string_yzpz = (String)parent.getItemAtPosition(position);
			}
		});
		//培训类别
		String[] pxlb = ResourceUtil.getInstance().getStringArrayById(R.array.pxlb);
		if (pxlb.length <= 0) {
			return;
		}
		List<String> list_pxlb = new ArrayList<String>();
		Collections.addAll(list_pxlb, pxlb);
		popuGridViewAdapter_lb = new FilterPopuGridViewAdapter(list_pxlb, context);
		gridView_lb.setAdapter(popuGridViewAdapter_lb);

		gridView_lb.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				popuGridViewAdapter_lb.setSelection(position);
				popuGridViewAdapter_lb.notifyDataSetChanged();
				string_pxlb = (String)parent.getItemAtPosition(position);
			}
		});

		//项目类型
		String[] xmlx = ResourceUtil.getInstance().getStringArrayById(R.array.xmlx);
		if (xmlx.length <= 0) {
			return;
		}
		List<String> list_xmlx = new ArrayList<String>();
		Collections.addAll(list_xmlx, xmlx);
		popuGridViewAdapter_lx = new FilterPopuGridViewAdapter(list_xmlx, context);
		gridView_xmlx.setAdapter(popuGridViewAdapter_lx);

		gridView_xmlx.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				popuGridViewAdapter_lx.setSelection(position);
				popuGridViewAdapter_lx.notifyDataSetChanged();
				string_xmzt = (String)parent.getItemAtPosition(position);
			}
		});

		Button btn_repeat = (Button)conentView.findViewById(R.id.btn_repeat);
		Button btn_submit = (Button)conentView.findViewById(R.id.btn_submit);

		btn_repeat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				string_xmjd = "";
				string_xmzt = "";
				string_xm_time = "";
				string_yzpz = "";
				string_pxlb = "";
				popuGridViewAdapter_gzyy.setSelection(-1);
				popuGridViewAdapter_gzyy.notifyDataSetChanged();
				popuGridViewAdapter_gzfs.setSelection(-1);
				popuGridViewAdapter_gzfs.notifyDataSetChanged();
				popuGridViewAdapter_lx.setSelection(-1);
				popuGridViewAdapter_lx.notifyDataSetChanged();
				popuGridViewAdapter_fl.setSelection(-1);
				popuGridViewAdapter_fl.notifyDataSetChanged();
				popuGridViewAdapter_xgznbm.setSelection(-1);
				popuGridViewAdapter_xgznbm.notifyDataSetChanged();
				popuGridViewAdapter_lb.setSelection(-1);
				popuGridViewAdapter_lb.notifyDataSetChanged();
				popuGridViewAdapter_jd.setSelection(-1);
				popuGridViewAdapter_jd.notifyDataSetChanged();
				popuGridViewAdapter_zt.setSelection(-1);
				popuGridViewAdapter_zt.notifyDataSetChanged();
				popuGridViewAdapter_pz.setSelection(-1);
				popuGridViewAdapter_pz.notifyDataSetChanged();
			}
		});

		btn_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (showMessageListener != null) {

					showMessageListener.Message(string_xmjd.replace("不限", ""),string_xmzt.replace("不限", ""),string_yzpz.replace("不限", ""), tv_time.getText().toString().trim().replace("时间不限", ""));
				}
				if (showOneMessageListener != null) {
					showOneMessageListener.Message(string_pxlb.replace("不限", ""), tv_time.getText().toString().trim().replace("时间不限", ""));
				}
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
	 * 设置养殖品种筛选条件是否显示
	 *
	 *
	 * 2016年6月14日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void showYzpzGridView(boolean isshow){
		this.is_show_yzpz = isshow;

		if (is_show_yzpz) {
			LinearLayout ll = (LinearLayout) conentView.findViewById(R.id.ll_yzpz);
			ll.setVisibility(View.VISIBLE);
		}
	}
	/**
	 * 是否显示项目进度
	 *
	 * @param isshow
	 *
	 * 2016年6月14日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void showXmjdGridView(boolean isshow){
		this.is_show_xmjd = isshow;

		if (is_show_xmjd) {
			LinearLayout ll = (LinearLayout) conentView.findViewById(R.id.ll_xmjd);
			ll.setVisibility(View.VISIBLE);
		}
	}
	/**
	 * 是否显示项目状态
	 *
	 * @param isshow
	 *
	 * 2016年6月14日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void showXmztGridView(boolean isshow){
		this.is_show_xmzt = isshow;
		if (is_show_xmzt) {
			LinearLayout ll = (LinearLayout) conentView.findViewById(R.id.ll_xmzt);
			ll.setVisibility(View.VISIBLE);
		}
	}
	/**
	 * 培训类别
	 *
	 * @param isshow
	 *
	 * 2016年6月14日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void showPxlbGridView(boolean isshow){
		this.is_show_pxlb = isshow;
		if (is_show_pxlb) {
			LinearLayout ll = (LinearLayout) conentView.findViewById(R.id.ll_pxlb);
			ll.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 改造原因
	 *
	 * @param isshow
	 *
	 * 2016年6月14日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void showGzyyGridView(boolean isshow){
		this.is_show_gzyy = isshow;
		if (is_show_gzyy) {
			LinearLayout ll = (LinearLayout) conentView.findViewById(R.id.ll_gzyy);
			ll.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 改造方式
	 *
	 * @param isshow
	 *
	 * 2016年6月14日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void showGzfsGridView(boolean isshow){
		this.is_show_gzfs = isshow;
		if (is_show_gzfs) {
			LinearLayout ll = (LinearLayout) conentView.findViewById(R.id.ll_gzfs);
			ll.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 改造方式
	 *
	 * @param isshow
	 *
	 * 2016年6月14日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void showFlPxlbGridView(boolean isshow){
		this.is_show_fl_pxlb = isshow;
		if (is_show_fl_pxlb) {
			LinearLayout ll = (LinearLayout) conentView.findViewById(R.id.ll_fl_pxlb);
			ll.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 项目类型
	 *
	 * @param isshow
	 *
	 * 2016年6月14日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void showXmlxGridView(boolean isshow){
		this.is_show_xmlx = isshow;
		if (is_show_xmlx) {
			LinearLayout ll = (LinearLayout) conentView.findViewById(R.id.ll_xmlx);
			ll.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 相关职能部门
	 *
	 * @param isshow
	 *
	 * 2016年6月14日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void showXgznbmGridView(boolean isshow){
		this.is_show_xgznbm = isshow;
		if (is_show_xgznbm) {
			LinearLayout ll = (LinearLayout) conentView.findViewById(R.id.ll_xgznbm);
			ll.setVisibility(View.VISIBLE);
		}
	}
	/**
	 * 设置选中的position
	 *
	 *
	 * 2016年6月14日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void setSelectionAdapter(Class<?> class1,List<MapEntity> list){

		if (class1 == NoPoorProjectCaiZhengJuActivity.class) {

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.xgznbm))) {

						string_xmzt = list.get(i).getValue();
						popuGridViewAdapter_xgznbm.setSelectionPosition(list.get(i).getValue());

					}else if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.xmjd))) {
						string_xmjd = list.get(i).getValue();
						popuGridViewAdapter_jd.setSelectionPosition(list.get(i).getValue());

					}
				}
			}

		}else if (class1 == NoPoorProjectFuLianActivity.class) {
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.pxlb))) {

						string_pxlb = list.get(i).getValue();
						popuGridViewAdapter_fl.setSelectionPosition(list.get(i).getValue());

					}
				}
			}
		}else if (class1 == NoPoorProjectJinDuTiXingActivity.class) {
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.xgznbm))) {

						string_xmzt = list.get(i).getValue();
						popuGridViewAdapter_xgznbm.setSelectionPosition(list.get(i).getValue());

					}
				}
			}
		}else if (class1 == NoPoorProjectFuPinBanActivity.class) {
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.xmjd))) {

						string_xmjd = list.get(i).getValue();
						popuGridViewAdapter_jd.setSelectionPosition(list.get(i).getValue());

					}else if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.xmlx))) {
						string_pxlb = list.get(i).getValue();
						popuGridViewAdapter_lx.setSelectionPosition(list.get(i).getValue());

					}
				}
			}
		}else if (class1 == NoPoorProjectActivity.class) {
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.xmjd))) {

						string_xmjd = list.get(i).getValue();
						popuGridViewAdapter_jd.setSelectionPosition(list.get(i).getValue());

					}else if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.xmzt))) {
						string_xmzt = list.get(i).getValue();
						popuGridViewAdapter_zt.setSelectionPosition(list.get(i).getValue());

					}
				}
			}
		}else if (class1 == NoPoorProjectCanLianActivity.class) {
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.gzyy))) {

						string_pxlb = list.get(i).getValue();
						popuGridViewAdapter_gzfs.setSelectionPosition(list.get(i).getValue());

					}else if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.pxlb))) {
						string_pxlb = list.get(i).getValue();
						popuGridViewAdapter_lb.setSelectionPosition(list.get(i).getValue());

					}
				}
			}
		}else if (class1 == NoPoorProjectZhuJianJuActivity.class) {
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.gzyy))) {

						string_pxlb = list.get(i).getValue();
						popuGridViewAdapter_gzyy.setSelectionPosition(list.get(i).getValue());

					}
				}
			}
		}else if (class1 == NoPoorProjectRenLaoJuActivity.class) {
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.pxlb))) {

						string_yzpz = list.get(i).getValue();
						popuGridViewAdapter_lb.setSelectionPosition(list.get(i).getValue());

					}
				}
			}
		}else if (class1 == NoPoorProjectLinYeJuActivity.class) {
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.xmjd))) {

						string_xmjd = list.get(i).getValue();
						popuGridViewAdapter_jd.setSelectionPosition(list.get(i).getValue());

					}else if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.xmzt))) {
						string_xmzt = list.get(i).getValue();
						popuGridViewAdapter_zt.setSelectionPosition(list.get(i).getValue());

					}else if (list.get(i).getKey().equals(ResourceUtil.getInstance().getStringById(R.string.yzpz))) {
						string_yzpz = list.get(i).getValue();
						popuGridViewAdapter_pz.setSelectionPosition(list.get(i).getValue());

					}
				}
			}
		}
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
		void Message(String string_xmjd, String string_xmzt, String string_yzpz, String time);
	}

	public interface ShowOneMessageListener
	{
		/**
		 * 培训类别
		 *
		 * @param string_pxlb
		 *
		 * 2016年6月14日
		 *
		 * ZhaoDongShao
		 *
		 */
		void Message(String string_pxlb, String time);
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
	
}
