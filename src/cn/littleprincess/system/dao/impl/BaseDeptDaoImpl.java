/**   
 * @ProjectName: MyFramework
 * @Package: cn.littleprincess.system.dao.impl 
 * @ClassName: BaseDeptDaoImpl 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-3-10 下午11:38:03 
 *
 */
package cn.littleprincess.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.littleprincess.orm.dao.impl.BaseNestedDaoImpl;
import cn.littleprincess.system.dao.BaseDeptDao;
import cn.littleprincess.system.po.BaseDept;

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
     * @see cn.littleprincess.orm.dao.BaseDaoImpl#getNameSpace()
     */
    //@formatter:on
    @Override
    public String getNameSpace() {
        return "cn.littleprincess.system.BaseDept";
    }
}
