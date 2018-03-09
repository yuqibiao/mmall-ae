package com.yyyu.user.service.impl;

import com.yyyu.user.dao.MallPermissionMapper;
import com.yyyu.user.dao.MallRolePermissionMapper;
import com.yyyu.user.pojo.MallPermission;
import com.yyyu.user.pojo.MallPermissionExample;
import com.yyyu.user.pojo.MallRolePermissionKey;
import com.yyyu.user.pojo.bean.ZTreeNode;
import com.yyyu.user.pojo.vo.UpdateRolePermissionVo;
import com.yyyu.user.service.inter.RolePermissionServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/29.
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionServiceInter {

    @Autowired
    private MallRolePermissionMapper rolePermissionMapper;

    @Autowired
    private MallPermissionMapper mallPermissionMapper;

    @Override
    public List<ZTreeNode> selectAllPermissionByRoleId(Integer roleId) {

        //查看该用户的权限
        List<MallPermission> rolePermissionList = rolePermissionMapper.selectPermissionByRoleId(roleId);

        MallPermissionExample permissionExample = new MallPermissionExample();
        List<MallPermission> allPermissionList = mallPermissionMapper.selectByExample(permissionExample);

        List<Integer> rolePermissionIdList = new ArrayList<>();
        for (MallPermission rolePermission : rolePermissionList) {
            Integer permissionId = rolePermission.getPermissionId();
            rolePermissionIdList.add(permissionId);
        }
        List<ZTreeNode> zTreeNodeList = new ArrayList<>();
        for (MallPermission allPermission : allPermissionList) {
            ZTreeNode zTreeNode = new ZTreeNode();
            Integer permissionId = allPermission.getPermissionId();
            zTreeNode.setId(permissionId);
            zTreeNode.setpId(allPermission.getParentId());
            zTreeNode.setName(allPermission.getName());
            zTreeNode.setOpen(true);
            zTreeNode.setPage(allPermission.getDescription());
            Integer parentId = allPermission.getParentId();
            if (parentId != null && parentId != 1) {
                zTreeNode.setParent(true);
            }else{
                zTreeNode.setParent(false);
            }
            //判断该用户是否有该权限
            if (rolePermissionIdList.contains(permissionId)){
                zTreeNode.setChecked(true);
            }else{
                zTreeNode.setChecked(false);
            }
            zTreeNodeList.add(zTreeNode);
        }

        return zTreeNodeList;
    }

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

    @Override
    public void updateRolePermission(UpdateRolePermissionVo updateRolePermissionVo) {
        //删除原来的
        rolePermissionMapper.deleteByRoleId(updateRolePermissionVo.getRoleId());
        //添加新的
        rolePermissionMapper.addRolePermission(updateRolePermissionVo);
    }
}
