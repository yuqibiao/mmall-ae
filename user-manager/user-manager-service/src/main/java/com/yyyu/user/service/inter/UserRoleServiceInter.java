package com.yyyu.user.service.inter;

import com.yyyu.user.pojo.MallRole;
import com.yyyu.user.pojo.bean.RoleChecked;

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

    /**
     * 批量添加用户权限
     *
     * @param userId
     * @param roleIdList
     */
    void addUserRoleList(Long userId, List<Integer> roleIdList);

    /***
     * 得到所有的权限信息 和用户对应的权限
     *
     * @param userId
     * @return 用户有的权限 checked=true
     */
    List<RoleChecked> selectAllRoleByUserId(Long userId);
}
