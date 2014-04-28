/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.orm.service 
 * @ClassName: IBaseNestedOperation 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-3-10 下午11:56:27 
 *
 */
package cn.mypandora.orm.service;

import java.util.List;

import cn.mypandora.orm.model.BaseTree;

/**
 * @ClassName: IBaseNestedOperation
 * @Description: TODO
 * @Author: kaibo
 * @date: 2014-3-10
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-3-10 下午11:56:27
 * @UpdateRemark: What is modified?
 */
public interface IBaseNestedService<T extends BaseTree> extends IBaseEntityService<BaseTree> {

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
     * @Title: getChilds
     * @Description: 获得本节点的孩子节点
     * @param id
     *            当前操作节点id
     * @return
     * @return List<T>
     */
    List<T> getChilds(Long id);

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
     * @Title: addChild
     * @Description: 添加孩子节点
     * @param id
     *            父节点的nodeId
     * @param params
     *            子节点的信息
     * @return void
     */
    void addChild(Long id, Object params);

    /**
     * @Title: delChild
     * @Description: 删除节点
     * @param id
     *            要删除的节点ID
     * @return void
     */
    void delChild(Long id);

    /**
     * @Title: moveUp
     * @Description: 上移某个节点(同级叶子之间,即把弟弟节点移到哥哥节点之前)
     * @param id
     *            弟弟节点ID
     * @param upId
     *            哥哥节点ID
     * @return void
     */
    void moveUp(Long id, Long upId);

    /**
     * @Title: moveDown
     * @Description: 下移某个节点(同级叶子之间,即把哥哥节点移到弟弟节点之后)
     * @param id
     *            哥哥节点ID
     * @param downId
     *            弟弟节点ID
     * @return void
     */
    void moveDown(Long id, Long downId);

}
