package com.vow.demo.Controller;

import com.vow.demo.domain.vo.CarsItem;
import com.vow.demo.domain.vo.Shakespeare;
import com.vow.demo.service.EsService;
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

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/searchShakespeare")
    public Result<List<Shakespeare>> searchShakespeare() {
        return Result.success(esService.searchShakespeare());
    }
}
