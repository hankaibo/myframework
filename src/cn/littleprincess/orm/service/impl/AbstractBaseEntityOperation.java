package cn.littleprincess.orm.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.littleprincess.orm.Page;
import cn.littleprincess.orm.dao.IBaseEntityDao;
import cn.littleprincess.orm.model.BaseEntity;
import cn.littleprincess.orm.service.IBaseEntityService;

/**
 * @ClassName:AbstractBaseEntityOperation
 * @Description:接口实现抽象类。
 * @Author:hankaibo
 * @date:2014-1-1
 * @UpdateUser:hankaibo
 * @UpdateDate:2014-1-1 上午11:57:48
 * @UpdateRemark:What is modified?
 * @param <T>
 */
public abstract class AbstractBaseEntityOperation<T extends BaseEntity> implements IBaseEntityService<T> {

    public abstract IBaseEntityDao<T> getDao();

    @Override
    @Transactional
    public void addEntity(T t) {
        getDao().addEntity(t);
    }

    @Override
    @Transactional
    public void updateEntity(T t) {
        getDao().updateEntity(t);
    }

    @Override
    @Transactional
    public void deleteEntity(Serializable id) {
        getDao().deleteEntity(id);
    }

    @Transactional
    @Override
    public void bulkDelete(Serializable[] ids) {
        getDao().bulkDelete(ids);
    }

    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    @Override
    public T findById(Serializable id) {
        return getDao().findById(id);
    }

    @Override
    public Page<T> findByCondition(String sqlKey, Object params, Page<T> page) {
        return getDao().findByCondition(sqlKey, params, page);
    }

}
