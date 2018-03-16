package com.yyyu.mmall.controller;

import com.yyyu.mmall.uitls.lang.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
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

}
