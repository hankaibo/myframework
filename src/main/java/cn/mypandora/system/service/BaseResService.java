package cn.mypandora.system.service;

import cn.mypandora.system.po.BaseRes;

import java.util.List;

/**
 * Created by kaibo on 2014/8/4.
 * desc
 */
public interface BaseResService {
    /**
     * 获取所有资源（一次性全部加载，适合数据量少的情况）。
     *
     * @return
     */
    List<BaseRes> loadFullRes();

    /**
     * 获取带深度的所有资源。
     * @param level
     * @return
     */
    List<BaseRes> loadFullResWithLevel(int level);

    /**
     * 获得本资源（节点）及下面的所有资源（节点）。
     *
     * @param id 当前操作资源（节点）id
     * @return
     */
    List<BaseRes> getResDescendants(Long id);

    /**
     * 获得本资源（节点）的孩子资源（节点）。
     *
     * @param id 当前操作资源（节点）id
     * @return
     */
    List<BaseRes> getResChilds(Long id);

    /**
     * 获得本资源（节点）的父资源（节点）
     *
     * @param id 当前操作资源（节点）id
     * @return
     */
    BaseRes getResParent(Long id);

    /**
     * 获得本资源（节点）的祖先资源（节点）。
     *
     * @param id 当前操作资源（节点）id
     * @return
     */
    List<BaseRes> getResAncestry(Long id);

    /**
     * 添加孩子资源（节点）
     *
     * @param id     父资源（节点）的nodeId
     * @param params 子资源（节点）的信息
     */
    void addRes(Long id, Object params);

    /**
     * 删除资源（节点）
     *
     * @param id 要删除的资源（节点）ID
     */
    void delRes(Long id);

    /**
     * 上移某个资源（节点）(同级叶子之间,即把弟弟资源（节点）移到哥哥资源（节点）之前)
     *
     * @param id   弟弟资源（节点）ID
     * @param upId 哥哥资源（节点）ID
     */
    void moveUpRes(Long id, Long upId);

    /**
     * 下移某个资源（节点）(同级叶子之间,即把哥哥资源（节点）移到弟弟资源（节点）之后)
     *
     * @param id     哥哥资源（节点）ID
     * @param downId 弟弟资源（节点）ID
     */
    void moveDownRes(Long id, Long downId);

    /**
     * 查询一个资源。
     *
     * @param id
     * @return
     */
    BaseRes findResById(Long id);

    /**
     * 更新一个资源。
     *
     * @param res
     */
    void updateRes(BaseRes res);
}
