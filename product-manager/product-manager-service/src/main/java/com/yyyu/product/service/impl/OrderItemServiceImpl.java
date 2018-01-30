package com.yyyu.product.service.impl;

import com.github.pagehelper.PageInfo;
import com.yyyu.product.dao.MallOrderItemMapper;
import com.yyyu.product.pojo.MallOrder;
import com.yyyu.product.pojo.MallOrderItem;
import com.yyyu.product.pojo.MallOrderItemExample;
import com.yyyu.product.service.inter.OrderItemServiceInter;
import com.yyyu.product.service.inter.OrderServiceInter;
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
public class OrderItemServiceImpl implements OrderItemServiceInter{

    @Autowired
    private MallOrderItemMapper orderItemMapper;


    @Override
    public List<MallOrderItem> selectOrderItemByOrderId(Long orderId) {

        MallOrderItemExample example = new MallOrderItemExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<MallOrderItem> mallOrderItems = orderItemMapper.selectByExample(example);

        return mallOrderItems;
    }

    @Override
    public void updateOrderItem(MallOrderItem orderItem) {
        orderItemMapper.updateByPrimaryKeySelective(orderItem);
    }

    @Override
    public void deleteOrderItemById(Long orderItemId) {
        orderItemMapper.deleteByPrimaryKey(orderItemId);
    }

    @Override
    public void addOrderItem(MallOrderItem item) {
        orderItemMapper.insertSelective(item);
    }
}
