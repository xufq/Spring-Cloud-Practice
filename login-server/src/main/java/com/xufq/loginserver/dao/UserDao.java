package com.xufq.loginserver.dao;

import com.xufq.loginserver.entity.UserEntity;
import com.xufq.loginserver.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/7/2020 07:33 PM
 * @Version 1.0
 */
@Repository
@Mapper
public interface UserDao {

    UserVo getUser(UserEntity user);
}
