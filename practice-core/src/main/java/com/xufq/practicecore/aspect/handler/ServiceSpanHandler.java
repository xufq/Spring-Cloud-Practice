package com.xufq.practicecore.aspect.handler;

import brave.ScopedSpan;
import brave.Span;
import brave.Tracer;
import brave.propagation.TraceContext;
import com.xufq.practicecore.utils.AspectUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @ClassName ServiceLogger
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 10/28/2019 10:40 PM
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
@DependsOn("myPointCut")
@ConditionalOnProperty(name="com.xufq.enable-service-span", matchIfMissing = false)
public class ServiceSpanHandler {

    @Autowired
    private Tracer tracer;

    @Around("com.xufq.practicecore.aspect.pointcut.MyPointCut.serviceMethod()")
    public Object doService(ProceedingJoinPoint pjp) throws Throwable{
        log.debug("method:{}--params:{}", pjp.getSignature().getName(), AspectUtil.getParams(pjp));
        Signature methodSignature = pjp.getSignature();
        Span currentSpan = tracer.currentSpan();
        TraceContext context = currentSpan.context();
        ScopedSpan newSpan = tracer.startScopedSpanWithParent(methodSignature.getName(), context);
        Object obj;
        try {
            newSpan.tag("mvc.service.class", pjp.getSourceLocation().getWithinType().getTypeName());
            newSpan.tag("mvc.service.method", methodSignature.getName());
            obj = pjp.proceed();
        } finally {
            newSpan.finish();
        }
        return obj;
    }
}
