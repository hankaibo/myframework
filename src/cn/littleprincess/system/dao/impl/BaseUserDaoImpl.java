/**   
 * @ProjectName:MySpring
 * @Package:cn.littleprincess.dao 
 * @ClassName:UserDao 
 * @Description:TODO
 * Copyright © 2013东软集团股份有限公司. All rights reserved.
 * @Author:hankaibo
 * @CreateDate: 2013-8-13 下午11:36:17 
 * @Version:v1.0
 *
 */
package cn.littleprincess.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.littleprincess.orm.dao.impl.BaseEntityDaoImpl;
import cn.littleprincess.system.dao.BaseUserDao;
import cn.littleprincess.system.po.BaseUser;

/**
 * @ClassName:UserDao
 * @Description:TODO
 * @Author:hankaibo
 * @date:2013-8-13
 * @UpdateUser:hankaibo
 * @UpdateDate:2013-8-13 下午11:36:17
 * @UpdateRemark:What is modified?
 */
@Repository
public class BaseUserDaoImpl extends BaseEntityDaoImpl<BaseUser> implements BaseUserDao {

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
        return "cn.littleprincess.system.UserEntity";
    }

}
