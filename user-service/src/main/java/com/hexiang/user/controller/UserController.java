package com.hexiang.user.controller;

import com.hexiang.user.config.PatternProperties;
import com.hexiang.user.pojo.User;
import com.hexiang.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
//@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    //@Value("${pattern.dateformat}")
    //private String dateFormat;

    @Autowired
    private PatternProperties patternProperties;

    /**
     * 路径： /user/101
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }

    /**
     * 获取当前时间
     */
    @GetMapping("/now")
    public String getNowTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateFormat()));
    }
    /**
     * 获取配置属性
     */
    @GetMapping("/prop")
    public PatternProperties getpatternProperties() {
        return patternProperties;
    }
}
