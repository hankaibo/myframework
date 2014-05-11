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

import cn.mypandora.orm.Page;
import cn.mypandora.system.po.BaseUser;

/**
 * @ClassName: BaseUserService
 * @Description: 用户管理Service。
 * @Author: kaibo
 * @date: 2014年3月7日
 * @UpdateUser: kaibo
 * @UpdateDate: 2014年3月7日 下午5:08:53
 * @UpdateRemark: What is modified?
 */
public interface BaseUserService {
    

    /**
     * @Title: loginSuccess
     * @Description: 用户登录成功之后，积分+5，保存。
     * @param user
     * @return void
     */
    void loginSuccess(BaseUser user);

    /** 
     * @Title: addUser
     * @Description: 新增用户。
     * @param baseUser
     * @return void
     */
    void addUser(BaseUser baseUser);

    /** 
     * @Title: deleteUser
     * @Description: 按主键删除用户(物理)。
     * @param id
     * @return void
     */
    void deleteUser(Long id);

    /** 
     * @Title: updateEntity
     * @Description: 修改用户。
     * @param baseUser
     * @return void
     */
    void updateUser(BaseUser baseUser);

    /** 
     * @Title: findById
     * @Description: 按主键查询用户。
     * @param id
     * @return
     * @return BaseUser
     */
    BaseUser findUserById(Long id);

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
     * @Title: findPageUserByCondition
     * @Description: 根据条件分页查询用户。
     * @param string
     * @param object
     * @param page
     * @return
     * @return Page<BaseUser>
     */
    Page<BaseUser> findPageUserByCondition(String string, Object object, Page<BaseUser> page);
    
}
