/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.util;

import cn.mypandora.orm.model.BaseTree;
import cn.mypandora.system.po.BaseDept;
import cn.mypandora.system.po.BaseRes;
import cn.mypandora.system.vo.ParentChildTree;

/**
 * 完成父子节点树与左右值节点树的格式转换。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public class MyTreeUtil {
    /**
     * 将左右值节点的树转成父子节点的树。
     *
     * @param treeNode
     * @return
     */
    public static ParentChildTree lfNode2pcNode(BaseRes treeNode) {
        ParentChildTree pcTree = new ParentChildTree();
        //TODO 在这里新添加的节点没有noteId，故暂时用主键id替换，待更好的方法出现。
        pcTree.setId(Integer.parseInt(String.valueOf(treeNode.getId())));
        pcTree.setName(treeNode.getName());
        pcTree.setOpen(true);
        pcTree.setPid(treeNode.getParentId());
        pcTree.setParent((treeNode.getRgt() - treeNode.getLft() == 1) ? false : true);
        pcTree.setURL(treeNode.getUrl());
        pcTree.setChkDisabled(treeNode.isEnable());

        return pcTree;
    }

    /**
     * 将左右值节点的树转成父子节点的树。
     *
     * @param treeNode
     * @return
     */
    public static ParentChildTree lfNode2pcNode(BaseDept treeNode) {
        ParentChildTree pcTree = new ParentChildTree();
        //TODO 在这里新添加的节点没有noteId，故暂时用主键id替换，待更好的方法出现。
        pcTree.setId(Integer.parseInt(String.valueOf(treeNode.getId())));
        pcTree.setName(treeNode.getName());
        pcTree.setOpen(true);
        pcTree.setPid(treeNode.getParentId());
        pcTree.setParent((treeNode.getRgt() - treeNode.getLft() == 1) ? false : true);
        // pcTree.setChkDisabled(treeNode.isChkDisabled());

        return pcTree;
    }

    /**
     * 将左右值节点的树转成父子节点的树。
     *
     * @param treeNode
     * @return
     */
    public static ParentChildTree lfNode2pcNode(BaseTree treeNode) {
        ParentChildTree pcTree = new ParentChildTree();
        //TODO 在这里新添加的节点没有noteId，故暂时用主键id替换，待更好的方法出现。
        pcTree.setId(Integer.parseInt(String.valueOf(treeNode.getId())));
        pcTree.setName(treeNode.getName());
        pcTree.setOpen(true);
        pcTree.setPid(treeNode.getParentId());
        pcTree.setParent((treeNode.getRgt() - treeNode.getLft() == 1) ? false : true);
        // pcTree.setChkDisabled(treeNode.isChkDisabled());

        return pcTree;
    }

}
