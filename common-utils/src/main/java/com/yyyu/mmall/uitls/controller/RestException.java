package com.yyyu.mmall.uitls.controller;

/**
 * 功能：自定义exception
 *
 * @author yu
 * @version 1.0
 * @date 2018/6/19
 */
public class RestException extends Exception{

    private Integer code;
    private String msg;

    public RestException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getDesc();
    }

    public RestException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return getMsg();
    }
}
