package com.yyyu.user.service.impl;

import com.yyyu.user.dao.MallUserTokenMapper;
import com.yyyu.user.pojo.MallUserToken;
import com.yyyu.user.pojo.MallUserTokenExample;
import com.yyyu.user.service.inter.UserTokenServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/3/16.
 */
@Service
public class UserTokenServiceImpl implements UserTokenServiceInter{

    @Autowired
    private MallUserTokenMapper userTokenMapper;

    @Override
    public MallUserToken isSessionLegal(MallUserToken userToken) {
        MallUserToken savedUserToken = selectUserTokenBySessionId(userToken.getSessionId());
        Long currentDateTime = new Date(System.currentTimeMillis()).getTime();
        Long createTime = savedUserToken.getCreateTime().getTime();
        if (userToken.getVisitIp().equals(savedUserToken.getVisitIp()) &&
                userToken.getSessionId().equals(savedUserToken.getSessionId())&&
                currentDateTime-createTime<7*60*60*24*1000){
            MallUserTokenExample userTokenExample = new MallUserTokenExample();
            userTokenExample.createCriteria().andSessionIdEqualTo(savedUserToken.getSessionId());
            MallUserToken updateToken = new MallUserToken();
            updateToken.setCreateTime(new Date(currentDateTime));
            userTokenMapper.updateByExampleSelective(updateToken , userTokenExample);
            return savedUserToken;
        }else{

        }
        return null;
    }

    @Override
    public MallUserToken selectUserTokenBySessionId(String sessionId) {
        MallUserTokenExample userTokenExample = new MallUserTokenExample();
        userTokenExample.createCriteria().andSessionIdEqualTo(sessionId);
        List<MallUserToken> mallUserTokens = userTokenMapper.selectByExample(userTokenExample);
        return (mallUserTokens==null||mallUserTokens.size()==0)?null:mallUserTokens.get(0);
    }

    @Override
    public MallUserToken selectUserTokenByUserId(Long userId) {

        MallUserTokenExample userTokenExample = new MallUserTokenExample();
        userTokenExample.setOrderByClause("create_time desc");
        userTokenExample.createCriteria().andUserIdEqualTo(userId);
        List<MallUserToken> mallUserTokens = userTokenMapper.selectByExample(userTokenExample);
        return  (mallUserTokens==null||mallUserTokens.size()==0)?null:mallUserTokens.get(0);
    }

    @Override
    public void deleteTokenByUserId(Long userId) {

        MallUserTokenExample userTokenExample = new MallUserTokenExample();
        userTokenExample.createCriteria().andUserIdEqualTo(userId);
        userTokenMapper.deleteByExample(userTokenExample);

    }

    @Override
    public void addUserToken(MallUserToken userToken) {

        userTokenMapper.insert(userToken);

    }
}
