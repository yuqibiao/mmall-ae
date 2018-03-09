package com.yyyu.user.service.inter;

import com.github.pagehelper.PageInfo;
import com.yyyu.user.pojo.MallRole;
import com.yyyu.user.pojo.MallRoleExample;
import com.yyyu.user.pojo.MallUserExample;

import java.util.List;

/**
 * 功能：角色相关操作接口
 *
 * @author yu
 * @date 2018/1/29.
 */
public interface RoleServiceInter {


    /**
     * 分页查询角色
     *
     * @param start
     * @param size
     * @return
     */
    PageInfo<MallRole> selectRoleByPage(Integer start , Integer size , MallRoleExample roleExample);

    /**
     * 根据roleId查询角色
     *
     * @param roleId
     * @return
     */
    MallRole selectRoleByRoleId(Integer roleId);

    /**
     * 修改角色
     *
     * @param role
     */
    void updateRole(MallRole role);

    /**
     * 删除角色
     *
     * 真正意义上的删除
     * @param roleId
     */
    void reallyDeleteRole(Integer roleId);

    /**
     * 删除角色
     *
     * 只是status状态设置为2
     * @param roleId
     */
    void deleteRole(Integer roleId);

    /**
     * 增加角色
     *
     * @param role
     */
    void addRole(MallRole role);

    /**
     * 批量删除角色
     *
     * @param roleIdList
     */
    void reallyDeleteRoleByIdList(List<Integer> roleIdList);

    /**
     * 得到所有的角色
     *
     * @return
     */
    List<MallRole> selectAllRole();
}
