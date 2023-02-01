package com.vow.demo.domain.shard.appOrder.service.impl;

import com.vow.demo.domain.param.OrderSaveParam;
import com.vow.demo.domain.shard.appOrder.entity.AppOrder;
import com.vow.demo.domain.shard.appOrder.mapper.AppOrderMapper;
import com.vow.demo.domain.shard.appOrder.service.IAppOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vow
 * @since 2023-02-01
 */
@Service
public class AppOrderServiceImpl extends ServiceImpl<AppOrderMapper, AppOrder> implements IAppOrderService {

    @Override
    public void saveOrder(OrderSaveParam orderSaveParam) {
        AppOrder appOrder = new AppOrder();
        BeanUtils.copyProperties(orderSaveParam, appOrder);
        LocalDateTime now = LocalDateTime.now();
        appOrder.setAddTime(now);
        appOrder.setUpdTime(now);
        this.save(appOrder);
    }
}
