package com.yyyu.user.service.inter;

import com.yyyu.user.pojo.bean.MenuBean;

import java.util.List;

/**
 * 功能：用户-角色-权限 关联操作 接口
 *
 * @author yu
 * @date 2018/2/2.
 */
public interface UserRolePermissionInter {

    /**
     * 根据用户Id查询该用户对应的权限信息
     *
     * @param userId
     * @return
     */
    List<MenuBean> selectMenusByUserId(Long userId);

}
