package com.yyyu.mmall.controller.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.pagehelper.PageInfo;
import com.yyyu.mmall.controller.BaseController;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.mmall.uitls.lang.StringUtils;
import com.yyyu.user.pojo.MallUser;
import com.yyyu.user.pojo.MallUserExample;
import com.yyyu.user.pojo.vo.UserUpdateVo;
import com.yyyu.user.pojo.vo.UserVo;
import com.yyyu.user.service.inter.UserServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 功能：用户相关Controller
 * @author yu
 * @date 2018/1/24.
 */
@Api(value = "user", description = "用户相关操作",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api/user")
@Controller
public class UserController extends BaseController{

    @Autowired
    private UserServiceInter userService;

    @ApiOperation(value = "添加用户",
            notes = "传入json格式的用户信息的",
            httpMethod = "POST",
            produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "v1/user" , method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils addUser(@ApiParam(value = "用户信息", required = true ) @RequestBody UserVo user){

        try {

            //查看用户名是否已经存在
            List<MallUser> mallUsers = userService.selectByUsername(user.getUsername());
            if (mallUsers.size()>0){
                return ResultUtils.createError("该用户名已存在");
            }

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

    @ApiOperation(value = "批量删除用户",
            httpMethod = "POST",
            produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "v1/users:delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils deleteUser(@RequestBody List<Long> userIdList){

        try {
            userService.reallyDeleteUserByIdList(userIdList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("删除成功");
    }

    @ApiOperation(value = "删除用户",
            httpMethod = "DELETE",
            produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "v1/users/{userId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultUtils deleteUser(@ApiParam(value = "用户id"  , required = true) @PathVariable  Long userId){

        try {
            userService.reallyDeleteUserById(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("删除成功");
    }


    @ApiOperation(value = "修改用户",
            notes = "传入json格式的用户信息的，需要admin权限才能操作",
            httpMethod = "PATCH",
            produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "v1/user" , method = RequestMethod.PATCH )
    @ResponseBody
    public ResultUtils updateUser(@RequestBody UserUpdateVo userUpdateVo){

        try {
            //查看用户名是否已经存在
            List<MallUser> mallUsers = userService.selectByUsername(userUpdateVo.getUsername());
            if (mallUsers.size()>0){
                return ResultUtils.createError("该用户名已存在");
            }
            MallUser user = new MallUser();
            user.setUserId(userUpdateVo.getUserId());
            user.setUsername(userUpdateVo.getUsername());
            //---TODO 加密
            user.setPassword(userUpdateVo.getPassword());
            user.setStatus(userUpdateVo.getStatus());
            user.setPhone(userUpdateVo.getPhone());
            user.setEmail(userUpdateVo.getEmail());
            user.setQuestion(userUpdateVo.getQuestion());
            user.setAnswer(userUpdateVo.getAnswer());
            userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }
        MallUser mallUser = userService.selectByUserId(userUpdateVo.getUserId());
        return ResultUtils.createSuccess("修改用户信息成功",mallUser);
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
                                     @ApiParam(value = "取数据的条数" , required = true)  @RequestParam(defaultValue = "10") Integer size ,
                                     HttpServletRequest request){

        PageInfo<MallUser> mallUserPageInfo;
        try {
            String sort = getParameterUtf8(request , "sort");
            String order =  getParameterUtf8(request , "order");
            String username =  getParameterUtf8(request , "username");
            MallUserExample mallUserExample = new MallUserExample();
            String orderByClause = genOrderByClause(sort, order);
            if (!StringUtils.isEmpty(orderByClause)){
                mallUserExample.setOrderByClause(orderByClause);
            }
            if(!StringUtils.isEmpty(username)){
                mallUserExample.createCriteria().andUsernameLike("%"+username+"%");
            }
            mallUserPageInfo = userService.selectUserByPage(start,size , mallUserExample);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(mallUserPageInfo);
    }

}
