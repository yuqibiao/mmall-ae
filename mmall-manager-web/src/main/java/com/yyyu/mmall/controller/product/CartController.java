package com.yyyu.mmall.controller.product;

import com.yyyu.mmall.controller.BaseController;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.product.pojo.MallCart;
import com.yyyu.product.pojo.bean.CartProduct;
import com.yyyu.product.pojo.bean.CartProductInfo;
import com.yyyu.product.pojo.vo.CartDeleteVo;
import com.yyyu.product.pojo.vo.CartVo;
import com.yyyu.product.service.inter.CartServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能：购物车相关api
 *
 * TODO  添加登录成功或token验证
 * @author yu
 * @date 2018/1/30.
 */
@Api(value = "cart" , description = "购物车相关操作",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api/cart")
@Controller
public class CartController extends BaseController{

    @Autowired
    private CartServiceInter cartService;

    @ApiOperation(value = "查看用户的购物车" ,notes = "根据用户Id查询所有购物车中的商品信息",httpMethod = "GET")
    @RequestMapping(value = "v1/users/{userId}/products/all" , method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils uncheckedProductByProductId(@ApiParam(value = "用户id" , required = true)@PathVariable  Long userId) {

        CartProductInfo cartProductInfo;
        try {
            cartProductInfo = cartService.getCartProductInfo(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(cartProductInfo);
    }

    @ApiOperation(value = "取消勾选某一商品" ,notes = "取消单个商品的勾选",httpMethod = "PATCH")
    @RequestMapping(value = "v1/unchecked/users/{userId}/products/{productId}" , method = RequestMethod.PATCH)
    @ResponseBody
    public ResultUtils uncheckedProductByProductId(@ApiParam(value = "用户id" , required = true) @PathVariable  Long userId ,
                                                   @ApiParam(value = "商品d" , required = true)@PathVariable Long productId){

        CartProductInfo cartProductInfo;
        try {
            cartProductInfo = cartService.updateUncheckedProductByProductId(userId , productId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(cartProductInfo);
    }


    @ApiOperation(value = "勾选某一商品" ,notes = "单个商品的勾选",httpMethod = "PATCH")
    @RequestMapping(value = "v1/checked/users/{userId}/products/{productId}" , method = RequestMethod.PATCH)
    @ResponseBody
    public ResultUtils checkedProductByProductId(@ApiParam(value = "用户id" , required = true) @PathVariable  Long userId ,
                                                 @ApiParam(value = "商品d" , required = true)@PathVariable Long productId){

        CartProductInfo cartProductInfo;
        try {
            cartProductInfo = cartService.updateCheckedProductByProductId(userId , productId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(cartProductInfo);
    }

    @ApiOperation(value = "商品全部勾选" ,notes = "勾选上全部的商品",httpMethod = "PATCH")
    @RequestMapping(value = "v1/checked_all/users/{userId}" , method = RequestMethod.PATCH)
    @ResponseBody
    public ResultUtils checkedAll(@ApiParam(value = "用户id" , required = true) @PathVariable  Long userId){

        CartProductInfo cartProductInfo;
        try {
            cartProductInfo = cartService.updateCheckedAll(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(cartProductInfo);
    }

    @ApiOperation(value = "商品全部不勾选" ,notes = "取消全部商品的勾选",httpMethod = "PATCH")
    @RequestMapping(value = "v1/unchecked_all/users/{userId}" , method = RequestMethod.PATCH)
    @ResponseBody
    public ResultUtils uncheckedAll(@ApiParam(value = "用户id" , required = true) @PathVariable  Long userId){

        CartProductInfo cartProductInfo;
        try {
            cartProductInfo = cartService.updateUncheckedAll(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(cartProductInfo);
    }

    @ApiOperation(value = "删除购物车" ,notes = "删除某一用户对应的购物车中的商品",httpMethod = "DELETE")
    @RequestMapping(value = "v1/carts" , method = RequestMethod.DELETE)
    @ResponseBody
    public ResultUtils deleteCart(@RequestBody CartDeleteVo cartDeleteVo){

        CartProductInfo cartProductInfo;
        try {
            cartProductInfo = cartService.deleteCart(cartDeleteVo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("删除成功" , cartProductInfo);
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
