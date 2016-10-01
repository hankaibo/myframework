/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.service.impl;

import cn.mypandora.system.dao.BaseLogDao;
import cn.mypandora.po.BaseLog;
import cn.mypandora.service.BaseLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
     * @param log 日志
     */
    @Override
    @Transactional
    public void addLog(BaseLog log) {
        dao.add(log);
    }

    /**
     * 删除日志(物理)。
     *
     * @param id 日志id
     */
    @Override
    @Transactional
    public void deleteLog(Long id) {
        dao.delete(id);
    }

    /**
     * 批量删除日志(物理)。
     *
     * @param ids 日志ids
     */
    @Override
    @Transactional
    public void deleteBatchLog(Long[] ids) {
        dao.deleteBatch(ids);
    }

    /**
     * 查询所有日志
     *
     * @return 所有日志
     */
    @Override
    public List<BaseLog> findAllLog() {
        return dao.findAll();
    }

    /**
     * 分页查询日志。
     *
     * @param string sql映射语句名称
     * @param object 参数
     * @param page   分页信息
     * @return 分页日志
     */
    @Override
    public PageInfo<BaseLog> findLogByCondition(String string, Object object, PageInfo<BaseLog> page) {
        return dao.findPageByCondition(string, object, page);
    }

}
