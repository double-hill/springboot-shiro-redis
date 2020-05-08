package com.xl.youliao.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WeiC
 * @date 2020/5/7 12:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserVo{
    private Long userId;
    private String userName;
    private String token;
}
