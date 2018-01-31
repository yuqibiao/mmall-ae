package com.yyyu.product.pojo.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/31.
 */
public class CartProductInfo {

    private List<CartProduct> cartProductList;
    //总价格
    private BigDecimal totalPerice;
    //是否全选了（便于前台的显示）
    private boolean CheckedAll;

    public List<CartProduct> getCartProductList() {
        return cartProductList;
    }

    public void setCartProductList(List<CartProduct> cartProductList) {
        this.cartProductList = cartProductList;
    }

    public BigDecimal getTotalPerice() {
        return totalPerice;
    }

    public void setTotalPerice(BigDecimal totalPerice) {
        this.totalPerice = totalPerice;
    }

    public boolean isCheckedAll() {
        return CheckedAll;
    }

    public void setCheckedAll(boolean checkedAll) {
        CheckedAll = checkedAll;
    }
}
