package com.yyyu.user.pojo.bean;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/3/8.
 */
public class UserRoleIdListBean {

    private long userId;
    private List<Integer> roleIdList;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
