package com.hexiang.springboot.controller;

import com.hexiang.springboot.service.TimeService;
import com.hexiang.springboot.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 何翔
 * @date: 2022/5/24
 * @description: 打招呼
 */

@RestController
public class HelloController {

    @Autowired
    TimeService timeService;

    @GetMapping("/hello")
    public RespBean hello(@RequestParam String username) {

        return RespBean.success(timeService.getTime(), username);
    }

}
