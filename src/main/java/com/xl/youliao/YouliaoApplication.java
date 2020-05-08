package com.xl.youliao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author WeiC
 * @date 2020/5/6 13:09
 */
@SpringBootApplication
@MapperScan(basePackages = "com.xl.youliao.mapper")
public class YouliaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(YouliaoApplication.class, args);
    }
}
