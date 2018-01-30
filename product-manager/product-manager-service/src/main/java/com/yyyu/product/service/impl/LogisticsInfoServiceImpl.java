package com.yyyu.product.service.impl;

import com.yyyu.product.dao.MallLogisticsInfoMapper;
import com.yyyu.product.pojo.MallLogisticsInfo;
import com.yyyu.product.pojo.MallLogisticsInfoExample;
import com.yyyu.product.service.inter.LogisticsInfoServiceInter;
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
public class LogisticsInfoServiceImpl implements LogisticsInfoServiceInter{

    @Autowired
    private MallLogisticsInfoMapper logisticsInfoMapper;

    @Override
    public List<MallLogisticsInfo> selectAllLogisticsInfoByUserId(Long userId) {

        MallLogisticsInfoExample example = new MallLogisticsInfoExample();
        example.createCriteria().andUserIdEqualTo(userId);

        return logisticsInfoMapper.selectByExample(example);
    }

    @Override
    public void updateLogisticsInfo(MallLogisticsInfo logisticsInfo) {
        logisticsInfoMapper.updateByPrimaryKey(logisticsInfo);
    }

    @Override
    public void deleteLogisticsInfoByLogisticsId(Long logisticsId) {
        logisticsInfoMapper.deleteByPrimaryKey(logisticsId);
    }

    @Override
    public void addLogisticsInfo(MallLogisticsInfo logisticsInfo) {
        logisticsInfoMapper.insertSelective(logisticsInfo);
    }
}
