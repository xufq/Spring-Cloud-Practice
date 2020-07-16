package com.xufq.practicecore.aspect.annotation;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repository
@Mapper
public @interface MybatisRepository {
}
