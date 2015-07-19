/**
 * Copyright © 2015. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service;

import cn.mypandora.orm.Page;
import cn.mypandora.system.po.BaseLog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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

    @Test
    public void addLog(){
        BaseLog log=new BaseLog();
        log.setIp("127.0.0.1");
        log.setName("测试一");
        log.setDescription("这样可以吗？");
        for(int i=0;i<10;i++){
            service.addLog(log);
        }
    }

    @Test
    public void deleteLog(){
        service.deleteLog(11L);
    }

    @Test
    public void findLogByCondition(){
        Page<BaseLog> pageLog=new Page<>();
        pageLog.setCurrentPage(1);
        pageLog.setPageSize(3);
        pageLog=service.findLogByCondition("pageLogs",null,pageLog);
        Assert.assertTrue(pageLog.getResultList().size()==3);
    }
}
