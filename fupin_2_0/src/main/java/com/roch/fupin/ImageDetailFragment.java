/**
 * 
 */
package com.roch.fupin;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.roch.fupin.entity.Photo;
import com.roch.fupin.photo.PhotoViewAttacher;
import com.roch.fupin_2_0.R;

/**
 * 图片加载器Activity中ViewPager的适配器中的每一个fragment类型的item
 * @author ZhaoDongShao
 * 2016年5月10日
 */
public class ImageDetailFragment extends BaseFragment{

	/**
	 * 图片加载器Activity中ViewPager的适配器中的每一个fragment类型的item，即photo的路径
	 */
	private String mImageUrl;
	/**
	 * 照片查看器中的imageView布局
	 */
	@ViewInject(R.id.image)
	private ImageView mImageView;
	@ViewInject(R.id.loading)
	private ProgressBar progressBar;
	/**
	 * 照片查看器中的imageView布局---承载器---实现了单击、双击功能
	 */
	private PhotoViewAttacher mAttacher;

	public static ImageDetailFragment newInstance(Photo photo,boolean ifLoadLocalPhoto) {
		final ImageDetailFragment f = new ImageDetailFragment();
		final Bundle bundle = new Bundle();
		bundle.putString("url", photo.getUrl());
		bundle.putBoolean("ifLoadLocalPhoto",ifLoadLocalPhoto);
		f.setArguments(bundle);
		return f;
	}

	/**
	 * 加载的是否是本地图片---默认不是，即加载的是网络图片
	 */
	boolean mIfLoadLocalPhoto=false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//图片加载器Activity中ViewPager的适配器中的每一个fragment类型的item，即photo的路径
		mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
		mIfLoadLocalPhoto=getArguments() != null ? getArguments().getBoolean("ifLoadLocalPhoto") : false;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_image_detail, container, false);
		ViewUtils.inject(this, view);
		//照片查看器中的imageView布局---承载器---实现了单击、双击功能
		mAttacher = new PhotoViewAttacher(mImageView);
		//设置单击监听---关闭该Activity
		mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
			@Override
			public void onPhotoTap(View arg0, float arg1, float arg2) {
				getActivity().finish();
			}
		});
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if(mIfLoadLocalPhoto){
			DisplayImageOptions displayImageOptions = new DisplayImageOptions
					.Builder()
					.showStubImage(R.drawable.ic_launcher)
					.showImageOnFail(R.drawable.ic_launcher) //设置图片加载/解码过程中错误时候显示的图片
					.showImageForEmptyUri(R.drawable.ic_launcher) //设置图片Uri为空或是错误的时候显示的图片
					.cacheInMemory(true) //设置下载的图片是否缓存在内存中
					.cacheOnDisc(true) //设置下载的图片是否缓存在SD卡中
					.displayer(new FadeInBitmapDisplayer(100)) //是否图片加载好后渐入的动画时间，可能会出现闪动
					.imageScaleType(ImageScaleType.IN_SAMPLE_INT) //设置图片的缩放方式
					.bitmapConfig(Bitmap.Config.RGB_565) //设置图片的解码类型
					.build();
			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity())
					.defaultDisplayImageOptions(displayImageOptions)
					.discCacheSize(50 * 1024 * 1024)//
					.discCacheFileCount(100)// 缓存一百张图片
					.writeDebugLogs()
					.build();
			ImageLoader.getInstance().init(config);

			ImageLoader.getInstance().displayImage(mImageUrl, mImageView, displayImageOptions);
		}else {
			DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//				.showImageOnLoading(R.drawable.empty_photo) //设置图片在下载期间显示的图片
					.showImageForEmptyUri(R.drawable.empty_photo) //设置图片Uri为空或是错误的时候显示的图片
					.showImageOnFail(R.drawable.empty_photo) //设置图片加载/解码过程中错误时候显示的图片
					.cacheInMemory(true) //设置下载的图片是否缓存在内存中
					.cacheOnDisc(true) //设置下载的图片是否缓存在SD卡中
					.imageScaleType(ImageScaleType.IN_SAMPLE_INT) //设置图片的缩放方式
					.bitmapConfig(Bitmap.Config.RGB_565) //设置图片的解码类型
					.build();
			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity())
					.defaultDisplayImageOptions(defaultOptions)
					.discCacheSize(50 * 1024 * 1024)//
					.discCacheFileCount(100)// 缓存一百张图片
					.writeDebugLogs()
					.build();
			ImageLoader.getInstance().init(config);

			ImageLoader.getInstance().displayImage(mImageUrl, mImageView, new SimpleImageLoadingListener() {
				@Override
				public void onLoadingStarted(String imageUri, View view) {
					progressBar.setVisibility(View.VISIBLE);
				}

				@Override
				public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
					String message = null;
					switch (failReason.getType()) {
						case IO_ERROR:
							message = "下载错误";
							break;

						case DECODING_ERROR:
							message = "图片无法显示";
							break;

						case NETWORK_DENIED:
							message = "网络有问题，无法下载";
							break;

						case OUT_OF_MEMORY:
							message = "图片太大无法显示";
							break;

						case UNKNOWN:
							message = "未知的错误";
							break;
					}
					Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
					progressBar.setVisibility(View.GONE);
				}

				@Override
				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
					progressBar.setVisibility(View.GONE);
					mAttacher.update();
				}
			});
		}
	}

}
