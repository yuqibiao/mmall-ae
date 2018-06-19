package com.yyyu.mmall.uitls.controller;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/24.
 */
public enum  ResultCode {

    //成功返回数据
    SUCCESS(200,"SUCCESS"),
    //错误
    ERROR(500,"ERROR"),
    //参数异常
    ILLEGAL_ARGUMENT(503,"ILLEGAL_ARGUMENT"),

    //---token相关
   TOKEN_EXCEPTION(1001 , "token异常"),//exception
    TOKEN_IS_NULL(1002 , "token为空"),
    TOKEN_IS_ILLEGAL(1003 , "token不合法"),
   TOKEN_OUT_OF_DATE(1004 , "token过期了"),//token过期

    //---认证相关
    //未登录
    NEED_LOGIN(2001,"NEED_LOGIN");

    final private int code;
    final private String desc;

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
