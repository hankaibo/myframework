package cn.mypandora.service;

import cn.mypandora.po.BasePermission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 权限JUnit测试。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
// 1基于JUnit4的Spring测试框架
@RunWith(SpringJUnit4ClassRunner.class)
// 2启动Spring容器
@ContextConfiguration(locations = {"/applicationContext-test.xml", "/applicationContext-shiro.xml"})
public class TestPermissionService {
    protected BasePermission p1;
    protected BasePermission p2;
    protected BasePermission p3;
    // 3注入Spring容器中的Bean
    @Resource
    private BasePermissionService service;

    @Test
    public void addPermission() {
        //1、新增权限
        p1 = new BasePermission("user:create", "用户1模块新增", Boolean.TRUE);
        p2 = new BasePermission("user:update", "用户1模块修改", Boolean.TRUE);
        p3 = new BasePermission("menu:create", "菜单1模块新增", Boolean.TRUE);
        service.addPermission(p1);
        service.addPermission(p2);
        service.addPermission(p3);
    }

    @Test
    public void deletePermission() {
        service.deletePermission(2L);
    }
}
