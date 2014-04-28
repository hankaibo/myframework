/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.system.dao.impl 
 * @ClassName: BaseDeptDaoImpl 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-3-10 下午11:38:03 
 *
 */
package cn.mypandora.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.mypandora.orm.dao.impl.BaseNestedDaoImpl;
import cn.mypandora.system.dao.BaseDeptDao;
import cn.mypandora.system.po.BaseDept;

/**
 * @ClassName: BaseDeptDaoImpl
 * @Description: TODO
 * @Author: kaibo
 * @date: 2014-3-10
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-3-10 下午11:38:03
 * @UpdateRemark: What is modified?
 */
@Repository
public class BaseDeptDaoImpl extends BaseNestedDaoImpl<BaseDept> implements BaseDeptDao {

    //@formatter:off
    /* (非 Javadoc)
     * Title: getNameSpace
     * Description:
     * @return
     * @see cn.mypandora.orm.dao.BaseDaoImpl#getNameSpace()
     */
    //@formatter:on
    @Override
    public String getNameSpace() {
        return "cn.mypandora.system.BaseDept";
    }
}
