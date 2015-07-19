/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service;

import cn.mypandora.system.po.BaseDept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门JUnit测试。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class TestDeptService {
    @Resource
    private BaseDeptService service;

    /*----------测试方法开始----------*/
    @Test
    public void loadFullDept() {
        List<BaseDept> listBaseDept = service.loadFullDept();
        myPrint(listBaseDept);
    }

    @Test
    public void getDeptDescendant() {
        List<BaseDept> list = service.getDeptDescendant(4L);
        myPrint(list);
    }

    @Test
    public void getDeptChild() {
        List<BaseDept> list = service.getDeptChild(2L);
        myPrint(list);
    }

    @Test
    public void getDeptParent() {
        BaseDept dept = service.getDeptParent(3L);
        if (null != dept) {
            System.out.println(dept.getId() + "__" + dept.getName());
        }
    }

    @Test
    public void getDeptAncestry() {
        List<BaseDept> list = service.getDeptAncestry(9L);
        myPrint(list);
    }

    @Test
    public void addDept() {
        //添加根节点
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "美国公民");
//        map.put("lft", 1);
//        map.put("rgt", 2);
//        map.put("parentId", 0);
//        service.addDept(0L, map);
        //添加非根节点
        Map<String, Object> map = new HashMap<>();
        BaseDept dept = service.findDeptById(4L);
        map.put("name", "特种法院4");
        map.put("lft", dept.getRgt());
        map.put("rgt", dept.getRgt() + 1);
        map.put("parentId", dept.getId());
        service.addDept(dept.getId(), map);
    }

    @Test
    public void delDept() {
        service.delDept(29L);
    }

    @Test
    public void findDeptById() {
        BaseDept dept=service.findDeptById(3L);
        System.out.println(dept.getName());
    }

    @Test
    public void updateDept() {
        BaseDept dept=service.findDeptById(1L);
        dept.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        service.updateDept(dept);
    }

    @Test
    public void moveDownDept(){
        service.moveDownDept(20L,21L);
    }

    @Test
    public void moveUpDept(){
        service.moveUpDept(20L,21L);
    }
    /*----------测试方法结束----------*/
    private void myPrint(List<BaseDept> list) {
        for (BaseDept dept : list) {
            System.out.print(dept.getId() + "___");
            System.out.print(dept.getName());
            for (int i = 0; i < (17 - dept.getName().length()); i++) {
                System.out.print(" ");
            }
            System.out.print("___" + (dept.getLft() > 9 ? dept.getLft() : "_" + dept.getLft()));
            System.out.print("___" + (dept.getRgt() > 9 ? dept.getRgt() : "_" + dept.getRgt()));
            System.out.println("___" + dept.getParentId());
        }
    }

}
