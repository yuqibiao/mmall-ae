package com.yyyu.mmall.filter;

import com.google.gson.Gson;
import com.yyyu.mmall.uitls.controller.RestException;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.mmall.utils.TokenManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 功能：token认证拦截器
 *
 * @author yu
 * @version 1.0
 * @date 2018/6/29
 */
public class TokenAuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TokenManager tokenManager = TokenManager.getInstance();
        Gson gson = new Gson();
        try {
            if (tokenManager.isLegal(request)){
                return true;
            }
        } catch (RestException e) {
            response.reset();
            response.setContentType("text/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            ResultUtils<String> result = ResultUtils.createResult(e.getCode(), e.getMsg());
            writer.print(gson.toJson(result));
            return false;
        }
        return false;

    }
}
