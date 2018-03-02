package com.yyyu.user.service.inter;

import com.github.pagehelper.PageInfo;
import com.yyyu.user.pojo.MallUser;
import com.yyyu.user.pojo.MallUserExample;

import java.util.List;

/**
 * 功能：用户表相关接口
 *
 * @author yu
 * @date 2018/1/24.
 */
public interface UserServiceInter{

    /**
     * 添加用户
     * @param user
     */
    void addUser(MallUser user);


    /**
     * 根据Id删除用户
     *
     * 真正意义上的删除
     * @param userId
     */
    void reallyDeleteUserById(Long userId);

    /**
     * 根据Id删除用户
     *
     * 设置status状态为2（禁止）、不是真正意义上的删除
     * @param userId
     */
    void deleteUserById(Long userId);

    /**
     * 更新用户
     *
     * @param user
     */
    void updateUser(MallUser user);

    /**
     *根据用户名查询用户
     *
     * @param username
     * @return
     */
    List<MallUser> selectByUsername(String username);

    /**
     * 根据Id查询用户
     * @param userId
     * @return
     */
    MallUser selectByUserId(Long userId);

    /**
     * 分页查询用户
     * @param from
     * @param to
     * @return
     */
    PageInfo<MallUser> selectUserByPage(Integer from, Integer to , MallUserExample mallUserExample) ;

    /**
     * 批量删除用户
     *
     * @param userIdList
     */
    void reallyDeleteUserByIdList(List<Long> userIdList);
}
