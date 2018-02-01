package com.yyyu.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyyu.mmall.uitls.math.BigDecimalUtil;
import com.yyyu.product.dao.MallOrderItemMapper;
import com.yyyu.product.dao.MallOrderMapper;
import com.yyyu.product.pojo.MallOrder;
import com.yyyu.product.pojo.MallOrderExample;
import com.yyyu.product.pojo.MallOrderItem;
import com.yyyu.product.pojo.MallProduct;
import com.yyyu.product.pojo.vo.CartVo;
import com.yyyu.product.pojo.vo.OrderVo;
import com.yyyu.product.service.inter.OrderServiceInter;
import com.yyyu.product.service.inter.ProductServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/30.
 */
@Service
public class OrderServiceImpl implements OrderServiceInter {

    @Autowired
    private MallOrderMapper orderMapper;
    @Autowired
    private MallOrderItemMapper mallOrderItemMapper;
    @Autowired
    private ProductServiceInter productService;

    @Override
    public PageInfo<MallOrder> selectOrderByPage(Integer start, Integer size) {

        PageHelper.offsetPage(start, size);
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
    public void addOrder(OrderVo orderVo) {

        List<CartVo> cartList = orderVo.getCartList();
        List<Long> productIdList = new ArrayList<>();
        for (CartVo cartVo: cartList) {
            productIdList.add(cartVo.getProductId());
        }
        Map<Long, MallProduct> priceMap = productService.selectProductsMap(productIdList);
        //计算总价格
        BigDecimal totalPrice = new BigDecimal(0);
        for (CartVo cartVo: cartList) {
            Long productId = cartVo.getProductId();
            Integer quantity = cartVo.getQuantity();
            BigDecimal price = priceMap.get(productId).getPrice();
            BigDecimal classGoodsPrice = BigDecimalUtil.mul(price.doubleValue(), quantity);
            totalPrice = totalPrice.add(classGoodsPrice);
        }
        Long autoInc  = orderMapper.getAutoIncrement();//orderId
        MallOrder order = new MallOrder();
        order.setOrderNo(generateOrderNo()); //设置订单号
        order.setUserId(orderVo.getUserId());
        order.setLogisticsId(orderVo.getLogisticsId());
        order.setPaymentType(orderVo.getPaymentType());
        order.setPayment(totalPrice);
        order.setPostage(getPostage());
        //1.生成主单
        Long orderId = orderMapper.insertSelective(order);
        //2.生成子单 TODO
        for (CartVo cartVo: cartList) {
            Long productId = cartVo.getProductId();
            Integer quantity = cartVo.getQuantity();
            MallProduct product = priceMap.get(productId);
            MallOrderItem orderItem = new MallOrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(productId);
            orderItem.setQuantity(quantity);
            orderItem.setCurrentUnitPrice(product.getPrice());
            orderItem.setProductName(product.getName());
            orderItem.setProductImage(product.getMainImage());
            orderItem.setTotalPrice(BigDecimalUtil.mul(product.getPrice().doubleValue() , quantity));
            mallOrderItemMapper.insertSelective(orderItem);
        }
    }


    /**
     * 通过后台查询运费
     *
     * @return
     */
    private BigDecimal getPostage() {

        return new BigDecimal(Double.toString(10));
    }

    /**
     * 生成订单号
     * <p>
     * 年（两位）+月+日+orderId后六位（不足补齐）
     *
     * @return
     */
    private String generateOrderNo() {

        String orderNo = getOrderPrefix()+getOrderSuffix();

        return orderNo;
    }

    private  String getOrderPrefix(){

        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");

        return simpleDateFormat.format(currentDate);
    }

    private  String getOrderSuffix() {
        StringBuilder sb = new StringBuilder();
        Long autoInc  = orderMapper.getAutoIncrement();
        String autoIncStr = autoInc.toString();
        int length = autoIncStr.length();
        if (length < 6) {//没有六位
            for (int i = 6; i > 0; i = i - length) {
                sb.append("0");
            }
            sb.append(autoIncStr);
        } else {
            sb.append(autoIncStr.substring(length - 6, length));
        }

        return sb.toString();
    }

}
