package com.yyyu.product.dao;

import com.yyyu.product.pojo.MallOrderItem;
import com.yyyu.product.pojo.MallOrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallOrderItemMapper {
    int countByExample(MallOrderItemExample example);

    int deleteByExample(MallOrderItemExample example);

    int deleteByPrimaryKey(Long orderItemId);

    int insert(MallOrderItem record);

    int insertSelective(MallOrderItem record);

    List<MallOrderItem> selectByExample(MallOrderItemExample example);

    MallOrderItem selectByPrimaryKey(Long orderItemId);

    int updateByExampleSelective(@Param("record") MallOrderItem record, @Param("example") MallOrderItemExample example);

    int updateByExample(@Param("record") MallOrderItem record, @Param("example") MallOrderItemExample example);

    int updateByPrimaryKeySelective(MallOrderItem record);

    int updateByPrimaryKey(MallOrderItem record);
}