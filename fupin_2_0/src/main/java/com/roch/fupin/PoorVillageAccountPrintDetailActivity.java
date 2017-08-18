/**
 * 
 */
package com.roch.fupin;

import java.util.Map;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.SerializableMap;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.StringUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/**
 * 贫困村台账信息，贫困村信息化
 * 
 * @author ZhaoDongShao
 *
 * 2016年5月24日 
 *
 */
@ContentView(R.layout.activity_poorvillage_accountprint_detail)
public class PoorVillageAccountPrintDetailActivity extends MainBaseActivity{

	@ViewInject(R.id.ll_accountprint)
	LinearLayout ll_account_print;

	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		MyApplication.getInstance().addActivity(this);
		initToolbar();
		initDate();
	}

	/**
	 *
	 *
	 * 2016年8月5日
	 *
	 * ZhaoDongShao
	 *
	 */
	private void initToolbar() {
		// TODO Auto-generated method stub
		toolbar.setTitle("");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowTitleEnabled(false);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case android.R.id.home:  
			
			MyApplication.getInstance().finishActivity(this);
			
			break;

		default:
			break;
		}

		return true;
	}
	
	/**
	 * 初始化数据
	 *
	 * 2016年5月25日
	 *
	 * ZhaoDongShao
	 *
	 */
	private void initDate() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra(Common.INTENT_KEY);
		String title = bundle.getString(Common.TITLE_KEY);
		if (title != null && !title.equals("")) {
			tv_title.setText(title);
		}
		SerializableMap mSerializableMap = (SerializableMap) bundle.getSerializable(Common.BUNDEL_KEY);
		if (mSerializableMap != null) {

			Map<String, Map<String, String>> map = mSerializableMap.getMap();

			for (Map.Entry<String, Map<String, String>> mEntry : map.entrySet()) {

//				if (mEntry.getKey().equals(Common.KEY_TUDI)) {
					
					initTudiDate(mEntry.getValue());
//				}

			}

		}
	}
	
	/**
	 * 初始化土地信息
	 *
	 * 2016年5月25日
	 *
	 * ZhaoDongShao
	 * @param map 
	 *
	 */
	@SuppressLint("InflateParams")
	private void initTudiDate(Map<String, String> map) {
		// TODO Auto-generated method stub

		for (Map.Entry<String, String> mEntry : map.entrySet()) {
			RelativeLayout layout = new RelativeLayout(mContext);
			layout.setPadding(30, 0, 30, 0);
			
			TextView tv_key = new TextView(mContext);
			tv_key.setTextColor(Color.BLACK);
			tv_key.setTextSize(16);
//			tv_key.setEllipsize(TruncateAt.MIDDLE);
//			tv_key.setSingleLine(true);
			tv_key.setText(mEntry.getKey());

			RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams  
					(Common.Width / 3 * 2, LayoutParams.WRAP_CONTENT);    
			lp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);    
			lp1.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);    
			// tv_key位于 layout 的左边，水平居中    
			layout.addView(tv_key, lp1);    



			TextView tv_value = new TextView(mContext);
			tv_value.setTextColor(Color.BLACK);
			tv_value.setTextSize(16);
			//			tv_value.setText(mEntry.getValue());
			if (StringUtil.isEmpty(mEntry.getValue())) {
				tv_value.setText("");
			}else {
				tv_value.setText(mEntry.getValue());
			}
			

			RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams  
					(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);    
			lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);    
			lp2.addRule(RelativeLayout.CENTER_VERTICAL);
			// tv_value 位于 layout 的右边，水平居中    
			layout.addView(tv_value, lp2);   


			LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams  
					(ViewGroup.LayoutParams.MATCH_PARENT, Common.Width / 8);    
			ll_account_print.addView(layout,lp3);
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.layout_view_horizontal, null);
			ll_account_print.addView(view);
		}

	}

}
