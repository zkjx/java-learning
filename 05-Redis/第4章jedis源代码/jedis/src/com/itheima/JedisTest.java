package com.itheima;

import com.itheima.util.JedisUtils;
import redis.clients.jedis.Jedis;

public class JedisTest {

    public static void main(String[] args) {
        //1.获取连接对象
//        Jedis jedis = new Jedis("192.168.40.130",6379);
        Jedis jedis = JedisUtils.getJedis();
        //2.执行操作
//        jedis.set("age","39");
//        String hello = jedis.get("hello");
//        System.out.println(hello);
//        jedis.lpush("list1","a","b","c","d");
//        List<String> list1 = jedis.lrange("list1", 0, -1);
//        for (String s:list1 ) {
//            System.out.println(s);
//        }
        jedis.sadd("set1","abc","abc","def","poi","cba");
        Long len = jedis.scard("set1");
        System.out.println(len);
        //3.关闭连接
        jedis.close();
    }
}
