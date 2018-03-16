package com.yyyu.user.pojo.bean;

import java.io.Serializable;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/10/30.
 */
public class ZTreeNode {

    private Serializable id;
    private Serializable pId;
    private String name;
    private String page;
    private boolean checked;
    private boolean open;
    private boolean isParent;

    public ZTreeNode() {

    }

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public Serializable getpId() {
        return pId;
    }

    public void setpId(Serializable pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }
}
