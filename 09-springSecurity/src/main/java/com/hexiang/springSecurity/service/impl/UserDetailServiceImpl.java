package com.hexiang.springSecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hexiang.springSecurity.mapper.MenuMapper;
import com.hexiang.springSecurity.mapper.UserMapper;
import com.hexiang.springSecurity.pojo.LoginUser;
import com.hexiang.springSecurity.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author: hexiang
 * @date: 2022/3/24
 * @description:
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName,name);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        //如果没有查询到用户，就会抛出异常
        if(Objects.isNull(user)) throw new RuntimeException("用户名或者密码错误！");
        //查询对应权限信息
        List<String> menus = menuMapper.selectPermsByUserId(user.getId());
        //把数据封装成UserDetails返回
        return new LoginUser(user,menus);
    }
}
