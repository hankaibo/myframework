/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.system.service 
 * @ClassName: BaseUserService 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014年3月7日 下午5:08:53 
 *
 */
package cn.mypandora.system.service;

import cn.mypandora.orm.service.IBaseEntityService;
import cn.mypandora.system.po.BaseUser;

/**
 * @ClassName: BaseUserService
 * @Description: 用户接口。
 * @Author: kaibo
 * @date: 2014年3月7日
 * @UpdateUser: kaibo
 * @UpdateDate: 2014年3月7日 下午5:08:53
 * @UpdateRemark: What is modified?
 */
public interface BaseUserService extends IBaseEntityService<BaseUser> {

    /**
     * @Title: hasMatchUser
     * @Description: 根据用户名和密码判断用户是否匹配。
     * @param username
     * @param password
     * @return
     * @return boolean true,表示该用户存在，可以访问；false，反之。
     */
    boolean hasMatchUser(String username, String password);

    /**
     * @Title: findUserByUsername
     * @Description: 根据用户名查询用户实例。
     * @param username
     * @return
     * @return BaseUser
     */
    BaseUser findUserByUsername(String username);

    /**
     * @Title: loginSuccess
     * @Description: 用户登录成功之后，积分+5，保存。
     * @param user
     * @return void
     */
    void loginSuccess(BaseUser user);
}
