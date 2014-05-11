package cn.mypandora.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.mypandora.orm.dao.impl.BaseEntityDaoImpl;
import cn.mypandora.system.dao.BaseUserDao;
import cn.mypandora.system.po.BaseUser;

/**
 * @ClassName:UserDao
 * @Description:用户管理DAO实现类。
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
     * @see cn.mypandora.orm.dao.BaseDaoImpl#getNameSpace()
     */
    //@formatter:on
    @Override
    public String getNameSpace() {
        return "cn.mypandora.system.UserEntity";
    }

}
