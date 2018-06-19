package com.yyyu.mmall.uitls.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie读写工具类
 *
 * @author gxc
 */
public class CookieUtil {

    /**
     * 获取cookie
     *
     * @param request
     * @return
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 删除cookie
     *
     * @param response
     */
    public static void removeCookie(HttpServletResponse response, String cookieName, String path) {
        Cookie cookie = new Cookie(cookieName, "");
        cookie.setPath(path);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    /**
     * 设置cookie
     *
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param path
     * @param maxAge
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, String path, int maxAge) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 设置cookie
     *
     * @param response
     * @param cookieName
     * @param cookieValue
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, String path) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath(path);
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }

    /**
     * 设置cookie
     *
     * @param response
     * @param cookieName
     * @param cookieValue
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue) {
        CookieUtil.setCookie(response, cookieName, cookieValue, "/");
    }

    /**
     * 移除cookie
     *
     * @param response
     * @param cookieName
     */
    public static void removeCookie(HttpServletResponse response, String cookieName) {
        CookieUtil.removeCookie(response, cookieName, "/");
    }

}
