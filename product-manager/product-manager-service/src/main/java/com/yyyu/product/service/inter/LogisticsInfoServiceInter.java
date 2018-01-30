package com.yyyu.product.service.inter;

import com.yyyu.product.pojo.MallLogisticsInfo;

import java.util.List;

/**
 * 功能：物流信息操作接口
 *
 * @author yu
 * @date 2018/1/30.
 */
public interface LogisticsInfoServiceInter {

    /**
     * 得到某一用户的所有物流信息
     *
     * @return
     */
    List<MallLogisticsInfo> selectAllLogisticsInfoByUserId(Long userId);

    /**
     * 修改物流信息
     *
     * @param logisticsInfo
     */
    void updateLogisticsInfo(MallLogisticsInfo  logisticsInfo);

    /**
     * 删除物流信息
     *
     * @param logisticsId
     */
    void deleteLogisticsInfoByLogisticsId(Long logisticsId);

    /**
     * 添加物流信息
     *
     * @param logisticsInfo
     */
    void addLogisticsInfo(MallLogisticsInfo logisticsInfo);
}
