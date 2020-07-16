package com.xufq.userserver.dao;

import com.xufq.practicecore.aspect.annotation.MybatisRepository;
import com.xufq.userserver.entity.UserEntity;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/7/2020 07:33 PM
 * @Version 1.0
 */
@MybatisRepository
public interface UserDao {

    UserEntity getUser(UserEntity user);

    int saveUser(UserEntity user);

    int updateUser(UserEntity user);
}
