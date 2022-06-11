package com.hexiang.springSecurity;

import com.hexiang.springSecurity.mapper.MenuMapper;
import com.hexiang.springSecurity.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * @author: hexiang
 * @date: 2022/3/23
 * @description:
 */

@SpringBootTest
public class SpringSecurityApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testMysqlConnection(){
        System.out.println(userMapper.selectList(null));
    }

    @Test
    public void testPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("1234"));

    }

    @Test
    public void testSelectPermsByUserId(){
        List<String> strings = menuMapper.selectPermsByUserId(1L);
        strings.forEach(System.out::println);
    }
}