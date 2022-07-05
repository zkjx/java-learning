package com.hexiang.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: hexiang
 * @date: 2022/3/23
 * @description: 启动类
 */

@SpringBootApplication
@MapperScan("com.hexiang.mybatisplus.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
