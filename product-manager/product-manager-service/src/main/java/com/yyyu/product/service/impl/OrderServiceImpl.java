package com.yyyu.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyyu.product.dao.MallOrderMapper;
import com.yyyu.product.pojo.MallOrder;
import com.yyyu.product.pojo.MallOrderExample;
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
public class OrderServiceImpl implements OrderServiceInter{

    @Autowired
    private MallOrderMapper orderMapper;

    @Override
    public PageInfo<MallOrder> selectOrderByPage(Integer start, Integer size) {

        PageHelper.offsetPage(start , size);
        MallOrderExample example = new MallOrderExample();
        List<MallOrder> mallOrders = orderMapper.selectByExample(example);

        return new PageInfo<>(mallOrders);
    }

    @Override
    public List<MallOrder> selectAllOrderByUserId(Long userId) {

        MallOrderExample example = new MallOrderExample();
        example.createCriteria().andUserIdEqualTo(userId);

        return orderMapper.selectByExample(example);
    }

    @Override
    public void updateOrder(MallOrder order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public void deleteOrderByOrderId(Long orderId) {
        orderMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public void addOrder(MallOrder order) {
        orderMapper.insertSelective(order);
    }
}
