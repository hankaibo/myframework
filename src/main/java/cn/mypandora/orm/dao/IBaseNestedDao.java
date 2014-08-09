/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.orm.dao.impl 
 * @ClassName: IBaseNestedDao 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-3-16 上午1:03:39 
 *
 */
package cn.mypandora.orm.dao;

import java.util.List;

import cn.mypandora.orm.model.BaseTree;

/**
 * @ClassName: IBaseNestedDao
 * @Description: 嵌套DAO通用操作。
 * @Author: kaibo
 * @date: 2014-3-16
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-3-16 上午1:03:39
 * @UpdateRemark: What is modified?
 */
public interface IBaseNestedDao<T extends BaseTree> extends IBaseEntityDao<BaseTree> {
    /**
     * @Title: loadFullTree
     * @Description: 获取整棵树（一次性全部加载，适合数据量少的情况）
     * @return
     * @return List<T>
     */
    List<T> loadFullTree();

    /**
     * @Title: getDescendants
     * @Description: 获得本节点及下面的所有节点
     * @param id
     *            当前操作节点id
     * @return
     * @return List<T>
     */
    List<T> getDescendants(Long id);

    /**
     * @Title: getParent
     * @Description: 获得本节点的父节点
     * @param id
     *            当前操作节点id
     * @return
     * @return T
     */
    T getParent(Long id);

    /**
     * @Title: getAncestry
     * @Description: 获得本节点的祖先节点
     * @param id
     *            当前操作节点id
     * @return
     * @return List<T>
     */
    List<T> getAncestry(Long id);

    /**
     * @Title: getChilds
     * @Description: 获得本节点的孩子节点
     * @param id
     *            当前操作节点id
     * @return
     * @return List<T>
     */
    List<T> getChilds(Long id);

    /**
     * @Title: lftPlus2
     * @Description: 左节点加2
     * @param id
     * @return void
     */
    void lftPlus2(Long id);

    /**
     * @Title: rgtPlus2
     * @Description: 右节点加2
     * @param id
     * @return void
     */
    void rgtPlus2(Long id);

    /**
     * @Title: insertChild
     * @Description: 添加节点
     * @param params
     * @return void
     */
    void insertNode(Object params);

    /**
     * @Title: parentRgtPlus2
     * @Description: 父右节点加2
     * @param id
     * @return void
     */
    void parentRgtPlus2(Long id);

    /**
     * @Title: lftMinus2
     * @Description: 左节点减2
     * @param id
     * @return void
     */
    void lftMinus2(Long id);

    /**
     * @Title: rgtMinus2
     * @Description: 右节点减2
     * @param id
     * @return void
     */
    void rgtMinus2(Long id);

    /**
     * @Title: brotherPlus2
     * @Description: 左右节点加2
     * @param id
     * @return void
     */
    void brotherPlus2(Long id);

    /**
     * @Title: brotherMinus2
     * @Description: 左右节点减2
     * @param id
     * @return void
     */
    void brotherMinus2(Long id);

    /**
     * @Title: isFirstNode
     * @Description: 判断是否是第一个节点
     * @param id
     * @return
     * @return boolean
     */
    boolean isFirstNode(Long id);

    /**
     * @Title: isLastNode
     * @Description: 判断是否是最后一个节点
     * @param id
     * @return
     * @return boolean
     */
    boolean isLastNode(Long id);

}
