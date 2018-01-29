package com.yyyu.user.service.impl;

import com.yyyu.user.dao.MallUserRoleMapper;
import com.yyyu.user.pojo.MallRole;
import com.yyyu.user.pojo.MallUserRoleKey;
import com.yyyu.user.service.inter.UserRoleServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/29.
 */
@Service
public class UserRoleServiceImpl implements UserRoleServiceInter{

    @Autowired
    private MallUserRoleMapper userRoleMapper;

    @Override
    public List<MallRole> selectRoleByUserId(Long userId) {

        return userRoleMapper.selectRoleByUserId(userId);
    }

    @Override
    public void deleteUserRole(Long userId, Integer roleId) {
        MallUserRoleKey userRoleKey = new MallUserRoleKey();
        userRoleKey.setUserId(userId);
        userRoleKey.setRoleId(roleId);
        userRoleMapper.deleteByPrimaryKey(userRoleKey);
    }

    @Override
    public void addUserRole(Long userId, Integer roleId) {
        MallUserRoleKey userRoleKey = new MallUserRoleKey();
        userRoleKey.setUserId(userId);
        userRoleKey.setRoleId(roleId);
        userRoleMapper.insertSelective(userRoleKey);
    }

}
