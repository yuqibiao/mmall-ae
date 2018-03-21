package com.yyyu.mmall.uitls.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * 功能：json格式的controller返回结果
 *
 * @author yu
 * @date 2018/1/24.
 */
//保证序列化json的时候,如果是null的对象,key也会消失
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class ResultUtils<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    private ResultUtils(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ResultUtils(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResultUtils(int code, T data) {
        this.code = code;
        this.data = data;
    }

    private ResultUtils(int code) {
        this.code = code;
    }

    public static ResultUtils createSuccess(){

        return new ResultUtils(ResultCode.SUCCESS.getCode());
    }

    public static<T>  ResultUtils createSuccess(T data){

        return new ResultUtils<T>(ResultCode.SUCCESS.getCode() , data);
    }

    public static <T>ResultUtils createSuccess(String msg){

        return new ResultUtils<T>(ResultCode.SUCCESS.getCode() ,msg);
    }

    public static <T>ResultUtils createSuccess(String msg , T data){

        return new ResultUtils<T>(ResultCode.SUCCESS.getCode() ,msg, data);
    }


    public static <T>ResultUtils<T> createError(String msg){

        return new ResultUtils<T>(ResultCode.ERROR.getCode() ,msg);
    }

    public static <T>ResultUtils<T> createResult(Integer code , String msg , T data){

        return new ResultUtils<T>(code , msg , data);
    }

    public static <T>ResultUtils<T> createResult(Integer code , String msg ){

        return new ResultUtils<T>(code , msg , null);
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
