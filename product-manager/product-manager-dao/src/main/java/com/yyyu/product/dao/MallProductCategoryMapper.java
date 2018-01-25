package com.yyyu.product.dao;

import com.yyyu.product.pojo.MallProductCategory;
import com.yyyu.product.pojo.MallProductCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallProductCategoryMapper {
    int countByExample(MallProductCategoryExample example);

    int deleteByExample(MallProductCategoryExample example);

    int deleteByPrimaryKey(Long categoryId);

    int insert(MallProductCategory record);

    int insertSelective(MallProductCategory record);

    List<MallProductCategory> selectByExample(MallProductCategoryExample example);

    MallProductCategory selectByPrimaryKey(Long categoryId);

    int updateByExampleSelective(@Param("record") MallProductCategory record, @Param("example") MallProductCategoryExample example);

    int updateByExample(@Param("record") MallProductCategory record, @Param("example") MallProductCategoryExample example);

    int updateByPrimaryKeySelective(MallProductCategory record);

    int updateByPrimaryKey(MallProductCategory record);
}