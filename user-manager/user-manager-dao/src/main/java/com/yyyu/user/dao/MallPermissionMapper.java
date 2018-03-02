package com.yyyu.user.dao;

import com.yyyu.user.pojo.MallPermission;
import com.yyyu.user.pojo.MallPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallPermissionMapper {
    int countByExample(MallPermissionExample example);

    int deleteByExample(MallPermissionExample example);

    int deleteByPrimaryKey(Integer permissionId);

    int insert(MallPermission record);

    int insertSelective(MallPermission record);

    List<MallPermission> selectByExample(MallPermissionExample example);

    MallPermission selectByPrimaryKey(Integer permissionId);

    int updateByExampleSelective(@Param("record") MallPermission record, @Param("example") MallPermissionExample example);

    int updateByExample(@Param("record") MallPermission record, @Param("example") MallPermissionExample example);

    int updateByPrimaryKeySelective(MallPermission record);

    int updateByPrimaryKey(MallPermission record);

    void reallyDeletePermissionByIdList(List<Integer> permissionList);
}