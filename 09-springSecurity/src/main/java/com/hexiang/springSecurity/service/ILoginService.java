package com.hexiang.springSecurity.service;

import com.hexiang.springSecurity.pojo.User;
import com.hexiang.springSecurity.utils.ResponseResult;

/**
 * @author: hexiang
 * @date: 2022/3/24
 * @description:
 */

public interface ILoginService {

    //登录
    ResponseResult login(User user);

    //退出
    ResponseResult logout();
}
