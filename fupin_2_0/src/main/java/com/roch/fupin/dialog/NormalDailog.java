package com.roch.fupin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.roch.fupin_2_0.R;

public class NormalDailog extends Dialog {

    private android.view.View.OnClickListener mOnClick;
    private TextView content, title,tv_dingwei_location,tv_old_location,tv_qiandao_count;
    private Button doneBtn, cancelBtn,xiugaiBtn;
    LinearLayout doneBtnLayout;
    private int layout_type;

    public NormalDailog(Context context) {
        super(context);
    }

    public NormalDailog(Context context, int theme) {
        super(context, theme);
    }

    /**
     *
     * @param context 上下文
     * @param theme 主体
     * @param layout_type dialog弹窗口布局类型，如果为3，即为修改、删除、取消按钮布局，否则为确定、取消按钮布局;如果为4，
     *                    即为自定义的可以显示位置信息的布局
     */
    public NormalDailog(Context context, int theme,int layout_type) {
        super(context, theme);
        this.layout_type=layout_type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(3==layout_type){
            setContentView(R.layout.widget_normal_dialog_3);
        }else if(4==layout_type){
            setContentView(R.layout.widget_normal_dialog_zidingyi);
        } else {
            setContentView(R.layout.widget_normal_dialog);
        }
        // 使dialog全局
        getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        initViews();
    }

    //dialog 控件初始化
    private void initViews() {
        title = (TextView) findViewById(R.id.product_search_dialog_title);
        content = (TextView) findViewById(R.id.normal_dialog_content);
        doneBtn = (Button) findViewById(R.id.normal_dialog_done);
        cancelBtn = (Button) findViewById(R.id.normal_dialog_cancel);
        if(3==layout_type){
            xiugaiBtn = (Button) findViewById(R.id.normal_dialog_xiugai);
        }else if(4==layout_type){
            tv_dingwei_location = (TextView) findViewById(R.id.tv_dingwei_location);
            tv_old_location = (TextView) findViewById(R.id.tv_old_location);
            tv_qiandao_count = (TextView) findViewById(R.id.tv_qiandao_count);
        }
    }

    /**
     * 按钮点击事件
     * @param l
     */
    public void setOnClickLinener(android.view.View.OnClickListener l) {
        this.mOnClick = l;
        doneBtn.setOnClickListener(mOnClick);
        cancelBtn.setOnClickListener(mOnClick);
        if(3==layout_type){
            xiugaiBtn.setOnClickListener(mOnClick);
        }
    }

    /**
     * 设置title 文字
     * @param text
     */
    public void setTitleText(String text) {
        title.setText(text);
    }

    /**
     * 设置dialog提示内容,如果是自定义的，即设置签到的时间
     * @param text
     */
    public void setContentText(String text) {
        content.setText(text);
    }

    /**
     * 设置当前定位的位置信息
     * @param text
     */
    public void setLocationText(String text) {
        tv_dingwei_location.setText(text);
    }
    /**
     * 设置服务器保存的定位的位置信息
     * @param text
     */
    public void setNetLocationText(String text) {
        tv_old_location.setText(text);
    }

    /**
     * 设置服务器保存的已签到次数
     * @param text
     */
    public void setQiaoDaoCount(String text) {
        tv_qiandao_count.setText(text);
    }

    /**
     * 设置dialog提示内容 颜色
     *
     * @param color
     */
    public void setContentTextColor(int color) {
        content.setTextColor(color);
    }

    /**
     * 设置完成按钮文字描述
     * @param text
     */
    public void setDoneButtonText(String text) {
        doneBtn.setText(text);
    }

    /**
     * 设置取消按钮文字描述
     * @param text
     */
    public void setCancelButtonText(String text) {
        cancelBtn.setText(text);
    }

    /**
     * 设置确定按钮隐藏 or 显示
     * @param visibility
     */
    public void setDoneBtnVisible(int visibility) {
        //doneBtnLayout.setVisibility(visibility);
        doneBtn.setVisibility(visibility);
    }

    /**
     * 设置取消按钮隐藏 or 显示
     * @param visibility
     */
    public void setCancelVisible(int visibility) {
        //doneBtnLayout.setVisibility(visibility);
        cancelBtn.setVisibility(visibility);
    }

    public void setCancelBtnText(String text) {
        cancelBtn.setText(text);
    }

    public void setDoneBtnText(String text) {
        doneBtn.setText(text);
    }

}
