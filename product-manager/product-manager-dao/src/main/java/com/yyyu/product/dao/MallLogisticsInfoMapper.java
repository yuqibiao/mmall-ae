package com.yyyu.product.dao;

import com.yyyu.product.pojo.MallLogisticsInfo;
import com.yyyu.product.pojo.MallLogisticsInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallLogisticsInfoMapper {
    int countByExample(MallLogisticsInfoExample example);

    int deleteByExample(MallLogisticsInfoExample example);

    int deleteByPrimaryKey(Long logisticsId);

    int insert(MallLogisticsInfo record);

    int insertSelective(MallLogisticsInfo record);

    List<MallLogisticsInfo> selectByExample(MallLogisticsInfoExample example);

    MallLogisticsInfo selectByPrimaryKey(Long logisticsId);

    int updateByExampleSelective(@Param("record") MallLogisticsInfo record, @Param("example") MallLogisticsInfoExample example);

    int updateByExample(@Param("record") MallLogisticsInfo record, @Param("example") MallLogisticsInfoExample example);

    int updateByPrimaryKeySelective(MallLogisticsInfo record);

    int updateByPrimaryKey(MallLogisticsInfo record);
}