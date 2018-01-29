package com.yyyu.mmall.controller.user;

import com.github.pagehelper.PageInfo;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.user.pojo.MallPermission;
import com.yyyu.user.pojo.vo.PermissionUpdateVo;
import com.yyyu.user.pojo.vo.PermissionVo;
import com.yyyu.user.service.inter.PermissionServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：权限相关api
 *
 * @author yu
 * @date 2018/1/29.
 */
@Api(value = "permission", description = "权限相关操作",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api/permission")
@Controller
public class PermissionController {

    @Autowired
    private PermissionServiceInter permissionService;

    @ApiOperation(value = "分页查询权限信息" , httpMethod = "GET")
    @RequestMapping(value = "v1/permissions" , method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getPermissionByPage(@ApiParam(value = "从第几条开始取数据" ,required = true)@RequestParam(defaultValue = "0") Integer start ,
                                           @ApiParam(value = "一共取多少条数据" ,required = true) @RequestParam(defaultValue = "10") Integer size){

        PageInfo<MallPermission> mallPermissionPageInfo ;
        try {
            mallPermissionPageInfo = permissionService.selectPermissionByPage(start, size);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(mallPermissionPageInfo);
    }

    @ApiOperation(value = "根据id查询权限信息" , httpMethod = "GET")
    @RequestMapping(value = "v1/permissions/{permissionId}" , method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getPermissionByPermissionId( @ApiParam(value = "权限Id" , required = true) @PathVariable Integer permissionId){

        MallPermission permission ;
        try {
            permission = permissionService.selectPermissionByPermissionId(permissionId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(permission);
    }


    @ApiOperation(value = "更新权限" , httpMethod = "PATCH")
    @RequestMapping(value = "v1/permissions" , method = RequestMethod.PATCH)
    @ResponseBody
    public ResultUtils updatePermission(@RequestBody PermissionVo permissionVo){

        try {
            permissionService.updatePermission(setPermission(permissionVo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("修改成功");
    }


    @ApiOperation(value = "根据id权限"  , httpMethod = "DELETE")
    @RequestMapping(value = "v1/permissions/{permissionId}" , method = RequestMethod.DELETE)
    @ResponseBody
    public ResultUtils deletePermissionByPermissionId(@ApiParam(value = "权限id" , required = true)@PathVariable  Integer permissionId){

        try {
            permissionService.deletePermissionByPermissionId(permissionId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResultUtils.createSuccess("删除成功");
    }


    @ApiOperation(value = "添加权限"  , httpMethod = "POST")
    @RequestMapping(value = "v1/permissions" , method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils addPermission(@RequestBody  PermissionVo permissionVo){

        try {
            permissionService.addPermission(setPermission(permissionVo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("添加成功");
    }

    public MallPermission setPermission(PermissionVo permissionVo){
        MallPermission permission = new MallPermission();
        permission.setParentId(permissionVo.getParentId());
        permission.setName(permissionVo.getName());
        permission.setDescription(permissionVo.getDescription());
        permission.setStatus(permissionVo.getStatus());
        permission.setCode(permissionVo.getCode());
        permission.setTarget(permissionVo.getTarget());
        permission.setType(permissionVo.getType());
        if (permissionVo instanceof PermissionUpdateVo){
            permission.setPermissionId(((PermissionUpdateVo) permissionVo).getPermissionId());
        }
       return permission;
    }

}
