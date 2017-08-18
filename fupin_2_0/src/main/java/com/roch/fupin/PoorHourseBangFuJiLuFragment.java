package com.roch.fupin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.BangFuJiLuAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.NormalDailog;
import com.roch.fupin.entity.PoorHouseBangFuJiLu;
import com.roch.fupin.entity.PoorHouseBangFuJiLu.BangFuJiLu;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.LogUtil;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 贫困户或贫困村帮扶记录的fragment，是五个fragment的其中之一，继承自BaseFragment，实现了OnItemClickListener
 * @author 
 * 2016年10月31日 
 */
public class PoorHourseBangFuJiLuFragment extends BaseFragment implements OnItemClickListener, AdapterView.OnItemLongClickListener {

	/**
	 * 展示帮扶记录的listview，如帮扶记录1，帮扶记录2......
	 */
	@ViewInject(R.id.listView_bfjl)
	ListView listView_bfjl; 
	@ViewInject(R.id.rl_no_bangfujilu)
	RelativeLayout rl_no_bangfujilu;

	/**
	 * 标志位，标志初始化已经完成
	 */
	private boolean isPrepared;
	
	/**
	 * 标识当前fragment是否可见
	 */
	private boolean isVisible;

	private List<BangFuJiLu> jsondata;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_poorhourse_bangfujilu, container, false);
		ViewUtils.inject(this,view);
		this.mContext = getActivity();
		isPrepared = true;
		
		// 如果没有初始化或当前fragment不可见就return
