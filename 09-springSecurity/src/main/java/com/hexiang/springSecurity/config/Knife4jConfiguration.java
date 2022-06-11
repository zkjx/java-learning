package com.hexiang.springSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author: hexiang
 * @date: 2022/3/23
 * @description:
 */

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //.title("SpringSecurity+JWT 接口测试文档")
                        .description("# SpringSecurity+JWT 接口测试文档")
                        .termsOfServiceUrl("http://localhost:8080/doc.html")
                        //.contact("172837855@qq.com")
                        .contact(new Contact("何翔","http://localhost:8080/doc.html","172837855@qq.com"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("1.0版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.hexiang.springSecurity.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
