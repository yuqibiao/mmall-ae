package com.yyyu.user.dao;

import com.yyyu.user.pojo.MallPermission;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/2/2.
 */
public interface MallUserRolePermissionMapper {

    List<MallPermission> selectMenusByUserId(Long userId);

}
