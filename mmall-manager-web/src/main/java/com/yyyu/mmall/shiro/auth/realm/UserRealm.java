package com.yyyu.mmall.shiro.auth.realm;

import com.yyyu.user.pojo.MallPermission;
import com.yyyu.user.pojo.MallRole;
import com.yyyu.user.pojo.MallUser;
import com.yyyu.user.service.inter.RolePermissionServiceInter;
import com.yyyu.user.service.inter.UserRoleServiceInter;
import com.yyyu.user.service.inter.UserServiceInter;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/10/26.
 */
public class UserRealm extends AuthorizingRealm{


    @Autowired
    private UserServiceInter userService;
    @Autowired
    private UserRoleServiceInter userRoleService;
    @Autowired
    private RolePermissionServiceInter rolePermissionsService;

    Logger logger = Logger.getLogger(StatelessRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {//授权

        Collection<String> userIdCon = principals.fromRealm("userRealm");//根据realmName获取信息
        ArrayList<String> userIdList = new ArrayList<>(userIdCon);
        if (userIdList==null||userIdList.size()==0){//没有对应的realm（判断授权时会调用所有realm的doGetAuthorizationInfo）
            return null;
        }
        //System.out.println("=============userRealm=========授权11========doGetAuthorizationInfo===========");
        String userIdStr = userIdList.get(0);
       // String username = usernameAndId.split("#")[0];
        Long userId =Long.parseLong( userIdStr);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<MallRole> mallRoles = userRoleService.selectRoleByUserId(userId);
        Set<String> roleCodeSet = new HashSet<>();
        Set<String> perCodeSet = new HashSet<>();
        for (MallRole role:mallRoles) {
            roleCodeSet.add(role.getAlias());
            Integer roleId = role.getRoleId();
            List<MallPermission> permissionList = rolePermissionsService.selectPermissionByRoleId(roleId);
            Set<String> perCodeSetSub = new HashSet<>();
            for (MallPermission permission:permissionList) {
                perCodeSetSub.add(permission.getAlias());
            }
            perCodeSet.addAll(perCodeSetSub);
        }
        //1.设置角色编号
        authorizationInfo.setRoles(roleCodeSet);
        //2.设置权限编号
        authorizationInfo.setStringPermissions(perCodeSet);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {//认证

        //System.out.println("=============userRealm=========认证========doGetAuthenticationInfo===========");

        // 1. 把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //2.取出用户名
        String username = usernamePasswordToken.getUsername();
        List<MallUser> mallUsers = userService.selectByUsername(username);
        if (mallUsers==null || mallUsers.size()==0){
            throw new UnknownAccountException("用户不存在");
        }
        String password = mallUsers.get(0).getPassword();
        Long userId = mallUsers.get(0).getUserId();
        String realmName = "userRealm";
        ByteSource salt = ByteSource.Util.bytes(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userId , password , salt , realmName);
        return info;
    }
}
