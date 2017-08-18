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
import com.roch.fupin.adapter.NoPoorProjectDetailAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.ProjectInfoAppEntity;
import com.roch.fupin.entity.ViewItem;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.DbUtil;
import com.roch.fupin_2_0.R;
import java.util.List;

/**
 * 交通局、水利局、新村办项目详情页面
 * @author ZhaoDongShao
 * 2016年6月1日 
 */
@ContentView(R.layout.activity_helppeople_familypeople)
public class NoPoorProjectDetailActivity extends MainBaseActivity implements OnClickListener{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.tv_xiangmu_photo)
	TextView tv_xiangmu_photo;
	@ViewInject(R.id.lv_poor)
	ListView listView;

	NoPoorProjectDetailAdapter mAdapter;
	/**
	 * 项目实体类
	 */
	private ProjectInfoAppEntity mAppEntity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		ViewUtils.inject(this);
		initToolbar();
		initDate();
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


	/**
	 * 初始化数据
	 * 2016年6月1日
	 * ZhaoDongShao
	 */
	private void initDate() {
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra(Common.INTENT_KEY);
		mAppEntity = (ProjectInfoAppEntity) bundle.get(Common.BUNDEL_KEY);
		String title_name = bundle.getString("title_name");
		if (mAppEntity != null) {
			tv_title.setText(Common.PROJECT_DETAIL);
			tv_xiangmu_photo.setVisibility(View.VISIBLE);
			tv_xiangmu_photo.setOnClickListener(this);
		}else {
			return;
		}
		//		List<ViewItem> list = new ArrayList<ViewItem>();
		//		ViewItem viewItem = null;
		//		Map<String, String> map = null;

		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(0);
		//		map.put("项目", "");
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("项目类型", mAppEntity.getProjectcategoryidcall());
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("项目名称", mAppEntity.getProjectname());
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(2);
		//		map.put("建设内容", mAppEntity.getBuildcontent());
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("扶贫对象", mAppEntity.getProjectcategoryidcall());
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("项目效益", mAppEntity.getProjecteffect());
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("项目责任单位", mAppEntity.getDutydeptname());
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("项目责任人", mAppEntity.getDutyperson());
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		
		//		map.put("联系方式", mAppEntity.getDutypersonphone());
		//		
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		
		//		map.put("项目进度", mAppEntity.getProjectscheduleidcall());
		//		
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		
		//		map.put("项目状态", mAppEntity.getProjectstatusidcall());
		//		
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("中标单位名称", mAppEntity.getZhongbiaocompany());
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(0);
		//		map.put("项目时间", "");
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("计划开始时间", mAppEntity.getPlanstartdate());
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("计划结束时间", mAppEntity.getPlanenddate());
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("立项日期", mAppEntity.getLixiangdate());
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("报备日期", mAppEntity.getBaobeidate());
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("招标日期", mAppEntity.getZhaobiaodate());
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("开工日期", mAppEntity.getKaigongdate());
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("竣工日期", mAppEntity.getJungongdate());
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("验收日期", mAppEntity.getYanshoudate());
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(0);
		//		map.put("项目资金", "");
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("中省资金", String.valueOf(mAppEntity.getZszj()));
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("市级资金", String.valueOf(mAppEntity.getSjzj()));
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("镇村配套", String.valueOf(mAppEntity.getZcpt()));
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		//		
		//		map = new HashMap<String, String>();
		//		viewItem = new ViewItem();
		//		viewItem.setType(1);
		//		map.put("投资合计", String.valueOf(mAppEntity.getInvesttotal()));
		//		viewItem.setMap(map);
		//		list.add(viewItem);
		List<ViewItem> mArray = DbUtil.getMapList(mAppEntity);
		if (mArray != null && mArray.size() > 0) {
			mAdapter = new NoPoorProjectDetailAdapter(mContext, mArray);
			listView.setAdapter(mAdapter);
		}
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
			case R.id.tv_xiangmu_photo:
//			showToast("点击了项目照片");
				Intent intent=new Intent(this,XiangMuZhaoPianActivity.class);
				intent.putExtra("xiangmu_id", mAppEntity.getId());
				startActivity(intent);
				break;

			default:
				break;
		}
	}

}
