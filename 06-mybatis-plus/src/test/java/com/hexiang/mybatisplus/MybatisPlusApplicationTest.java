package com.hexiang.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hexiang.mybatisplus.mapper.UserMapper;
import com.hexiang.mybatisplus.pojo.User;
import com.hexiang.mybatisplus.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hexiang
 * @date: 2022/3/23
 * @description: 测试类
 */

@SpringBootTest
public class MybatisPlusApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService userService;

    @Test
    public void testQueryList(){
        System.out.println(userMapper.selectList(null));
    }

    @Test
    public void testInsert(){
       User user =  new User(null,"hexiang","666","何翔",21,"珠科");
        userMapper.insert(user);
        System.out.println(user.getId());
    }

    @Test
    public void testDeleteById(){
        userMapper.deleteById(5);
    }
    @Test
    public void testDeleteByIds(){
        userMapper.deleteBatchIds(Arrays.asList(6,7));
    }
    @Test
    public void testDeleteByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","何翔");
        map.put("age",21);
        userMapper.deleteByMap(map);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(4L);
        user.setAge(18);
        userMapper.updateById(user);
    }

    @Test
    public void testQueryWrapper01(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",13);
        queryWrapper.eq("address","狐山");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testQueryWrapper02(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",1,2,3);
        queryWrapper.between("age",10,25);
        queryWrapper.like("address","山");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testQueryWrapper03(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",1,2,3);
        queryWrapper.gt("age",10);
        queryWrapper.orderByDesc("age");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testWrapperSelect01(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","user_name");
        userMapper.selectList(queryWrapper);
    }

    @Test
    public void testWrapperSelect02(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(User.class, tableFieldInfo -> "user_name".equals(tableFieldInfo.getColumn()));
        userMapper.selectList(queryWrapper);
    }

    @Test
    public void testWrapperSelect03(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(new User());
        queryWrapper.select(tableFieldInfo -> !"address".equals(tableFieldInfo.getColumn()));
        userMapper.selectList(queryWrapper);
    }

    @Test
    public void testUpdateWrapper01(){
       UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
       updateWrapper.lt("id",2).set("age",6);
        userMapper.update(null,updateWrapper);

    }

    @Test
    public void testLambdaWrapper(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.gt(User::getAge,15)
                .eq(User::getAddress,"狐山");
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testFindMyUser(){
        System.out.println(userMapper.findMyUser(1L));
    }

    @Test
    public void testFindMyUserByWrapper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",15)
                        .eq("address","狐山");
        userMapper.findMyUserByWrapper(queryWrapper);
    }

    @Test
    public void testPagePlug(){
        IPage<User> page = new Page<>();
        page.setCurrent(1);
        page.setSize(2);
        userMapper.selectPage(page, null);
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
    }

    @Test
    public void testServiceUse(){
        userService.list().forEach(System.out::println);
    }

}
