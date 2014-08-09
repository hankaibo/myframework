/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.system.service.impl 
 * @ClassName: BaseLogServiceImpl 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-4-27 下午3:31:10 
 *
 */
package cn.mypandora.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mypandora.orm.Page;
import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.orm.service.AbstractBaseEntityOperation;
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
@Service
public class BaseLogServiceImpl extends AbstractBaseEntityOperation<BaseLog> implements BaseLogService {

    @Resource
    private BaseLogDao dao;

    //@formatter:off
    /* (非 Javadoc)
     * Title: getDao
     * Description:
     * @return
     * @see cn.mypandora.orm.service.impl.AbstractBaseEntityOperation#getDao()
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
