package com.yyyu.mmall.controller.user;

import com.yyyu.mmall.controller.BaseController;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.user.pojo.bean.ZTreeNode;
import com.yyyu.user.pojo.vo.UpdateRolePermissionVo;
import com.yyyu.user.service.inter.RolePermissionServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
public class RolePermissionController extends BaseController{

    @Autowired
    private RolePermissionServiceInter rolePermissionService;

    @ApiOperation(value = "查询所有的权限信息" , httpMethod = "GET")
    @RequestMapping(value = "v1/permissions" , method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getAllPermission(){

        List<ZTreeNode> zTreeNodeList;
        try {
            zTreeNodeList = rolePermissionService.selectAllPermissionByRoleId(0);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(zTreeNodeList);
    }

    @ApiOperation(value = "查询某一角色的所有权限" , httpMethod = "GET")
    @RequestMapping(value = "v1/roles/{roleId}/permissions" , method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getAllPermissionByRoleId(@ApiParam(value = "角色id" , required = true)@PathVariable  Integer roleId){

        List<ZTreeNode> zTreeNodeList;
        try {
            zTreeNodeList = rolePermissionService.selectAllPermissionByRoleId(roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(zTreeNodeList);
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

    @ApiOperation(value = "更新用户的权限" , httpMethod = "PATCH")
    @RequestMapping(value = "v1/roles/permissions:update" , method = RequestMethod.PATCH)
    @ResponseBody
    public ResultUtils updateRolePermission(@ApiParam(value = "更新信息" , required = true)@RequestBody   UpdateRolePermissionVo updateRolePermissionVo){

        try {
            //删除原来的添加新增的
            rolePermissionService.updateRolePermission(updateRolePermissionVo );
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("添加权限成功");
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
