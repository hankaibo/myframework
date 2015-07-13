package cn.cpbpbc.service;

import cn.cpbpbc.po.ReportData;
import cn.mypandora.orm.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kaibo on 2015/6/16.
 * desc
 */
public interface ReportService {

    /**
     * 添加报告数据。
     *
     * @param reportData
     */
    void addReportData(ReportData reportData);

    /**
     * 批量添加报告数据。
     *
     * @param list
     */
    void addBatchReportData(List<ReportData> list);

    /**
     * 删除一条报告数据。
     *
     * @param id
     */
    void deleteReportData(Long id);

    /**
     * 批量删除报告数据。
     *
     * @param ids
     */
    void deleteBatchReportData(Serializable ids);

    /**
     * 修改报告数据。
     *
     * @param reportData
     */
    void updateReportData(ReportData reportData);

    /**
     * 分页按条件查询报告数据。
     *
     * @param string
     * @param object
     * @param page
     * @return
     */
    Page<ReportData> findPageReportDataByCondition(String string, Object object, Page<ReportData> page);
}
