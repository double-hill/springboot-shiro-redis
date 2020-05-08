package com.xl.youliao.config;

import com.xl.youliao.entity.User;
import com.xl.youliao.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Set;

/**
 * @author WeiC
 * @date 2020/5/7 9:05
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    /**
     * 鉴权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("====鉴权====");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User)principalCollection.getPrimaryPrincipal();
        String userName = user.getUserName();
        Set<String> permissions = userService.findPermissionsByUserName(userName);
        if(permissions != null && permissions.size()>0){
            simpleAuthorizationInfo.addStringPermissions(permissions);
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("====认证====");
        String username = ((UsernamePasswordToken) token).getUsername();
        //获取用户
        User user = userService.findByUserName(username);
        if(Objects.isNull(user)){
            return null;
        }else{
            ByteSource byteSalt = new SimpleByteSource(user.getSalt());
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),byteSalt,getName());
            return authenticationInfo;
        }
    }
}
