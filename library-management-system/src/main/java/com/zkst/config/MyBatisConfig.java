package com.zkst.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class MyBatisConfig {

    @Bean
    public SqlSessionFactoryBean factoryBean(DataSource dataSource)
    {
          SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();
          factoryBean.setDataSource(dataSource);
          factoryBean.setTypeAliasesPackage("com.zkst.domain");
          return factoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer()
    {
       MapperScannerConfigurer msc=new MapperScannerConfigurer();
       msc.setBasePackage("com.zkst.dao");
       return msc;
    }
}
