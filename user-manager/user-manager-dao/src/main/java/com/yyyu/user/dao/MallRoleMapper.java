package com.yyyu.user.dao;

import com.yyyu.user.pojo.MallRole;
import com.yyyu.user.pojo.MallRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallRoleMapper {
    int countByExample(MallRoleExample example);

    int deleteByExample(MallRoleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(MallRole record);

    int insertSelective(MallRole record);

    List<MallRole> selectByExample(MallRoleExample example);

    MallRole selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") MallRole record, @Param("example") MallRoleExample example);

    int updateByExample(@Param("record") MallRole record, @Param("example") MallRoleExample example);

    int updateByPrimaryKeySelective(MallRole record);

    int updateByPrimaryKey(MallRole record);
}