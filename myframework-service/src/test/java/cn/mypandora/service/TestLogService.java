/**
 * Copyright © 2015. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.service;

import cn.mypandora.po.BaseLog;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * 日志JUnit测试。
 * <p>User: hankaibo
 * <p>Date: 2015/7/19
 * <p>Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class TestLogService {
    @Resource
    private BaseLogService service;

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void addLog() {
        BaseLog log = new BaseLog();
        log.setIp("127.0.0.1");
//        log.setName("测试12");
        log.setDescription("这样可以吗？");
        for (int i = 0; i < 2; i++) {
            service.addLog(log);
        }
        Set<ConstraintViolation<BaseLog>> constraintViolations = validator.validate(log);
        assertEquals(1, constraintViolations.size());
        assertEquals("不能为null", constraintViolations.iterator().next().getMessage());

    }

    @Test
    public void deleteLog() {
        service.deleteLog(37L);
    }

    @Test
    public void deleteBatchLog() {
        Long[] arrLong = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L};
        service.deleteBatchLog(arrLong);
    }

    @Test
    public void findAllLog() {
        for (BaseLog log : service.findAllLog()) {
            System.out.println(log.getName());
        }
    }

    @Test
    public void findLogByCondition() {
        PageInfo<BaseLog> pageLog = new PageInfo<>();
        pageLog.setPageNum(2);
        pageLog.setPageSize(7);
        pageLog = service.findLogByCondition("pageLogs", null, pageLog);
        Assert.assertTrue(pageLog.getList().size() == 7);
        Assert.assertTrue(pageLog.getTotal() > 33);
        System.out.println(pageLog.toString());
    }
}
