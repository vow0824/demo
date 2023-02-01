package com.vow.demo.domain.main.user.service;

import com.vow.demo.domain.main.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vow.demo.domain.param.UserSaveParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author vow
 * @since 2022-11-21
 */
public interface IUserService extends IService<User> {

    Long saveUser(UserSaveParam param);

}
