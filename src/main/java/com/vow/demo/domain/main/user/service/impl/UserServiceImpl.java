package com.vow.demo.domain.main.user.service.impl;

import com.vow.demo.domain.main.user.entity.User;
import com.vow.demo.domain.main.user.mapper.UserMapper;
import com.vow.demo.domain.main.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vow.demo.domain.param.UserSaveParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vow
 * @since 2022-11-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public Long saveUser(UserSaveParam param) {
        User user = new User();
        BeanUtils.copyProperties(param, user);
        LocalDateTime now = LocalDateTime.now();
        user.setAddTime(now);
        user.setUpdTime(now);
        this.save(user);
        return user.getUserId();
    }
}
