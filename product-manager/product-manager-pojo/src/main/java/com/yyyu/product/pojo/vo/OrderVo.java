package com.yyyu.product.pojo.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/31.
 */
public class OrderVo {


    private Long userId;

    private Long logisticsId;

    private Short paymentType;

    //传入要购买商品的信息、由后台生成价格、运费等信息
    private List<CartVo> cartList;

    public List<CartVo> getCartList() {
        return cartList;
    }

    public void setCartList(List<CartVo> cartList) {
        this.cartList = cartList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Long logisticsId) {
        this.logisticsId = logisticsId;
    }

    public Short getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Short paymentType) {
        this.paymentType = paymentType;
    }
}
