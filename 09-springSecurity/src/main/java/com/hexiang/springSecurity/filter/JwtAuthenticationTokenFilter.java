package com.hexiang.springSecurity.filter;

import com.hexiang.springSecurity.pojo.LoginUser;
import com.hexiang.springSecurity.utils.JwtUtil;
import com.hexiang.springSecurity.utils.RedisCacheUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author: hexiang
 * @date: 2022/3/24
 * @description:
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获取token，解析token
        String token = httpServletRequest.getHeader("token");
        if(!StringUtils.hasText(token)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        //解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token 非法！");
        }
        //从redis中获取用户信息
        String redisKey = "login:"+userId;
        LoginUser loginUser = redisCacheUtil.getCacheObject(redisKey);
        if(Objects.isNull(loginUser)) throw new RuntimeException("用户未登入！");
        //存入springSecurityContextHolder
        // 获取权限信息，封装到 AuthenticationToke

        UsernamePasswordAuthenticationToken authenticationToke
                = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToke);
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

}
