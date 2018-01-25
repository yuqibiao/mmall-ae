package com.yyyu.user.dao;

import com.yyyu.user.pojo.MallRolePermissionExample;
import com.yyyu.user.pojo.MallRolePermissionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallRolePermissionMapper {
    int countByExample(MallRolePermissionExample example);

    int deleteByExample(MallRolePermissionExample example);

    int deleteByPrimaryKey(MallRolePermissionKey key);

    int insert(MallRolePermissionKey record);

    int insertSelective(MallRolePermissionKey record);

    List<MallRolePermissionKey> selectByExample(MallRolePermissionExample example);

    int updateByExampleSelective(@Param("record") MallRolePermissionKey record, @Param("example") MallRolePermissionExample example);

    int updateByExample(@Param("record") MallRolePermissionKey record, @Param("example") MallRolePermissionExample example);
}