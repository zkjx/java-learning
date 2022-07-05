package com.hexiang.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author: hexiang
 * @date: 2022/4/25
 * @description:
 */

public class TransactionConfig {

    @Bean(name = "transactionManager")
    public PlatformTransactionManager createTransactionManager(DataSource dataSource){
        return  new DataSourceTransactionManager(dataSource);
    }
}
