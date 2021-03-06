/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.service;

import cn.mypandora.po.BaseDept;

import java.util.List;

/**
 * 部门管理Service。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface BaseDeptService {

    /**
     * 获取所有部门（一次性全部加载，适合数据量少的情况）。
     *
     * @return 所有部门数据
     */
    List<BaseDept> loadFullDept();

    /**
     * 获得本部门（节点）下面的所有后代部门（节点）。
     *
     * @param id 当前操作部门（节点）id
     * @return 指定部门下的所有后代部门
     */
    List<BaseDept> getDeptDescendant(Long id);

    /**
     * 获得本部门（节点）的孩子部门（节点）。
     *
     * @param id 当前操作部门（节点）id
     * @return 指定部门下的所有部门
     */
    List<BaseDept> getDeptChild(Long id);

    /**
     * 获得本部门（节点）的父部门（节点）
     *
     * @param id 当前操作部门（节点）id
     * @return 本部门的父部门
     */
    BaseDept getDeptParent(Long id);

    /**
     * 获得本部门（节点）的祖先部门（节点）
     *
     * @param id 当前操作部门（节点）id
     * @return 本部门的祖先部门
     */
    List<BaseDept> getDeptAncestry(Long id);

    /**
     * 添加孩子部门（节点）
     *
     * @param id     父部门（节点）的nodeId
     * @param params 子部门（节点）的信息
     */
    void addDept(Long id, Object params);

    /**
     * 删除部门（节点）
     *
     * @param id 要删除的部门ID
     */
    void delDept(Long id);

    /**
     * 上移某个部门（节点）(同级叶子之间,即把弟弟部门（节点）移到哥哥部门（节点）之前)
     *
     * @param id   弟弟部门（节点）ID
     * @param upId 哥哥部门（节点）ID
     */
    void moveUpDept(Long id, Long upId);

    /**
     * 下移某个部门（节点）(同级叶子之间,即把哥哥部门（节点）移到弟弟部门（节点）之后)
     *
     * @param id     哥哥部门（节点）ID
     * @param downId 弟弟部门（节点）ID
     */
    void moveDownDept(Long id, Long downId);

    /**
     * 查询一个部门。
     *
     * @param id 当前操作部门（节点）id
     * @return 一个部门
     */
    BaseDept findDeptById(Long id);

    /**
     * 更新一个部门。
     *
     * @param dept 部门信息
     */
    void updateDept(BaseDept dept);

}
