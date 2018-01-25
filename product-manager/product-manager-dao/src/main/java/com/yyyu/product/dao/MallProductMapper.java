package com.yyyu.product.dao;

import com.yyyu.product.pojo.MallProduct;
import com.yyyu.product.pojo.MallProductExample;
import com.yyyu.product.pojo.MallProductWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallProductMapper {
    int countByExample(MallProductExample example);

    int deleteByExample(MallProductExample example);

    int deleteByPrimaryKey(Long productId);

    int insert(MallProductWithBLOBs record);

    int insertSelective(MallProductWithBLOBs record);

    List<MallProductWithBLOBs> selectByExampleWithBLOBs(MallProductExample example);

    List<MallProduct> selectByExample(MallProductExample example);

    MallProductWithBLOBs selectByPrimaryKey(Long productId);

    int updateByExampleSelective(@Param("record") MallProductWithBLOBs record, @Param("example") MallProductExample example);

    int updateByExampleWithBLOBs(@Param("record") MallProductWithBLOBs record, @Param("example") MallProductExample example);

    int updateByExample(@Param("record") MallProduct record, @Param("example") MallProductExample example);

    int updateByPrimaryKeySelective(MallProductWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MallProductWithBLOBs record);

    int updateByPrimaryKey(MallProduct record);
}