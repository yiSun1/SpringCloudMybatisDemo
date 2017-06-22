package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by zhangxs7 on 2017/6/22.
 */
@SpringBootApplication
@MapperScan("com.demo.*")
public class ApplictionService {
    public static void main(String[] args) {
        SpringApplication.run(ApplictionService.class, args);
    }
}
