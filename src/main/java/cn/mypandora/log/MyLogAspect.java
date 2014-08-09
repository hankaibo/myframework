/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.log 
 * @ClassName: MyLogAspect 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-4-27 下午3:02:55 
 *
 */
package cn.mypandora.log;

import java.lang.reflect.Method;
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.mypandora.system.po.BaseLog;
import cn.mypandora.system.service.BaseLogService;

/**
 * @ClassName: MyLogAspect
 * @Description: 增强类。
 * @Author: kaibo
 * @date: 2014-4-27
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-4-27 下午3:02:55
 * @UpdateRemark: What is modified?
 */
@Aspect
public class MyLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(MyLogAspect.class);

    @Resource
    private BaseLogService service;

    @Pointcut("execution (* cn.mypandora.system.service.impl.*.*(..))")
    public void pointcut() {
    }

    /**
     * @Title: doSuccessLog
     * @Description: 方法成功执行后调用
     * @param point
     * @throws Throwable
     * @return void
     */
    @After("pointcut()")
    public void afterSuccessLog(JoinPoint point) throws Throwable {
        //
        MethodSignature joinMethodSignature=(MethodSignature) point.getSignature();
        //连接点对象的方法和方法名
        Method method = joinMethodSignature.getMethod();
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        //
        Object target=point.getTarget();
        method=target.getClass().getMethod(methodName,parameterTypes);
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
     * @Title: afterThrowingLog
     * @Description: 方法运行出现异常时调用.
     * @param ex
     * @return void
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "ex")
    public void afterThrowingLog(Exception ex) {
        System.out.println("afterThrowing");
        System.out.println(ex);
    }

    /**
     * @Title: around
     * @Description: 方法执行的前后调用(主要用于性能检测时)
     * @param point
     * @return
     * @throws Throwable
     * @return Object
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        logger.debug("begin around");
        Object object = point.proceed();
        logger.debug("end around");
        return object;
    }

}
