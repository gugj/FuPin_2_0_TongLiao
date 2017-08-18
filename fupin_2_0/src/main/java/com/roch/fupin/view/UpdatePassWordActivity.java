package com.roch.fupin.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.roch.fupin.LoginActivity;
import com.roch.fupin.MainBaseActivity;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

/**
 * 修改登录密码页面
 * 作者：GuGaoJie
 * 时间：2017/5/4/004 13:51
 */
@ContentView(R.layout.activity_update_password)
public class UpdatePassWordActivity extends MainBaseActivity {

    @ViewInject(R.id.edt_old_pwd)
    ClearEditText edt_old_pwd;

    @ViewInject(R.id.edt_new_pwd)
    ClearEditText edt_new_pwd;

    @ViewInject(R.id.edt_new_pwd2)
    ClearEditText edt_new_pwd2;

    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.tv_title)
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        MyApplication.getInstance().addActivity(this);
        initToolbar();
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
        tv_title.setText("修改密码");
    }

    @OnClick({R.id.btn_quxiao,R.id.btn_quding})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_quxiao:
                MyApplication.getInstance().finishActivity(this);
                break;

            case R.id.btn_quding:
                //检查输入密码是否为空，并请求服务器修改密码
                checkIfNull();
                break;
        }
    }

    String old_pwd;
    String new_pwd;
    String new_pwd2;

    /**
     * 检查输入密码是否为空
     */
    private void checkIfNull() {
        old_pwd=getEditText(edt_old_pwd);
        new_pwd=getEditText(edt_new_pwd);
        new_pwd2=getEditText(edt_new_pwd2);

        View focusView = null;
        boolean isNot = false; // 判断输入框是否为空

        if (StringUtil.isEmpty(old_pwd)){
            focusView = edt_old_pwd;
            isNot = true;
            showToast("原始密码不能为空");
            return;
        }
        if (StringUtil.isEmpty(new_pwd)){
            focusView = edt_new_pwd;
            isNot = true;
            showToast("新密码不能为空");
            return;
        }
        if (StringUtil.isEmpty(new_pwd2)){
            focusView = edt_new_pwd2;
            isNot = true;
            showToast("再次输入新密码不能为空");
            return;
        }
        if(!new_pwd.equals(new_pwd2)){
            showToast("两次输入密码不一致");
            return;
        }
        if(old_pwd.equals(new_pwd)){
            showToast("新密码和原始密码不能一样");
            return;
        }
        if(isNot){
            focusView.requestFocus();
            showToast("输入密码不能为空");
        }else {
            SharedPreferences sp = getSharedPreferences("loginactivty", Context.MODE_APPEND);
            String login_pwd=sp.getString("password", "");
            System.out.println("修改密码时获取登陆密码为：=="+login_pwd);
            if(!old_pwd.equals(login_pwd)){
                showToast("原始密码不正确");
                return;
            }
            RequestParams rp = new RequestParams();
            rp.addBodyParameter("password", new_pwd);
            MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST, URLs.Update_password, rp,
                    new MyRequestCallBack(this, MyConstans.FIRST));
            System.out.println("修改密码页面的网址为：===" + URLs.Update_password + "?password=" + new_pwd);
        }
    }

    @Override
    public void onSuccessResult(String str, int flag) {
        super.onSuccessResult(str, flag);
        //修改成功跳转到登陆页面
        MyApplication.getInstance().finishAllActivity();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFaileResult(String str, int flag) {
        super.onFaileResult(str, flag);
        showToast("密码修改失败");
        System.out.println("密码修改失败：=="+str);
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
