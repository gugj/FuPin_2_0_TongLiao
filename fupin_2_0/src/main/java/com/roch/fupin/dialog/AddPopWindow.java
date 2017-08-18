package com.roch.fupin.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.roch.fupin.adapter.PopuMenuListAdapter;
import com.roch.fupin.entity.ListMenu;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.ResourceUtil;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

public class AddPopWindow extends PopupWindow {
	
	private View conentView;  
	PopuMenuListAdapter menuListAdapter;
	private ShowMessageListener showMessageListener;

	public void setShowMessageListener(ShowMessageListener showMessageListener) {
		this.showMessageListener = showMessageListener;
	}

	/**
	 * @param context 上下文
	 * @param type_selection_conut 类型为1，说明是只有 帮扶记录 1个选项;类型为2，说明是有 照片和拍照 2个选项
	 */
	@SuppressLint("InflateParams")
	public AddPopWindow(final Activity context ,int type_selection_conut) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.popuwindow_menu, null,false);  
//		int h = context.getWindowManager().getDefaultDisplay().getHeight();  
//		int w = context.getWindowManager().getDefaultDisplay().getWidth();  
		// 设置SelectPicPopupWindow的View  
		this.setContentView(conentView);  
		// 设置SelectPicPopupWindow弹出窗体的宽  
		this.setWidth((int)(Common.Width * 0.3));  
		// 设置SelectPicPopupWindow弹出窗体的高  
		this.setHeight(LayoutParams.WRAP_CONTENT);  
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);  
		this.setOutsideTouchable(true);  
		// 刷新状态  
		this.update();  
		// 实例化一个ColorDrawable颜色为半透明  
//		ColorDrawable dw = new ColorDrawable(0000000000);  
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作  
		this.setBackgroundDrawable(ResourceUtil.getInstance().getDrawableByID(R.drawable.pop_info_window_1));  
		// mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);  
		// 设置SelectPicPopupWindow弹出窗体动画效果  
		this.setAnimationStyle(R.style.AnimationPreview);  
		ListView listView = (ListView) conentView.findViewById(R.id.lv_menu);
		
		List<ListMenu> list = new ArrayList<ListMenu>();
		ListMenu listMenu = null;

		if(type_selection_conut==1){ //类型为1，说明是只有 帮扶记录 1个选项
			listMenu = new ListMenu();
			listMenu.setName("帮扶记录");
			listMenu.setRid(0);
			list.add(listMenu);
		}else if(type_selection_conut==2){ //类型为2，说明是有 照片和拍照 2个选项
			listMenu = new ListMenu();
			listMenu.setName("照片");
			listMenu.setRid(R.drawable.ico_photo);
			list.add(listMenu);

			listMenu = new ListMenu();
			listMenu.setName("拍照");
			listMenu.setRid(R.drawable.ico_cream);
			list.add(listMenu);
		}

		menuListAdapter = new PopuMenuListAdapter(list, context);
		listView.setAdapter(menuListAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ListMenu listMenu = (ListMenu)parent.getItemAtPosition(position);
				AddPopWindow.this.dismiss();
				showMessageListener.Message(listMenu);
			}
		});
	}

	/** 
	 * 显示popupWindow
	 * @param parent 
	 */  
	public void showPopupWindow(View parent,int pox) {  
		if (!this.isShowing()) {  
			// 以下拉方式显示Popupwindow  
			this.showAsDropDown(parent, pox, 10);
		} else {  
			this.dismiss();  
		}  
	}
	
	public interface ShowMessageListener {
		/**
		 * 显示信息
		 * @param object
		 */
		void Message(Object object);
	}

}
