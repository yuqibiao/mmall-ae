package com.yyyu.product.service.inter;

import com.github.pagehelper.PageInfo;
import com.yyyu.product.pojo.MallOrder;
import com.yyyu.product.pojo.vo.OrderVo;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * 功能：订单操作接口
 *
 * @author yu
 * @date 2018/1/30.
 */
public interface OrderServiceInter {

    /**
     * 分页获取订单信息
     *
     * @param start
     * @param size
     * @return
     */
    PageInfo<MallOrder> selectOrderByPage(Integer start , Integer size);

    /**
     * 得到某一用户的所有订单
     *
     * @param userId
     * @return
     */
    List<MallOrder> selectAllOrderByUserId(Long userId);

    /**
     * 更新order信息
     *
     * @param order
     */
    void updateOrder(MallOrder order);

    /**
     * 删除订单
     *
     * @param orderId
     */
    void deleteOrderByOrderId(Long orderId);

    /**
     * 添加订单
     *
     * @param orderVo
     */
    void addOrder(OrderVo orderVo);

}
