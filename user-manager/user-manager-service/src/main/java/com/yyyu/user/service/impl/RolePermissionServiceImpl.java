package com.yyyu.user.service.impl;

import com.yyyu.user.dao.MallRolePermissionMapper;
import com.yyyu.user.pojo.MallPermission;
import com.yyyu.user.pojo.MallRolePermissionKey;
import com.yyyu.user.service.inter.RolePermissionServiceInter;
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
public class RolePermissionServiceImpl implements RolePermissionServiceInter{

    @Autowired
    private MallRolePermissionMapper rolePermissionMapper;

    @Override
    public List<MallPermission> selectPermissionByRoleId(Integer roleId) {

        return rolePermissionMapper.selectPermissionByRoleId(roleId);
    }

    @Override
    public void deleteRolePermission(Integer roleId, Integer permissionId) {
        MallRolePermissionKey rolePermissionKey = new MallRolePermissionKey();
        rolePermissionKey.setRoleId(roleId);
        rolePermissionKey.setPermissionId(permissionId);
        rolePermissionMapper.deleteByPrimaryKey(rolePermissionKey);
    }

    @Override
    public void addRolePermission(Integer roleId, Integer permissionId) {
        MallRolePermissionKey rolePermissionKey = new MallRolePermissionKey();
        rolePermissionKey.setRoleId(roleId);
        rolePermissionKey.setPermissionId(permissionId);
        rolePermissionMapper.insertSelective(rolePermissionKey);
    }
}
