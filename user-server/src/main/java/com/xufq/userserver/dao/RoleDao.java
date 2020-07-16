package com.xufq.userserver.dao;

import com.xufq.practicecore.aspect.annotation.MybatisRepository;
import com.xufq.userserver.entity.RoleEntity;

@MybatisRepository
public interface RoleDao {

    RoleEntity getRole(RoleEntity roleEntity);

    int saveRole(RoleEntity roleEntity);

    int updateRole(RoleEntity roleEntity);
}
