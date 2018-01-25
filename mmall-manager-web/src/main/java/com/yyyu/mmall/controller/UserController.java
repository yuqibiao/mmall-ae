package com.yyyu.mmall.controller;

import com.github.pagehelper.PageInfo;
import com.yyyu.user.pojo.MallUser;
import com.yyyu.user.service.inter.UserServiceInter;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能：用户相关Controller
 *  @ApiModelProperty("用户名") 传参为对象的时候用来描述属性
 * @author yu
 * @date 2018/1/24.
 */
@Api(value = "user", description = "用户相关操作",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("user")
@Controller
public class UserController {


    @Autowired
    private UserServiceInter userService;

    @ApiOperation(value = "根据Id获得用户信息",
            notes = "传入用户id",
            httpMethod = "GET",
            produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "",response=ResultUtils.class),
            @ApiResponse(code = 500, message = "访问错误",  response=ResultUtils.class)
    })
    @RequestMapping(value = "v1/users/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils  getUserById(@ApiParam(value = "用户id",  required = true) @PathVariable("userId")  Long userId){

        MallUser mallUser = userService.selectByUserId(userId);

        return ResultUtils.createSuccess(mallUser);
    }


    @ApiOperation(value = "分页获得用户信息",
            notes = "传入分页信息",
            httpMethod = "GET",
            produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "返回MallUser集合"),
            @ApiResponse(code = 500, message = "msg为具体的错误信息")
    })
    @RequestMapping(value = "v1/users", method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getUserByPage(@ApiParam(value = "页码", required = true) @RequestParam Integer from ,
                                     @ApiParam(value = "一页显示的条数" , required = true)  @RequestParam Integer to){
        from = from==null?0:from;
        to = to==null?20:to;
        PageInfo<MallUser> mallUserPageInfo = userService.selectUserByPage(from, to);

        return ResultUtils.createSuccess(mallUserPageInfo);
    }

}
