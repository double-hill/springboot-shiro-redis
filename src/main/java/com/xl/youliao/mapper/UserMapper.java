package com.xl.youliao.mapper;

import com.xl.youliao.base.BaseMapper;
import com.xl.youliao.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author WeiC
 * @date 2020/5/6 17:32
 */
public interface UserMapper extends BaseMapper<User> {
    User selectByUserName(@Param("userName") String userName);
    Set<String> selectPermissionsByUserName(@Param("userName") String userName);
}
