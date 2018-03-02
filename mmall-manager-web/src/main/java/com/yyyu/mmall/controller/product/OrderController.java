package com.yyyu.mmall.controller.product;

import com.yyyu.mmall.controller.BaseController;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.product.pojo.vo.OrderVo;
import com.yyyu.product.service.inter.OrderServiceInter;
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
 * 功能：订单操作api
 *
 * @author yu
 * @date 2018/1/31.
 */
@Api(value = "order" , description = "订单相关操作",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api/order")
@Controller
public class OrderController extends BaseController{

    @Autowired
    private OrderServiceInter orderService;

    @ApiOperation(value = "添加订单" , httpMethod = "POST")
    @RequestMapping(value = "v1/orders" , method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils addOrder(@RequestBody OrderVo orderVo){

        try {
            orderService.addOrder(orderVo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("添加成功");
    }


}
