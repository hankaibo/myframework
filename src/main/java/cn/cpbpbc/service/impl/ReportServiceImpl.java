package cn.cpbpbc.service.impl;

import cn.cpbpbc.po.ReportData;
import cn.cpbpbc.service.ReportService;
import cn.mypandora.orm.Page;
import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.orm.service.AbstractBaseEntityOperation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kaibo on 2015/6/18.
 * desc
 */
public class ReportServiceImpl extends AbstractBaseEntityOperation<ReportData> implements ReportService {
    /**
     * 由继承子类实现真正地实体Dao.
     *
     * @return
     */
    @Override
    public IBaseEntityDao<ReportData> getDao() {
        return null;
    }

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
