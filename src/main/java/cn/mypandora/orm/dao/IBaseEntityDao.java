package cn.mypandora.orm.dao;

import cn.mypandora.orm.Page;
import cn.mypandora.orm.model.BaseEntity;

import java.io.Serializable;
import java.util.List;


/**
 * DAO通用操作API。
 *
 * @param <T>
 */
public interface IBaseEntityDao<T extends BaseEntity> {

    /**
     * 添加实体。
     *
     * @param t 实体
     */
    void addEntity(T t);

    /**
     * 修改实体。
     *
     * @param t 实体
     */
    void updateEntity(T t);

    /**
     * 删除实体。
     *
     * @param id 实体id
     */
    void deleteEntity(Serializable id);

    /**
     * 批量删除实体。
     *
     * @param ids 实体id数组
     */
    void bulkDelete(Serializable[] ids);

    /**
     * 根据id获取实体。
     *
     * @param id 实体id
     * @return 返回单个实体
     */
    T findById(Serializable id);

    /**
     * 查询所有实体。
     *
     * @return 返回所有实体
     */
    List<T> findAll();

    /**
     * 分页查询实体。
     *
     * @param sqlKey 查询sql的名称
     * @param params 查询参数
     * @param page   返回实体Page
     * @return 返回分页实体
     */
    Page<T> findByCondition(String sqlKey, Object params, Page<T> page);
}
