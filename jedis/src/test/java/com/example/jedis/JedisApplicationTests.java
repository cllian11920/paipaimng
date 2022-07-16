package com.example.jedis;

import com.alibaba.fastjson.JSON;
import com.example.jedis.service.CacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class JedisApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private CacheService cacheService;

    @Test
    public void add() {
        String value = "1234";
        System.out.println(JSON.toJSONString(value));
        cacheService.add("test", value);
    }
    /**
     * 功能描述：添加对象至redis
     */
    @Test
    void addObject() {
        User user = new User(10,"张三",10);
        cacheService.add(user.getId(), user);
    }


    /**
     * 功能描述：添加对象集合至redis
     */
    @Test
    void addObjects() {
        ArrayList<User> users = new ArrayList();
        User user = new User(10,"张三",10);
        users.add(user);
        cacheService.add("key", users, 1, TimeUnit.HOURS);
    }

    /**
     * 功能描述：获取对象
     */
    @Test
    void getObject() {
        User object = cacheService.getObject(10, User.class);
        System.out.println("object = " + object);
    }

    /**
     * 功能描述：获取对象集合
     */
    @Test
    void getObjects() {
        List<User> users = cacheService.getList("key", User.class);
        System.out.println("users = " + users);
    }
    /**
     * 功能描述：添加 hash-set
     */
    @Test
    void addHashCache() {
        cacheService.addHashCache("hashKey", "key", "value");
    }


    /**
     * 功能描述：获取hash-set
     */
    @Test
    void getHashCache() {
        System.out.println(cacheService.getHashCache("hashKey", "key"));
    }
}
