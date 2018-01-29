package com.yyyu.user.service.inter;

import com.github.pagehelper.PageInfo;
import com.yyyu.user.pojo.MallPermission;

import java.util.List;

/**
 * 功能：权限操作相关的接口
 *
 * @author yu
 * @date 2018/1/29.
 */
public interface PermissionServiceInter {

    /**
     * 分页查询权限信息
     *
     * @param start
     * @param size
     * @return
     */
    PageInfo<MallPermission> selectPermissionByPage(Integer start , Integer size);

    /**
     * 根据permissionId查询权限信息
     *
     * @param permissionId
     * @return
     */
    MallPermission selectPermissionByPermissionId(Integer permissionId);

    /**
     * 修改权限信息
     *
     * @param permission
     */
    void updatePermission(MallPermission permission);

    /**
     * 根据Id删除权限
     *
     * 真正意义上的删除
     * @param permissionId
     */
    void reallyDeletePermissionByPermissionId(Integer permissionId);

    /**
     * 根据Id删除权限
     *
     * status设置为2
     * @param permissionId
     */
    void deletePermissionByPermissionId(Integer permissionId);

    /**
     * 添加权限
     *
     * @param permission
     */
    void addPermission(MallPermission permission);

}
