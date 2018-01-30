package com.yyyu.product.dao;

import com.yyyu.product.pojo.MallCart;
import com.yyyu.product.pojo.MallCartExample;
import java.util.List;

import com.yyyu.product.pojo.bean.CartProduct;
import org.apache.ibatis.annotations.Param;

public interface MallCartMapper {
    int countByExample(MallCartExample example);

    int deleteByExample(MallCartExample example);

    int deleteByPrimaryKey(Long cartId);

    int insert(MallCart record);

    int insertSelective(MallCart record);

    List<MallCart> selectByExample(MallCartExample example);

    MallCart selectByPrimaryKey(Long cartId);

    int updateByExampleSelective(@Param("record") MallCart record, @Param("example") MallCartExample example);

    int updateByExample(@Param("record") MallCart record, @Param("example") MallCartExample example);

    int updateByPrimaryKeySelective(MallCart record);

    int updateByPrimaryKey(MallCart record);

    List<CartProduct> selectAllCartProductByUserId(Long userId);
}