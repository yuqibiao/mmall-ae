package com.yyyu.user.pojo.bean;

import com.yyyu.user.pojo.MallRole;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/3/9.
 */
public class RoleChecked extends MallRole{

    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
