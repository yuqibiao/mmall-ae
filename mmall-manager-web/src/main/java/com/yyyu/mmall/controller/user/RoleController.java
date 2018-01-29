package com.yyyu.mmall.controller.user;

import com.github.pagehelper.PageInfo;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.user.pojo.MallRole;
import com.yyyu.user.pojo.vo.RoleUpdateVo;
import com.yyyu.user.pojo.vo.RoleVo;
import com.yyyu.user.service.inter.RoleServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：角色相关操作api
 *
 * @author yu
 * @date 2018/1/29.
 */

@Api(value = "role", description = "角色相关操作",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api/role")
@Controller
public class RoleController {

    @Autowired
    private RoleServiceInter roleService;

    @ApiOperation(value = "分页查询角色信息", notes = "传入start 和 size" , httpMethod = "GET")
    @RequestMapping("v1/roles")
    @ResponseBody
    public ResultUtils getRoleByPage(@ApiParam(value = "从第几行开始区数据")  @RequestParam(defaultValue = "0") Integer start ,
                                     @ApiParam(value = "取数据的条数") @RequestParam(defaultValue = "10")  Integer size){

        PageInfo<MallRole> mallRolePageInfo ;
        try {
            mallRolePageInfo = roleService.selectRoleByPage(start, size);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(mallRolePageInfo);
    }

    @ApiOperation(value = "根据roleId查询角色信息", notes = "传入角色Id" , httpMethod = "GET")
    @RequestMapping(value = "v1/roles/{roleId}" , method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getRoleByRoleId(@ApiParam(value = "角色id" , required = true)  @PathVariable  Integer roleId){

        MallRole role;
        try {
            role = roleService.selectRoleByRoleId(roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(role);
    }

    @ApiOperation(value = "更新角色信息", notes = "传入json格式的RoleVo" , httpMethod = "PATCH")
    @RequestMapping(value = "v1/roles" , method = RequestMethod.PATCH)
    @ResponseBody
    public ResultUtils updateRole(@RequestBody RoleUpdateVo roleUpdateVo){

        try {
            roleService.updateRole(setMallRole(roleUpdateVo));
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("更新成功");
    }

    @ApiOperation(value = "根据roleId删除角色", notes = "传入角色Id" , httpMethod = "DELETE")
    @RequestMapping(value = "v1/roles/{roleId}" , method = RequestMethod.DELETE)
    @ResponseBody
    public ResultUtils deleteRole(@ApiParam(value = "角色id" ,required = true) @PathVariable  Integer roleId){

        try {
            roleService.deleteRole(roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("删除成功");
    }

    @ApiOperation(value = "添加角色", notes = "传入json格式RoleVo" , httpMethod = "POST")
    @RequestMapping(value = "v1/roles" , method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils addRole(@RequestBody  RoleVo roleVo){

        try {
            roleService.addRole(setMallRole(roleVo));
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("插入成功");
    }

    private MallRole setMallRole(RoleVo roleVo){
        MallRole role = new MallRole();
        role.setName(roleVo.getName());
        role.setCode(roleVo.getCode());
        role.setDescription(roleVo.getDescription());
        role.setStatus(roleVo.getStatus());
        if (roleVo instanceof  RoleUpdateVo){
            role.setRoleId(((RoleUpdateVo)roleVo).getRoleId());
        }
        return role;
    }

}
