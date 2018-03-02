package com.yyyu.user.pojo.vo;

import java.util.List;

/**
 * 功能：批量删除
 *
 * @author yu
 * @date 2018/2/27.
 */
public class BulkDeletionVo {

    private List<String> deleteIdList;

    public List<String> getDeleteIdList() {
        return deleteIdList;
    }

    public void setDeleteIdList(List<String> deleteIdList) {
        this.deleteIdList = deleteIdList;
    }
}
