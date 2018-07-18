package com.yyyu.mmall.uitls.controller.validator;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-12-16
 * <p>Version: 1.0
 *
 * 需要在xml中配置加载该类
 *由于在utils包中无法通过注解扫描方式加载
 */
public class SpringUtils implements ApplicationContextAware {

    @Autowired
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringUtils.context = context;
    }

    public static <T> T getBean(String name) {
        return (T)context.getBean(name);
    }
}
