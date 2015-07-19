/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service;

import cn.mypandora.orm.Page;
import cn.mypandora.system.po.BaseLog;

/**
 * 日志管理Service。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface BaseLogService {

    /**
     * 新增日志。
     *
     * @param log
     */
    void addLog(BaseLog log);

    /**
     * 删除日志(物理)。
     *
     * @param id
     */
    void deleteLog(Long id);

    /**
     * 分页查询日志。
     *
     * @param string
     * @param object
     * @param page
     * @return
     */
    Page<BaseLog> findLogByCondition(String string, Object object, Page<BaseLog> page);

}
