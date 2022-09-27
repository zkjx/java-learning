package com.zkst.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.zkst.controller","com.zkst.config"})
@EnableWebMvc
public class SpringMvcConfig {
}
