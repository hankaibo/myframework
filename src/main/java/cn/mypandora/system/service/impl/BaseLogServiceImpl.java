/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service.impl;

import javax.annotation.Resource;

import cn.mypandora.orm.service.AbstractBaseEntityService;
import org.springframework.stereotype.Service;

import cn.mypandora.orm.Page;
import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.system.dao.BaseLogDao;
import cn.mypandora.system.po.BaseLog;
import cn.mypandora.system.service.BaseLogService;

/**
 * @ClassName: BaseLogServiceImpl
 * @Description: 日志管理Service实现类。
 * @Author: kaibo
 * @date: 2014-4-27
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-4-27 下午3:31:10
 * @UpdateRemark: What is modified?
 */
/**
 * 登录页面PO。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Service
public class BaseLogServiceImpl extends AbstractBaseEntityService<BaseLog> implements BaseLogService {

    @Resource
    private BaseLogDao dao;

    //@formatter:off
    /* (非 Javadoc)
     * Title: getDao
     * Description:
     * @return
     * @see cn.mypandora.orm.service.impl.AbstractBaseEntityService#getDao()
     */
    //@formatter:on
    @Override
    public IBaseEntityDao<BaseLog> getDao() {
        return dao;
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: addLog
     * Description:
     * @param log
     * @see cn.mypandora.system.service.BaseLogService#addLog(cn.mypandora.system.po.BaseLog)
     */
    //@formatter:on
    @Override
    public void addLog(BaseLog log) {
        dao.addEntity(log);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: deleteLog
     * Description:
     * @param id
     * @see cn.mypandora.system.service.BaseLogService#deleteLog(java.lang.Long)
     */
    //@formatter:on
    @Override
    public void deleteLog(Long id) {
        dao.deleteEntity(id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: findByCondition
     * Description:
     * @param string
     * @param object
     * @param page
     * @return
     * @see cn.mypandora.system.service.BaseLogService#findByCondition(java.lang.String, java.lang.Object, cn.mypandora.orm.Page)
     */
    //@formatter:on
    @Override
    public Page<BaseLog> findLogByCondition(String string, Object object, Page<BaseLog> page) {
        return dao.findByCondition(string, object, page);
    }

}
