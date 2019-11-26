package com.xufq.practicecore.aspect.pointcut;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @ClassName ControllerPointCut
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 10/31/2019 11:33 AM
 * @Version 1.0
 */
@Component("myPointCut")
public class MyPointCut {

    @Pointcut("execution(public * com.xufq..*.controller..*.*(..))")
    public void controllerMethod(){

    }

    @Pointcut("execution(public * com.xufq..*.service..*.*(..))")
    public void serviceMethod(){

    }
}
