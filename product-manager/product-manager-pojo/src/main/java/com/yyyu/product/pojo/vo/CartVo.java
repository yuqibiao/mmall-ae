package com.yyyu.product.pojo.vo;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/30.
 */
public class CartVo {

    private Long userId;

    private Long productId;

    private Integer quantity;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
