package com.roch.fupin.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.view.animation.AlphaAnimation;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BitmapUtil {
	public static File saveBitmapTofile(Bitmap bmp,String path){  
		CompressFormat format= Bitmap.CompressFormat.JPEG;  
		int quality = 100;  
		File file = new File(path);
		FileOutputStream stream = null;  
		try {  
			stream = new FileOutputStream(file);  
			bmp.compress(format, quality, stream);  
			stream.close();
		} catch (Exception e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}  
		return file;
	}
	/**
	 * 显示网络上获取到的图片
	 * @param context
	 * @return
	 */
	public static BitmapUtils getBitmapUtil(Context context)
	{
		BitmapDisplayConfig displayConfig;
		BitmapUtils utils;

		int maxCache = (int)Runtime.getRuntime().maxMemory();
		int cacheSize = maxCache / 8;
		FileUtils fileUtils = new FileUtils(context, Common.CACHE_DIR);


		utils = new BitmapUtils(context, fileUtils.getCacheDir(), cacheSize);

		displayConfig=new BitmapDisplayConfig();
		displayConfig.setBitmapMaxSize(BitmapCommonUtils.getScreenSize(context));
		AlphaAnimation animation=new AlphaAnimation(0.1f,1.0f);
		animation.setDuration(500);
		displayConfig.setAnimation(animation);
		return utils;
	}
	/**
	 * 获取网络上的图片
	 * @param url
	 * @return
	 */
	public static Bitmap getHttpBitmap(String url)
	{
		URL mUrl = null;
		Bitmap bitmap = null;
		InputStream	inputStream = null;

		try {
			mUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)mUrl.openConnection();
			connection.setConnectTimeout(0);
			connection.setDoInput(true);
			connection.connect();
			inputStream  = connection.getInputStream();
			bitmap = BitmapFactory.decodeStream(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			closeStream(inputStream, null);
		}
		return bitmap;
	}

	/**  
	 * 关闭输入输出流 
	 * @param in 
	 * @param out 
	 */  
	public static void closeStream(InputStream in, OutputStream out)  
	{  
		try  
		{  
			if (null != in)  
			{  
				in.close();  
			}  
			if (null != out)  
			{  
				out.close();  
			}  
		}  
		catch (IOException e)  
		{  
			e.printStackTrace();  
		}  
	}
}
