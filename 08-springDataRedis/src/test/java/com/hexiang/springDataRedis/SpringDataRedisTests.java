package com.hexiang.springDataRedis;

import com.hexiang.springDataRedis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: hexiang
 * @date: 2022/3/7
 * @description:
 */

@SpringBootTest
public class SpringDataRedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testInitConnection(){
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("name","hexiang");
        System.out.println(ops.get("name"));
        ValueOperations<String, String> ops1 = stringRedisTemplate.opsForValue();
        ops1.set("age","21");
        System.out.println(ops1.get("age"));
        redisTemplate.delete("name");
        redisTemplate.delete("age");
    }

    @Test
    public void testSerial(){
        User user = new User(2022,"hexiang","root");
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("user",user);
        System.out.println(ops.get("user"));
        redisTemplate.delete("user");
    }

    @Test
    public void testString(){
        //添加多条数据
        HashMap<String, String> map = new HashMap<>();
        map.put("name","hexiang");map.put("age","21");map.put("sex","男");
        ValueOperations ops = redisTemplate.opsForValue();
        ops.multiSet(map);
        List<String> keys = new ArrayList<>();
        keys.add("name");keys.add("age");keys.add("sex");
        List lists = ops.multiGet(keys);
        lists.forEach(System.out::println);
        redisTemplate.delete(keys);
    }

    @Test
    public void testHash(){
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("user","name","hexiang");
        System.out.println(hashOperations.get("user", "name"));
        HashMap<String, String> map = new HashMap<>();
        map.put("age","21");map.put("sex","男");
        hashOperations.putAll("user",map);
        List<String> keys = new ArrayList<>();
        keys.add("name");keys.add("age");keys.add("sex");
        List user = hashOperations.multiGet("user", keys);
        user.forEach(System.out::println);
        //获取hash类型的所有数据
        Map entries = hashOperations.entries("user");
        entries.forEach((key, value) -> {
            System.out.println(key+"--->"+value);
        });
        hashOperations.delete("user","name","age","sex");
    }

    @Test
    public void testList(){
        ListOperations ops = redisTemplate.opsForList();
        ops.leftPush("student","he");
        ops.rightPush("students","xiang");
        List student = ops.range("student", 0, 1);
        student.forEach(System.out::println);
        System.out.println(student.size());
        ops.remove("student",1,"he");

    }

    @Test
    public void testSet(){
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add("letters","a","b","c","d","e");
        //setOperations.add("letters",new String[]{"a","b","c","d","e"});
        Set set = setOperations.members("letters");
        set.forEach(System.out::println);
        setOperations.remove("letters","a","b","c","d","e");
    }

    @Test
    public void testGeneral(){
        Set keys = redisTemplate.keys("*");
        keys.forEach(System.out::println);
        ValueOperations ops = redisTemplate.opsForValue();
        //设置失效时间，添加key的时候
        ops.set("code","test",30, TimeUnit.SECONDS);
        //给已经存在的key设置
        redisTemplate.expire("name",30,TimeUnit.SECONDS);
        //查看失效时间
        System.out.println(redisTemplate.getExpire("code"));

    }
}
