package com.roch.fupin;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.roch.fupin.adapter.PoorHousePhotoAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.AddPopWindow;
import com.roch.fupin.dialog.NormalDailog;
import com.roch.fupin.entity.Bfjl_Detail;
import com.roch.fupin.entity.Bfjl_Detail_Img;
import com.roch.fupin.entity.ListMenu;
import com.roch.fupin.entity.Photo;
import com.roch.fupin.entity.ShowBfjl;
import com.roch.fupin.entity.ShowBfjl_ResultList;
import com.roch.fupin.utils.BitmapUtil;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.LogUtil;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;
import com.roch.time.JudgeDate;
import com.roch.time.ScreenInfo;
import com.roch.time.WheelMain;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者：GuGaoJie
 * 时间：2017/5/17/017 16:53
 */
public class AddBangFuJiLuActivity extends MainBaseActivity implements AddPopWindow.ShowMessageListener {

    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    /**
     * toolbar的标题title
     */
    @ViewInject(R.id.tv_title)
    TextView tv_title;

    /**
     * 帮扶记录的标题
     */
    @ViewInject(R.id.et_bfjl_title)
    EditText et_bfjl_title;
    /**
     * 添加帮扶记录的时间
     */
    @ViewInject(R.id.tv_bfjl_time)
    TextView tv_bfjl_time;
    /**
     * 帮扶记录的位置信息
     */
    @ViewInject(R.id.tv_bfjl_location)
    TextView tv_bfjl_location;
    /**
     * 帮扶记录的内容
     */
    @ViewInject(R.id.et_bfjl_content)
    EditText et_bfjl_content;
    /**
     * 帮扶记录的照片GridView
     */
    @ViewInject(R.id.gv_photo)
    GridView gv_photo;
    /**
     * 提交按钮
     */
    @ViewInject(R.id.btn_commit)
    Button btn_commit;

