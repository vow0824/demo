package com.vow.demo.controller;

import com.vow.demo.domain.vo.Shakespeare;
import com.vow.demo.service.EsService;
import com.vow.demo.service.RedisService;
import com.vow.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: wushaopeng
 * @date: 2022/11/21 15:05
 */
@RestController
public class HelloController {

    @Autowired
    private EsService esService;
    @Autowired
    private RedisService redisService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/searchShakespeare")
    public Result<List<Shakespeare>> searchShakespeare() {
        return Result.success(esService.searchShakespeare());
    }

    @GetMapping("/getValueFromRedis")
    public Result<String> getValueFromRedis() {
        redisService.set();
        return Result.success(redisService.get());
    }

    @GetMapping("/printHelloWorld")
    public Result<Void> printHelloWorld() {
        redisService.printHelloWorld();
        return Result.success();
    }
}
