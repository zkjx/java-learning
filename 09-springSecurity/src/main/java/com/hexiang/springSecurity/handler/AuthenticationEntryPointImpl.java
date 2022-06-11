package com.hexiang.springSecurity.handler;

import com.alibaba.fastjson.JSON;
import com.hexiang.springSecurity.utils.ResponseResult;
import com.hexiang.springSecurity.utils.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: hexiang
 * @date: 2022/3/25
 * @description:
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户认证失败，请重新登录！");
        String jsonString = JSON.toJSONString(result);
        //处理异常
        WebUtil.renderString(httpServletResponse,jsonString);
    }
}
