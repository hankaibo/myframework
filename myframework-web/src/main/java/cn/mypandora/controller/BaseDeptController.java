/**
 * Copyright © 2015.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.controller;

import cn.mypandora.po.BaseDept;
import cn.mypandora.service.BaseDeptService;
import cn.mypandora.util.MyTreeUtil;
import cn.mypandora.vo.ParentChildTree;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>User: hankaibo
 * <p>Date: 2016/9/26
 * <p>Version: 1.0
 */
@Api(value = "dept-api", description = "部门的CURD操作")
@Controller
@RequestMapping(value = "/depts")
public class BaseDeptController {
    @Resource
    private BaseDeptService baseDeptService;

    /**
     * 跳转到部门首页。
     *
     * @return 跳转到部门首页
     */
    @RequestMapping(value="gotoList",method = RequestMethod.GET)
    public String gotoList() {
        return "dept/list";
    }

    /**
     * 获取整个部门树。
     *
     * @return 获取整个部门树
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ParentChildTree> list(ModelMap model) {
        List<BaseDept> listDepts = baseDeptService.loadFullDept();

        List<ParentChildTree> listPCTrees = new ArrayList<>();
        for (BaseDept dept : listDepts) {
            listPCTrees.add(MyTreeUtil.lfNode2pcNode(dept));
        }
        return listPCTrees;
    }

    /**
     * 添加部门
     * @return 添加部门
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(Long id) {
        Map<String, Object> map = new HashMap<>();
        BaseDept dept = baseDeptService.findDeptById(id);
        map.put("name", "testName");
        map.put("lft", dept.getRgt());
        map.put("rgt", dept.getRgt() + 1);
        map.put("parentId", dept.getId());
        baseDeptService.addDept(id, map);
        return map;
    }

    /**
     * 编辑部门
     * @return 编辑部门
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String edit(@RequestBody BaseDept dept) {
        baseDeptService.updateDept(dept);
        return "true";
    }

    /**
     * 删除一个部门（叶子节点）
     * @return 删除一个部门（叶子节点）
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String del(@PathVariable Long id) {
        baseDeptService.delDept(id);
        return "true";
    }

    /**
     * @param id   弟弟节点ID
     * @param upId 哥哥节点ID
     * @return 上移。（同级叶子节点）
     */
    @RequestMapping(value = "/{id}/{upId}/up", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, String> moveUp(@PathVariable Long id, @PathVariable Long upId) {
        Map<String, String> result = new HashMap<>();
        baseDeptService.moveUpDept(id, upId);

        result.put("result", "true");
        return result;
    }

    /**
     * @param id     哥哥节点ID
     * @param downId 弟弟节点ID
     * @return 下移。（同级叶子节点）
     */
    @RequestMapping(value = "{id}/{upId}/down", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> moveDown(@PathVariable Long id, @PathVariable Long downId) {
        Map<String, Object> result = new HashMap<>();
        baseDeptService.moveDownDept(id, downId);

        result.put("result", "true");
        return result;
    }

}
