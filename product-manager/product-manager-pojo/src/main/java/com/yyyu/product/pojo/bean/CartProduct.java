package com.yyyu.product.pojo.bean;

import com.yyyu.product.pojo.MallProduct;

/**
 * 功能：购物车-商品信息
 *
 * @author yu
 * @date 2018/1/30.
 */
public class CartProduct extends MallProduct{

    private Long cartId;
    private Long userId;
    private Integer quantity;
    private Short checked;


    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Short getChecked() {
        return checked;
    }

    public void setChecked(Short checked) {
        this.checked = checked;
    }
}
