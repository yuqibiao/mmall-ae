package com.yyyu.mmall.controller.user;

import com.yyyu.mmall.controller.BaseController;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.user.pojo.bean.MenuBean;
import com.yyyu.user.service.inter.PermissionServiceInter;
import com.yyyu.user.service.inter.UserRolePermissionInter;
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
 * 功能：后台管理菜单
 *
 * @author yu
 * @date 2018/2/2.
 */
@Api(value = "admin/menu", description = "后台管理菜单相关",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api/admin/menu")
@Controller
public class MenuController extends BaseController{

    @Autowired
    private UserRolePermissionInter userRolePermission;

    @ApiOperation(value = "获取用户对应的菜单" , httpMethod = "GET")
    @RequestMapping(value = "v1/menus/users/{userId}" , method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getMenusByUserId(@ApiParam(value = "用户id" ,required = true) @PathVariable  Long userId){

        List<MenuBean> menuList;
        try {
            menuList = userRolePermission.selectMenusByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(menuList);
    }

}
