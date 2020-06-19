package com.xufq.userserver.dao;

import com.xufq.practicecore.aspect.annotation.MybatisRepository;
import com.xufq.userserver.entity.UserEntity;
import com.xufq.userserver.entity.UserRoleEntity;

/**
 * @ClassName UserRoleDao
 * @Description save user role
 * @Author fangqiang.xu
 * @Date 2/7/2020 07:33 PM
 * @Version 1.0
 */
@MybatisRepository
public interface UserRoleDao {

    int saveUserRole(UserRoleEntity user);

    int updateUserRole(UserRoleEntity user);
}
