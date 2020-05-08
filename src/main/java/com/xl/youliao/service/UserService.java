package com.xl.youliao.service;

import com.xl.youliao.entity.User;

import java.util.Set;

/**
 * @author WeiC
 * @date 2020/5/6 17:42
 */
public interface UserService {

    User findByUserName(String userName);

    void deleteByUserId(Long userId);

    void saveUser(User user);

    Set<String> findPermissionsByUserName(String userName);

}
