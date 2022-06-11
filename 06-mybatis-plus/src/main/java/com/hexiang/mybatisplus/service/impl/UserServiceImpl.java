package com.hexiang.mybatisplus.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hexiang.mybatisplus.mapper.UserMapper;
import com.hexiang.mybatisplus.pojo.User;
import com.hexiang.mybatisplus.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author: hexiang
 * @date: 2022/3/23
 * @description:
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {

}
