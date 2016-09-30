/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service;

import cn.mypandora.po.BaseLog;
import com.github.pagehelper.PageInfo;

import java.util.List;

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
     * @param log 日志
     */
    void addLog(BaseLog log);

    /**
     * 删除日志(物理)。
     *
     * @param id 日志id
     */
    void deleteLog(Long id);

    /**
     * 批量删除日志(物理)。
     *
     * @param ids 日志ids
     */
    void deleteBatchLog(Long[] ids);

    /**
     * 查询所有日志
     *
     * @return 所有日志
     */
    List<BaseLog> findAllLog();

    /**
     * 分页查询日志。
     *
     * @param string sql映射语句名称
     * @param object 参数
     * @param page   分页信息
     * @return 分页日志
     */
    PageInfo<BaseLog> findLogByCondition(String string, Object object, PageInfo<BaseLog> page);

}
