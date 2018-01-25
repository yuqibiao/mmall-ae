package com.yyyu.product.dao;

import com.yyyu.product.pojo.MallPayInfo;
import com.yyyu.product.pojo.MallPayInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallPayInfoMapper {
    int countByExample(MallPayInfoExample example);

    int deleteByExample(MallPayInfoExample example);

    int deleteByPrimaryKey(Long payId);

    int insert(MallPayInfo record);

    int insertSelective(MallPayInfo record);

    List<MallPayInfo> selectByExample(MallPayInfoExample example);

    MallPayInfo selectByPrimaryKey(Long payId);

    int updateByExampleSelective(@Param("record") MallPayInfo record, @Param("example") MallPayInfoExample example);

    int updateByExample(@Param("record") MallPayInfo record, @Param("example") MallPayInfoExample example);

    int updateByPrimaryKeySelective(MallPayInfo record);

    int updateByPrimaryKey(MallPayInfo record);
}