package com.yyyu.user.dao;

import com.yyyu.user.pojo.MallUserToken;
import com.yyyu.user.pojo.MallUserTokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallUserTokenMapper {
    int countByExample(MallUserTokenExample example);

    int deleteByExample(MallUserTokenExample example);

    int insert(MallUserToken record);

    int insertSelective(MallUserToken record);

    List<MallUserToken> selectByExample(MallUserTokenExample example);

    int updateByExampleSelective(@Param("record") MallUserToken record, @Param("example") MallUserTokenExample example);

    int updateByExample(@Param("record") MallUserToken record, @Param("example") MallUserTokenExample example);
}