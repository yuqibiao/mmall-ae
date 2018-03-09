package com.yyyu.user.service.inter;

import com.yyyu.user.pojo.MallPermission;
import com.yyyu.user.pojo.bean.ZTreeNode;
import com.yyyu.user.pojo.vo.UpdateRolePermissionVo;

import java.util.List;

/**
 * 功能：角色权限操作接口
 *
 * @author yu
 * @date 2018/1/29.
 */
public interface RolePermissionServiceInter {

    /**
     * 根据roleId得到所有权限
     *
     * @param roleId 所有的权限信息
     * @return
     */
    List<ZTreeNode> selectAllPermissionByRoleId(Integer roleId);

    /**
     * 根据roleId查询角色对应的权限
     *
     * @param roleId
     * @return
     */
    List<MallPermission> selectPermissionByRoleId(Integer roleId);

    /**
     * 删除角色对应的权限
     *
     * @param roleId
     * @param permissionId
     */
    void deleteRolePermission(Integer roleId , Integer permissionId);

    /**
     * 添加角色权限
     *
     * @param roleId
     * @param permissionId
     */
    void addRolePermission(Integer roleId , Integer permissionId);

    /**
     * 更新角色的权限
     *
     * @param updateRolePermissionVo
     */
    void updateRolePermission(UpdateRolePermissionVo updateRolePermissionVo);
}
