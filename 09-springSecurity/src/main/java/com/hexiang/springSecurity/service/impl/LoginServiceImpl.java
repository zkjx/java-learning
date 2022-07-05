package com.hexiang.springSecurity.service.impl;

import com.hexiang.springSecurity.mapper.UserMapper;
import com.hexiang.springSecurity.pojo.LoginUser;
import com.hexiang.springSecurity.pojo.User;
import com.hexiang.springSecurity.service.ILoginService;
import com.hexiang.springSecurity.utils.JwtUtil;
import com.hexiang.springSecurity.utils.RedisCacheUtil;
import com.hexiang.springSecurity.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: hexiang
 * @date: 2022/3/24
 * @description:
 */

@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Override
    public ResponseResult login(User user) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出相应的提示
         if(Objects.isNull(authenticate))  throw new RuntimeException("登录失败！");
        //如果认证通过了，使用userid生成一个jwt 并存入ResponseResult返回
        LoginUser loginUser= (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把完整的用户信息存入到redis中
        redisCacheUtil.setCacheObject("login:"+userId,loginUser);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",jwt);
        return new ResponseResult<>(200,"登录成功！",map);
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //删除redis中的值
        String userId = loginUser.getUser().getId().toString();
        redisCacheUtil.deleteObject("login:"+userId);
        return new ResponseResult<>(200,"退出成功！");
    }
}
