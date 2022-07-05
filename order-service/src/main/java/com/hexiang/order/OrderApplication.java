package com.hexiang.order;

import com.hexiang.feign.clients.UserClient;
import com.hexiang.feign.config.DefaultFeignConfiguration;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration.class,clients = UserClient.class)
@MapperScan("com.hexiang.order.mapper")
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        /*
        * @Author: 何翔
        * @Description: 创建RestTemplate对象并注入Spring容器中
        * @DateTime: 2022/7/2 1:14
        * @Params: []
        * @Return org.springframework.web.client.RestTemplate
        */
        return new RestTemplate();
    }

    //@Bean
    //public IRule randomRule(){
    //    /*
    //    * @Author: 何翔
    //    * @Description: 设置负载均衡模式（全局）
    //    * @DateTime: 2022/7/2 11:27
    //    * @Params: []
    //    * @Return com.netflix.loadbalancer.IRule
    //    */
    //    return new RandomRule();
    //}
}
