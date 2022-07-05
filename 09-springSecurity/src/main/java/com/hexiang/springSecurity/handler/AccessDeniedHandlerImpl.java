package com.hexiang.springSecurity.handler;

import com.alibaba.fastjson.JSON;
import com.hexiang.springSecurity.utils.ResponseResult;
import com.hexiang.springSecurity.utils.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
 @Override
 public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
  ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(),"您的权限不足！");
  String jsonString = JSON.toJSONString(result);
  //处理异常
  WebUtil.renderString(httpServletResponse,jsonString);
 }
}
