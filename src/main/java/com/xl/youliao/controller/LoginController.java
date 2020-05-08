package com.xl.youliao.controller;

import com.xl.youliao.common.JSONResult;
import com.xl.youliao.entity.User;
import com.xl.youliao.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author WeiC
 * @date 2020/5/6 17:59
 */
@Controller
public class LoginController {

    @ResponseBody
    @RequestMapping("/toTest1")
    @RequiresPermissions("user:111view")
    public JSONResult toTest1(){
        return JSONResult.ok("测试===");
    }

    @ResponseBody
    @RequestMapping("/toTest2")
    @RequiresPermissions("user:view")
    public JSONResult toTest2(){
        return JSONResult.ok("测试2===");
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * login
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public JSONResult login(@RequestParam String username,@RequestParam String password){
        try{
            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken token = new UsernamePasswordToken(username,password);
            subject.login(token);
            User user = (User)subject.getPrincipals().getPrimaryPrincipal();
            UserVo userVo = new UserVo();
            if(Objects.nonNull(user)){
                Serializable sessionId = subject.getSession().getId();
                userVo.setUserId(user.getUserId());
                userVo.setUserName(user.getUserName());
                userVo.setToken(sessionId.toString());
            }
            return JSONResult.ok(userVo);
        }catch (UnknownAccountException e){
            return JSONResult.errorLogin("用户不存在");
        }catch (IncorrectCredentialsException e){
            return JSONResult.errorLogin("密码错误");
        }catch (AuthenticationException e) {
            return JSONResult.errorLogin("用户验证失败");
        }
    }

    /**
     * logout
     * @return
     */
    @RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }


}
