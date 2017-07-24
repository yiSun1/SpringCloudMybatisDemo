package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by zhangxs7 on 2017/6/22.
 */
@SpringBootApplication
@EnableTransactionManagement //开启事务管理器 用于数据库连接事务
public class ApplictionService {
    public static void main(String[] args) {
        SpringApplication.run(ApplictionService.class, args);
    }
}
