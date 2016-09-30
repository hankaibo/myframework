/**
 * Copyright © 2015. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service;

import cn.mypandora.po.BaseRes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源JUnit测试
 * <p>User: hankaibo
 * <p>Date: 2015/7/21
 * <p>Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class TestResService {
    @Resource
    private BaseResService service;

    @Test
    public void loadFullRes() {
        myPrint(service.loadFullRes());
    }

    @Test
    public void loadFullResWithLevel() {
        myPrint(service.loadFullResWithLevel(3));
    }

    @Test
    public void getResDescendant() {
        myPrint(service.getResDescendant(2L));
    }

    @Test
    public void getResChild() {
        myPrint(service.getResChild(3L));
    }

    @Test
    public void getResParent() {
        BaseRes res=service.getResParent(3L);
        if (null != res) {
            System.out.println(res.getUrl() + "__" + res.getName());
        }
    }

    @Test
    public void getResAncestry() {
        myPrint(service.getResAncestry(3L));
    }

    @Test
    public void addRes() {
        Map<String, Object> map = new HashMap<>();
        //添加根节点
//        map.put("name", "美");
//        map.put("lft", 1);
//        map.put("rgt", 2);
//        map.put("parentId", 0);
//        map.put("isEnable", true);
//        map.put("url", "www.google.com");
//        service.addRes(0L, map);
        //添加非根节点
        BaseRes res = service.findResById(2L);
        map.put("name", "特种法院3");
        map.put("lft", res.getRgt());
        map.put("rgt", res.getRgt() + 1);
        map.put("parentId", res.getId());
        map.put("isEnable", true);
        map.put("url", "www.google.com");
        service.addRes(res.getId(), map);
    }

    @Test
    public void delRes() {
        service.delRes(4L);
    }

    @Test
    public void moveUpRes() {
        service.moveUpRes(3L, 5L);
    }

    @Test
    public void moveDownRes() {
        service.moveDownRes(3L, 5L);
    }

    @Test
    public void findResById() {
        BaseRes baseRes=service.findResById(1L);
        org.junit.Assert.assertNotNull(baseRes);
    }

    @Test
    public void updateRes() {
        BaseRes res = service.findResById(2L);
        res.setEnable(true);
        service.updateRes(res);
    }

    /*----------测试方法结束----------*/
    private void myPrint(List<BaseRes> list) {
        for (BaseRes res : list) {
            System.out.print(res.getId() + "___");
            System.out.print(res.getName());
            for (int i = 0; i < (17 - res.getName().length()); i++) {
                System.out.print(" ");
            }
            System.out.print("___" + (res.getLft() > 9 ? res.getLft() : "_" + res.getLft()));
            System.out.print("___" + (res.getRgt() > 9 ? res.getRgt() : "_" + res.getRgt()));
            System.out.println("___" + res.getParentId());
            System.out.println("___" + res.getUrl());
            System.out.println("___" + res.isEnable());
        }
    }

}
