package com.roch.fupin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.KaoQinXinXiDetailAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.NoPoorProject_tpqk_FilterPopWindow;
import com.roch.fupin.entity.KaoQinXinXi;
import com.roch.fupin.entity.KaoQinXinXi_ResultList;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 考勤信息Activity
 * 作者：ZDS
 * 时间：2016/12/21/021 15:50
 */
public class KaoQinXinXiActivityDetail_New extends MainBaseActivity implements AdapterView.OnItemClickListener,NoPoorProject_tpqk_FilterPopWindow.ShowMessageListener  {

    @ViewInject(R.id.toolbar)
    Toolbar toolbar;

    @ViewInject(R.id.tv_title)
    TextView tv_title;

    @ViewInject(R.id.lv_poor_qixian)
    PullToRefreshListView lv_poor_qixian;
    private List<KaoQinXinXi> kaoQinXinXis;

    NoPoorProject_tpqk_FilterPopWindow filterPopWindow;

    private static int FRIST = 0;
    int current_page = 1; //当前页码
    private KaoQinXinXiDetailAdapter kaoQinXinXiAdapter;
    private KaoQinXinXi kaoQinXinXi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poorqixian);
        ViewUtils.inject(this);

        Intent intent = getIntent();
        kaoQinXinXi = (KaoQinXinXi) intent.getSerializableExtra("kaoqin");

        //初始化toolbar
        initToolbar();
        initListener();
        MyApplication.getInstance().addActivity(mActivity);

        //请求获取贫困旗县数据
        requestNetData();
    }

    private void initListener() {
        lv_poor_qixian.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                FRIST = 0;
                current_page = 1;
                RequestParams rp = getRequestParams();
                rp.addBodyParameter("page", String.valueOf(current_page));
                rp.addBodyParameter("id", kaoQinXinXi.getId());
                MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                        URLs.KaoQinXinXi_Detail, rp,
                        new MyRequestCallBack(KaoQinXinXiActivityDetail_New.this, MyConstans.FIRST));
                System.out.println("贫困户页面进行高级查询时请求服务器中的贫困户List(flag=1)的网址为：==" + URLs.KaoQinXinXi_Detail + "?id=" + kaoQinXinXi.getId()+"&page="+current_page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                FRIST = 1;
                current_page++;
                RequestParams rp = getRequestParams();
                rp.addBodyParameter("page", String.valueOf(current_page));
                rp.addBodyParameter("id", kaoQinXinXi.getId());
                MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                        URLs.KaoQinXinXi_Detail, rp,
                        new MyRequestCallBack(KaoQinXinXiActivityDetail_New.this, MyConstans.FIRST));
                System.out.println("贫困户页面进行高级查询时请求服务器中的贫困户List(flag=1)的网址为：==" + URLs.KaoQinXinXi_Detail + "?id=" + kaoQinXinXi.getId()+"&page="+current_page);
            }
        });
        lv_poor_qixian.setOnItemClickListener(this);
    }

    /**
     * 获取请求网络的参数
     * @return
     */
    private RequestParams getRequestParams() {
        RequestParams rp = new RequestParams();
        return rp;
    }

    /**
     * 请求获取贫困旗县数据
     */
    private void requestNetData() {
        RequestParams rp = getRequestParams();
        rp.addBodyParameter("page", current_page+"");
        rp.addBodyParameter("id", kaoQinXinXi.getId());
        System.out.println("请求获取考勤信息数据网址：===" + URLs.KaoQinXinXi_Detail + "?id=" + kaoQinXinXi.getId()+"&page="+current_page);
        MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST, URLs.KaoQinXinXi_Detail, rp,
                new MyRequestCallBack(this, MyConstans.FIRST));

    }

    @Override
    public void onSuccessResult(String str, int flag) {
        super.onSuccessResult(str, flag);
        switch (flag){
            case MyConstans.FIRST:
                System.out.println("获取考勤信息数据成功：===" + str);
                KaoQinXinXi_ResultList kaoQinXinXi_resultList=KaoQinXinXi_ResultList.parseToT(str,KaoQinXinXi_ResultList.class);
                if(StringUtil.isNotEmpty(kaoQinXinXi_resultList)){
                    if(kaoQinXinXi_resultList.getSuccess()){
                        kaoQinXinXis = kaoQinXinXi_resultList.getJsondata();
                        if(!StringUtil.isEmpty(kaoQinXinXis) && kaoQinXinXis.size()>0){
                            switch (FRIST){
                                case 0: //下拉刷新
                                    if(kaoQinXinXiAdapter!=null){
                                        kaoQinXinXiAdapter.onRefsh(kaoQinXinXis);
                                    }else {
                                        kaoQinXinXiAdapter = new KaoQinXinXiDetailAdapter(mContext, kaoQinXinXis);
                                        lv_poor_qixian.setAdapter(kaoQinXinXiAdapter);
                                    }
                                    break;

                                case 1: //上拉加载
                                    if (kaoQinXinXiAdapter == null) {
                                        kaoQinXinXiAdapter = new KaoQinXinXiDetailAdapter(mContext, kaoQinXinXis);
                                        lv_poor_qixian.setAdapter(kaoQinXinXiAdapter);
                                    }else {
                                        kaoQinXinXiAdapter.addList(kaoQinXinXis);
                                    }
                                    break;
                            }
                        }else {
                            showToast("没有更多数据");
                            lv_poor_qixian.onRefreshComplete();
                        }
                    }
                }
                lv_poor_qixian.onRefreshComplete();
                break;

            default:
                break;
        }
    }

    @Override
    public void onFaileResult(String str, int flag) {
        super.onFaileResult(str, flag);
        showToast(str);
        System.out.println("获取考勤信息数据失败：===" + str);
        lv_poor_qixian.onRefreshComplete();
    }

    /**
     * 初始化toolbar
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
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.select:
                        int xPox = (int) (Common.Width * 0.9);
                        filterPopWindow = new NoPoorProject_tpqk_FilterPopWindow(mContext);
                        //监听者模式，将监听者传过来
                        filterPopWindow.setShowMessageListener(KaoQinXinXiActivityDetail_New.this);
                        //设置筛选过之后的时间
                        filterPopWindow.setSelectionAdapter(maps, "");
                        filterPopWindow.setShowShouRu(false);
                        //显示popupWindow
                        filterPopWindow.showPopupWindow(toolbar, xPox);
                        break;

                    default:
                        break;
                }
                return false;
            }
        });
        tv_title.setText("帮扶详细信息");
    }

    /**
     * 当点击菜单选项时调用此方法
     * @param menu
     * @return
     */
    @SuppressLint("NewApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.select_menu, menu);
        return true;
    }

    /**
     * 当点击了toolbar的返回箭头时，关闭该activity回到上一级的activity
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                MyApplication.getInstance().finishActivity(this);
                break;
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        KaoQinXinXi kaoQinXinXi= (KaoQinXinXi) parent.getItemAtPosition(position);
        if(null!=kaoQinXinXi){
            Intent intent=new Intent(this,GongZuoRiZhiDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("type","kaoqin");
            bundle.putSerializable(Common.BUNDEL_KEY, kaoQinXinXi);
            intent.putExtra(Common.INTENT_KEY, bundle);
            startActivity(intent);
        }
    }

    List<MapEntity> maps = new ArrayList<MapEntity>();
    @Override
    public void Message(String time_end,String shouru) {
        maps.clear();
        String times = "";
        RequestParams rp = new RequestParams();
        if (!time_end.equals("")) {
            int year = Integer.parseInt(time_end.split("-")[0]);
//			int months = Integer.parseInt(time_end.split("-")[1]);
            Calendar calendar = Calendar.getInstance();
            if (year > calendar.get(Calendar.YEAR)) {
                showToast("请选择正确的查询日期");
                return;
            }else if (year == calendar.get(Calendar.YEAR)) {
//				if (months > calendar.get(Calendar.MONTH) + 1) {
//					showToast("请选择正确的查询日期");
//					return;
//				}else {
//					times += year + "年" + months + "月份";
                times += year + "年";
                maps.add(new MapEntity("wejend", time_end));
//					if (months > 0 && months < 10) {
//						time_end = year + "-0" + months;
//						time_end = year+"";
//					}
//					rp.addBodyParameter("lastMonth", time_end);
//					rp.addBodyParameter("endDate", time_end);
//				}
                time_end = year+"";
                rp.addBodyParameter("helpyear", time_end);
            }else {
//				times += year + "年" + months + "月份";
                times += year + "年";
                maps.add(new MapEntity("wejend", time_end));
//				if (months > 0 && months < 10) {
//					time_end = year + "-0" + months;
//					time_end = year + "";
//				}
//				rp.addBodyParameter("lastMonth", time_end);
                time_end = year + "";
                rp.addBodyParameter("helpyear", time_end);
            }
        }else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
//			String a = year + "年" + month + "月份";
            String a = year + "年";
            times += a;
        }
        System.out.println("筛选时请求的网址为："+ URLs.POOR_PROPLE_TPQK+"?helpyear="+time_end);
        MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                URLs.KaoQinXinXi_Detail, rp,
                new MyRequestCallBack(this, MyConstans.FIRST));
        filterPopWindow.dismiss();
    }
}
