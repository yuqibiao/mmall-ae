package com.yyyu.mmall.controller.user;

import com.yyyu.mmall.controller.BaseController;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.user.pojo.MallRole;
import com.yyyu.user.pojo.bean.RoleChecked;
import com.yyyu.user.service.inter.UserRoleServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能：用户-权限操作 api
 *
 * @author yu
 * @date 2018/1/29.
 */
@Api(value = "user/role", description = "用户-权限操作",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api")
@Controller
public class UserRoleController extends BaseController{

    @Autowired
    private UserRoleServiceInter userRoleService;

    @ApiOperation(value = "得到用户对应的权限信息" ,  notes = "返回全部的权限、用户有的checked=true" , httpMethod = "GET")
    @RequestMapping(value = "v1/users/{userId}/roles:all" , method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getAllRoleByUserId(@ApiParam(value = "用户id" ,required = true)@PathVariable Long userId){

        List<RoleChecked> roleCheckedList = null;
        try {
            roleCheckedList = userRoleService.selectAllRoleByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(roleCheckedList);
    }

    @ApiOperation(value = "查询用户的所有权限" , httpMethod = "GET")
    @RequestMapping(value = "v1/users/{userId}/roles" , method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getRoleByUserId(@ApiParam(value = "用户id" ,required = true)@PathVariable Long userId){

        List<MallRole> mallRoles = null;
        try {
            mallRoles = userRoleService.selectRoleByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(mallRoles);
    }

    @ApiOperation(value = "删除用户某一权限" , httpMethod = "DELETE")
    @RequestMapping(value = "v1/users/{userId}/roles/{roleId}" , method = RequestMethod.DELETE)
    @ResponseBody
    public ResultUtils deleteUserRole(@ApiParam(value = "用户id" ,required = true)@PathVariable Long userId ,
                                      @ApiParam(value = "权限id" ,required = true) @PathVariable Integer roleId){

        try {
            userRoleService.deleteUserRole(userId , roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("删除用户的角色成功");
    }

    @ApiOperation(value = "批量添加用户权限" , httpMethod = "POST")
    @RequestMapping(value = "v1/users/{userId}/roles:add" , method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils addUserRoleList(@ApiParam(value = "用户id" ,required = true)@PathVariable Long userId ,
                                   @RequestBody List<Integer> roleIdList){

        try {
            userRoleService.addUserRoleList(userId , roleIdList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("添加用户的角色成功");
    }


    @ApiOperation(value = "添加用户权限" , httpMethod = "POST")
    @RequestMapping(value = "v1/users/{userId}/roles/{roleId}" , method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils addUserRole(@ApiParam(value = "用户id" ,required = true)@PathVariable Long userId ,
                                   @ApiParam(value = "权限id" ,required = true) @PathVariable Integer roleId){

        try {
            userRoleService.addUserRole(userId , roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("添加用户的角色成功");
    }

}
