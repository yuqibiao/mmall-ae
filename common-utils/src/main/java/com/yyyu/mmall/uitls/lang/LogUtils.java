package com.yyyu.mmall.uitls.lang;

/**
 * 功能：Log打印
 *
 * @author yu
 * @version 1.0
 * @date 2018/6/29
 */
public class LogUtils {

    private boolean isDebug = true;

    public static void d(String str){
        System.out.println(str);
    }

    public static void e(String str){
        System.err.println(str);
    }


}
