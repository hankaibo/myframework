/**   
 * @ProjectName:MySpring
 * @Package:cn.mypandora.service 
 * @ClassName:UserService 
 * @Description:TODO
 * Copyright © 2013东软集团股份有限公司. All rights reserved.
 * @Author:hankaibo
 * @CreateDate: 2013-8-14 下午11:12:00 
 * @Version:v1.0
 *
 */
package cn.mypandora.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.orm.service.impl.AbstractBaseEntityOperation;
import cn.mypandora.system.dao.BaseUserDao;
import cn.mypandora.system.po.BaseUser;
import cn.mypandora.system.service.BaseUserService;

/**
 * @ClassName:UserService
 * @Description:TODO
 * @Author:hankaibo
 * @date:2013-8-14
 * @UpdateUser:hankaibo
 * @UpdateDate:2013-8-14 下午11:12:00
 * @UpdateRemark:What is modified?
 */
@Service
public class BaseUserServiceImpl extends AbstractBaseEntityOperation<BaseUser> implements BaseUserService {
    @Resource
    private BaseUserDao dao;

    // @Resource
    // private LoginLogDao loginLogDao;

    //@formatter:off
    /* (非 Javadoc)
     * Title: getDao
     * Description:
     * @return
     * @see cn.mypandora.orm.service.AbstractBaseEntityOperation#getDao()
     */
    //@formatter:on
    @Override
    public IBaseEntityDao<BaseUser> getDao() {
        return dao;
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: hasMatchUser
     * Description:
     * @param username
     * @param password
     * @return
     * @see cn.mypandora.system.service.BaseUserService#hasMatchUser(java.lang.String, java.lang.String)
     */
    //@formatter:on
    @Override
    public boolean hasMatchUser(String username, String password) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);

        List<BaseUser> list = dao.findByCondition("hasMatchUser", params);
        return list.size() > 0;
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: findUserByUsername
     * Description:
     * @param username
     * @return
     * @see cn.mypandora.system.service.BaseUserService#findUserByUsername(java.lang.String)
     */
    //@formatter:on
    @Override
    public BaseUser findUserByUsername(String username) {
        return dao.findEntityByCondition("findUserByName", username);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: loginSuccess
     * Description:
     * @param user
     * @see cn.mypandora.system.service.BaseUserService#loginSuccess(cn.mypandora.system.po.BaseUser)
     */
    //@formatter:on
    @Override
    @Transactional 
    public void loginSuccess(BaseUser user) {
        user.setCredits(5 + user.getCredits());
        dao.updateEntity(user);

        // LoginLog loginLog = new LoginLog();
        // loginLog.setUserId(user.getUserId());
        // loginLog.setIp(user.getLastIp());
        // loginLog.setLoginDate(user.getLastVisit());
        // loginLogDao.insertLoginLog(loginLog);

    }
}
