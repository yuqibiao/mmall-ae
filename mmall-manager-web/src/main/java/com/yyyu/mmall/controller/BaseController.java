package com.yyyu.mmall.controller;

import com.yyyu.mmall.uitls.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * 功能：Controller基类
 *
 * @author yu
 * @date 2018/3/2.
 */
public class BaseController {


    /**
     * GET请求中文乱码处理
     *
     * @param request
     * @param key
     * @return
     */
    protected  String getParameterUtf8(HttpServletRequest request , String key){
        String result = request.getParameter(key);
        try {
            if (!StringUtils.isEmpty(result)){
                result= new String(result.getBytes("ISO-8859-1"), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  result;
    }

    /**
     * 生成排序语句
     *
     * @param sort
     * @param order
     * @return
     */
    protected String genOrderByClause(String sort , String order ){
        StringBuilder orderByClause = new StringBuilder();
        if(!StringUtils.isEmpty(sort)){
            if (sort.equals("createTime") ){
                orderByClause.append("create_time");
            }else{
                orderByClause.append(sort);
            }
            if(!StringUtils.isEmpty(order)){
                orderByClause.append(" ");
                orderByClause.append(order);
            }
        }
        return orderByClause.toString();
    }

}
