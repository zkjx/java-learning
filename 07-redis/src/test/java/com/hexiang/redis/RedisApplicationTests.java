package com.hexiang.redis;

import com.hexiang.redis.pojo.User;
import com.hexiang.redis.utils.SerializeUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.params.SetParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: hexiang
 * @date: 2022/3/6
 * @description:
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisApplicationTests {
    @Autowired
    private JedisPool jedisPool;

    private Jedis jedis = null;

    //初始化jedis实例对象
    @Before
    public void initConnection(){
        jedis = jedisPool.getResource();
    }

    @Test
    public void testString(){
        //添加数据
        jedis.mset("name","hexiang","sex","男");
        //获取数据
        List<String> list = jedis.mget("name", "sex");
        list.forEach(System.out::println);
        jedis.del("name","sex");
    }

    @Test
    public void testHashMap(){
        //添加单条数据
        jedis.hset("user","name","hexiang");
        String result = jedis.hget("user", "name");
        System.out.println(result);
        //添加多条数据
        Map<String, String> map = new HashMap<>();
        map.put("age","20");
        map.put("sex","男");
        jedis.hmset("user",map);
        //获取多条数据
        List<String> list = jedis.hmget("user", "age", "sex");
        list.forEach(System.out::println);
        //获取hash类型的所有数据
        Map<String, String> user = jedis.hgetAll("user");
        user.forEach((key, value) -> System.out.println(key + "--->" + value));
        //删除数据
        jedis.del("user","name","age","sex");
        jedis.del("user");
    }

    @Test
    public void testList(){
        //左添加
        jedis.lpush("students","hexiang","dongkangpin");
        //右添加
        jedis.rpush("students","laiweifan","huangwenbin");
        //获取数据
        List<String> students = jedis.lrange("students", 0, 3);
        students.forEach(System.out::println);
        //获取总条数
        Long total = jedis.llen("students");
        System.out.println(total);
        System.out.println("----------");
        //左弹出
        String lpop = jedis.lpop("students");
        System.out.println(lpop);
        //右弹出
        String rpop = jedis.rpop("students");
        System.out.println(rpop);
        //删除数据
        jedis.lrem("students",1,"huangwenbin");
        jedis.del("students");
    }

    @Test
    public void testSet(){
        //添加数据
        jedis.sadd("letters","a","b","c","d","e");
        //获取数据
        Set<String> letters = jedis.smembers("letters");
        letters.forEach(System.out::println);
        //获取总条数
        Long len = jedis.scard("letters");
        System.out.println(len);
        //删除数据
        jedis.srem("letters","a","b");
        jedis.del("letters");
    }

    @Test
    public void testSortedSet(){
        Map<String, Double> map = new HashMap<>();
        map.put("huangwenbing",4D);
        map.put("hexiang",1D);
        map.put("laiweifan",3D);
        map.put("dongkangpin",2D);
        //添加数据
        jedis.zadd("score",map);
        //获取数据
        Set<String> set = jedis.zrange("score", 0, 3);
        set.forEach(System.out::println);
        //获取总条数
        Long len = jedis.zcard("score");
        System.out.println(len);
        //删除数据
        jedis.zrem("score","huangwenbin","laiweifan");
        jedis.del("score");
    }

    @Test
    public void testDir(){
        jedis.set("cart:user01:item01","apple");
        System.out.println(jedis.get("cart:user01:item01"));
        jedis.del("cart:user01:item01");
    }

    @Test
    public void testExpire(){
        //给已经存在key设置失效时间
        jedis.set("code","test");
        //设置失效时间，单位秒
        jedis.expire("code",30);
        //设置失效时间，单位毫秒
        jedis.pexpire("code",30000);
        //查看失效时间，-1为不失效，-2为失效
        jedis.ttl("code");

        //添加key的时候，设置失效时间
        //设置失效时间，单位秒
        jedis.setex("code",30,"test");
        //设置失效时间，单位毫秒
        jedis.psetex("code",30000,"test");
        Long pttl = jedis.pttl("code");
        System.out.println(pttl);

        //nx.xx的用法
        SetParams setParams = new SetParams();
        //不存在的时候才能设置成功
        setParams.nx();
        //存在的时候才能设置成功
        setParams.xx();
        //设置失效时间，单位秒
        setParams.ex(30);
        setParams.px(30000);
        jedis.set("code","test",setParams);
    }

    @Test
    public void testAllKey(){
        //查询当前数据库key的数量
        Long dbSize = jedis.dbSize();
        System.out.println(dbSize);
        //查询当前数据库的所有key
        Set<String> keys = jedis.keys("*");
        keys.forEach(System.out::println);
    }

    @Test
    public void testMulti(){
        //开启事务
        Transaction tx = jedis.multi();
        tx.set("tel","10086");
        //提交事务
        tx.exec();
        //回滚事务
        tx.discard();

    }

    @Test
    public void testByte(){
        User user = new User(2022,"hexiang","root");
        //序列化为byte数组
        byte[] userKey = SerializeUtil.serialize("user:" + user.getId());
        byte[] userValue = SerializeUtil.serialize(user);
        //存入redis
        jedis.set(userKey,userValue);
        byte[] bytes = jedis.get(userKey);
        User deserializeUser = SerializeUtil.deserialize(bytes, User.class);
        System.out.println(deserializeUser);
        jedis.del(userKey);

    }

    //释放资源
    @After
    public void closeConnection(){
        if(jedis!=null) jedis.close();
    }




    /**
     * 未封装的redis
     */
    //@Test
    //public void testInitConnect(){
    //    /*
    //    * @Author: 何翔
    //    * @Description: 连接redis
    //    * @DateTime: 2022/3/6 15:34
    //    * @Params: []
    //    * @Return void
    //    */
    //    //创建jedis对象，连接redis服务器
    //    Jedis jedis = new Jedis("192.168.238.128",6379);
    //    //设置认证密码
    //    jedis.auth("root");
    //    //指定数据库，默认0
    //    jedis.select(0);
    //    //使用ping命令，测试连接是否成功
    //    String result = jedis.ping();
    //    System.out.println(result);
    //    //添加一条数据
    //    jedis.set("name","hexiang");
    //    //获取一条数据
    //    result = jedis.get("name");
    //    System.out.println(result);
    //    if(jedis!=null)jedis.close();
    //}
    //
    //@Test
    //public void testInitConnectByPool(){
    //    //初始化jedis连接池对象
    //    JedisPool jedisPool = new JedisPool(new JedisPoolConfig(),"192.168.238.128",6379,10000,"root");
    //    //从连接池获取jedis对象
    //    Jedis jedis = jedisPool.getResource();
    //    String result = jedis.ping();
    //    System.out.println(result);
    //    //删除一条数据
    //    jedis.del("name");
    //    //获取一条数据
    //    result = jedis.get("name");
    //    System.out.println(result);
    //    if(jedis!=null)jedis.close();
    //}






}
