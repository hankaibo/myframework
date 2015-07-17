/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.mypandora.orm.dao.impl.IBaseTreeDao;
import cn.mypandora.system.dao.BaseDeptDao;
import cn.mypandora.system.po.BaseDept;

/**
 * @ClassName: BaseDeptDaoImpl
 * @Description: 部门管理DAO实现类。
 * @Author: kaibo
 * @date: 2014-3-10
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-3-10 下午11:38:03
 * @UpdateRemark: What is modified?
 */
/**
 * 登录页面PO。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Repository
public class BaseDeptDaoImpl extends IBaseTreeDao<BaseDept> implements BaseDeptDaoImpl {

    //@formatter:off
    /* (非 Javadoc)
     * Title: getNameSpace
     * Description:
     * @return
     * @see cn.mypandora.orm.dao.BaseDaoImpl#getNameSpace()
     */
    //@formatter:on
    @Override
    public String getNameSpace() {
        return "cn.mypandora.system.BaseDept";
    }
}
