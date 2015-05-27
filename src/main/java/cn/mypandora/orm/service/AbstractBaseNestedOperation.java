/**
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.orm.service.impl
 * @ClassName: AbstractBaseNestedOperation
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-3-11 上午12:17:29 
 *
 */
package cn.mypandora.orm.service;

import cn.mypandora.orm.dao.IBaseNestedDao;
import cn.mypandora.orm.model.BaseTree;

/**
 * 接口抽象类。(嵌套类型)
 *
 * @param <T>
 */
public abstract class AbstractBaseNestedOperation<T extends BaseTree> {
    /**
     * 由继承子类实现真正地实体Dao.
     *
     * @return
     */
    public abstract IBaseNestedDao<T> getDao();

}
