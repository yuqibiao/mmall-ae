package com.yyyu.mmall.controller.product;

import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.product.pojo.MallCart;
import com.yyyu.product.pojo.vo.CartDeleteVo;
import com.yyyu.product.pojo.vo.CartVo;
import com.yyyu.product.service.inter.CartServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能：购物车相关api
 *
 * @author yu
 * @date 2018/1/30.
 */
@Api(value = "cart" , description = "购物车相关操作",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api/cart")
@Controller
public class CartController {

    @Autowired
    private CartServiceInter cartService;


    @ApiOperation(value = "删除购物车" ,notes = "删除某一用户对应的购物车中的商品",httpMethod = "DELETE")
    @RequestMapping(value = "v1/carts" , method = RequestMethod.DELETE)
    @ResponseBody
    public ResultUtils deleteCart(@RequestBody CartDeleteVo cartDeleteVo){

        try {
            cartService.deleteCart(cartDeleteVo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("添加成功");
    }

    @ApiOperation(value = "添加商品到购物车" , httpMethod = "POST")
    @RequestMapping(value = "v1/carts" , method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils addCart(@RequestBody CartVo cartVo){

        try {
            cartService.addCart(setCart(cartVo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("添加成功");
    }

    private MallCart setCart(CartVo cartVo){

        MallCart cart = new MallCart();
        cart.setUserId(cartVo.getUserId());
        cart.setProductId(cartVo.getProductId());
        cart.setQuantity(cartVo.getQuantity());

        return cart;
    }


}
