package com.yyyu.user.dao;

import com.yyyu.user.pojo.MallRole;
import com.yyyu.user.pojo.MallUserRoleExample;
import com.yyyu.user.pojo.MallUserRoleKey;
import java.util.List;

import com.yyyu.user.pojo.bean.UserRoleIdListBean;
import org.apache.ibatis.annotations.Param;

public interface MallUserRoleMapper {
    int countByExample(MallUserRoleExample example);

    int deleteByExample(MallUserRoleExample example);

    int deleteByPrimaryKey(MallUserRoleKey key);

    int insert(MallUserRoleKey record);

    int insertSelective(MallUserRoleKey record);

    List<MallUserRoleKey> selectByExample(MallUserRoleExample example);

    int updateByExampleSelective(@Param("record") MallUserRoleKey record, @Param("example") MallUserRoleExample example);

    int updateByExample(@Param("record") MallUserRoleKey record, @Param("example") MallUserRoleExample example);

    List<MallRole> selectRoleByUserId(Long userId);

    void insertRoleList(UserRoleIdListBean userRoleIdListBean);
}