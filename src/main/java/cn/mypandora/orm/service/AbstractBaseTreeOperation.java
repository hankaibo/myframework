/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.orm.service;

import cn.mypandora.orm.dao.IBaseTreeDao;
import cn.mypandora.orm.model.BaseTree;

/**
 * 接口抽象类。(树类型)
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public abstract class AbstractBaseTreeOperation<T extends BaseTree> {
    /**
     * 由继承子类实现真正地实体Dao.
     *
     * @return
     */
    public abstract IBaseTreeDao<T> getDao();

}
