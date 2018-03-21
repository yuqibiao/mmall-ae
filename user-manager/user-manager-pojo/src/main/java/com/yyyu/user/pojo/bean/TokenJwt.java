package com.yyyu.user.pojo.bean;

import java.util.Date;

/**
 * 功能：简单的JWT
 *
 * @author yu
 * @date 2018/3/19.
 */
public class TokenJwt {

    private String userId;
    private Long iat; //token签发时间
    private Long exp;// token过期时间

    public TokenJwt(String userId, Long iat, Long exp) {
        this.userId = userId;
        this.iat = iat;
        this.exp = exp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getIat() {
        return iat;
    }

    public void setIat(Long iat) {
        this.iat = iat;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }
}
