package com.yyyu.product.pojo;

import java.util.Date;

public class MallPayInfo {
    private Long payId;

    private Long orderId;

    private Short payPlatform;

    private String platformNo;

    private String platformStatus;

    private Date createTime;

    private Date updateTime;

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Short getPayPlatform() {
        return payPlatform;
    }

    public void setPayPlatform(Short payPlatform) {
        this.payPlatform = payPlatform;
    }

    public String getPlatformNo() {
        return platformNo;
    }

    public void setPlatformNo(String platformNo) {
        this.platformNo = platformNo;
    }

    public String getPlatformStatus() {
        return platformStatus;
    }

    public void setPlatformStatus(String platformStatus) {
        this.platformStatus = platformStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}