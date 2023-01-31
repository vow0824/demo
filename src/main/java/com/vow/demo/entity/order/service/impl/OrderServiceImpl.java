package com.vow.demo.entity.order.service.impl;

import com.vow.demo.entity.order.entity.Order;
import com.vow.demo.entity.order.mapper.OrderMapper;
import com.vow.demo.entity.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vow
 * @since 2023-01-30
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
