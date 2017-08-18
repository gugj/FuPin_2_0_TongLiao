package com.roch.fupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.NoPoorProjectFuLian_XMXX_Adapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.ProjectInfoAppEntity;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.CommonUtil;
import com.roch.fupin_2_0.R;
import java.util.ArrayList;
import java.util.List;

/**
 * 林业局道路绿化
 * @author ZhaoDongShao
 * 2016年6月2日 
 */
@ContentView(R.layout.activity_helppeople_familypeople)
public class NoPoorProjectLinYeJu_DLLH_DetailActivity extends MainBaseActivity implements OnClickListener{

	@ViewInject(R.id.lv_poor)
	ListView listview;
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;

	@ViewInject(R.id.tv_xiangmu_photo)
	TextView tv_xiangmu_photo;

	NoPoorProjectFuLian_XMXX_Adapter adapter;
	private ProjectInfoAppEntity appModel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		MyApplication.getInstance().addActivity(this);
		initToolbar();
		initData();
	}

	/**
	 * 2016年5月25日
	 * ZhaoDongShao
	 */
	private void initData() {
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra(Common.INTENT_KEY);
		appModel = (ProjectInfoAppEntity) bundle.getSerializable(Common.BUNDEL_KEY);
		if (appModel != null) {
			tv_title.setText(Common.PROJECT_DETAIL);
			tv_xiangmu_photo.setVisibility(View.VISIBLE);
			tv_xiangmu_photo.setOnClickListener(this);
			List<MapEntity> xmxxs = new ArrayList<MapEntity>(); //项目信息
			xmxxs.add(new MapEntity("项目名称", appModel.getProjectname()));
			xmxxs.add(new MapEntity("立项时间", appModel.getLixiangdate() != null ? appModel.getLixiangdate().split(" ")[0] : ""));
			xmxxs.add(new MapEntity("中省资金（万元）", String.valueOf(appModel.getZszj()) + "万元"));
			xmxxs.add(new MapEntity("市级资金（万元）", String.valueOf(appModel.getSjzj()) + "万元"));
			xmxxs.add(new MapEntity("镇村配套（万元）", String.valueOf(appModel.getZcpt()) + "万元"));
			xmxxs.add(new MapEntity("群众自筹（万元）", String.valueOf(appModel.getQzzc()) + "万元"));
			xmxxs.add(new MapEntity("已完成投资", String.valueOf(appModel.getYwctz()) + "万元"));
			xmxxs.add(new MapEntity("投资比例", String.valueOf(CommonUtil.getBili(appModel.getYwctz(), appModel.getInvesttotal()))));

			xmxxs.add(new MapEntity("项目类型", appModel.getProjectcategoryidcall()));
			xmxxs.add(new MapEntity("建设内容", appModel.getBuildcontent()));
			xmxxs.add(new MapEntity("项目效益", appModel.getProjecteffect()));
			xmxxs.add(new MapEntity("项目进度", appModel.getProjectscheduleidcall()));
			xmxxs.add(new MapEntity("项目状态", appModel.getProjectstatusidcall()));
			xmxxs.add(new MapEntity("项目负责人", appModel.getDutyperson()));
			xmxxs.add(new MapEntity("联系方式", appModel.getDutypersonphone()));
			xmxxs.add(new MapEntity("项目责任单位", appModel.getDutydeptname()));
			xmxxs.add(new MapEntity("备注", appModel.getRemark()));
			adapter = new NoPoorProjectFuLian_XMXX_Adapter(mContext, xmxxs);
			listview.setAdapter(adapter);
		}
	}

	/**
	 * 2016年8月5日
	 * ZhaoDongShao
	 */
	private void initToolbar() {
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
		switch (item.getItemId()) {
			case android.R.id.home:
				MyApplication.getInstance().finishActivity(this);
				break;

			default:
				break;
		}
		return true;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
			case R.id.tv_xiangmu_photo:
//			showToast("点击了项目照片");
				Intent intent=new Intent(this,XiangMuZhaoPianActivity.class);
				intent.putExtra("xiangmu_id", appModel.getId());
				startActivity(intent);
				break;

			default:
				break;
		}
	}

}