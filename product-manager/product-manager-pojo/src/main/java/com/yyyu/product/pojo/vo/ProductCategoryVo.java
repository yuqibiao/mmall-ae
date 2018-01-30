package com.yyyu.product.pojo.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/29.
 */
public class ProductCategoryVo {

    @ApiModelProperty("父商品类别Id")
    private Long parentId;

    @ApiModelProperty("商品类别名称")
    private String name;

    @ApiModelProperty("状态")
    private Short status;

    @ApiModelProperty("编码")
    private Short code;

    public ProductCategoryVo() {

    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }
}
