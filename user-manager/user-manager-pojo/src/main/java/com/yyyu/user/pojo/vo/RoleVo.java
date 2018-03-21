package com.yyyu.user.pojo.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/29.
 */
public class RoleVo {

    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "角色别名")
    private String alias;

    @ApiModelProperty(value = "编码" )
    private Short code;

    @ApiModelProperty(value = "角色的描述信息" )
    private String description;

    @ApiModelProperty(value = "角色状态" )
    private Short status;

    public RoleVo() {
    }

    public RoleVo(String name, Short code, String description, Short status) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
