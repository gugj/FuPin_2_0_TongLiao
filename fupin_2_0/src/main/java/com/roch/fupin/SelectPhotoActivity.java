package com.roch.fupin;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.roch.fupin.adapter.MyAdapter;
import com.roch.fupin.adapter.MyAdapter.OnItemClickListener;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.ListImageDirPopupWindow;
import com.roch.fupin.dialog.ListImageDirPopupWindow.OnImageDirSelected;
import com.roch.fupin.entity.ImageFloder;
import com.roch.fupin.entity.ImageItem;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.ResourceUtil;
import com.roch.fupin_2_0.R;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SelectPhotoActivity extends MainBaseActivity implements OnImageDirSelected, OnItemClickListener {

    private static final String TAG = "SelectPhotoActivity";
    private ProgressDialog mProgressDialog;

    private Intent intent;
    /**
     * 存储文件夹中的图片数量
     */
    private int mPicsSize;
    /**
     * 图片数量最多的文件夹
     */
    private File mImgDir;
    /**
     * 所有的图片
     */
    private List<ImageItem> mImgs;
    /**
     * 选择照片页面中的GridView
     */
    private GridView mGirdView;
    private MyAdapter mAdapter;
    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths = new HashSet<String>();
    Toolbar toolbar;
    /**
     * 照片上传的服务器地址：URLs.IMAGE_UP_LOAD <tr/>
     * 即 http://101.200.190.254:9100/poverty/app/poorfamily/upload.do
     */
    private String url = "";
    /**
     * 贫困户householderID或贫困村id
     */
    private String id = "";

    /**
     * 扫描拿到所有的图片文件夹
     */
    private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();
    /**
     * 选择照片页面中查看所有照片文件夹的按钮
     */
    private RelativeLayout mBottomLy;
    /**
     * 选择照片的文件夹的按钮
     */
    private TextView mChooseDir;
    /**
     * 手机中所有照片的数量
     */
    private TextView mImageCount;
    /**
     * 选择照片页面中的完成按钮
     */
    private TextView mSelectPhoto;
    /**
     * 预览照片按钮
     */
    private TextView mYuLanTextView;
    private TextView tv_title;
    int totalCount = 0;
    /**
     * 手机屏幕的高度
     */
    private int mScreenHeight;
    private ListImageDirPopupWindow mListImageDirPopupWindow;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mProgressDialog.dismiss();
            // 给Gridview设置适配器
            data2View();
            // 初始化展示文件夹的popupWindw
            initListDirPopupWindw();
        }
    };

    /**
     * 给Gridview设置适配器
     */
    private void data2View() {
        if (mImgDir == null) {
            Toast.makeText(getApplicationContext(), "一张图片没扫描到", Toast.LENGTH_SHORT).show();
            return;
        }
        List<String> list = Arrays.asList(mImgDir.list());
        mImgs = new ArrayList<ImageItem>();
        for (int i = 0; i < list.size(); i++) {
            mImgs.add(new ImageItem(list.get(i), false));
        }
        //		mImgs = Arrays.asList(mImgDir.list());
        /**
         * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
         */
        mAdapter = new MyAdapter(getApplicationContext(), mImgs, R.layout.grid_item, mImgDir.getAbsolutePath());
        mGirdView.setAdapter(mAdapter);
        mImageCount.setText(totalCount + "张");
        mAdapter.setOnItemClickListener(this);
        initText();
    }

    /**
     * 初始化展示文件夹的popupWindw
     */
    @SuppressLint("InflateParams")
    private void initListDirPopupWindw() {
        mListImageDirPopupWindow = new ListImageDirPopupWindow(
                LayoutParams.MATCH_PARENT, (int) (mScreenHeight * 0.7),
                mImageFloders, LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.list_dir, null));

        mListImageDirPopupWindow.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        // 设置选择文件夹的回调
        mListImageDirPopupWindow.setOnImageDirSelected(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_select);

        MyApplication.getInstance().addActivity(mActivity);
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;

        //初始化查找View，并初始化toolbar
        initView();
        //利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
        getImages();
        //初始化完成按钮、查看照片文件夹按钮、预览按钮的点击监听
        initEvent();
        registerMyReceiver();
    }

    MyBroadCastReceiver myBroadCastReceiver=new MyBroadCastReceiver();

    private void registerMyReceiver() {
        IntentFilter intentFilter=new IntentFilter("xzzp_yulan_photo_complete");
        registerReceiver(myBroadCastReceiver,intentFilter);
    }

    class MyBroadCastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            SelectPhotoActivity.this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadCastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle("选择照片");
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
        //初始化完成按钮和照片预览按钮的照片数量
        initText();
    }

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
     */
    private void getImages() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
            return;
        }
        // 显示进度条
        mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

        new Thread(new Runnable() {
            @Override
            public void run() {
                String firstImage = null;
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = SelectPhotoActivity.this.getContentResolver();

                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);

                Log.e("TAG", mCursor.getCount() + "");
                while (mCursor.moveToNext()) {
                    // 获取图片的路径
                    String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    Log.e("TAG", path);
                    // 拿到第一张图片的路径
                    if (firstImage == null)
                        firstImage = path;
                    // 获取该图片的父路径名
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null)
                        continue;
                    String dirPath = parentFile.getAbsolutePath();
                    ImageFloder imageFloder = null;
                    // 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
                    if (mDirPaths.contains(dirPath)) {
                        continue;
                    } else {
                        mDirPaths.add(dirPath);
                        // 初始化imageFloder
                        imageFloder = new ImageFloder();
                        imageFloder.setDir(dirPath);
                        imageFloder.setFirstImagePath(path);
                    }

                    int picSize = parentFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {
                            if (filename.endsWith(".jpg")
                                    || filename.endsWith(".png")
                                    || filename.endsWith(".jpeg"))
                                return true;
                            return false;
                        }
                    }).length;
                    totalCount += picSize;

                    imageFloder.setCount(picSize);
                    mImageFloders.add(imageFloder);

                    if (picSize > mPicsSize) {
                        mPicsSize = picSize;
                        mImgDir = parentFile;
                    }
                }
                mCursor.close();

                // 扫描完成，辅助的HashSet也就可以释放内存了
                mDirPaths = null;
                // 通知Handler扫描图片完成
                mHandler.sendEmptyMessage(0x110);
            }
        }).start();
    }

    /**
     * 初始化查找View，并初始化toolbar
     */
    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        mGirdView = (GridView) findViewById(R.id.id_gridView);
        mChooseDir = (TextView) findViewById(R.id.id_choose_dir);
        mImageCount = (TextView) findViewById(R.id.id_total_count);
        mSelectPhoto = (TextView) findViewById(R.id.tv_sure);
        mBottomLy = (RelativeLayout) findViewById(R.id.id_bottom_ly);
        mYuLanTextView = (TextView) findViewById(R.id.id_yulan_count);
        tv_title.setVisibility(View.GONE);
        mSelectPhoto.setVisibility(View.VISIBLE);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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
                MyAdapter.mSelectedImage.clear();
                MyApplication.getInstance().finishActivity(this);
                break;
        }
        return true;
    }


    /**
     * 标志位---如果是"hu"为贫困户选择照片，如果是"cun"为贫困村选择照片
     */
    String shangchuan_type = "";
    /**
     * 是否删除之前选中的照片---只要是从帮扶记录页面传进来，都是删除之前选中的照片
     */
    boolean ifClearPhoto;

    /**
     * 初始化完成按钮、查看照片文件夹按钮、预览按钮的点击监听
     */
    private void initEvent() {
        Intent it = getIntent();
        if (it != null) {
            url = it.getStringExtra(Common.UP_LOAD_PHOTO_KEY);
            id = it.getStringExtra(Common.INTENT_KEY);
            shangchuan_type = it.getStringExtra("shangchuan_type");
            ifClearPhoto = it.getBooleanExtra("ifClearPhoto",false);
        }
        if(ifClearPhoto){
            //判断是否删除之前选中的照片集合
            MyAdapter.mSelectedImage.clear();
        }
        //初始化完成按钮和照片预览按钮的照片数量
        initText();
        //点击选择照片完成按钮
        mSelectPhoto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> photos = new ArrayList<String>();
                for (int i = 0; i < MyAdapter.mSelectedImage.size(); i++) {
                    photos.add(MyAdapter.mSelectedImage.get(i).getPath());
                    Log.i(TAG, MyAdapter.mSelectedImage.get(i).getPath());
                }
                //照片选择好后跳转到上传服务器照片界面
                intent = new Intent();
//                intent.setClass(mContext, UploadPhotoActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putStringArrayList(Common.BUNDEL_KEY, (ArrayList<String>) photos);
//                bundle.putString(Common.TITLE_KEY, id);
//                bundle.putString(Common.UP_LOAD_PHOTO_KEY, url);
//                bundle.putString("shangchuan_type", shangchuan_type);
//                intent.putExtra(Common.INTENT_KEY, bundle);
//                startActivity(intent);
//                MyAdapter.mSelectedImage.clear();
                intent.putExtra("select_photo_paths",(ArrayList<String>) photos);
                SelectPhotoActivity.this.setResult(2,intent);
                SelectPhotoActivity.this.finish();
            }
        });
        // 点击查看所有照片按钮，弹出popupWindow
        mBottomLy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListImageDirPopupWindow.setAnimationStyle(R.style.anim_popup_dir);
                mListImageDirPopupWindow.showAsDropDown(mBottomLy, 0, 0);
                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = .3f;
                getWindow().setAttributes(lp);
            }
        });
        // 点击预览照片按钮
        mYuLanTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyAdapter.mSelectedImage.size() > 0) {
                    intent = new Intent();
                    intent.putExtra("position", "0");
                    intent.putExtra(Common.INTENT_KEY, id);
                    intent.setClass(SelectPhotoActivity.this, GalleryActivity.class);
                    startActivity(intent);
//                    finish();
                }
            }
        });
    }

    @Override
    public void onSuccessResult(String str, int flag) {
        super.onSuccessResult(str, flag);
        switch (flag) {
            case MyConstans.FIRST:
                System.out.println("正在上传...");
                break;
        }
    }

    @Override
    public void selected(ImageFloder floder) {
        mImgDir = new File(floder.getDir());
        List<String> list = Arrays.asList(mImgDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg"))
                    return true;
                return false;
            }
        }));
        mImgs = new ArrayList<ImageItem>();
        for (int i = 0; i < list.size(); i++) {
            mImgs.add(new ImageItem(list.get(i), false));
        }
        /**
         * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
         */
        mAdapter = new MyAdapter(getApplicationContext(), mImgs, R.layout.grid_item, mImgDir.getAbsolutePath());
        mGirdView.setAdapter(mAdapter);
        // mAdapter.notifyDataSetChanged();
        mImageCount.setText(floder.getCount() + "张");
        mChooseDir.setText(floder.getName());
        mListImageDirPopupWindow.dismiss();
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        if (MyAdapter.mSelectedImage.size() >= 15) {
            Toast.makeText(SelectPhotoActivity.this, "最多不能超过15张", Toast.LENGTH_SHORT).show();
            mSelectPhoto.setText("完成" + "(" + MyAdapter.mSelectedImage.size() + "/" + 15 + ")");
            mYuLanTextView.setText("预览(" + MyAdapter.mSelectedImage.size() + ")");
            return;
        } else {
            initText();
        }
    }

    /**
     * 初始化完成按钮和照片预览按钮的照片数量
     * 2016年6月22日<p/>
     * ZhaoDongShao
     */
    private void initText() {
        if (MyAdapter.mSelectedImage.size() <= 0) {
            mSelectPhoto.setEnabled(false);
            mSelectPhoto.setTextColor(ResourceUtil.getInstance().getColorById(R.color.gainsboro));
            mYuLanTextView.setEnabled(false);
            mYuLanTextView.setTextColor(ResourceUtil.getInstance().getColorById(R.color.gainsboro));
        } else {
            mSelectPhoto.setEnabled(true);
            mSelectPhoto.setTextColor(ResourceUtil.getInstance().getColorById(R.color.white));
            mYuLanTextView.setEnabled(true);
            mYuLanTextView.setTextColor(ResourceUtil.getInstance().getColorById(R.color.white));
        }
        mSelectPhoto.setText("完成" + "(" + MyAdapter.mSelectedImage.size() + "/" + 15 + ")");
        mYuLanTextView.setText("预览(" + MyAdapter.mSelectedImage.size() + ")");
    }

}
