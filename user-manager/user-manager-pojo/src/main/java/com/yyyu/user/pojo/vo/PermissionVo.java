package com.yyyu.user.pojo.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/29.
 */
public class PermissionVo {

    @ApiModelProperty(value = "父级Id", notes = "为null表示根节点")
    private Integer parentId;

    @ApiModelProperty(value = "权限名")
    private String name;

    @ApiModelProperty(value = "权限描述")
    private String description;

    @ApiModelProperty(value ="权限状态")
    private Short status;

    @ApiModelProperty(value ="权限编码")
    private Short code;

    @ApiModelProperty(value ="对应的action、href 链接地址")
    private String target;

    @ApiModelProperty(value ="类别" ,notes = "1-权限 2-菜单 默认1")
    private Short type;

    public PermissionVo() {
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}
