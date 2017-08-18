package com.roch.fupin.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.json.JSONObject;

import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;

public class FileUtil {

	/**
	 * 在SD卡上创建文件
	 * 
	 * @throws IOException
	 */
	public static File creatSDFile(String fileName) {
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
		}
		return file;
	}

	/**
	 * 在SD卡上创建目录
	 * 
	 * @param dirName
	 */
	public static File creatSDDir(String dirName) {
		File dir = new File(dirName);
		dir.mkdirs();
		return dir;
	}

	/**
	 * 判断SD卡上的文件夹是否存在
	 */
	public static boolean isFileExist(String fileName) {

		try {
			File file = new File(fileName);
			return file.exists();
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 根据文件绝对路径获取文件�?
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		if (TextUtils.isEmpty(filePath))
			return "";
		return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
	}
	/**
	 * 将一个InputStream里面的数据写入到SD卡中
	 */
	public static File saveFile(File file, Bitmap photo) {

		FileOutputStream fOut = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fOut = new FileOutputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		photo.compress(Bitmap.CompressFormat.PNG, 100, fOut);// 把Bitmap对象解析成流
		try {
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}

	public static String getSizeName(double size) {
		if (String.valueOf(size).length() > 0) {
			DecimalFormat format = new DecimalFormat("0.00");

			if (size < 1024) {
				return size + "B";
			} else if (size >= 1024 && size < 1024 * 1024) {
				return format.format((double) size / 1024) + "KB";
			} else {
				return format.format((double) size / 1024 / 1024) + "M";

			}
		}
		return null;
	}

	/**
	 * 用到cache目录时调�?
	 */
	public static void isCreatCache() {
		if (!new File(Common.CACHE_DIR).exists()) {
			new File(Common.CACHE_DIR).mkdirs();
		} else {
			return;
		}
	}

	/** 删除本地文件 */
	public static void deleleFile(String path) {
		if (checkSDCard() && !TextUtils.isEmpty(path)) {// 存在sdcard
			File file = new File(Common.CACHE_DIR, path);
			if (file.exists()) {
				file.delete();
				System.out.println("--执行本地删除成功--");
			}
		}
	}

	public static boolean checkSDCard() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			return true;
		else
			return false;
	}

//	public static int freeSpaceOnSd() {
//		StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
//		double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat.getBlockSize()) / Common.MB;
//		return (int) sdFreeMB;
//	}

	/**
	 * 文件重命�?
	 * 
	 * @param orifile
	 */
	public static void makefilenewName(File orifile, String newName) {
		try {
			String newFileName = parseServerName(newName);
			File file = new File(orifile.getAbsolutePath());
			File newFile = new File(orifile.getParent(), newFileName);
			if (file.exists()) {
				file.renameTo(newFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String parseSeverstr(String str) {
		String newName = null;
		try {
			if (null != str && str.length() > 5) {
				JSONObject j = new JSONObject(str);
				newName = j.getString("filePath");
				return newName;
			}
		} catch (Exception e) {
		}
		return newName;
	}

	public static String parseServerName(String str) {
		String newFileName = null;
		if (null != str && !"".equals(str) && str.contains("/")) {
			newFileName = str.substring(str.lastIndexOf("/") + 1, str.length());
			return newFileName;
		}
		return newFileName;
	}
}