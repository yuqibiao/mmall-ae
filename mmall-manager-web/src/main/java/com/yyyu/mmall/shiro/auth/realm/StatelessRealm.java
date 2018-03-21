package com.yyyu.mmall.shiro.auth.realm;

import com.yyyu.mmall.shiro.auth.token.StatelessToken;
import com.yyyu.mmall.uitls.codec.DESCoder;
import com.yyyu.user.pojo.MallRole;
import com.yyyu.user.pojo.MallUserToken;
import com.yyyu.user.service.inter.UserRoleServiceInter;
import com.yyyu.user.service.inter.UserTokenServiceInter;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 功能：无状态realm
 *
 * @author yu
 * @date 2017/11/14.
 */
public class StatelessRealm extends AuthorizingRealm{

    @Autowired
    private UserRoleServiceInter userRoleService;

    Logger logger = Logger.getLogger(StatelessRealm.class);

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Collection<String> userIdCon = principals.fromRealm("statelessRealm");//根据realmName获取信息
        ArrayList<String> userIdList = new ArrayList<>(userIdCon);
        if (userIdList==null||userIdList.size()==0){//没有对应的realm（判断授权时会调用所有realm的doGetAuthorizationInfo）
            return null;
        }
        //System.out.println("=================StatelessRealm1==========授权===doGetAuthorizationInfo");
        String userId = userIdList.get(0);
        SimpleAuthorizationInfo authorizationInfo =  new SimpleAuthorizationInfo();
        List<MallRole> mallRoles = userRoleService.selectRoleByUserId(Long.parseLong(userId));
        Set<String> roleCodeSet = new HashSet<>();
        for (MallRole role:mallRoles) {
            roleCodeSet.add(role.getAlias());
        }
        //--TODO 把权限信息加入内存缓存中
        //添加角色/权限
        authorizationInfo.setRoles(roleCodeSet);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //System.out.println("=================StatelessRealm==========认证===doGetAuthenticationInfo");

        //验证token是否正确
        StatelessToken token = (StatelessToken) authenticationToken;
        String userId = token.getUserId();
        String clientDigest = token.getClientDigest();
        String realmName = "statelessRealm";
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userId, clientDigest, realmName);
        return info;
    }


}