//		lazyLoad();

		return view;
	}
	
	/**
	 * 这个是fragment的方法，该方法用于告诉系统，这个Fragment的UI是否是可见的。所以我们只需要继承Fragment并重写该方法，
	 * 即可实现在fragment可见时才进行数据加载操作，即Fragment的懒加载
	 */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			isVisible = true;
			lazyLoad();
		}else {
			isVisible = false;
		}
	}
	
	/**
	 * 如果没有初始化或当前fragment不可见就return;否则就开始初始化数据initData <br/>
	 * 2016年10月31日
	 */
	private void lazyLoad() {
		if (!isPrepared || !isVisible) {
			return;
		}
		initData();
	}

	@Override
	public void onResume() {
		super.onResume();
		initData();
	}

	/**
	 * 标志位---贫困户householderid或贫困村id
	 */
	String houseHolderId;
	/**
	 * 标志位---如果为"hu"即为贫困户类型，如果为"cun"即为贫困村类型
	 */
	String type_hu_cun;
	/**
	 * 初始化数据  <br/>
	 * 2016年10月31日
	 */
	private void initData() {
		Bundle bundle = getArguments();
		houseHolderId = bundle.getString(Common.BUNDEL_KEY);
		type_hu_cun = bundle.getString("type_hu_cun");
		LogUtil.println("获取的householderid==" + houseHolderId);

		if("hu".equals(type_hu_cun)){ //如果是贫困户，就请求贫困户帮扶记录的接口
			RequestParams rp = new RequestParams();
			rp.addBodyParameter("householderid", houseHolderId);
			// 通过post请求网络数据，请求参数为户IDhouseholderid
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
					URLs.POOR_HOUSE_BangFuJiLu_HourseId, rp,
					new MyRequestCallBack(this, MyConstans.BangFuJiLu));
			LogUtil.println("贫困户请求帮扶记录的列表网址：==="+URLs.POOR_HOUSE_BangFuJiLu_HourseId+"&?householderid="+houseHolderId);
		}else if("cun".equals(type_hu_cun)){ //如果是贫困村，就请求贫困村帮扶记录的接口
			RequestParams rp = new RequestParams();
			rp.addBodyParameter("villageid", houseHolderId);
			// 通过post请求网络数据，请求参数为户IDhouseholderid
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
					URLs.POOR_Village_BangFuJiLu_VillageId, rp,
					new MyRequestCallBack(this, MyConstans.BangFuJiLu));
			LogUtil.println("贫困村请求帮扶记录的列表网址：===" + URLs.POOR_Village_BangFuJiLu_VillageId + "&?villageid=" + houseHolderId);
		}
	}

	BangFuJiLuAdapter bangFuJiLuAdapter;
	/**
	 * 当通过户ID请求网络数据成功时调用该方法，这个是Success接口类里面的方法，而当前的父类实现了该接口，
	 * 当前类请求了网络数据，所以就一定会调用该方法
	 * @param str
	 * @param flag
	 * 2016年10月31日
	 */
	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		switch (flag) {
		case MyConstans.BangFuJiLu:
			LogUtil.println("帮扶记录请求网络时服务器返回的json数据为:==="+str);
			// 使用Gson解析帮扶记录到其对象中
			PoorHouseBangFuJiLu poorHouseBangFuJiLu=PoorHouseBangFuJiLu.parseToT(str, PoorHouseBangFuJiLu.class);
			// 贫困户帮扶记录的对象不为空
			if(poorHouseBangFuJiLu!=null){
				// 贫困户帮扶记录的对象中的success字段为true
				if(poorHouseBangFuJiLu.getSuccess()){
					jsondata = poorHouseBangFuJiLu.getJsondata();
					// 解析服务器返回的List类型的jsondata集合不为空并且size大于0
				    if(jsondata!=null && jsondata.size()>0){
						listView_bfjl.setVisibility(View.VISIBLE);
						rl_no_bangfujilu.setVisibility(View.GONE);
				    	// 展示帮扶记录的数据--使用adapter
				    	bangFuJiLuAdapter=new BangFuJiLuAdapter(jsondata, getContext());
				    	listView_bfjl.setAdapter(bangFuJiLuAdapter);
				    	// 设置条目点击事件
				    	listView_bfjl.setOnItemClickListener(this);
						listView_bfjl.setOnItemLongClickListener(this);
				    }else {
						listView_bfjl.setVisibility(View.GONE);
						rl_no_bangfujilu.setVisibility(View.VISIBLE);
					}
				}
			}
			break;

			case MyConstans.SECOND:
				LogUtil.println("删除帮扶记录时请求服务连接成功：=="+str);
				//删除这条帮扶记录
				deleteBfjl(deletePosition);
				ShowToast("删除成功");
				break;
		}
	}

	@Override
	public void onFaileResult(String str, int flag) {
		super.onFaileResult(str, flag);
		switch (flag){
			case MyConstans.FIRST:
				ShowToast(str);
				LogUtil.println("帮扶记录fragment获取帮扶记录List数据失败：===" + str);
				break;

			case MyConstans.SECOND:
				ShowToast("删除失败");
				LogUtil.println("删除帮扶记录失败：==" + str);
				break;
		}
	}

	/**
	 * 删除这条帮扶记录
	 * @param position
	 */
	private void deleteBfjl(int position) {
		jsondata.remove(position);
		bangFuJiLuAdapter.notifyDataSetChanged();
		if(jsondata.size()<=0){
			listView_bfjl.setVisibility(View.GONE);
			rl_no_bangfujilu.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 帮扶记录的ListView条目被点击后调用此方法
	 * @param parent
	 * @param view
	 * @param position
	 * @param id
	 * 2016年11月1日
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		 // 点击后跳转到帮扶记录详情页面
		Intent intent=new Intent(mContext, ActivityBangFuJiLuHelpDetail.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		// 把帮扶记录的title和时间date传到详情的activity中
		String helptitle = jsondata.get(position).helptitle;
		String helpdate = jsondata.get(position).helpdate;
		String helpdname = jsondata.get(position).getName();
		String location = jsondata.get(position).getLocation();
		intent.putExtra("helptitle", helptitle);
		intent.putExtra("helpdate", helpdate);
		intent.putExtra("helpdname", helpdname);
		intent.putExtra("location", location);
		intent.putExtra("type_hu_cun", type_hu_cun);

		// 把帮扶记录的json中的id传过去，作为请求参数请求网络数据
		String id2 = jsondata.get(position).id;
		intent.putExtra("id", id2);
		
		startActivity(intent);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		SharedPreferences sp = getActivity().getSharedPreferences("loginactivty", Context.MODE_APPEND);
		String user_id=sp.getString("user_id","");
		LogUtil.println("修改或删除时userid="+user_id+",,帮扶记录主体ID="+jsondata.get(position).getZhutiid());
		if(user_id.equals(jsondata.get(position).getZhutiid())){
			//显示是否修改或删除该条帮扶记录的对话框
			showDeleteDialog(position);
		}else {
			ShowToast("无权限修改或删除");
		}
		return true;
	}

	int deletePosition;
	/**
	 * 显示是否删除照片的对话框
	 */
	private void showDeleteDialog(final int position) {
		final NormalDailog normalDailog=new NormalDailog(getActivity(),R.style.popup_dialog_style,3);
		normalDailog.show();
		normalDailog.setTitleText("提示");
		normalDailog.setContentText("是否要修改、删除这条帮扶记录吗？");
		normalDailog.setOnClickLinener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
					case R.id.normal_dialog_xiugai: //修改帮扶记录---跳转到修改页面
//						deletePosition = position;
						normalDailog.dismiss();

						Intent intent=new Intent(getActivity(),AddBangFuJiLuActivity.class);
						intent.putExtra("type","xiugai");
						intent.putExtra("house_village_id",houseHolderId);
						intent.putExtra("shangchuan_type",type_hu_cun);

						// 把帮扶记录的title和时间date传到详情的activity中
						String helptitle = jsondata.get(position).helptitle;
						String helpdate = jsondata.get(position).helpdate;
						intent.putExtra("helptitle", helptitle);
						intent.putExtra("helpdate", helpdate);

						// 把帮扶记录的json中的id传过去，作为请求参数请求网络数据
						String id2 = jsondata.get(position).id;
						intent.putExtra("id", id2);
						startActivity(intent);
						break;

					case R.id.normal_dialog_done: //确定---删除
						//请求服务器删除这条帮扶记录
						requestNetDeleteBfjl(position);
						deletePosition = position;
						normalDailog.dismiss();
						break;

					case R.id.normal_dialog_cancel: //取消
						normalDailog.dismiss();
						break;
				}
			}
		});
	}

	/**
	 * 请求服务器删除这条帮扶记录
	 */
	private void requestNetDeleteBfjl(int position) {
		if("hu".equals(type_hu_cun)){
			RequestParams rp = new RequestParams();
			rp.addBodyParameter("id", jsondata.get(position).getId());
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
					URLs.POOR_House_Delete_Bfjl, rp,
					new MyRequestCallBack(this, MyConstans.SECOND));
			System.out.println("帮扶记录fragment请求删除贫困户帮扶记录的网址为：===" + URLs.POOR_House_Delete_Bfjl + "&?id=" + jsondata.get(position).getId());
		}else if("cun".equals(type_hu_cun)){
			RequestParams rp = new RequestParams();
			rp.addBodyParameter("id", jsondata.get(position).getId());
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
					URLs.POOR_Village_Delete_Bfjl, rp,
					new MyRequestCallBack(this, MyConstans.SECOND));
			System.out.println("帮扶记录fragment请求删除贫困村帮扶记录的网址为：===" + URLs.POOR_Village_Delete_Bfjl + "&?id=" + jsondata.get(position).getId());
		}
	}

}
