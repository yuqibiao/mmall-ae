package com.yyyu.user.service.impl;

import com.yyyu.user.dao.MallRoleMapper;
import com.yyyu.user.dao.MallUserRoleMapper;
import com.yyyu.user.pojo.MallRole;
import com.yyyu.user.pojo.MallRoleExample;
import com.yyyu.user.pojo.MallUserRoleExample;
import com.yyyu.user.pojo.MallUserRoleKey;
import com.yyyu.user.pojo.bean.RoleChecked;
import com.yyyu.user.pojo.bean.UserRoleIdListBean;
import com.yyyu.user.service.inter.UserRoleServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private MallRoleMapper roleMapper;

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

    @Override
    public void addUserRoleList(Long userId, List<Integer> roleIdList) {
        UserRoleIdListBean userRoleIdListBean = new UserRoleIdListBean();
        userRoleIdListBean.setUserId(userId);
        userRoleIdListBean.setRoleIdList(roleIdList);
        //先删除原来的
        MallUserRoleExample mallUserRoleExample = new MallUserRoleExample();
        mallUserRoleExample.createCriteria().andUserIdEqualTo(userId);
        userRoleMapper.deleteByExample(mallUserRoleExample);
        //添加新的
        userRoleMapper.insertRoleList(userRoleIdListBean);
    }

    @Override
    public List<RoleChecked> selectAllRoleByUserId(Long userId) {

        List<RoleChecked> roleCheckedList = new ArrayList<>();

        //---得到全部权限
        List<MallRole> mallRoles = roleMapper.selectByExample(new MallRoleExample());

        //---得到用户拥有的权限
        MallUserRoleExample mallUserRoleExample = new MallUserRoleExample();
        mallUserRoleExample.createCriteria().andUserIdEqualTo(userId);
        List<MallUserRoleKey> mallUserRoleKeys = userRoleMapper.selectByExample(mallUserRoleExample);

        Set<Integer> checkedRoleIdList = new HashSet<>();
        for (MallUserRoleKey userRole: mallUserRoleKeys) {
            checkedRoleIdList.add(userRole.getRoleId());
        }

        for (MallRole role:mallRoles) {
            RoleChecked roleChecked = new RoleChecked();
            Integer roleId = role.getRoleId();
            roleChecked.setRoleId(roleId);
            roleChecked.setName(role.getName());
            roleChecked.setDescription(role.getDescription());
            roleChecked.setCode(role.getCode());
            roleChecked.setStatus(role.getStatus());
            roleChecked.setCreateTime(role.getCreateTime());
            roleChecked.setUpdateTime(role.getUpdateTime());
            if(checkedRoleIdList.contains(roleId)){
                roleChecked.setChecked(true);
            }else{
                roleChecked.setChecked(false);
            }
            roleCheckedList.add(roleChecked);
        }

        return roleCheckedList;
    }

}
