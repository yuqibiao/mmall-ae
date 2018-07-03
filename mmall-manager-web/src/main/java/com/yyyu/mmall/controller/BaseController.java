package com.yyyu.mmall.controller;

import com.google.gson.Gson;
import com.yyyu.mmall.uitls.controller.RestException;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.mmall.uitls.lang.LogUtils;
import com.yyyu.mmall.uitls.lang.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 功能：Controller基类
 *
 * @author yu
 * @date 2018/3/2.
 */
public class BaseController  {

    private Gson gson = new Gson();

    /**
     * 得到项目的路径
     *
     * @return
     */
    protected String getProjectRealPath(){
        WebApplicationContext webApplicationContext = ContextLoader
                .getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext
                .getServletContext();
        // 得到文件绝对路径
        String realPath = servletContext.getRealPath("/");
        return realPath;
    }

    /**
     * GET请求中文乱码处理
     *
     * @param request
     * @param key
     * @return
     */
    protected String getParameterUtf8(HttpServletRequest request, String key) {
        String result = request.getParameter(key);
        try {
            if (!StringUtils.isEmpty(result)) {
                result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 生成排序语句
     *
     * @param sort
     * @param order
     * @return
     */
    protected String genOrderByClause(String sort, String order) {
        StringBuilder orderByClause = new StringBuilder();
        if (!StringUtils.isEmpty(sort)) {
            if (sort.equals("createTime")) {
                orderByClause.append("create_time");
            } else if (sort.equals("parentId")) {
                orderByClause.append("parent_id");
            } else if (sort.equals("categoryId")) {
                orderByClause.append("category_id");
            } else {
                orderByClause.append(sort);
            }
            if (!StringUtils.isEmpty(order)) {
                orderByClause.append(" ");
                orderByClause.append(order);
            }
        }
        return orderByClause.toString();
    }

    /** 基于@ExceptionHandler异常处理 */
    @ExceptionHandler
    public String exp(HttpServletRequest request, HttpServletResponse httpServletResponse,Exception e) {
        LogUtils.d("======================exp");
        if (e instanceof RestException){
            ResultUtils exp = ResultUtils.createResult(((RestException) e).getCode() , ((RestException) e).getMsg());
            writeJson(httpServletResponse , gson.toJson(exp));
        }else {
            ResultUtils exp = ResultUtils.createError(e.getMessage());
            writeJson(httpServletResponse , gson.toJson(exp));
            e.printStackTrace();
        }
        return null;
    }

    protected void writeJson(HttpServletResponse servletResponse,String content)  {
        servletResponse.setContentType("text/json;charset=utf-8");
        try {
            PrintWriter writer = servletResponse.getWriter();
            writer.print(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
