package com.xufq.practicecore.aspect.handler;

import com.xufq.practicecore.utils.AspectUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @ClassName ControllerLogger
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 10/8/2019 09:57 PM
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
@DependsOn("myPointCut")
@ConditionalOnProperty(name = "com.xufq.enable-controller-log", matchIfMissing = false)
public class ControllerLoggerHandler {

    @Before("com.xufq.practicecore.aspect.pointcut.MyPointCut.controllerMethod()")
    public void printLog(JoinPoint joinPoint) {
        log.debug("method:{}--params:{}", joinPoint.getSignature().getName(), AspectUtil.getParams(joinPoint));
    }

}
