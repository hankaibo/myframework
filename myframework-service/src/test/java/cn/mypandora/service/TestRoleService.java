/**
 * Copyright © 2015. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.service;

import cn.mypandora.po.BaseRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 角色JUnit测试。
 * <p>User: hankaibo
 * <p>Date: 2015/7/22
 * <p>Version: 1.0
 */
// 1基于JUnit4的Spring测试框架
@RunWith(SpringJUnit4ClassRunner.class)
// 2启动Spring容器
@ContextConfiguration(locations = {"/applicationContext-test.xml", "/applicationContext-shiro.xml"})
public class TestRoleService {
    protected BaseRole r1;
    protected BaseRole r2;
    @Resource
    private BaseRoleService service;

    @Test
    public void addRole() {
        r1 = new BaseRole("admin", "管理员21", Boolean.TRUE);
        r2 = new BaseRole("user", "用户管理员21", Boolean.TRUE);
        service.addRole(r1);
        service.addRole(r2);
    }

    @Test
    public void deleteRole() {
        service.deleteRole(2L);
    }

    @Test
    public void correlationPermission() {
        service.correlationPermission(4L,6L);
    }

    @Test
    public void uncorrelationPermission() {
        service.uncorrelationPermission(5L,6L);
    }


}
