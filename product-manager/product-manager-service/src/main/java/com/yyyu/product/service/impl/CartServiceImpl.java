package com.yyyu.product.service.impl;

import com.yyyu.product.dao.MallCartMapper;
import com.yyyu.product.pojo.MallCart;
import com.yyyu.product.pojo.MallCartExample;
import com.yyyu.product.pojo.bean.CartProduct;
import com.yyyu.product.pojo.vo.CartDeleteVo;
import com.yyyu.product.service.inter.CartServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/30.
 */
@Service
public class CartServiceImpl implements CartServiceInter{

    @Autowired
    private MallCartMapper cartMapper;

    @Override
    public List<CartProduct> refreshCart(List<MallCart> mallCartList) {
        //TODO
        return null;
    }

    @Override
    public List<CartProduct> selectAllCartProductByUserId(Long userId) {

        return cartMapper.selectAllCartProductByUserId(userId);
    }

    @Override
    public void updateCart(MallCart cart) {
        cartMapper.updateByPrimaryKeySelective(cart);
    }

    @Override
    public void deleteCart(CartDeleteVo cartDeleteVo) {

        Long userId = cartDeleteVo.getUserId();
        List<Long> productIdList = cartDeleteVo.getProductIdList();
        MallCartExample example = new MallCartExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andProductIdIn(productIdList);

        cartMapper.deleteByExample(example);
    }

    @Override
    public void deleteCartByCartId(Long cartId) {
        cartMapper.deleteByPrimaryKey(cartId);
    }

    @Override
    public void addCart(MallCart cart) {

        //判断该用户是否已经添加了该类商品
        Long userId = cart.getUserId();
        Long productId = cart.getProductId();
        List<MallCart> mallCarts = isAdded(userId, productId);
        if(mallCarts==null || mallCarts.size()==0){//没添加（insert）
            cartMapper.insertSelective(cart);
        }else{//添加了(更新quantity)
            MallCart cart1 = mallCarts.get(0);
            cart1.setQuantity(cart.getQuantity()+cart1.getQuantity());
            cartMapper.updateByPrimaryKeySelective(cart1);
        }

    }

    /**
     * 该用户是否已经添加了该类商品
     *
     * @Param
     * @return
     */
    private List<MallCart> isAdded(Long userId , Long productId){

        MallCartExample example = new MallCartExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andProductIdEqualTo(productId);
        List<MallCart> mallCarts = cartMapper.selectByExample(example);

        return mallCarts;
    }

}
