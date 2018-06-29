package com.yyyu.mmall.filter;

import com.yyyu.mmall.uitls.lang.LogUtils;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能：性能监控拦截器
 *             打印一个请求得前后执行时间
 *
 * @author yu
 * @version 1.0
 * @date 2018/6/29
 */
public class PerformanceInterceptor extends HandlerInterceptorAdapter {

    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("StopWatch-StartWatch");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long beginTime = System.currentTimeMillis();//开始时间
        startTimeThreadLocal.set(beginTime);//绑定线程变量（该数据只有当前请求线程可见）

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        long endTime = System.currentTimeMillis();//结束时间
        long beginTime = startTimeThreadLocal.get();//得到请求开始时间
        long consumeTime = endTime - beginTime;
        if (consumeTime>1000){//耗时1s以上得请求记录
            //TODO 改成Log4J记录日志
            String str = String.format("%s consume %d millis", request.getRequestURI(), consumeTime);
            LogUtils.d(str);
        }

    }
}
