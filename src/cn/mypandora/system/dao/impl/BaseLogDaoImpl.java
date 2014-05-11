/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.system.dao.impl 
 * @ClassName: BaseLogDaoImpl 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-4-27 下午3:33:29 
 *
 */ 
package cn.mypandora.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.mypandora.orm.dao.impl.BaseEntityDaoImpl;
import cn.mypandora.system.dao.BaseLogDao;
import cn.mypandora.system.po.BaseLog;

/**
 * @ClassName: BaseLogDaoImpl
 * @Description: 日志管理DAO实现类。
 * @Author: kaibo
 * @date: 2014-4-27
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-4-27 下午3:33:29
 * @UpdateRemark: What is modified?
 */
@Repository
public class BaseLogDaoImpl extends BaseEntityDaoImpl<BaseLog> implements BaseLogDao {

    //@formatter:off
    /* (非 Javadoc)
     * Title: getNameSpace
     * Description:
     * @return
     * @see cn.mypandora.orm.dao.impl.BaseEntityDaoImpl#getNameSpace()
     */
    //@formatter:on
    @Override
    public String getNameSpace() {
        return "cn.mypandora.system.LogEntity";
    }

}
