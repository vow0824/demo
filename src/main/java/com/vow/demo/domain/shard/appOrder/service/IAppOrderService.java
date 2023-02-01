package com.vow.demo.domain.shard.appOrder.service;

import com.vow.demo.domain.param.OrderSaveParam;
import com.vow.demo.domain.shard.appOrder.entity.AppOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author vow
 * @since 2023-02-01
 */
public interface IAppOrderService extends IService<AppOrder> {

    void saveOrder(OrderSaveParam orderSaveParam);
}
