package com.whu.charityangel.shiro;


import com.whu.charityangel.jwt.JwtToken;
import com.whu.charityangel.model.entity.User;
import com.whu.charityangel.service.UserService;
import com.whu.charityangel.service.impl.CurrentUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Chris
 * @date 2021/7/9 12:45
 * @Email:gem7991@dingtalk.com
 */
public class JWTShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 限定这个 Realm 只处理我们自定义的 JwtToken
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    /**
     * 用户认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        if (jwtToken.getPrincipal() == null) {
            throw new AccountException("JWT token参数异常！");
        }
        // 从 JwtToken 中获取当前用户
        String username = authenticationToken.getPrincipal().toString();
        User user = userService.getUserByName(username);
        if (user == null) {
            throw new UnknownAccountException("用户不存在！");
        } else {
            // 这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, username, getName());
            CurrentUserService.setUser(user);
            return simpleAuthenticationInfo;
        }
    }

    /**
     * 角色权限和对应权限添加
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取登录用户名
        //User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        User currentUser = (User) principalCollection.getPrimaryPrincipal();

        // 查询用户名称
        User user = userService.getUserByName(currentUser.getName());
        // 查询用户权限
        List<String> permissionList = userService.queryAllPerms(user.getId());
        // 添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (String p : permissionList) {
            simpleAuthorizationInfo.addStringPermission(p);
        }

        return simpleAuthorizationInfo;
    }
}
