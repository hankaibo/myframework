/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service;

import cn.mypandora.orm.Page;
import cn.mypandora.system.po.BaseUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


/**
 * 用户JUnit测试。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
// 1基于JUnit4的Spring测试框架
@RunWith(SpringJUnit4ClassRunner.class)
// 2启动Spring容器
@ContextConfiguration(locations = {"/applicationContext.xml", "/applicationContext-shiro.xml"})
public class TestUserService {
    // 3注入Spring容器中的Bean
    @Resource
    private BaseUserService service;

    // 4标注测试方法
    @Test
    public void loginSuccess() {
        BaseUser user = new BaseUser();
        user.setId(4L);
        user.setUsername("admin");
        user.setPassword("123456");

        service.loginSuccess(user);
    }

    @Test
    public void addUser() {
        BaseUser user = new BaseUser();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setPhone("12345678910");
        user.setSalt("salt");
        user.setCredit(0);
        user.setEmail("han@126.com");
        user.setLastIp("127.0.0.1");
        user.setLastVisit(new Timestamp(System.currentTimeMillis()));
        user.setLocked(false);
        user.setRealName("kaibo");
        user.setSex(0);

        service.addUser(user);
    }

    @Test
    public void delUser() {
        service.deleteUser(2L);
    }

    @Test
    public void deleteBatchUser() {
        Long[] arrLong = new Long[]{1L, 3L};
        service.deleteBatchUser(arrLong);
    }

    @Test
    public void updateUser() {
        BaseUser user = new BaseUser();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setPhone("12345678910");
        user.setSalt("salt");
        user.setCredit(1000);
        user.setEmail("han@126.com");
        user.setLastIp("127.0.0.1");
        user.setLastVisit(new Timestamp(System.currentTimeMillis()));
        user.setLocked(false);
        user.setRealName("kaibo");
        user.setSex(1);
        user.setId(4L);
        service.updateUser(user);
    }

    @Test
    public void changePassword() {
        service.changePassword(4L, "12345689");
    }

    @Test
    public void correlationRole() {
        Long l1 = 8L;
        Long[] l2 = {2l, 3l, 4l, 5l};
        service.correlationRole(l1, l2);
    }

    @Test
    public void uncorrelationRole() {
        Long l1 = 8L;
        Long[] l2 = {2l, 5l};
        service.uncorrelationRole(l1, l2);
    }

    @Test
    public void findUserById() {
        System.out.println(service.findUserById(4L).toString());
    }

    @Test
    public void hasMatchUser() {
        boolean b1 = service.hasMatchUser("admin", "1");
        boolean b2 = service.hasMatchUser("admin", "1111");
        assertFalse(b1);
        assertFalse(b2);
    }

    @Test
    public void findUserByUserName() {
        BaseUser user = service.findUserByUsername("admin");
        assertEquals(user.getUsername(), "admin");
    }

    @Test
    public void findPageUserByCondition() {
        Page<BaseUser> page = new Page<>();
        page.setCurrentPage(1);
        page.setPageSize(10);
        page = service.findPageUserByCondition("pageUsers", null, page);
        Assert.assertTrue(page.getResultList().size() > 0);
    }

    @Test
    public void findUserCount(){
//        service.findUserCount();
    }

    @Test
    public void findUserSexCount(){
        service.findUserSexCount();
    }

}
