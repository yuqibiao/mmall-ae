package com.yyyu.mmall.utils;

import com.google.gson.Gson;
import com.yyyu.mmall.global.Constant;
import com.yyyu.mmall.uitls.codec.Coder;
import com.yyyu.mmall.uitls.controller.CookieUtil;
import com.yyyu.mmall.uitls.controller.RestException;
import com.yyyu.mmall.uitls.controller.ResultCode;
import com.yyyu.mmall.uitls.lang.LogUtils;
import com.yyyu.mmall.uitls.lang.StringUtils;
import com.yyyu.user.pojo.bean.JWT;
import org.springframework.core.NamedThreadLocal;

import javax.servlet.http.HttpServletRequest;
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
     * @return
     */
    public boolean isLegal(HttpServletRequest request) throws Exception {

        Gson gson = new Gson();
        String token = getToken(request , Constant.TOKEN);
        if (StringUtils.isEmpty(token)) {
            throw new RestException(ResultCode.TOKEN_IS_NULL);
        }
        String[] strings = token.split("\\.");
        if (strings.length!=3){//token被篡改
            throw new RestException(ResultCode.TOKEN_IS_ILLEGAL);
        }
        String headerStr = new String(Coder.decryptBASE64(strings[0]));
        String payloadStr = new String(Coder.decryptBASE64(strings[1]));
        String signature = new String(Coder.decryptBASE64(strings[2]));
        String headerNPayload = Coder.encryptBASE64(headerStr.getBytes())+"."
                +Coder.encryptBASE64(payloadStr.getBytes());
        String bSignature = Coder.encryptSHA(headerNPayload+SALT);
        if (!bSignature.equals(signature)){//token被篡改
            throw new RestException(ResultCode.TOKEN_IS_ILLEGAL);
        }
        JWT.Payload payload = gson.fromJson(payloadStr, JWT.Payload.class);
        Long expTime = payload.getExp();
        if (System.currentTimeMillis() > expTime) {//token过期
            throw new RestException(ResultCode.TOKEN_OUT_OF_DATE);
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
        String token = token = request.getParameter("token");;

        if (StringUtils.isEmpty(token)) {
            String authorization = request.getHeader("Authorization");
            if (authorization != null && authorization.startsWith("Bearer")) {
                token = authorization.substring("Bearer".length()).trim();
            }
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
    private static final String ALG = "SHA";//消息摘要算法
    private static final Integer expDay = 7;//过期得天数
    public static final String SALT = "yyyu";//签名摘要得盐
    public String genToken(Long userId) {
        Gson gson = new Gson();
        //生成头部
        JWT.Header header = new JWT.Header(ALG , "JWT");
        String headerStr = gson.toJson(header);
        //生成PayLoad
        JWT.Payload payload = new JWT.Payload();
        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date(System.currentTimeMillis());
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, expDay);
        long currentTime = currentDate.getTime();
        long expTime = calendar.getTime().getTime();
        payload.setIss("yyyu");
        payload.setExp(expTime);//过期时间
        payload.setJat(currentTime);//签发时间
        payload.setSub(userId);//存用户id
        String payloadStr = gson.toJson(payload);
        //生成签名信息
        String headerNPayload = Coder.encryptBASE64(headerStr.getBytes())+"."
                +Coder.encryptBASE64(payloadStr.getBytes());
        String signature = Coder.encryptSHA(headerNPayload+SALT);
        String jwtStr = headerNPayload + "."+Coder.encryptBASE64(signature.getBytes());

        return jwtStr;
    }

}
