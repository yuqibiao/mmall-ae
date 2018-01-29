package com.yyyu.user.service.inter;

import com.yyyu.user.pojo.MallRole;

import java.util.List;

/**
 * 功能：User Role 关联操作接口
 *
 * @author yu
 * @date 2018/1/29.
 */
public interface UserRoleServiceInter {

    /**
     * 查询用户对应的所有角色
     *
     * @param userId
     * @return
     */
    List<MallRole> selectRoleByUserId(Long userId);

    /**
     * 删除用户的角色
     *
     * @param userId
     * @param roleId
     */
    void deleteUserRole(Long userId , Integer roleId);

    /**
     * 给用户添加角色
     *
     * @param userId
     * @param roleId
     */
    void addUserRole(Long userId , Integer roleId);
}
