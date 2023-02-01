package com.vow.demo.domain.shard.appOrder.controller;

import com.vow.demo.domain.param.OrderSaveParam;
import com.vow.demo.domain.shard.appOrder.service.IAppOrderService;
import com.vow.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author vow
 * @since 2023-02-01
 */
@RestController
@RequestMapping("/appOrder")
public class AppOrderController {

    @Autowired
    private IAppOrderService appOrderService;

    @GetMapping("/save")
    public Result<Void> save() {
        OrderSaveParam orderSaveParam = new OrderSaveParam();
        orderSaveParam.setOrderId(System.currentTimeMillis());
        appOrderService.saveOrder(orderSaveParam);
        return Result.success();
    }
}
