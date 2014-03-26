/**   
 * @ProjectName:MySpring
 * @Package:cn.littleprincess.service.impl
 * @ClassName:BaseDeptServiceImpl
 * @Description:TODO
 * Copyright © 2013东软集团股份有限公司. All rights reserved.
 * @Author:hankaibo
 * @CreateDate: 2013-8-14 下午11:12:00 
 * @Version:v1.0
 *
 */
package cn.littleprincess.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.littleprincess.orm.dao.IBaseNestedDao;
import cn.littleprincess.orm.service.impl.AbstractBaseNestedOperation;
import cn.littleprincess.system.dao.BaseDeptDao;
import cn.littleprincess.system.po.BaseDept;
import cn.littleprincess.system.service.BaseDeptService;

/**
 * @ClassName:BaseDeptServiceImpl
 * @Description:TODO
 * @Author:hankaibo
 * @date:2013-8-14
 * @UpdateUser:hankaibo
 * @UpdateDate:2013-8-14 下午11:12:00
 * @UpdateRemark:What is modified?
 */
@Service
public class BaseDeptServiceImpl extends AbstractBaseNestedOperation<BaseDept> implements BaseDeptService {
    @Resource
    private BaseDeptDao dao;

    //@formatter:off
    /* (非 Javadoc)
     * Title: getDao
     * Description:
     * @return
     * @see cn.littleprincess.orm.service.impl.AbstractBaseNestedOperation#getDao()
     */
    //@formatter:on
    @Override
    public IBaseNestedDao<BaseDept> getDao() {
        return dao;
    }


}
