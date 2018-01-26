package com.yyyu.mmall.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.pagehelper.PageInfo;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.user.pojo.MallUser;
import com.yyyu.user.pojo.vo.UserVo;
import com.yyyu.user.service.inter.UserServiceInter;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：用户相关Controller
 * @author yu
 * @date 2018/1/24.
 */
@Api(value = "user", description = "用户相关操作",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api/user")
@Controller
public class UserController {

    @Autowired
    private UserServiceInter userService;


    @ApiOperation(value = "添加用户",
            notes = "传入用户id",
            httpMethod = "POST",
            produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "v1/user" , method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils addUser(@ApiParam(value = "用户信息", required = true ) @RequestBody UserVo user){

        try {
            MallUser mallUser = new MallUser();
            mallUser.setUsername(user.getUsername());
            //todo 加密
            mallUser.setPassword(user.getPassword());
            mallUser.setPhone(user.getPhone());
            mallUser.setEmail(user.getEmail());
            mallUser.setStatus(user.getStatus());
            mallUser.setQuestion(user.getQuestion());
            // todo 加密
            mallUser.setAnswer(user.getAnswer());
            userService.addUser(mallUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("添加用户成功");
    }


    @ApiOperation(value = "根据Id获得用户信息",
            notes = "传入用户id",
            httpMethod = "GET",
            produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "v1/users/{userId}",method = RequestMethod.GET)
    @JsonView(MallUser.UserReturn.class)
    @ResponseBody
    public ResultUtils  getUserById(@ApiParam(value = "用户id",  required = true) @PathVariable("userId")  Long userId){

        MallUser mallUser;
        try {
            mallUser = userService.selectByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(mallUser);
    }


    @ApiOperation(value = "分页获得用户信息",
            notes = "传入分页信息",
            httpMethod = "GET",
            response = ResultUtils.class,
            produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "v1/users", method = RequestMethod.GET)
    @JsonView(MallUser.UserDetail.class)
    @ResponseBody
    public ResultUtils getUserByPage(@ApiParam(value = "从第几行开始区数据", required = true) @RequestParam(defaultValue = "0") Integer start ,
                                     @ApiParam(value = "取数据的条数" , required = true)  @RequestParam(defaultValue = "10") Integer size){

        PageInfo<MallUser> mallUserPageInfo;
        try {
            mallUserPageInfo = userService.selectUserByPage(start,size);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(mallUserPageInfo);
    }

}
