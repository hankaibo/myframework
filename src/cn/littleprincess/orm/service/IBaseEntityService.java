package cn.littleprincess.orm.service;

import java.io.Serializable;
import java.util.List;

import cn.littleprincess.orm.Page;
import cn.littleprincess.orm.model.BaseEntity;

/**
 * @ClassName:IBaseEntityService
 * @Description:接口和DAO通用操作。
 * @Author:hankaibo
 * @date:2014-1-1
 * @UpdateUser:hankaibo
 * @UpdateDate:2014-1-1 上午11:57:29
 * @UpdateRemark:What is modified?
 * @param <T>
 */
public interface IBaseEntityService<T extends BaseEntity> {

    /**
     * @Title: addEntity
     * @Description: 添加实体。
     * @param t
     * @return void
     * @author hankaibo
     * @date 2013-12-19 下午1:26:55
     * @throws
     */
    void addEntity(T t);

    /**
     * @Title: updateEntity
     * @Description: 修改实体。
     * @param t
     * @return void
     * @author hankaibo
     * @date 2013-12-19 下午1:27:33
     * @throws
     */
    void updateEntity(T t);

    /**
     * @Title: deleteEntity
     * @Description: 删除实体。
     * @param id
     * @return void
     * @author hankaibo
     * @date 2013-12-19 下午1:28:33
     * @throws
     */
    void deleteEntity(Serializable id);

    /**
     * @Title: bulkDelete
     * @Description: 批量删除。
     * @param ids
     * @return void
     * @author hankaibo
     * @date 2013-12-19 下午1:29:21
     * @throws
     */
    void bulkDelete(Serializable[] ids);

    /**
     * @Title: findById
     * @Description: 根据id获取实体。
     * @param id
     * @return
     * @return T
     * @author hankaibo
     * @date 2013-12-19 下午1:29:49
     * @throws
     */
    T findById(Serializable id);

    /**
     * @Title: findAll
     * @Description: 查询所有数据。
     * @return
     * @return List<T>
     * @author hankaibo
     * @date 2013-12-19 下午1:30:19
     * @throws
     */
    List<T> findAll();

    /**
     * @Title: findByCondition
     * @Description: 分页查询。
     * @param sqlKey
     * @param params
     * @param page
     * @return
     * @return Page<T>
     * @author hankaibo
     * @date 2013-12-19 下午1:32:36
     * @throws
     */
    Page<T> findByCondition(String sqlKey, Object params, Page<T> page);
}
