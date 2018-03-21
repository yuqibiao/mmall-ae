package com.yyyu.mmall.aop;

import com.google.gson.Gson;
import com.yyyu.mmall.uitls.controller.ResultCode;
import com.yyyu.mmall.uitls.controller.ResultUtils;
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

/**
 * 功能：权限控制切面
 *
 * @author yu
 * @date 2018/3/20.
 */
@Aspect
public class AuthorizationAdvice {

    @Pointcut("execution(* com.yyyu.mmall.controller.user.UserController.get*(..))")
    public void checkAdminRole(){

    }

    @Around("checkAdminRole()")
    public Object hasAdminRole(ProceedingJoinPoint pjp)throws Throwable{
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Subject currentUser = SecurityUtils.getSubject();
        boolean hasRole = currentUser.hasRole("role:admin");
        if (hasRole){//有权限
            Object proceed = pjp.proceed();
            return proceed;
        }else{
            ResultUtils<Object> error = ResultUtils.createResult(ResultCode.TOKEN_EXCEPTION.getCode(),"没有admin权限1");
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().print(new Gson().toJson(error));
            return null;
        }
    }

}
