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
    //未登录
    NEED_LOGIN(10,"NEED_LOGIN"),
    //参数异常
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

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
