/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.cpbpbc.service.impl;

import cn.cpbpbc.po.ReportData;
import cn.cpbpbc.service.ReportService;
import cn.mypandora.orm.Page;

import java.io.Serializable;
import java.util.List;

/**
 * cpbpbc报表接口实现类。
 * <p>User: hankaibo
 * <p>Date: 2015/7/19
 * <p>Version: 1.0
 */
public class ReportServiceImpl implements ReportService {
    /**
     * 添加报告数据。
     *
     * @param reportData
     */
    @Override
    public void addReportData(ReportData reportData) {

    }

    /**
     * 批量添加报告数据。
     *
     * @param list
     */
    @Override
    public void addBatchReportData(List<ReportData> list) {

    }

    /**
     * 删除一条报告数据。
     *
     * @param id
     */
    @Override
    public void deleteReportData(Long id) {

    }

    /**
     * 批量删除报告数据。
     *
     * @param ids
     */
    @Override
    public void deleteBatchReportData(Serializable ids) {

    }

    /**
     * 修改报告数据。
     *
     * @param reportData
     */
    @Override
    public void updateReportData(ReportData reportData) {

    }

    /**
     * 分页按条件查询报告数据。
     *
     * @param string
     * @param object
     * @param page
     * @return
     */
    @Override
    public Page<ReportData> findPageReportDataByCondition(String string, Object object, Page<ReportData> page) {
        return null;
    }
}
