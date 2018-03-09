package com.yyyu.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyyu.user.dao.MallRoleMapper;
import com.yyyu.user.pojo.MallRole;
import com.yyyu.user.pojo.MallRoleExample;
import com.yyyu.user.pojo.MallUserExample;
import com.yyyu.user.service.inter.RoleServiceInter;
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
public class RoleServiceImpl implements RoleServiceInter{

    @Autowired
    private MallRoleMapper roleMapper;

    @Override
    public PageInfo<MallRole> selectRoleByPage(Integer start, Integer size , MallRoleExample roleExample) {
        roleExample.setDistinct(false);
        PageHelper.offsetPage(start , size);
        List<MallRole> mallRoles = roleMapper.selectByExample(roleExample);
        return new PageInfo<>(mallRoles);
    }

    @Override
    public MallRole selectRoleByRoleId(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public void updateRole(MallRole role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void reallyDeleteRole(Integer roleId) {
        roleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public void deleteRole(Integer roleId) {
        MallRole role = new MallRole();
        role.setRoleId(roleId);
        short status = 2;
        role.setStatus(status);
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void addRole(MallRole role) {
        roleMapper.insertSelective(role);
    }

    @Override
    public void reallyDeleteRoleByIdList(List<Integer> roleIdList) {
        roleMapper.reallyDeleteRoleByIdList(roleIdList);
    }

    @Override
    public List<MallRole> selectAllRole() {
        List<MallRole> mallRoles = roleMapper.selectByExample(new MallRoleExample());
        return mallRoles;
    }
}
