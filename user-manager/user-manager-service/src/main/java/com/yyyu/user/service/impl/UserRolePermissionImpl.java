package com.yyyu.user.service.impl;

import com.yyyu.user.dao.MallUserRolePermissionMapper;
import com.yyyu.user.pojo.MallPermission;
import com.yyyu.user.pojo.bean.MenuBean;
import com.yyyu.user.service.inter.UserRolePermissionInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/2/2.
 */
@Service
public class UserRolePermissionImpl implements UserRolePermissionInter{

    @Autowired
    private MallUserRolePermissionMapper userRolePermissionMapper;

    @Override
    public List<MenuBean> selectMenusByUserId(Long userId) {

        List<MenuBean>  menuBeanList = new ArrayList<>();
        List<MallPermission> permissionList = userRolePermissionMapper.selectMenusByUserId(userId);
        for (MallPermission permission:permissionList) {
            MenuBean menuBean = new MenuBean();
            Integer parentId = permission.getParentId();
            menuBean.setId(permission.getPermissionId());
            menuBean.setpId(parentId);
            menuBean.setTitle(permission.getName());
            menuBean.setIcon(permission.getIcon());
            menuBean.setHref(permission.getTarget());
            if(parentId==null ||"".equals(parentId)){
                menuBean.setParent(true);
            }else{
                menuBean.setParent(false);
            }
            menuBeanList.add(menuBean);
        }

        return menuBeanList;
    }

}
