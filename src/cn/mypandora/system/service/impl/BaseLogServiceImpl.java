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

import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.orm.service.impl.AbstractBaseEntityOperation;
import cn.mypandora.system.dao.BaseLogDao;
import cn.mypandora.system.po.BaseLog;
import cn.mypandora.system.service.BaseLogService;

/**
 * @ClassName: BaseLogServiceImpl
 * @Description: TODO
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

}
