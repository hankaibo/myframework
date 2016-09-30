/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * 实体域对象左右树基础类。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public abstract class BaseTree extends BaseEntity {
    // 节点ID,用自增主键代替。
//    protected int nodeId;
    // 节点名称
    @NotNull
    protected String name;
    // 节点右值
    @Max(99999)
    protected int rgt;
    // 节点左值
    @Max(99999)
    protected int lft;
    // 父节点ID
    @Max(99999)
    protected int parentId;

//    public int getNodeId() {
//        return nodeId;
//    }
//
//    public void setNodeId(int nodeId) {
//        this.nodeId = nodeId;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLft() {
        return lft;
    }

    public void setLft(int lft) {
        this.lft = lft;
    }

    public int getRgt() {
        return rgt;
    }

    public void setRgt(int rgt) {
        this.rgt = rgt;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
