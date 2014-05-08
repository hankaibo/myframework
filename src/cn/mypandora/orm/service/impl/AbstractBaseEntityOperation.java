package cn.mypandora.orm.service.impl;

import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.orm.model.BaseEntity;

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
public abstract class AbstractBaseEntityOperation<T extends BaseEntity> {

    public abstract IBaseEntityDao<T> getDao();
}
