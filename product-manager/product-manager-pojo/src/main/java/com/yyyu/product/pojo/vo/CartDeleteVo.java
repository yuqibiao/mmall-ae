package com.yyyu.product.pojo.vo;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/30.
 */
public class CartDeleteVo {

    private Long userId;

    private List<Long> productIdList;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<Long> productIdList) {
        this.productIdList = productIdList;
    }
}
