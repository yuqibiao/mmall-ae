package com.yyyu.mmall.shiro.filter;

import com.google.gson.Gson;
import com.yyyu.mmall.global.Constant;
import com.yyyu.mmall.shiro.auth.token.StatelessToken;
import com.yyyu.mmall.uitls.codec.DESCoder;
import com.yyyu.mmall.uitls.controller.ResultCode;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.mmall.uitls.lang.StringUtils;
import com.yyyu.user.pojo.MallUserToken;
import com.yyyu.user.pojo.bean.TokenJwt;
import com.yyyu.user.service.inter.UserTokenServiceInter;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能：无状态认证拦截器（Restful 风格API）
 *
 * @author yu
 * @date 2017/11/14.
 */
public class StatelessAuthcFilter extends AccessControlFilter{

    Logger logger = Logger.getLogger(StatelessAuthcFilter.class);

    @Autowired
    private UserTokenServiceInter userTokenService;

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletRequest request = WebUtils.toHttp(servletRequest);

        try {
            //1.获取客户端传过来的身份信息（digest）
            String digest = request.getHeader("sessionId");
            System.out.println("digest=="+digest);
            if (StringUtils.isEmpty(digest)){//sessionId为空
                createError(servletResponse ,ResultCode.TOKEN_EXCEPTION.getCode(), "sessionId为空");
                return false;
            }
            String decryptDigest = DESCoder.decrypt(digest, Constant.DES_KEY);
            TokenJwt tokenJwt = new Gson().fromJson(decryptDigest , TokenJwt.class);
            String userId = tokenJwt.getUserId();
            MallUserToken mallUserTokens = userTokenService.selectUserTokenByUserId(Long.parseLong(userId));
            if (mallUserTokens==null){//找不到用户对应的sessionId
                throw new UnknownAccountException("sessionId无效");
            }
            //String sessionId = mallUserTokens.getSessionId();
            //Date iat = tokenJwt.getIat();
            Long exp = tokenJwt.getExp();//sessionId过期时间
            long currentTime = System.currentTimeMillis();
            if(currentTime>exp){//sessionId过期
                createError(servletResponse ,ResultCode.TOKEN_OUT_OF_DATE.getCode(), "sessionId过期");
                return false;
            }
            //--TODO 比对mac地址 看用户的设备是否改变了 改变了让sessionId失效
            //客户端请求参数列表
            Map<String, String[]> params = new HashMap<>();
            params.remove("sessionId");
            //生成无状态Token
            StatelessToken token = new StatelessToken(userId , params , decryptDigest);
            Subject subject = getSubject(servletRequest, servletResponse);
            //委托给realm进行登录
            subject.login(token);
        } catch (AuthenticationException e) {
            createError(servletResponse , ResultCode.TOKEN_EXCEPTION.getCode() ,"token验证失败");
            e.printStackTrace();
            return false;
        }catch (Exception e){
            createError(servletResponse , ResultCode.TOKEN_EXCEPTION.getCode() ,"token异常："+e.getMessage());
        }
        return true;
    }

    private void createError(ServletResponse servletResponse  , int code ,  String msg ) throws IOException {
        ResultUtils<Object> error = ResultUtils.createResult(code, msg,null);
        servletResponse.setContentType("text/json;charset=utf-8");
        String result = new Gson().toJson(error);
        servletResponse.getWriter().print(result);
    }
}
