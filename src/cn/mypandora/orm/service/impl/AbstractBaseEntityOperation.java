package cn.mypandora.orm.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.mypandora.log.MyMethodAnno;
import cn.mypandora.orm.Page;
import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.orm.model.BaseEntity;
import cn.mypandora.orm.service.IBaseEntityService;

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
    @MyMethodAnno(description="添加实体操作")
    public void addEntity(T t) {
        getDao().addEntity(t);
    }

    @Override
    @Transactional
    @MyMethodAnno(description="修改实体操作")
    public void updateEntity(T t) {
        getDao().updateEntity(t);
    }

    @Override
    @Transactional
    @MyMethodAnno(description="删除实体操作")
    public void deleteEntity(Serializable id) {
        getDao().deleteEntity(id);
    }

    @Override
    @Transactional
    @MyMethodAnno(description="批量删除实体操作")
    public void bulkDelete(Serializable[] ids) {
        getDao().bulkDelete(ids);
    }

    @Override
    @MyMethodAnno(description="查询所有实体操作")
    public List<T> findAll() {
        return getDao().findAll();
    }

    @Override
    @MyMethodAnno(description="查询实体操作")
    public T findById(Serializable id) {
        return getDao().findById(id);
    }

    @Override
    @MyMethodAnno(description="通过条件查询实体操作")
    public Page<T> findByCondition(String sqlKey, Object params, Page<T> page) {
        return getDao().findByCondition(sqlKey, params, page);
    }

}
