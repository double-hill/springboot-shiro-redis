package com.xl.youliao.service.impl;

import com.xl.youliao.entity.User;
import com.xl.youliao.mapper.UserMapper;
import com.xl.youliao.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author WeiC
 * @date 2020/5/6 17:42
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User findByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public void deleteByUserId(Long userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public void saveUser(User user) {
        String password = user.getPassword();
        String salt = RandomStringUtils.randomAlphanumeric(10);
        user.setSalt(salt);
        Md5Hash md5Hash = new Md5Hash(password,salt);
        user.setPassword(md5Hash.toString());
        userMapper.insert(user);
    }

    @Override
    public Set<String> findPermissionsByUserName(String userName) {
        Set<String> redisValue = redisTemplate.boundSetOps(userName).members();
        if(Objects.nonNull(redisValue)&&redisValue.size()>0){
            return redisValue;
        }
        Set<String> sqlValue = userMapper.selectPermissionsByUserName(userName);
        redisTemplate.boundSetOps(userName).add(sqlValue.toArray());
        redisTemplate.expire(userName,3600, TimeUnit.SECONDS);
        return sqlValue;
    }
}
