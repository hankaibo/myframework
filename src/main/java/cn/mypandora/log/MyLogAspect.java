/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.log;

import cn.mypandora.system.po.BaseLog;
import cn.mypandora.system.service.BaseLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.sql.Timestamp;

/**
 * 增强类。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Aspect
public class MyLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(MyLogAspect.class);

    @Resource
    private BaseLogService service;

    /**
     *
     */
    @Pointcut("execution (* cn.mypandora.system.service.impl.*.*(..))")
    public void pointcut() {
    }

    /**
     * 方法成功执行后调用
     *
     * @param point
     * @throws Throwable
     */
    @After("pointcut()")
    public void afterSuccessLog(JoinPoint point) throws Throwable {
        //
        MethodSignature joinMethodSignature = (MethodSignature) point.getSignature();
        //连接点对象的方法和方法名
        Method method = joinMethodSignature.getMethod();
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        //
        Object target = point.getTarget();
        method = target.getClass().getMethod(methodName, parameterTypes);
        if (method != null) {
            boolean hasAnnotation = method.isAnnotationPresent(MyMethodAnno.class);
            if (hasAnnotation) {
                MyMethodAnno anno = method.getAnnotation(MyMethodAnno.class);
                BaseLog log = new BaseLog();
                log.setCreateTime(new Timestamp(System.currentTimeMillis()));
                log.setUsername("稍后获取");
                log.setIp("稍后获取");
                log.setDescription(anno.description());

                service.addLog(log);
            }
        }
    }

    /**
     * 方法运行出现异常时调用.
     *
     * @param ex
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "ex")
    public void afterThrowingLog(Exception ex) {
        System.out.println("afterThrowing");
        System.out.println(ex);
    }

    /**
     * 方法执行的前后调用(主要用于性能检测时)
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        logger.debug("begin around");
        Object object = point.proceed();
        logger.debug("end around");
        return object;
    }

}
