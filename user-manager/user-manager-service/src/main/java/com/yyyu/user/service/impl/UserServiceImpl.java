package com.yyyu.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyyu.user.dao.MallUserMapper;
import com.yyyu.user.pojo.MallUser;
import com.yyyu.user.pojo.MallUserExample;
import com.yyyu.user.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/24.
 */
@Service
public class UserServiceImpl implements UserServiceInter{

    @Autowired
    private MallUserMapper userMapper;

    @Override
    public void addUser(MallUser user) {
        userMapper.insert(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public void updateUser(MallUser user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public MallUser selectByUserId(Long userId) {

        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public PageInfo<MallUser> selectUserByPage(Integer from, Integer to) {
        if (from<=0){
            from=1;
        }
        MallUserExample mallUserExample = new MallUserExample();
        mallUserExample.setDistinct(false);
        PageHelper.offsetPage(from , to-from+1);
        List<MallUser> mallUsers = userMapper.selectByExample(mallUserExample);
        return new PageInfo<>(mallUsers);
    }
}
