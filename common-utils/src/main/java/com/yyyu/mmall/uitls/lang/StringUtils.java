package com.yyyu.mmall.uitls.lang;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/3/2.
 */
public class StringUtils {

    /**
     * 百分比转换为小数
     *
     * @param percentStr
     * @return
     */
    public static  float percentToDecimals(String percentStr) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        try {
            Number number = nf.parse(percentStr);
            return number.floatValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 小数转百分比
     *
     * @param decimals
     * @return
     */
    public static String decimalsToPercent(float decimals) {

        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);

        return nf.format(decimals);
    }

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
