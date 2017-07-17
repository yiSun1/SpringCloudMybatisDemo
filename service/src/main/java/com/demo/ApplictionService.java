package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by zhangxs7 on 2017/6/22.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.demo.dao","com.demo.domain","com.demo.manager","com.demo.service"})
@EnableTransactionManagement //开启事务管理器 用于数据库连接事务
@ImportResource(locations={"classpath*:applicationContext-tx.xml","classpath*:applicationContext-manager.xml"})
public class ApplictionService {
    public static void main(String[] args) {
        SpringApplication.run(ApplictionService.class, args);
    }
}
