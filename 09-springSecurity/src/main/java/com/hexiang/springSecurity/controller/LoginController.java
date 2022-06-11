package com.hexiang.springSecurity.controller;

import com.hexiang.springSecurity.pojo.User;
import com.hexiang.springSecurity.service.ILoginService;
import com.hexiang.springSecurity.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hexiang
 * @date: 2022/3/23
 * @description:
 */

@RestController
@Api(tags = "登录模块")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @ApiOperation(value = "登录请求")
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginService.login(user);
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public ResponseResult logout(){
        //退出
        return loginService.logout();
    }

    @ApiOperation(value = "测试认证功能")
    @PreAuthorize("hasAuthority('system:dept:list')")
    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(){
        return ResponseEntity.ok("Hi: 何翔");
    }

}
