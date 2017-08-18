/**
 * 
 */
package com.roch.fupin.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * @author ZhaoDongShao
 *
 * 2016年8月6日 
 *
 */
public class KeyBoardUtils {

	/**
	 * 隐藏输入法
	 *
	 * @param context
	 *
	 * 2016年8月6日
	 *
	 * ZhaoDongShao
	 *
	 */
	public static void closeKeyBoard(Context context){
		
		// 隐藏输入法
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		// 显示或者隐藏输入法
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		
	}
	
	
}
