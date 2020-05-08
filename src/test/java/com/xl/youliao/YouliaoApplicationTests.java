package com.xl.youliao;


import com.xl.youliao.entity.User;
import com.xl.youliao.mapper.UserMapper;
import com.xl.youliao.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YouliaoApplicationTests {
    @Autowired
    private UserService userService;


    @Test
    public void saveUser(){
        User user = new User();
        user.setUserName("test");
        user.setPassword("123456");
        user.setCreateTime(new Date());
        userService.saveUser(user);
    }




}
