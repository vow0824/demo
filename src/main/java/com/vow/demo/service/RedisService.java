package com.vow.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author: wushaopeng
 * @date: 2023/2/2 16:56
 */
@Service
@Slf4j
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedissonClient redisson;

    private static final String HELLO_WORLD_LOCK = "hello_world";

    public void set() {
        stringRedisTemplate.opsForValue().set("a", "b");
    }

    public String get() {
        return stringRedisTemplate.opsForValue().get("a");
    }

    public void printHelloWorld() {
        RLock lock = redisson.getLock(HELLO_WORLD_LOCK);
        lock.lock(2, TimeUnit.SECONDS);
        log.info("hello world!");
        lock.unlock();
    }
}
