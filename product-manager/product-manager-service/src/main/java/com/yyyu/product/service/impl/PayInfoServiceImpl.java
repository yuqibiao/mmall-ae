package com.yyyu.product.service.impl;

import com.yyyu.product.dao.MallPayInfoMapper;
import com.yyyu.product.pojo.MallPayInfo;
import com.yyyu.product.pojo.MallPayInfoExample;
import com.yyyu.product.service.inter.PayInfoServiceInter;
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
public class PayInfoServiceImpl implements PayInfoServiceInter{

    @Autowired
    private MallPayInfoMapper payInfoMapper;

    @Override
    public List<MallPayInfo> selectPayInfoByOrderId(Long orderId) {

        MallPayInfoExample example = new MallPayInfoExample();
        example.createCriteria().andOrderIdEqualTo(orderId);

        return payInfoMapper.selectByExample(example);
    }

    @Override
    public void updatePayInfo(MallPayInfo payInfo) {
        payInfoMapper.updateByPrimaryKey(payInfo);
    }

    @Override
    public void deletePayInfoByPayId(Long payId) {
        payInfoMapper.deleteByPrimaryKey(payId);
    }

    @Override
    public void addPayInfo(MallPayInfo payInfo) {
        payInfoMapper.insertSelective(payInfo);
    }
}
