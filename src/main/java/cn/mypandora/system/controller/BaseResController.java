/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */package cn.mypandora.system.controller;

import cn.mypandora.system.po.BaseRes;
import cn.mypandora.system.service.BaseResService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kaibo on 2014/8/5.
 * desc
 */
@Controller
@RequestMapping(value = "/resources")
public class BaseResController {
    @Resource
    private BaseResService baseResService;

    /**
     * @return String
     * @Title: list
     * @Description: 获取资源树。
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,List<BaseRes>> list() {
        Map<String,List<BaseRes>> map=new HashMap<>();
        List<BaseRes> listResoureces = baseResService.loadFullResWithLevel(1);
        map.put("listBaseRes",listResoureces);
        return map;
    }

    /**
     * @return String
     * @Title: toList
     * @Description: 跳转到列表页面。
     */
    @RequestMapping(value = "/toList", method = RequestMethod.GET)
    public String toList() {
        return "res/list";
    }

    /**
     * @param baseRes
     * @return
     * @Title: add
     * @Description: 添加资源。
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(BaseRes baseRes) {
        Map<String, Object> map = new HashMap<>();
        Long defaultNode = Long.valueOf(baseRes.getParentId()) != null ? Long.valueOf(baseRes.getParentId()) : 1L;
        BaseRes res = baseResService.findResById(defaultNode);
        map.put("name", baseRes.getName());
        map.put("url", baseRes.getUrl());
        map.put("isenable", baseRes.isEnable());
        map.put("lft", res.getRgt());
        map.put("rgt", res.getRgt() + 1);
        map.put("parentId", res.getId());
        baseResService.addRes(defaultNode, map);
        return map;
    }

    /**
     * @param res
     * @return String
     * @Title: edit
     * @Description: 编辑资源。
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public String edit(BaseRes res) {
        baseResService.updateRes(res);
        return "true";
    }

    /**
     * @param id
     * @return String
     * @Title: del
     * @Description: 删除一个资源（叶子节点）
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String del(@PathVariable Long id) {
        baseResService.delRes(id);
        return "true";
    }

    /**
     * @param id   弟弟节点ID
     * @param upId 哥哥节点ID
     * @return void
     * @Title: moveUp
     * @Description: 上移。（同级叶子节点）
     */
    @RequestMapping(value = "/{id}/{upId}/up", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, String> moveUp(@PathVariable Long id, @PathVariable Long upId) {
        Map<String, String> result = new HashMap<>();
        baseResService.moveUpRes(id, upId);

        result.put("result", "true");
        return result;
    }

    /**
     * @param id     哥哥节点ID
     * @param downId 弟弟节点ID
     * @return void
     * @Title: moveDown
     * @Description: 下移。（同级叶子节点）
     */
    @RequestMapping(value = "{id}/{upId}/down", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> moveDown(@PathVariable Long id, @PathVariable Long downId) {
        Map<String, Object> result = new HashMap<>();
        baseResService.moveDownRes(id, downId);

        result.put("result", true);
        return result;
    }
}
