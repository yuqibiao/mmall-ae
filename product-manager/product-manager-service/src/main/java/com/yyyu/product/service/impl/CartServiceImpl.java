package com.yyyu.product.service.impl;

import com.yyyu.mmall.uitls.math.BigDecimalUtil;
import com.yyyu.product.dao.MallCartMapper;
import com.yyyu.product.pojo.MallCart;
import com.yyyu.product.pojo.MallCartExample;
import com.yyyu.product.pojo.bean.CartProduct;
import com.yyyu.product.pojo.bean.CartProductInfo;
import com.yyyu.product.pojo.vo.CartDeleteVo;
import com.yyyu.product.service.inter.CartServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public CartProductInfo updateUncheckedProductByProductId(Long userId, Long productId) {
        //1.设置checked=true
        MallCart mallCart = new MallCart();
        mallCart.setProductId(productId);
        short checked = 0;//false
        mallCart.setChecked(checked);
        MallCartExample example = new MallCartExample();
        example.createCriteria()
                .andProductIdEqualTo(productId)
                .andUserIdEqualTo(userId);
        cartMapper.updateByExampleSelective(mallCart,example);

        return getCartProductInfo(userId);
    }

    @Override
    public CartProductInfo updateCheckedProductByProductId(Long userId, Long productId) {

        //1.设置checked=true
        MallCart mallCart = new MallCart();
        mallCart.setProductId(productId);
        short checked = 1;//true
        mallCart.setChecked(checked);
        MallCartExample example = new MallCartExample();
        example.createCriteria()
                .andProductIdEqualTo(productId)
                .andUserIdEqualTo(userId);
        cartMapper.updateByExampleSelective(mallCart,example);

        return getCartProductInfo(userId);
    }

    @Override
    public CartProductInfo updateCheckedAll(Long userId) {

        //1.设置所有checked=true
        cartMapper.updateCheckedAllByUserId(userId);

        return getCartProductInfo(userId);
    }

    @Override
    public CartProductInfo updateUncheckedAll(Long userId) {

        //1.设置所有checked=false
        cartMapper.updateUncheckedAllByUserId(userId);

        return getCartProductInfo(userId);
    }

    @Override
    public CartProductInfo getCartProductInfo(Long userId) {
        CartProductInfo cartProductInfo = new CartProductInfo();
        //2.得到全部商品
        List<CartProduct> cartProducts = selectAllCartProductByUserId(userId);
        cartProductInfo.setCartProductList(cartProducts);
        //3.计算总价格
        BigDecimal totalPrice = new BigDecimal(Double.toString(0));
        cartProductInfo.setCheckedAll(true);
        for (CartProduct cartProduct :cartProducts) {
            if(cartProduct.getChecked()==1){//勾选
                Integer quantity = cartProduct.getQuantity();
                BigDecimal price = cartProduct.getPrice();
                BigDecimal classGoods = BigDecimalUtil.mul(price.doubleValue(), quantity);
                totalPrice = totalPrice.add(classGoods);
            }else{
                cartProductInfo.setCheckedAll(false);
            }
        }
        cartProductInfo.setTotalPerice(totalPrice);
        return cartProductInfo;
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
    public CartProductInfo deleteCart(CartDeleteVo cartDeleteVo) {

        Long userId = cartDeleteVo.getUserId();
        List<Long> productIdList = cartDeleteVo.getProductIdList();
        MallCartExample example = new MallCartExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andProductIdIn(productIdList);

        cartMapper.deleteByExample(example);

        //TODO
        return null;
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
            MallCart cart1 = new MallCart();
            cart1.setCartId(mallCarts.get(0).getCartId());
            cart1.setQuantity(cart.getQuantity()+mallCarts.get(0).getQuantity());
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
