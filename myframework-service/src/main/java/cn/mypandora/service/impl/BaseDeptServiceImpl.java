/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service.impl;

import cn.mypandora.system.dao.BaseDeptDao;
import cn.mypandora.po.BaseDept;
import cn.mypandora.system.service.BaseDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门管理Service实现类。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Service
public class BaseDeptServiceImpl implements BaseDeptService {
    @Resource
    private BaseDeptDao dao;

    /**
     * 获取所有部门（一次性全部加载，适合数据量少的情况）。
     *
     * @return 所有部门数据
     */
    @Override
    public List<BaseDept> loadFullDept() {
        return dao.loadFullTree();
    }

    /**
     * 获得本部门（节点）下面的所有后代部门（节点）。
     *
     * @param id 当前操作部门（节点）id
     * @return 指定部门下的所有后代部门
     */
    @Override
    public List<BaseDept> getDeptDescendant(Long id) {
        return dao.getDescendant(id);
    }

    /**
     * 获得本部门（节点）的孩子部门（节点）。
     *
     * @param id 当前操作部门（节点）id
     * @return 指定部门下的所有部门
     */
    @Override
    public List<BaseDept> getDeptChild(Long id) {
        return dao.getChild(id);
    }

    /**
     * 获得本部门（节点）的父部门（节点）
     *
     * @param id 当前操作部门（节点）id
     * @return 本部门的父部门
     */
    @Override
    public BaseDept getDeptParent(Long id) {
        return dao.getParent(id);
    }

    /**
     * 获得本部门（节点）的祖先部门（节点）
     *
     * @param id 当前操作部门（节点）id
     * @return 本部门的祖先部门
     */
    @Override
    public List<BaseDept> getDeptAncestry(Long id) {
        return dao.getAncestry(id);
    }

    /**
     * 添加孩子部门（节点）
     *
     * @param id     父部门（节点）的nodeId
     * @param params 子部门（节点）的信息
     */
    @Override
    @Transactional
    public void addDept(Long id, Object params) {
        dao.lftPlus2(id);
        dao.rgtPlus2(id);
        dao.insertNode(params);
        dao.parentRgtPlus2(id);
    }

    /**
     * 删除部门（节点）
     *
     * @param id 要删除的部门ID
     */
    @Override
    @Transactional
    public void delDept(Long id) {
        dao.lftMinus2(id);
        dao.rgtMinus2(id);
        dao.delete(id);
    }

    /**
     * 上移某个部门（节点）(同级叶子之间,即把弟弟部门（节点）移到哥哥部门（节点）之前)
     *
     * @param id   弟弟部门（节点）ID
     * @param upId 哥哥部门（节点）ID
     */
    @Override
    @Transactional
    public void moveUpDept(Long id, Long upId) {
        // 当前节点不是首节点
        if (!dao.isFirstNode(id)) {
            // 弟弟（自身）节点左右值减2
            dao.brotherMinus2(id);
            // 哥哥节点左右值加去2
            dao.brotherPlus2(upId);
        }

    }

    /**
     * 下移某个部门（节点）(同级叶子之间,即把哥哥部门（节点）移到弟弟部门（节点）之后)
     *
     * @param id     哥哥部门（节点）ID
     * @param downId 弟弟部门（节点）ID
     */
    @Override
    @Transactional
    public void moveDownDept(Long id, Long downId) {
        // 当前节点不是末节点
        if (!dao.isLastNode(id)) {
            // 哥哥节点（自身）左右值加2
            dao.brotherPlus2(id);
            // 弟弟节点左右值减2
            dao.brotherMinus2(downId);
        }
    }

    /**
     * 查询一个部门。
     *
     * @param id 当前操作部门（节点）id
     * @return 一个部门
     */
    @Override
    public BaseDept findDeptById(Long id) {
        return dao.findById(id);
    }

    /**
     * 更新一个部门。
     *
     * @param dept 部门信息
     */
    @Override
    @Transactional
    public void updateDept(BaseDept dept) {
        dao.update(dept);
    }

}
