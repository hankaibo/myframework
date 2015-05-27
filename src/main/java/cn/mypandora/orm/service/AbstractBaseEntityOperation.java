package cn.mypandora.orm.service;

import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.orm.model.BaseEntity;


/**
 * 接口抽象类。
 *
 * @param <T>
 */
public abstract class AbstractBaseEntityOperation<T extends BaseEntity> {

    /**
     * 由继承子类实现真正地实体Dao.
     *
     * @return
     */
    public abstract IBaseEntityDao<T> getDao();
}