    /**
     * 时间选择滚轮的布局View
     */
    private View timeView;
    /**
     * 第三方的时间滚轮选择器控件---WheelMain
     */
    private WheelMain wheelMain;
    /**
     * 手机屏幕信息
     */
    private ScreenInfo screenInfo;
    /**
     * 时间格式化器
     */
    private SimpleDateFormat dateFormat;
    /**
     * 时间滚轮选择器---Popup弹窗
     */
    private PopupWindow timePop;
    /**
     * 时间滚轮选择器---取消按钮
     */
    private TextView timeCancle;
    /**
     * 时间滚轮选择器---确定按钮
     */
    private TextView timeSure;
    private String srBirthDay = "";
    /**
     * 贫困户householderid或贫困村id
     */
    String house_village_id;
    /**
     * 标志位---如果为“hu”则是贫困户，如果为“cun”则是贫困村
     */
    String shangchuan_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bfjl_detail);
        ViewUtils.inject(this);
        MyApplication.getInstance().addActivity(this);

        // 初始化toolbar
        initToolbar();
        //初始化View并获取intent和上传类型
        initView();
        if("xiugai".equals(type)){
            requestNetBfjlAndPhoto();
        }else {
            //初始化照片GridView适配器和点击、长按监听
            initGridViewDataAndListener();
        }
        //注册监听帮扶记录完成照片预览的广播
        registerYuLanPhotoReceiver();
    }

    /**
     * 如果是修改帮扶记录---就先请求服务器中的照片
     */
    private void requestNetBfjlAndPhoto() {
        if("hu".equals(shangchuan_type)){
            RequestParams rp = new RequestParams();
            rp.addBodyParameter("id", id);
            // 通过post请求网络数据，请求参数为户IDhouseholderid
            MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                    URLs.POOR_HOUSE_BangFuJiLu_bfjlID, rp,
                    new MyRequestCallBack(this, MyConstans.SECOND));
            LogUtil.println("贫困户请求服务器修改帮扶记录的网址：===" + URLs.POOR_HOUSE_BangFuJiLu_bfjlID + "&?id=" + id);
        }else if("cun".equals(shangchuan_type)){
            RequestParams rp = new RequestParams();
            rp.addBodyParameter("id", id);
            // 通过post请求网络数据，请求参数为户IDhouseholderid
            MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                    URLs.POOR_Village_BangFuJiLu_bfjlID, rp,
                    new MyRequestCallBack(this, MyConstans.SECOND));
            LogUtil.println("贫困户请求服务器修改帮扶记录的网址：===" + URLs.POOR_Village_BangFuJiLu_bfjlID + "&?id=" + id);
        }
    }

    MyBroadCastReceiver myBroadCastReceiver=new MyBroadCastReceiver();
    /**
     * 注册监听帮扶记录完成照片预览的广播
     */
    private void registerYuLanPhotoReceiver() {
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("bfjl_yulan_photo_complete");
        intentFilter.addAction("xzzp_yulan_photo_complete");
        registerReceiver(myBroadCastReceiver,intentFilter);
    }

    class MyBroadCastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if("bfjl_yulan_photo_complete".equals(intent.getAction())){
                List<Photo> lists_bfjl= (List<Photo>) intent.getSerializableExtra("lists_bfjl");
                lists.clear();
                lists.addAll(lists_bfjl);
                mAdapter.notifyDataSetChanged();
            }else if("xzzp_yulan_photo_complete".equals(intent.getAction())){
                List<String> select_photo_paths= (List<String>) intent.getSerializableExtra("select_photo_paths");
                if(null!=select_photo_paths && select_photo_paths.size()>0){
                    for (int i = 0; i < select_photo_paths.size(); i++) {
                        Photo photoBea = new Photo();
                        photoBea.setUrl(select_photo_paths.get(i));
//                                    photoBea.setId(poorFamilyPhoto.getId());
                        lists.add(photoBea);
                    }
                    //有数据后刷新适配器
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadCastReceiver);
    }

    /**
     * 初始化照片GridView数据，并设置条目的点击监听和长按监听
     */
    private void initGridViewDataAndListener() {
        //创建适配器---此时数据为 0 ---如果是修改帮扶记录--此时数据不为0
        mAdapter = new PoorHousePhotoAdapter(mContext, lists);
        gv_photo.setAdapter(mAdapter);

        gv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击照片---加载照片详情
//                imageBrower(position, lists);
                Intent intent = new Intent();
                intent.putExtra("position", position + "");
                intent.putExtra("type_bfjl", true);
                intent.putExtra("photos_bfjl", (Serializable) lists);
                intent.putExtra(Common.INTENT_KEY, id);
                intent.setClass(AddBangFuJiLuActivity.this, GalleryActivity.class);
                startActivity(intent);
            }
        });
        gv_photo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //显示是否删除照片的对话框
                showDeleteDialog(position);
                return true;
            }
        });
    }

    /**
     * 标志位---如果为"xiugai",即为修改帮扶记录页面
     */
    String type;
    /**
     * 修改帮扶记录时传过来的帮扶记录的ID
     */
    String id;
    /**
     * 定位时的位置信息
     */
    String location;
    /**
     * 登陆用户的id
     */
    String user_id;
    String rolename_app;
    int roleType = 0;// 一般角色
    private void initView() {
        //时间格式化器
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        //手机屏幕信息
        screenInfo = new ScreenInfo(this);
        //时间选择滚轮的布局View
        timeView = LayoutInflater.from(mContext).inflate(R.layout.item_odertime, null);
        //取消按钮
        timeCancle = (TextView) timeView.findViewById(R.id.time_cancle);
        //确定按钮
        timeSure = (TextView) timeView.findViewById(R.id.time_sure);
        //时间滚轮选择器---Popup弹窗
        timePop = new PopupWindow(timeView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Intent intent=getIntent();
        house_village_id=intent.getStringExtra("house_village_id");
        shangchuan_type=intent.getStringExtra("shangchuan_type");
        type=intent.getStringExtra("type");
        id=intent.getStringExtra("id");
        String helptitle=intent.getStringExtra("helptitle");
        String helpdate=intent.getStringExtra("helpdate");

        Calendar calendar = Calendar.getInstance();
        String time=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        if("xiugai".equals(type)){
            tv_title.setText("修改帮扶记录");
            et_bfjl_title.setText(helptitle);
            if(StringUtil.isNotEmpty(helpdate)){
                tv_bfjl_time.setText(helpdate.split(" ")[0]);
            }
        }else {
            tv_title.setText("添加帮扶记录");
            tv_bfjl_time.setText(time);
        }

        SharedPreferences sp = getSharedPreferences("loginactivty", Context.MODE_APPEND);
        String location_str=sp.getString("location_str", "");
        user_id=sp.getString("user_id","");
        location=sp.getString("location_str", "");
        tv_bfjl_location.setText(location_str);

        rolename_app=sp.getString("rolename_app", "");
        if (rolename_app.equals("帮扶责任人")) {// 特殊角色
            roleType = 1;
        } else if (rolename_app.equals("贫困户")) {
            roleType = 2;
        } else if (rolename_app.equals("第一书记")) {
            roleType = 4;
        } else if (rolename_app.equals("驻村工作队队长")) {
            roleType = 5;
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.photo).setVisible(true);
        invalidateOptionsMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
     * (只会在第一次初始化菜单时调用)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.poor_house_menu, menu);
        return true;
    }

    /**
     * 初始化toolbar信息
     * 2016年11月1日
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
                    case R.id.photo:
                        int xPox = (int) (Common.Width * 0.7 - 10);
                        AddPopWindow window = new AddPopWindow(mActivity, 2);
                        window.setShowMessageListener(AddBangFuJiLuActivity.this);
                        window.showPopupWindow(toolbar, xPox);
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 点击toolbar上的返回箭头时，关闭activity
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

    @OnClick({R.id.btn_commit, R.id.tv_bfjl_time})
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.tv_bfjl_time: //点击帮扶记录时间按钮
//                initTimePicker(timeView);
//                initTimePop(1);
//                break;

            case R.id.btn_commit: //点击提交按钮---添加帮扶记录
                String bfjl_title=et_bfjl_title.getText().toString().trim();
                String bfjl_time=tv_bfjl_time.getText().toString().trim();
                String bfjl_content=et_bfjl_content.getText().toString().trim();
                if(StringUtil.isEmpty(bfjl_title)){
                    showToast("标题不能为空");
                    return;
                }
                if(StringUtil.isEmpty(bfjl_time)){
                    showToast("时间不能为空");
                    return;
                }
                if(StringUtil.isEmpty(bfjl_content) && lists.size()<=0){
                    showToast("帮扶内容和照片不能同时为空");
                    return;
                }
                //添加或修改帮扶记录的数据到服务器---有可能带照片，也有可能不带照片
                commitBfjlData(bfjl_title, bfjl_time, bfjl_content);
                break;
        }
    }

    /**
     * 添加或修改帮扶记录的数据到服务器---有可能带照片，也有可能不带照片
     * @param bfjl_title
     * @param bfjl_time
     * @param bfjl_content
     */
    private void commitBfjlData(String bfjl_title, String bfjl_time, String bfjl_content) {
        if(lists.size()>0){ //带照片
            if(lists.size()>net_old_lists.size()){ //总照片比老照片多---添加了新照片
                //添加或修改帮扶记录的数据到服务器---带照片,有新照片
                upFile(bfjl_title,bfjl_time,bfjl_content);
            }else { //总照片跟老照片一样----没有添加新照片
                //添加或修改帮扶记录的数据到服务器---带照片,但都是老照片修改
                upLoadBfjlContent_only_oldPhoto(bfjl_title, bfjl_time, bfjl_content);
            }
        }else { //不带照片
            //添加或修改帮扶记录的数据到服务器---不带照片
            upLoadBfjlContent(bfjl_title,bfjl_time,bfjl_content);
        }
    }

    /**
     * 提交帮扶记录的数据到服务器---不带照片
     * @param bfjl_title
     * @param bfjl_time
     * @param bfjl_content
     */
    private void upLoadBfjlContent(String bfjl_title, String bfjl_time, String bfjl_content) {
        // 第一种XUtils
        RequestParams rp = new RequestParams();
        if(StringUtil.isNotEmpty(bfjl_title)){ //帮扶记录---标题--1
            rp.addBodyParameter("helptitle", bfjl_title);
        }
        if(StringUtil.isNotEmpty(bfjl_time)){ //帮扶记录---时间--2
            rp.addBodyParameter("helpdate", bfjl_time);
        }
        if(StringUtil.isNotEmpty(bfjl_content)){ //帮扶记录---内容--3
            rp.addBodyParameter("helpdetail", bfjl_content);
        }
        rp.addBodyParameter("location", location);//--4
        if("hu".equals(shangchuan_type)){
            rp.addBodyParameter("householderid", house_village_id); //帮扶记录---贫困户householderid或贫困村id--5
        }else if("cun".equals(shangchuan_type)){
            rp.addBodyParameter("villageid", house_village_id); //帮扶记录---贫困户householderid或贫困村id--5
        }
        if("xiugai".equals(type)){ //修改帮扶记录
            rp.addBodyParameter("id", id); //--6
            LogUtil.println("修改帮扶记录（不带照片）时添加参数帮扶记录id=="+id);
        }else { //添加帮扶记录
            rp.addBodyParameter("zhutiid", user_id);//---6
            LogUtil.println("添加帮扶记录（不带照片）时添加参数主体id==" + user_id);
        }

        LogUtil.println("标题=="+bfjl_title+",时间=="+bfjl_time+",内容=="+bfjl_content+",householderid=="+house_village_id);
        if("xiugai".equals(type)){
            if("hu".equals(shangchuan_type)){ //贫困户修改帮扶记录
                MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                        URLs.POOR_HOUSE_Bfjl_Xiugai_NoPhoto, rp,
                        new MyRequestCallBack(AddBangFuJiLuActivity.this, MyConstans.FIRST));
                LogUtil.println("贫困户修改帮扶记录(不带照片)的网址为：" + URLs.POOR_HOUSE_Bfjl_Xiugai_NoPhoto + "?&householderid=" + house_village_id);
            }else if("cun".equals(shangchuan_type)){ //贫困村修改帮扶记录
                MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                        URLs.POOR_Village_Bfjl_Xiugai_NoPhoto, rp,
                        new MyRequestCallBack(AddBangFuJiLuActivity.this, MyConstans.FIRST));
                LogUtil.println("贫困村修改帮扶记录(不带照片)的网址为："+URLs.POOR_Village_Bfjl_Xiugai_NoPhoto+"?&villageid="+house_village_id);
            }
        }else {
            if("hu".equals(shangchuan_type)){ //贫困户添加帮扶记录
                MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                        URLs.POOR_HOUSE_Bfjl_Save_NoPhoto, rp,
                        new MyRequestCallBack(AddBangFuJiLuActivity.this, MyConstans.FIRST));
                LogUtil.println("贫困户添加帮扶记录(不带照片)的网址为："+URLs.POOR_HOUSE_Bfjl_Save_NoPhoto+"?&householderid="+house_village_id);
            }else if("cun".equals(shangchuan_type)){ //贫困村添加帮扶记录
                MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                        URLs.POOR_Village_Bfjl_Save_NoPhoto, rp,
                        new MyRequestCallBack(AddBangFuJiLuActivity.this, MyConstans.FIRST));
                LogUtil.println("贫困村添加帮扶记录(不带照片)的网址为："+URLs.POOR_Village_Bfjl_Save_NoPhoto+"?&villageid="+house_village_id);
            }
        }
    }

    /**
     * 提交帮扶记录的数据到服务器---带照片,但都是老照片修改
     * @param bfjl_title
     * @param bfjl_time
     * @param bfjl_content
     */
    private void upLoadBfjlContent_only_oldPhoto(String bfjl_title, String bfjl_time, String bfjl_content) {
        String imgPa="";
        for (int i = 0; i < net_old_lists.size(); i++) {
            imgPa=imgPa+net_old_lists.get(i).getUrl()+",";
        }
        if("xiugai".equals(type)){
            LogUtil.println("修改帮扶记录时拼接老照片的路径为：==="+imgPa+",标题="+bfjl_title+",时间="+bfjl_time+",内容="+bfjl_content+",帮扶记录id="+id);
        }
        // 第一种XUtils
        RequestParams rp = new RequestParams();
        if(StringUtil.isNotEmpty(bfjl_title)){ //帮扶记录---标题--1
            rp.addBodyParameter("helptitle", bfjl_title);
        }
        if(StringUtil.isNotEmpty(bfjl_time)){ //帮扶记录---时间--2
            rp.addBodyParameter("helpdate", bfjl_time);
        }
        if(StringUtil.isNotEmpty(bfjl_content)){ //帮扶记录---内容--3
            rp.addBodyParameter("helpdetail", bfjl_content);
        }
        rp.addBodyParameter("location", location);//--4
        if("hu".equals(shangchuan_type)){
            rp.addBodyParameter("householderid", house_village_id); //帮扶记录---贫困户householderid或贫困村id--5
        }else if("cun".equals(shangchuan_type)){
            rp.addBodyParameter("villageid", house_village_id); //帮扶记录---贫困户householderid或贫困村id--5
        }
//        if("xiugai".equals(type)){ //修改帮扶记录
//            rp.addBodyParameter("id", id); //--6
//            LogUtil.println("修改帮扶记录（带照片都是老照片）时添加参数帮扶记录id=="+id);
//        }else { //添加帮扶记录
//            rp.addBodyParameter("zhutiid", user_id);//---6
//            LogUtil.println("添加帮扶记录（带照片都是老照片）时添加参数主体id==" + user_id);
//        }
        rp.addBodyParameter("id", id); //--6
        rp.addBodyParameter("picpath", imgPa); //--7

        LogUtil.println("修改帮扶记录(带照片都是老照片)="+"helptitle=="+bfjl_title+",helpdate=="+bfjl_time+",helpdetail=="+bfjl_content+",householderid=="
                        +house_village_id+",picpath="+imgPa+",id="+id+",picpath="+imgPa);
        if("xiugai".equals(type)){
            if("hu".equals(shangchuan_type)){ //贫困户修改帮扶记录
                MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                        URLs.POOR_HOUSE_Bfjl_Xiugai_Only_Old_Photo, rp,
                        new MyRequestCallBack(AddBangFuJiLuActivity.this, MyConstans.FIRST));
                LogUtil.println("贫困户修改帮扶记录(带照片都是老照片)的网址为："+URLs.POOR_HOUSE_Bfjl_Xiugai_Only_Old_Photo+"?&householderid="+house_village_id);
            }else if("cun".equals(shangchuan_type)){ //贫困村修改帮扶记录
                MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                        URLs.POOR_Village_Bfjl_Xiugai_Only_old_Photo, rp,
                        new MyRequestCallBack(AddBangFuJiLuActivity.this, MyConstans.FIRST));
                LogUtil.println("贫困村修改帮扶记录(带照片都是老照片)的网址为："+URLs.POOR_Village_Bfjl_Xiugai_Only_old_Photo+"?&villageid="+house_village_id);
            }
        }else {
            if("hu".equals(shangchuan_type)){ //贫困户添加帮扶记录
                MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                        URLs.POOR_HOUSE_Bfjl_Save_NoPhoto, rp,
                        new MyRequestCallBack(AddBangFuJiLuActivity.this, MyConstans.FIRST));
                LogUtil.println("贫困户添加帮扶记录(带照片都是老照片)的网址为："+URLs.POOR_HOUSE_Bfjl_Save_NoPhoto+"?&householderid="+house_village_id);
            }else if("cun".equals(shangchuan_type)){ //贫困村添加帮扶记录
                MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                        URLs.POOR_Village_Bfjl_Save_NoPhoto, rp,
                        new MyRequestCallBack(AddBangFuJiLuActivity.this, MyConstans.FIRST));
                LogUtil.println("贫困村添加帮扶记录(带照片都是老照片)的网址为："+URLs.POOR_Village_Bfjl_Save_NoPhoto+"?&villageid="+house_village_id);
            }
        }
    }

    //参数类型
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/jpg");
    //创建OkHttpClient实例
    private final OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS).build();

    /**
     * 提交帮扶记录的数据到服务器---带照片
     * @param bfjl_title
     * @param bfjl_time
     * @param bfjl_content
     */
    private void upFile(String bfjl_title, String bfjl_time, String bfjl_content){
        AddBangFuJiLuActivity.this.showProgressDialog("正在加载...", false);

        String imgPa="";
        for (int i = 0; i < net_old_lists.size(); i++) {
            imgPa=imgPa+net_old_lists.get(i).getUrl()+",";
        }
        if("xiugai".equals(type)){
//            LogUtil.println("修改帮扶记录时拼接老照片的路径为：==="+imgPa+",标题="+bfjl_title+",时间="+bfjl_time+",内容="+bfjl_content+",帮扶记录id="+id);
        }

        MultipartBody.Builder builder = null;
        /* form的分割线,自己定义 */
        String boundary = "xx-----------------------xx";
        if("xiugai".equals(type)){
            if("hu".equals(shangchuan_type)){ //贫困户修改帮扶记录
                builder = new MultipartBody.Builder(boundary).setType(MultipartBody.FORM)
                        .addFormDataPart("helptitle", bfjl_title) //--1
                        .addFormDataPart("helpdate", bfjl_time) //--2
                        .addFormDataPart("helpdetail", bfjl_content) //--3
                        .addFormDataPart("location", location) //--4
                        .addFormDataPart("householderid", house_village_id) //--5
                        .addFormDataPart("zhutiid", user_id)

                        .addFormDataPart("id", id) //--6
                        .addFormDataPart("imgpa", imgPa)
                ;  //把服务器中老照片的路径传回去 //--7
                LogUtil.println("添加参数---（贫困户）修改帮扶记录时拼接老照片的路径为：==imgpa="+imgPa+"&helptitle="
                        +bfjl_title+"&helpdate="+bfjl_time+"&helpdetail="+bfjl_content+"&id="+id
                        +"&location="+location+"&householderid="+house_village_id+"&zhutiid="+user_id);
            }else if("cun".equals(shangchuan_type)){ //贫困村修改帮扶记录
                builder = new MultipartBody.Builder(boundary).setType(MultipartBody.FORM)
                        .addFormDataPart("helptitle", bfjl_title) //--1
                        .addFormDataPart("helpdate", bfjl_time) //--2
                        .addFormDataPart("helpdetail", bfjl_content) //--3
                        .addFormDataPart("location", location) //--4
                        .addFormDataPart("villageid", house_village_id) //--5
                        .addFormDataPart("zhutiid", user_id)

                        .addFormDataPart("id", id) //--6
                        .addFormDataPart("imgpa", imgPa); //把服务器中老照片的路径传回去 //--7
                LogUtil.println("添加参数---（贫困村）修改帮扶记录时拼接老照片的路径为：==imgpa="+imgPa+"&helptitle="
                        +bfjl_title+"&helpdate="+bfjl_time+"&helpdetail="+bfjl_content+"&id="+id
                        +"&location="+location+"&villageid="+house_village_id+"&zhutiid="+user_id);
            }
        }else {
            if("hu".equals(shangchuan_type)){ //贫困户添加帮扶记录---带照片
                builder = new MultipartBody.Builder(boundary).setType(MultipartBody.FORM)
                        .addFormDataPart("helptitle", bfjl_title) //--1
                        .addFormDataPart("helpdate", bfjl_time) //--2
                        .addFormDataPart("helpdetail", bfjl_content) //--3
                        .addFormDataPart("location", location) //--4
                        .addFormDataPart("householderid", house_village_id) //--5

                        .addFormDataPart("zhutiid", user_id);  //--6
                LogUtil.println("贫困户添加帮扶记录（带照片,有新照片）时添加参数helptitle=="+bfjl_title+",helpdate=="+bfjl_time
                        +",helpdetail=="+bfjl_content+",location=="+location+",householderid=="+house_village_id
                        +",zhutiid=="+user_id);
            }else if("cun".equals(shangchuan_type)){ //贫困村添加帮扶记录---带照片
                builder = new MultipartBody.Builder(boundary).setType(MultipartBody.FORM)
                        .addFormDataPart("helptitle", bfjl_title) //--1
                        .addFormDataPart("helpdate", bfjl_time) //--2
                        .addFormDataPart("helpdetail", bfjl_content) //--3
                        .addFormDataPart("location", location) //--4
                        .addFormDataPart("villageid", house_village_id) //--5

                        .addFormDataPart("zhutiid", user_id) //--6
                        .addFormDataPart("roletype", roleType+"")
                ; //--7
                LogUtil.println("贫困村添加帮扶记录（带照片,有新照片）时添加参数helptitle=="+bfjl_title+",helpdate=="+bfjl_time
                                +",helpdetail=="+bfjl_content+",location=="+location+",villageid=="+house_village_id
                                +",zhutiid=="+user_id+",roletype=="+roleType);
            }
        }

        for (int i = 0; i < lists.size(); i++) {
            LogUtil.println("贫困户或贫困村添加、修改帮扶记录（带照片,有新照片）时每张照片的路径(包括服务器照片)为==="+lists.get(i).getUrl());
        }

        if(lists.size()>0){ //有照片
            for (int i = 0; i <lists.size(); i++) {
                if(!lists.get(i).getUrl().startsWith("http")){
                    LogUtil.println("照片路径参数---贫困户或贫困村添加、修改帮扶记录（带照片,有新照片）时每张照片的路径(包括服务器照片)为==="+lists.get(i).getUrl());
                    builder.addFormDataPart(
                            "file",
                            "testFile" + i + ".jpg",
                            RequestBody.create(MEDIA_TYPE_PNG, new File(lists.get(i).getUrl())));
                    LogUtil.println("添加参数---（贫困户）修改帮扶记录时拼接老照片的路径为：==imgpa=" + imgPa + "&helptitle="
                            + bfjl_title + "&helpdate=" + bfjl_time + "&helpdetail=" + bfjl_content + "&id=" + id
                            + "&location=" + location + "&householderid=" + house_village_id + "&zhutiid=" + user_id
                            +"&file="+new File(lists.get(i).getUrl()));
                }
            }
        }
        MultipartBody mBody =builder.build();

        Request request = null;
        if("xiugai".equals(type)){ //如果是修改帮扶记录
            if("hu".equals(shangchuan_type)){ //贫困户修改帮扶记录
                request = new Request.Builder()
                        .url(URLs.POOR_HOUSE_Bfjl_Xiugai)
                        .post(mBody)
                        .build();
                LogUtil.println("贫困户--修改--帮扶记录(带照片,有新照片)的网址为：==="+URLs.POOR_HOUSE_Bfjl_Xiugai);
            }else if("cun".equals(shangchuan_type)){ //贫困村修改帮扶记录
                request = new Request.Builder()
                        .url(URLs.POOR_Village_Bfjl_Xiugai)
                        .post(mBody)
                        .build();
                LogUtil.println("贫困村--修改--帮扶记录(带照片,有新照片)的网址为：==="+URLs.POOR_Village_Bfjl_Xiugai);
            }
        }else { //如果是添加帮扶记录
            if("hu".equals(shangchuan_type)){ //贫困户添加帮扶记录
                request = new Request.Builder()
                        .url(URLs.POOR_HOUSE_Bfjl_Save)
                        .post(mBody)
                        .build();
                LogUtil.println("贫困户--添加--帮扶记录(带照片,有新照片)的网址为：==="+URLs.POOR_HOUSE_Bfjl_Save);
            }else if("cun".equals(shangchuan_type)){ //贫困村添加帮扶记录
                request = new Request.Builder()
                        .url(URLs.POOR_Village_Bfjl_Save)
                        .post(mBody)
                        .build();
                LogUtil.println("贫困村--添加--帮扶记录(带照片,有新照片)的网址为：==="+URLs.POOR_Village_Bfjl_Save);
            }
        }
        client.newCall(request).enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                final  String bodyStr = response.body().string();
                final boolean ok = response.isSuccessful();
                runOnUiThread(new Runnable() {
                    public void run() {
                        AddBangFuJiLuActivity.this.cancelProgressDialog();
                        if(ok){
                            LogUtil.println("上传照片成功：===" + bodyStr);
                            if("xiugai".equals(type)){
                                showToast("修改成功");
                            }else {
                                showToast("添加成功");
                            }
                            finish();
                        }else{
                            LogUtil.println("上传照片失败（访问服务器成功）：==="+bodyStr);
                            if("xiugai".equals(type)){
                                showToast("修改失败");
                            }else {
                                showToast("添加失败");
                            }
                        }
                    }
                });
            }
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        AddBangFuJiLuActivity.this.cancelProgressDialog();
                        if("xiugai".equals(type)){
                            showToast("修改失败--服务器异常");
                        }else {
                            showToast("添加失败--服务器异常");
                        }
                        LogUtil.println("上传照片异常（访问服务器失败）：===" + e.toString());
                    }
                });
            }
        });
    }

    @Override
    public void onSuccessResult(String str, int flag) {
        super.onSuccessResult(str, flag);
        switch (flag){
            case MyConstans.FIRST: //如果是添加帮扶记录---请求服务器帮添加扶记录数据成功
                LogUtil.println("添加帮扶记录页面提交数据成功：==" + str);
                if("xiugai".equals(type)){
                    showToast("修改成功");
                }else {
                    showToast("保存成功");
                }
                finish();
                break;

            case MyConstans.SECOND: //如果是修改帮扶记录---请求服务器帮扶记录数据成功
                LogUtil.println("修改帮扶记录请求服务器数据成功：==" + str);
                ShowBfjl_ResultList showBfjl_resultList=ShowBfjl_ResultList.parseToT(str,ShowBfjl_ResultList.class);
                if(null!=showBfjl_resultList && showBfjl_resultList.getSuccess()){
                    List<ShowBfjl> jsondata = showBfjl_resultList.getJsondata();
                    if(null!=jsondata && jsondata.size()>0){
                        //只有一条帮扶记录内容
                        List<Bfjl_Detail> bfjl_details = jsondata.get(0).getDetail();
                        if(null!=bfjl_details &&bfjl_details.size()>0){
                            et_bfjl_content.setText(bfjl_details.get(0).getDetailbf());
                        }
                        //帮扶记录的照片数量不一定
                        List<Bfjl_Detail_Img> bfjl_detail_imgs = jsondata.get(0).getImg();
                        if(null!=bfjl_detail_imgs &&bfjl_detail_imgs.size()>0){
                            for (int i = 0; i < bfjl_detail_imgs.size(); i++) {
                                Photo photo = new Photo();
                                photo.setUrl(bfjl_detail_imgs.get(i).getImagepath());
							    photo.setId(bfjl_detail_imgs.get(i).getId()); ///---------------------------
                                lists.add(photo);
                                net_old_lists.add(photo);
                            }
                        }
                        //初始化照片GridView数据，并设置条目的点击监听和长按监听
                        initGridViewDataAndListener();
                    }
                }
                break;

            case MyConstans.FIFTH:
                LogUtil.println("删除服务器中的老照片时成功：==="+ str);
                //删除照片
                deletePhoto(deletePosition);
                net_old_lists.remove(old_photo_deletePos);
                break;
        }
    }

    @Override
    public void onFaileResult(String str, int flag) {
        super.onFaileResult(str, flag);
        switch (flag){
            case MyConstans.FIRST:
                LogUtil.println("添加帮扶记录页面提交数据失败：=="+str);
//                showToast(str);
                if("xiugai".equals(type)){
                    showToast("修改失败");
                }else {
                    showToast("保存失败");
                }
                break;

            case MyConstans.FIFTH:
                LogUtil.println("删除服务器中的老照片时失败：==="+ str);
                break;
        }
    }

    /**
     * 选择日期
     */
    private void initTimePop(final int i) {
        timePop.setFocusable(true);
        timePop.setOutsideTouchable(false);
        timePop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePop.setAnimationStyle(R.style.PopupWindowTimerAnimation);
        timePop.showAtLocation(timeView, Gravity.BOTTOM, 0, ViewGroup.LayoutParams.WRAP_CONTENT);
        timeCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != timePop && timePop.isShowing()) {
                    timePop.setFocusable(false);
                    timePop.dismiss();
                }
            }
        });
        timeSure.setOnClickListener(new View.OnClickListener() {// 要求到货时间
            @Override
            public void onClick(View v) {
                String times = wheelMain.getYearAndMonthAndDay();
                if (!StringUtil.isEmpty(times)) {
                    String month_str = times.split("-")[1];
                    int month = Integer.parseInt(month_str) + 1;
                    int day = Integer.parseInt(times.split("-")[2]) + 1;
                    String time = times.split("-")[0] + "-" + month + "-" + day;
                    if (i == 1) {
                        tv_bfjl_time.setText(time);
                    }
                    srBirthDay = wheelMain.getYear();
                    timePop.setFocusable(false);
                    timePop.dismiss();
                }
            }
        });
    }

    /**
     * 初始化时间选择器
     * @param v
     */
    private void initTimePicker(View v) {
        wheelMain = new WheelMain(v, false, true, true);
        wheelMain.screenheight = screenInfo.getHeight();
        Calendar calendar = Calendar.getInstance();
        WheelMain.setSTART_YEAR(calendar.get(Calendar.YEAR) - 60);
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
        wheelMain.initDateTimePicker(year, month, day);
    }

    /**
     * 拍照时照片的路径---绝对路径
     */
    private String photo = null;
    /**
     * 拍照时照片的路径---URI路径
     */
    private Uri imageUri = null;
    private static final int CROP = 1;

    @Override
    public void Message(Object object) {
        ListMenu menu = (ListMenu) object;
        if (menu.getName().equals("照片")) {
            // 打开图片上传的Activity
            Intent intent = new Intent(mContext, SelectPhotoActivity.class);
            intent.putExtra(Common.INTENT_KEY, house_village_id); // 贫困户householderID或贫困村ID
            intent.putExtra("shangchuan_type", shangchuan_type);
            intent.putExtra("ifClearPhoto", true);
            intent.putExtra(Common.UP_LOAD_PHOTO_KEY, URLs.IMAGE_UP_LOAD);
            startActivityForResult(intent,0);
        } else if (menu.getName().equals("拍照")) {
            // 打开照相机
            Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            photo = Common.CACHE_DIR + "/" + UUID.randomUUID().toString() + ".jpg";
            imageUri = Uri.fromFile(new File(photo));
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intentFromCapture, CROP);
        }
    }

    /**
     * 照片GridView的适配器
     */
    PoorHousePhotoAdapter mAdapter;
    /**
     * 照片GridView的适配器的数据源
     */
    List<Photo> lists = new ArrayList<>();
    /**
     * 服务器中原来的老照片
     */
    List<Photo> net_old_lists = new ArrayList<>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Photo photoBea;
            switch (requestCode) {
                case 0: //选择照片完成后进入该方法
                    if(resultCode==2){
                        LogUtil.println("选择照片完成后返回该页面");
                        List<String> select_photo_paths= (List<String>) data.getSerializableExtra("select_photo_paths");
                        if(null!=select_photo_paths && select_photo_paths.size()>0){
                            for (int i = 0; i < select_photo_paths.size(); i++) {
                                photoBea = new Photo();
                                photoBea.setUrl(select_photo_paths.get(i));
//                                    photoBea.setId(poorFamilyPhoto.getId());
                                lists.add(photoBea);
                            }
                            //有数据后刷新适配器
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                    break;

                case CROP: //拍照完成后进入该方法
                    if (resultCode == Activity.RESULT_OK) {
                        LogUtil.println("拍照完成后返回该页面");
                        if (imageUri == null) {
                            imageUri = data.getData();
                        }
                        if (MyApplication.getInstance().getNetConnectInstance().ischeackNet(mContext)) {
                            getImageToView(data);
                            if (TextUtils.isEmpty(photo)) {
                                showToast("剪切照片失败...");
                                return;
                            }
                        }
//                        all_select_photo_paths.add(photo);
                        photoBea = new Photo();
                        photoBea.setUrl(photo);
    //                    photoBea.setId(poorFamilyPhoto.getId());
                        lists.add(photoBea);
                        //有数据后刷新适配器
                        mAdapter.notifyDataSetChanged();
                    }
                    break;
        }
    }

    int deletePosition;
    /**
     * 老照片删除时的位置
     */
    int old_photo_deletePos;
    /**
     * 显示是否删除照片的对话框
     */
    private void showDeleteDialog(final int position) {
        final NormalDailog normalDailog=new NormalDailog(this,R.style.popup_dialog_style);
        normalDailog.show();
        normalDailog.setTitleText("删除提示");
        normalDailog.setContentText("确定要删除这张照片吗？");
        normalDailog.setOnClickLinener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.normal_dialog_done: //确定
                        deletePosition = position;
                        normalDailog.dismiss();
                        //如果删除照片时包含服务器中的老照片，就把服务器中的老照片删除
                        for (int i = 0; i < net_old_lists.size(); i++) {
                            if(lists.size()>0){
                                if(net_old_lists.get(i).getUrl().equals(lists.get(deletePosition).getUrl())){
                                    //如果删除的是老照片请求服务器删除照片---贫困户
//                                  requestNetDeletePhoto(position);
                                    old_photo_deletePos=i;
                                    net_old_lists.remove(i);
                                }
                            }
                        }
                        //删除照片
                        deletePhoto(deletePosition);
//                        showToast("照片删除成功");
                        break;

                    case R.id.normal_dialog_cancel: //取消
                        normalDailog.dismiss();
                        break;
                }
            }
        });
    }

    /**
     * 请求服务器删除照片
     */
    private void requestNetDeletePhoto(int position) {
        RequestParams rp = new RequestParams();
        String picpath="poverty"+net_old_lists.get(position).getUrl().split("poverty")[1];
        rp.addBodyParameter("picpath", picpath);
        MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                URLs.POOR_House_Delete_Photo_Bfjl, rp,
                new MyRequestCallBack(this, MyConstans.FIFTH));
        LogUtil.println("删除服务器中的老照片时网址为：==="+ URLs.POOR_House_Delete_Photo_Bfjl+"&picpath="+picpath);
    }

    /**
     * 删除照片
     * @param position
     */
    private void deletePhoto(int position) {
        lists.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * @param position 照片GridView的点击位置
     * @param lists 照片GridView的适配器的数据源
     * 2016年5月10日
     * ZhaoDongShao
     */
    protected void imageBrower(int position, List<Photo> lists) {
        Intent intent = new Intent(mContext, ImagePagerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ImagePagerActivity.EXTRA_IMAGE_URLS, (Serializable)lists);
        bundle.putInt(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
        bundle.putBoolean("ifLoadLocalPhoto", true); //是否加载本地照片
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS_KEY, bundle);
        startActivity(intent);
    }

    /**
     * 保存照片
     * @param data
     * 2016年6月21日
     * ZhaoDongShao
     */
    private void getImageToView(Intent data) {
        try {
            Bundle extras = data.getExtras();
            if (extras != null) {
                if (Build.MODEL.equals("M9")) {
                    @SuppressWarnings("unused")
                    Drawable drawable = Drawable.createFromPath(photo);
                } else {
                    Bitmap drawable = extras.getParcelable("data");
                    File faceFile = BitmapUtil.saveBitmapTofile(drawable, photo);
                    photo = faceFile.getAbsolutePath();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
//			showToast("抱歉，您的手机没有本地图库，无法保存图片!");
        }
    }

}
