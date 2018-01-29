package com.yyyu.mmall.controller.user;

import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.user.pojo.MallPermission;
import com.yyyu.user.service.inter.RolePermissionServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 功能：角色-权限操作api
 *
 * @author yu
 * @date 2018/1/29.
 */
@Api(value = "role/permission", description = "角色-权限操作",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api")
@Controller
public class RolePermissionController {

    @Autowired
    private RolePermissionServiceInter rolePermissionService;

    @ApiOperation(value = "查询某一角色的所有权限" , httpMethod = "GET")
    @RequestMapping(value = "v1/roles/{roleId}/permissions" , method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getPermissionByRole(@ApiParam(value = "角色id" , required = true)@PathVariable  Integer roleId){

        List<MallPermission> mallPermissions;
        try {
            mallPermissions = rolePermissionService.selectPermissionByRoleId(roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(mallPermissions);
    }


    @ApiOperation(value = "删除角色的权限" , httpMethod = "DELETE")
    @RequestMapping(value = "v1/roles/{roleId}/permissions/{permissionId}" , method = RequestMethod.DELETE)
    @ResponseBody
    public ResultUtils deleteRolePermission(@ApiParam(value = "角色id" , required = true)@PathVariable  Integer roleId ,
                                         @ApiParam(value = "权限id" , required = true)@PathVariable Integer permissionId){

        try {
            rolePermissionService.deleteRolePermission(roleId , permissionId);
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("删除权限成功");
    }

    @ApiOperation(value = "给角色添加权限" , httpMethod = "POST")
    @RequestMapping(value = "v1/roles/{roleId}/permissions/{permissionId}" , method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils addRolePermission(@ApiParam(value = "角色id" , required = true)@PathVariable  Integer roleId ,
                                         @ApiParam(value = "权限id" , required = true)@PathVariable Integer permissionId){

        try {
            rolePermissionService.addRolePermission(roleId , permissionId);
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("添加权限成功");
    }

}
