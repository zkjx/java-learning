package com.hexiang.feign.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author: 何翔
 * @date: 2022/7/2
 * @description:
 */

public class DefaultFeignConfiguration {

    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.BASIC;
    }
}
