/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service.impl;

import cn.mypandora.orm.Page;
import cn.mypandora.system.dao.BaseLogDao;
import cn.mypandora.system.po.BaseLog;
import cn.mypandora.system.service.BaseLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 日志管理Service实现类。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Service
public class BaseLogServiceImpl implements BaseLogService {
    @Resource
    private BaseLogDao dao;

    /**
     * 新增日志。
     *
     * @param log
     */
    @Override
    public void addLog(BaseLog log) {
        dao.add(log);
    }

    /**
     * 删除日志(物理)。
     *
     * @param id
     */
    @Override
    public void deleteLog(Long id) {
        dao.delete(id);
    }

    /**
     * 分页查询日志。
     *
     * @param string
     * @param object
     * @param page
     * @return
     */
    @Override
    public Page<BaseLog> findLogByCondition(String string, Object object, Page<BaseLog> page) {
        return dao.findPageByCondition(string, object, page);
    }

}
