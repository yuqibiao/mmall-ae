package com.yyyu.product.pojo;

import java.util.Date;

public class MallProductCategoryWithParentName extends MallProductCategory{

    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}