package com.yyyu.product.service.inter;

import com.yyyu.product.pojo.MallPayInfo;

import java.util.List;

/**
 * 功能：支付信息操作接口
 *
 * @author yu
 * @date 2018/1/30.
 */
public interface PayInfoServiceInter {


    /**
     * 根据orderId查询对应的支付信息
     *
     * @param orderId
     * @return
     */
    List<MallPayInfo> selectPayInfoByOrderId(Long orderId);

    /**
     * 更新支付信息
     *
     * @param payInfo
     */
    void updatePayInfo(MallPayInfo payInfo);

    /**
     * 根据payId删除支付信息
     *
     * @param payId
     */
    void deletePayInfoByPayId(Long  payId);

    /**
     * 添加支付信息
     *
     * @param payInfo
     */
    void addPayInfo(MallPayInfo payInfo);

}
