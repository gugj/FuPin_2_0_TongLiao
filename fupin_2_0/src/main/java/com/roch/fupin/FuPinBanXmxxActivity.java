/**
 * 
 */
package com.roch.fupin;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.adapter.NoPoorProjectJiaoTiJu_XMXX_Adapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.ProjectFupinbanTrainAppModel;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.CommonUtil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 项目信息
 * 
 * @author ZhaoDongShao
 *
 * 2016年5月27日 
 *
 */
@ContentView(R.layout.fupinban_xmxx_activity)
public class FuPinBanXmxxActivity extends BaseActivity{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.lv_poor)
	ListView listview;

	NoPoorProjectJiaoTiJu_XMXX_Adapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		ViewUtils.inject(this);
		MyApplication.getInstance().addActivity(mActivity);
		initToolbar();
		initData();
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
	 *
	 *
	 * 2016年5月25日
	 *
	 * ZhaoDongShao
	 *
	 */
	private void initData() {

		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra(Common.INTENT_KEY);
		ProjectFupinbanTrainAppModel appModel = (ProjectFupinbanTrainAppModel) bundle.getSerializable(Common.BUNDEL_KEY);
		if (appModel != null) {
			tv_title.setText(Common.PROJECT_DETAIL);
			
			List<MapEntity> xmxxs = new ArrayList<MapEntity>(); //项目信息
			
			xmxxs.add(new MapEntity("项目名称", appModel.getProjectname()));
			xmxxs.add(new MapEntity("立项时间", appModel.getLixiangdate() != null ? appModel.getLixiangdate().split(" ")[0] : ""));
			xmxxs.add(new MapEntity("中省资金（万元）", String.valueOf(appModel.getZszj()) + "万元"));
			xmxxs.add(new MapEntity("市级资金（万元）", String.valueOf(appModel.getSjzj()) + "万元"));
			xmxxs.add(new MapEntity("镇村配套（万元）", String.valueOf(appModel.getZcpt()) + "万元"));
			xmxxs.add(new MapEntity("群众自筹（万元）", String.valueOf(appModel.getQzzc()) + "万元"));
			xmxxs.add(new MapEntity("已完成投资", String.valueOf(appModel.getYwctz()) + "万元"));
			xmxxs.add(new MapEntity("投资比例", String.valueOf(CommonUtil.getBili(appModel.getYwctz(), appModel.getInvesttotal()))));
			
			
			
//			xmxxs.add(new MapEntity("培训名称", appModel.getTrainname()));
//			xmxxs.add(new MapEntity("培训机构名称", appModel.getTraincompanyname()));
//			xmxxs.add(new MapEntity("培训类别", appModel.getTraintypetext()));
//			xmxxs.add(new MapEntity("培训时间", appModel.getTraindate() != null ? appModel.getTraindate().split(" ")[0] : ""));
//			xmxxs.add(new MapEntity("培训地点", appModel.getTrainaddress()));
//			xmxxs.add(new MapEntity("培训方式", appModel.getTrainmodetext()));
//			xmxxs.add(new MapEntity("培训负责人", appModel.getPersonname()));
//			xmxxs.add(new MapEntity("负责人联系方式", appModel.getPersonphone()));
	
//			xmxxs.add(new MapEntity("中省资金（万元）", String.valueOf(appModel.getZszj())));
//			xmxxs.add(new MapEntity("市级资金（万元）", String.valueOf(appModel.getSjzj())));
//			xmxxs.add(new MapEntity("镇村配套（万元）", String.valueOf(appModel.getZcpt())));
			xmxxs.add(new MapEntity("项目类型", appModel.getProjectcategoryidcall()));
			xmxxs.add(new MapEntity("项目进度", appModel.getProjectscheduleidcall()));
			xmxxs.add(new MapEntity("项目状态", appModel.getProjectstatusidcall()));
			xmxxs.add(new MapEntity("项目责任单位", appModel.getDutydeptname()));
			xmxxs.add(new MapEntity("项目责任人", appModel.getDutyperson()));
			xmxxs.add(new MapEntity("联系方式", appModel.getDutypersonphone()));
			xmxxs.add(new MapEntity("立项日期", CommonUtil.getSpliteDate(appModel.getLixiangdate())));
			xmxxs.add(new MapEntity("建设内容", appModel.getBuildcontent()));
			xmxxs.add(new MapEntity("项目效益", appModel.getProjecteffect()));
			
			xmxxs.add(new MapEntity("报备日期", appModel.getBaobeidate() != null ? appModel.getBaobeidate().split(" ")[0] : ""));
			xmxxs.add(new MapEntity("招标日期", appModel.getZhaobiaodate() != null ? appModel.getZhaobiaodate().split(" ")[0] : "".split(" ")[0]));
			xmxxs.add(new MapEntity("开工日期", appModel.getKaigongdate() != null ? appModel.getKaigongdate().split(" ")[0] : "".split(" ")[0]));
			xmxxs.add(new MapEntity("竣工日期", appModel.getJungongdate() != null ? appModel.getJungongdate().split(" ")[0] : "".split(" ")[0]));
			xmxxs.add(new MapEntity("备注", appModel.getRemark()));
			adapter = new NoPoorProjectJiaoTiJu_XMXX_Adapter(mContext, xmxxs);
			listview.setAdapter(adapter);
		}
		
	}
}
