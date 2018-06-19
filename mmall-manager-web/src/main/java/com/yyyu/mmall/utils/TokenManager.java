package com.yyyu.mmall.utils;

import com.google.gson.Gson;
import com.yyyu.mmall.uitls.codec.Coder;
import com.yyyu.mmall.uitls.controller.CookieUtil;
import com.yyyu.mmall.uitls.controller.RestException;
import com.yyyu.mmall.uitls.controller.ResultCode;
import com.yyyu.mmall.uitls.lang.StringUtils;
import com.yyyu.user.pojo.bean.TokenJwt;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能：token操作相关工具类
 *
 * @author yu
 * @version 1.0
 * @date 2018/6/19
 */
public class TokenManager {

    private TokenManager() {

    }

    private static class InstanceHolder {
        public static TokenManager INSTANCE = new TokenManager();
    }

    public static TokenManager getInstance() {

        return InstanceHolder.INSTANCE;
    }


    /**
     * 判断token否合法
     *
     * @param token     得到的token
     * @param userToken 数据库表中查询的token
     * @return
     */
    public boolean isLegal(String token, String userToken) throws IOException {

        if (StringUtils.isEmpty(token)) {
            throw new RestException(ResultCode.TOKEN_IS_NULL);
        }
        if (StringUtils.isEmpty(userToken)) {
            throw new RestException(ResultCode.TOKEN_IS_ILLEGAL);
        }
        //1.判断token时间是否过期
        String tokenJson = Coder.decryptBASE64(token).toString();
        TokenJwt tokenJwt = new Gson().fromJson(tokenJson, TokenJwt.class);
        Long exp = tokenJwt.getExp();
        if (System.currentTimeMillis() > exp) {//token过期
            throw new RestException(ResultCode.TOKEN_OUT_OF_DATE);
        }
        //2.查询对应userId对应的token是否一致
        if (!userToken.equals(token)) {
            throw new RestException(ResultCode.TOKEN_IS_ILLEGAL);
        }
        return true;
    }

    /**
     * 获取Token
     *
     * @param request
     * @param tokenName
     * @return
     */
    public String getToken(HttpServletRequest request, String tokenName) {
        String token = null;
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer")) {
            token = authorization.substring("Bearer".length()).trim();
        }

        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        if (StringUtils.isEmpty(token)) {
            token = request.getHeader("token");
        }

        if (StringUtils.isEmpty(token)) {
            token = CookieUtil.getCookie(request, tokenName);
        }
        return token;
    }

    /**
     * 生成token
     *
     * @return
     */
    public String genToken() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date(System.currentTimeMillis());
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, 7);
        TokenJwt tokenJwt = new TokenJwt(2 + "", currentDate.getTime(), calendar.getTime().getTime());
        String tokenJwtStr = new Gson().toJson(tokenJwt);
        //System.out.println("tokenJwtStr："+tokenJwtStr);
        String encryptJwt = Coder.encryptBASE64(tokenJwtStr.getBytes());
        return encryptJwt;
    }

}
