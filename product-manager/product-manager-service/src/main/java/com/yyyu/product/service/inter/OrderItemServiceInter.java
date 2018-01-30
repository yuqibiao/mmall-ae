package com.yyyu.product.service.inter;

import com.yyyu.product.pojo.MallOrderItem;

import java.util.List;

/**
 * 功能：子订单操作接口
 *
 * @author yu
 * @date 2018/1/30.
 */
public interface OrderItemServiceInter {


    /**
     * 根据orderId查询对应的子订单
     *
     * @param orderId
     * @return
     */
    List<MallOrderItem> selectOrderItemByOrderId(Long orderId);

    /**
     * 更新子订单
     *
     * @param orderItem
     */
    void updateOrderItem(MallOrderItem orderItem);

    /**
     * 根据id删除子订单
     *
     * @param orderItemId
     */
    void deleteOrderItemById(Long orderItemId);

    /**
     * 添加子订单
     *
     * @param item
     */
    void addOrderItem(MallOrderItem item);

}
