package com.yyyu.user.pojo.vo;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/3/5.
 */
public class UpdateRolePermissionVo {

    private Integer roleId;
    private List<Integer > permissionIdList;

    public UpdateRolePermissionVo() {
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getPermissionIdList() {
        return permissionIdList;
    }

    public void setPermissionIdList(List<Integer> permissionIdList) {
        this.permissionIdList = permissionIdList;
    }
}
