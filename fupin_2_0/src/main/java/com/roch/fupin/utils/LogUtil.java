package com.roch.fupin.utils;

/**
 * 打印log日志的工具---如果是debug模式可以打印，否则不打印
 * 作者：GuGaoJie
 * 时间：2017/5/15/015 9:21
 */
public class LogUtil {

    private static boolean isDebug=true;

    public static void println(String msg){
        if(isDebug){
            System.out.println(msg);
        }
    }

}
