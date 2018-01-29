package com.yyyu.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyyu.user.dao.MallPermissionMapper;
import com.yyyu.user.pojo.MallPermission;
import com.yyyu.user.pojo.MallPermissionExample;
import com.yyyu.user.service.inter.PermissionServiceInter;
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
public class PermissionServiceImpl implements PermissionServiceInter{

    @Autowired
    private MallPermissionMapper permissionMapper;

    @Override
    public PageInfo<MallPermission> selectPermissionByPage(Integer start, Integer size) {

        PageHelper.offsetPage(start , size);
        MallPermissionExample permissionExample = new MallPermissionExample();
        permissionExample.setDistinct(false);
        List<MallPermission> mallPermissions = permissionMapper.selectByExample(permissionExample);

        return new PageInfo<>(mallPermissions);
    }

    @Override
    public MallPermission selectPermissionByPermissionId(Integer permissionId) {
        return permissionMapper.selectByPrimaryKey(permissionId);
    }

    @Override
    public void updatePermission(MallPermission permission) {
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public void reallyDeletePermissionByPermissionId(Integer permissionId) {
        permissionMapper.deleteByPrimaryKey(permissionId);
    }

    @Override
    public void deletePermissionByPermissionId(Integer permissionId) {
        MallPermission permission = new MallPermission();
        permission.setPermissionId(permissionId);
        short status = 2;
        permission.setStatus(status);
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public void addPermission(MallPermission permission) {
        permissionMapper.insertSelective(permission);
    }

}
