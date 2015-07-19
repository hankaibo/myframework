/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service.impl;

import cn.mypandora.system.dao.BaseResDao;
import cn.mypandora.system.po.BaseRes;
import cn.mypandora.system.service.BaseResService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 资源管理Service实现类。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Service
public class BaseResServiceImpl implements BaseResService {
    @Resource
    private BaseResDao dao;

    /**
     * 获取所有资源（一次性全部加载，适合数据量少的情况）。
     *
     * @return
     */
    @Override
    public List<BaseRes> loadFullRes() {
        return dao.loadFullTree();
    }

    /**
     * 获取带深度的所有资源。
     *
     * @param level
     * @return
     */
    @Override
    public List<BaseRes> loadFullResWithLevel(int level) {
        return dao.loadTreeWithLevel(level);
    }

    /**
     * 获得本资源（节点）及下面的所有资源（节点）。
     *
     * @param id 当前操作资源（节点）id
     * @return
     */
    @Override
    public List<BaseRes> getResDescendant(Long id) {
        return dao.getDescendant(id);
    }

    /**
     * 获得本资源（节点）的孩子资源（节点）。
     *
     * @param id 当前操作资源（节点）id
     * @return
     */
    @Override
    public List<BaseRes> getResChild(Long id) {
        return dao.getChild(id);
    }

    /**
     * 获得本资源（节点）的父资源（节点）
     *
     * @param id 当前操作资源（节点）id
     * @return
     */
    @Override
    public BaseRes getResParent(Long id) {
        return dao.getParent(id);
    }

    /**
     * 获得本资源（节点）的祖先资源（节点）。
     *
     * @param id 当前操作资源（节点）id
     * @return
     */
    @Override
    public List<BaseRes> getResAncestry(Long id) {
        return dao.getAncestry(id);
    }

    /**
     * 添加孩子资源（节点）
     *
     * @param id     父资源（节点）的nodeId
     * @param params 子资源（节点）的信息
     */
    @Override
    public void addRes(Long id, Object params) {
        dao.lftPlus2(id);
        dao.rgtPlus2(id);
        dao.insertNode(params);
        dao.parentRgtPlus2(id);
    }

    /**
     * 删除资源（节点）
     *
     * @param id 要删除的资源（节点）ID
     */
    @Override
    public void delRes(Long id) {
        dao.lftMinus2(id);
        dao.rgtMinus2(id);
        dao.delete(id);
    }

    /**
     * 上移某个资源（节点）(同级叶子之间,即把弟弟资源（节点）移到哥哥资源（节点）之前)
     *
     * @param id   弟弟资源（节点）ID
     * @param upId 哥哥资源（节点）ID
     */
    @Override
    public void moveUpRes(Long id, Long upId) {
        // 当前节点不是首节点
        if (!dao.isFirstNode(id)) {
            // 弟弟（自身）节点左右值减2
            dao.brotherMinus2(id);
            // 哥哥节点左右值加去2
            dao.brotherPlus2(upId);
        }
    }

    /**
     * 下移某个资源（节点）(同级叶子之间,即把哥哥资源（节点）移到弟弟资源（节点）之后)
     *
     * @param id     哥哥资源（节点）ID
     * @param downId 弟弟资源（节点）ID
     */
    @Override
    public void moveDownRes(Long id, Long downId) {
        // 当前节点不是末节点
        if (!dao.isLastNode(id)) {
            // 哥哥节点（自身）左右值加2
            dao.brotherPlus2(id);
            // 弟弟节点左右值减2
            dao.brotherMinus2(downId);
        }
    }

    /**
     * 查询一个资源。
     *
     * @param id
     * @return
     */
    @Override
    public BaseRes findResById(Long id) {
        return dao.findById(id);
    }

    /**
     * 更新一个资源。
     *
     * @param res
     */
    @Override
    public void updateRes(BaseRes res) {
        dao.update(res);
    }


}
