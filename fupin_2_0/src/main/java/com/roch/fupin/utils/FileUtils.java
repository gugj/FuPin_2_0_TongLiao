package com.roch.fupin.utils;

import java.io.File;
import android.content.Context;
import android.os.Environment;

/**
 * 管理文件缓存路径的工具类
 * @author ZhaoDongShao
 * 2016年11月3日 
 */
public class FileUtils {

	/**
	 * 缓存文件目录
	 */
	private File mCacheDir;

	/**
	 * 创建管理文件缓存路径工具类的对象，里面做了一件事：如果sd卡存在就在sd卡中的创建缓存路径，否则就在手机内存中的创建缓存路径
	 * @param context 上下文
	 * @param CacheDir 文件缓存路径
	 */
	public FileUtils(Context context, String CacheDir) {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			mCacheDir = new File(CacheDir);
		} else {
			mCacheDir = context.getCacheDir();
		}
		if (!mCacheDir.exists()) {
			mCacheDir.mkdirs();
		}
		
		if (mCacheDir.exists()) {
			System.out.println("文件缓存路径（sd卡或手机内存中）创建成功");
		} else {
			System.out.println("文件缓存路径（sd卡或手机内存中）创建失败");
		}
	}

	/**
	 * 获取文件缓存的路径，如果sd卡存在就是sd卡中的缓存路径，否则就是手机内存中的缓存路径
	 * @return
	 * 2016年11月3日
	 */
	public String getCacheDir() {
		String pathString = mCacheDir.getAbsolutePath();
		return pathString;
	}
}
