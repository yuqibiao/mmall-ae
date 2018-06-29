package com.yyyu.mmall.aop;

import com.google.gson.Gson;
import com.yyyu.mmall.global.Constant;
import com.yyyu.mmall.uitls.controller.RestException;
import com.yyyu.mmall.uitls.controller.ResultCode;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.mmall.utils.TokenManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 功能：权限控制切面
 *
 * @author yu
 * @date 2018/3/20.
 */
@Aspect
public class AuthorizationAdvice {

    @Pointcut("execution(* com.yyyu.mmall.controller.user.UserController.get1*(..))")
    public void checkAdminRole() {

    }

    @Around("checkAdminRole()")
    public Object hasAdminRole(ProceedingJoinPoint pjp) throws Throwable {
        Subject currentUser = SecurityUtils.getSubject();
        boolean hasRole = currentUser.hasRole("role:admin");
        if (hasRole) {//有权限
            Object proceed = pjp.proceed();
            return proceed;
        } else {
            ResultUtils<Object> error = ResultUtils.createResult(ResultCode.TOKEN_EXCEPTION.getCode(), "没有admin权限1");
            String errorJson = new Gson().toJson(error);
            writeContent(errorJson);
            return null;
        }
    }


    /**
     * 将内容输出到浏览器
     *
     * @param content 输出内容
     */
    private void writeContent(String content) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/plain;charset=UTF-8");
        response.setHeader("icop-content-type", "exception");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer!=null){
                writer.flush();
                writer.close();
            }
        }
    }


}
