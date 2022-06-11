package com.hexiang.springDataRedis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author: hexiang
 * @date: 2022/3/8
 * @description:
 */

@Configuration
public class RedisConfig {

    @Bean
    public  RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        //为String类型的key设置序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //为String类型的value设置序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //为HASH类型的key设置序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //为HASH类型的value设置序列化
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        //放置连接工厂
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return redisTemplate;
    }

    //@Bean
    //public RedisSentinelConfiguration redisSentinelConfiguration(){
    //    RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration()
    //            .master("")
    //            .sentinel("",12345);
    //    redisSentinelConfiguration.setPassword("root");
    //    return redisSentinelConfiguration;
    //}


}
