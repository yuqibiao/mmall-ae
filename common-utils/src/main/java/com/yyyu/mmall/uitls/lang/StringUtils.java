package com.yyyu.mmall.uitls.lang;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/3/2.
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){

        return str==null || "".equals(str);
    }

}
