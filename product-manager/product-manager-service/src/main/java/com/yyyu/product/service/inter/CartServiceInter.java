package com.yyyu.product.service.inter;

import com.yyyu.product.pojo.MallCart;
import com.yyyu.product.pojo.bean.CartProduct;
import com.yyyu.product.pojo.vo.CartDeleteVo;

import java.util.List;

/**
 * 功能：购物车相关接口
 *
 * @author yu
 * @date 2018/1/29.
 */
public interface CartServiceInter {


    /**
     * 刷新购物车
     *
     * @param mallCartList
     * @return
     */
    List<CartProduct> refreshCart(List<MallCart> mallCartList);

    /**
     * 查询某一用户对应的所有购物车中的商品
     *
     * @param userId
     * @return
     */
    List<CartProduct> selectAllCartProductByUserId(Long userId);

    /**
     * 更新购物车信息
     *
     * @param cart
     */
    void updateCart(MallCart cart);

    /**
     * 批量删除购物车中商品
     *
     * @param cartDeleteVo
     */
    void deleteCart(CartDeleteVo cartDeleteVo);

    /**
     * 删除购物车
     *
     * @param cartId
     */
    void deleteCartByCartId(Long cartId);

    /**
     * 添加购物车
     *
     * @param cart
     * @return 返回userId对应的商品信息
     */
    void addCart(MallCart cart);

}
