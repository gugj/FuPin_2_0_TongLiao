package com.roch.fupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.PoorHouseFamilyPeopleDetailAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.PoorFamilyPeople;
import com.roch.fupin.entity.Yhzgx;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.ResourceUtil;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 家庭成员详细情况信息的Activity
 * @author ZhaoDongShao
 * 2016年5月18日 
 */
@ContentView(R.layout.activity_poorfamilypepoledetail)
public class PoorFamilyPeopleDetailActivity extends MainBaseActivity{

	private static int HEALTH = 1; //健康状况

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;

	@ViewInject(R.id.lv_poor)
	ListView lv;

	PoorHouseFamilyPeopleDetailAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(mActivity);
		ViewUtils.inject(this);
		initToolbar();
	}

	@Override
	protected void onResume() {
		super.onResume();
		initDate();
	}

	/**
	 *初始化数据
	 * 2016年5月18日
	 * ZhaoDongShao
	 */
	private void initDate() {
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra(Common.INTENT_KEY);
		if (bundle != null) {
			PoorFamilyPeople people = (PoorFamilyPeople) bundle.getSerializable(Common.BUNDEL_KEY);
			String type = bundle.getString("type");

			if (people != null) {
				tv_title.setText(people.getPersonname());
				List<MapEntity> mapEntities = new ArrayList<MapEntity>();
				MapEntity mapEntity = null;
				mapEntity = new MapEntity(getTextName(R.string.people_name), people.getPersonname());
				mapEntities.add(mapEntity);
				String familyrelationid = people.getFamilyrelationid();//与户主关系id
				if (familyrelationid!=null&&!familyrelationid.equals("")) {

					List<Yhzgx> list = MyApplication.lYhzgx;
					if (list != null && list.size() > 0) {
						for (Yhzgx yhzgx : list) { //与户主关系
							if (yhzgx.getValue().equals(familyrelationid)) {
								mapEntity = new MapEntity(getTextName(R.string.people_yhzgn), yhzgx.getText());
								mapEntities.add(mapEntity);
							}
						}
					}
				}
				mapEntity = new MapEntity(getTextName(R.string.people_sex), people.getSextext());
				mapEntities.add(mapEntity);
				if (people.getBirthday() != null) {
					String[] date = people.getBirthday().split(" ");
					mapEntity = new MapEntity(getTextName(R.string.people_date), date[0]);
					mapEntities.add(mapEntity);
				}
				mapEntity = new MapEntity(getTextName(R.string.people_card_num), people.getIdno());
				mapEntities.add(mapEntity);

				if ("2".equals(type)){
					mapEntity = new MapEntity("联系电话", people.getPp_phone());
					mapEntities.add(mapEntity);
					mapEntity = new MapEntity("文化程度", people.getPp_politicstext());
					mapEntities.add(mapEntity);
				}

				mapEntity = new MapEntity(getTextName(R.string.people_ifxnh), people.getIfxnhname());
				mapEntities.add(mapEntity);
				mapEntity = new MapEntity(getTextName(R.string.people_ifxnbx), people.getIfxnbxname());
				mapEntities.add(mapEntity);

				mapEntity = new MapEntity("是否参加新型农村社会养老保险", people.getIfxncylbxname());
				mapEntities.add(mapEntity);
				mapEntity = new MapEntity(getTextName(R.string.people_ifjbbx), people.getIfjbbxname());
				mapEntities.add(mapEntity);
				mapEntity = new MapEntity("是否参加新农合", people.getIfxinnhname());
				mapEntities.add(mapEntity);
//				mapEntities.add(mapEntity);
				mapEntity = new MapEntity("是否现役军人", people.getPp_onsoldiertext());
				mapEntities.add(mapEntity);

				mapEntity = new MapEntity(getTextName(R.string.zaixiaodiqu), people.getInschoolplacetext());
				mapEntities.add(mapEntity);
				mapEntity = new MapEntity("在校生状况", people.getInschoolstatustext());
				mapEntities.add(mapEntity);

				mapEntity = new MapEntity(getTextName(R.string.people_health), people.getPp_healthtext());
				mapEntities.add(mapEntity);

				if ("2".equals(type)){
					if (people.getPp_healthtext().contains("病")){
						mapEntity = new MapEntity("疾病类型", people.getDiseasetypetext());
						mapEntities.add(mapEntity);
					}
				}

				mapEntity = new MapEntity("劳动能力", people.getPp_laborskilltext());
				mapEntities.add(mapEntity);

				if ("2".equals(type)){
					mapEntity = new MapEntity("是否享受低保或五保", people.getIfdborwb());
					mapEntities.add(mapEntity);
				}

				mapEntity = new MapEntity("政治面貌", people.getPp_politicstext());
				mapEntities.add(mapEntity);

				if ("2".equals(type)){
					mapEntity = new MapEntity("个人情况", people.getPp_politicstext());
					mapEntities.add(mapEntity);
					mapEntity = new MapEntity("备注", people.getRemark());
					mapEntities.add(mapEntity);
				}

				adapter = new PoorHouseFamilyPeopleDetailAdapter(mContext, mapEntities);
				lv.setAdapter(adapter);
			}
		}
	}

	/**
	 * 返回资源文件文本
	 * @param resid
	 * @return
	 * 2016年8月6日
	 * ZhaoDongShao
	 */
	private String getTextName(int resid){
		return ResourceUtil.getInstance().getStringById(resid);
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
}
