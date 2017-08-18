/**
 * 
 */
package com.roch.fupin.utils;

import android.content.Context;
import android.content.Intent;

/**
 * @author ZhaoDongShao
 *
 * 2016年5月26日 
 *
 */
public interface IOpenActivity {

	/**
	 * 打开Activity
	 *
	 * @param ActivityName 需要打开的类的标识
	 * @return
	 *
	 * 2016年5月26日
	 *
	 * ZhaoDongShao
	 *
	 */
	public Intent OpenActivity(Context mContext, String ActivityName);
	
}
