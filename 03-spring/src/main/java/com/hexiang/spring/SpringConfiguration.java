package com.hexiang.spring;

import com.hexiang.spring.config.JdbcConfig;
import com.hexiang.spring.config.TransactionConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: hexiang
 * @date: 2022/4/12
 * @description:
 */

@Configuration
@Import({JdbcConfig.class, TransactionConfig.class})
@ComponentScan("com.hexiang.spring")
@MapperScan("com.hexiang.spring.dao")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class SpringConfiguration {


    /*

     Ioc实验内容

    @Bean("person")
    public Person getPerson(){
        Person person= new Person();
        person.setName("Zhangsan");
        person.setPet(getPet());
        return person;
    }

    @Bean
    public Pet getPet(){
        Dog pet= new Dog();
        pet.setName("WangWang");
        return pet;
    }

    */



}

