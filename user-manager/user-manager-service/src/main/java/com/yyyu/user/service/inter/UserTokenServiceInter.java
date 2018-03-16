package com.yyyu.user.service.inter;

import com.yyyu.user.pojo.MallUserToken;

import java.util.List;

/**
 * 功能：无状态访问sessionId操作相关
 *
 * @author yu
 * @date 2018/3/16.
 */
public interface UserTokenServiceInter {

    /**
     * 判断当前的token信息是否合法
     *
     * @param userToken
     * @return
     */
    MallUserToken isSessionLegal(MallUserToken userToken);

    /**
     * 根据sessionId得到对应的token
     *
     * @param sessionId
     * @return
     */
    MallUserToken selectUserTokenBySessionId(String sessionId);

    /**
     * 根据userId得到对应的token
     *
     * @param userId
     * @return
     */
    MallUserToken selectUserTokenByUserId(Long userId);

    /**
     * 根据userId删除token
     *
     * @param userId
     */
    void deleteTokenByUserId(Long userId);

    /**
     * 生成token
     *
     * @param userToken
     */
    void addUserToken(MallUserToken userToken);

}
