package com.lxl.webtool.commonutils;/**
 * Description:
 *
 * @author lxl
 * @date ${date} ${time}
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * ClassName MyRedisUtils
 *
 * @Author bruce
 * @Date 2021/4/12  18:54
 * Version 1.0
 **/
@Component
public class MyRedisUtils {

    @Autowired
    private RedisTemplate<String, String> redisTemplateProxy;

    private static RedisTemplate<String, String> redisTemplate;


    /**
     * PostConstruct用于在完成依赖项注入以执行任何初始化之后需要执行的方法
     */
    @PostConstruct
    public void init() {

        //不使用特殊字符
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplateProxy.setKeySerializer(stringSerializer);
        redisTemplateProxy.setValueSerializer(stringSerializer);
        redisTemplateProxy.setHashKeySerializer(stringSerializer);
        redisTemplateProxy.setHashValueSerializer(stringSerializer);

        MyRedisUtils.redisTemplate = this.redisTemplateProxy;
    }


    /**
     * 01.插入redis值
     *
     * @param key
     * @param val
     * @param seconds 秒
     */
    public static void insertVal(String key, String val, int seconds) {

        try {
            if (redisTemplate.hasKey(key)) {
                redisTemplate.opsForValue().set(key, val, redisTemplate.getExpire(key, TimeUnit.SECONDS), TimeUnit.SECONDS);
            } else {
                redisTemplate.boundValueOps(key).set(val, seconds, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getVal(String key) {
        try {
            Object dbObjVal = redisTemplate.opsForValue().get(key);
            return dbObjVal == null ? "" : String.valueOf(dbObjVal);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
