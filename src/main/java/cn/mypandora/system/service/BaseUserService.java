/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service;

import cn.mypandora.orm.Page;
import cn.mypandora.system.po.BaseUser;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: BaseUserService
 * @Description: 用户管理Service。
 * @Author: kaibo
 * @date: 2014年3月7日
 * @UpdateUser: kaibo
 * @UpdateDate: 2014年3月7日 下午5:08:53
 * @UpdateRemark: What is modified?
 */
/**
 * 登录页面PO。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface BaseUserService {


    /**
     * @param user
     * @return void
     * @Title: loginSuccess
     * @Description: 用户登录成功之后，积分+5，保存。
     */
    void loginSuccess(BaseUser user);

    /**
     * 新增用户。
     * @param baseUser
     */
    void addUser(BaseUser baseUser);

    /**
     * @param id
     * @return void
     * @Title: deleteUser
     * @Description: 按主键删除用户(物理)。
     */
    void deleteUser(Long id);

    /**
     * @param ids
     * @return void
     * @Title: deleteBatchUser
     * @Description: 按主键删除批量用户(物理)。
     */
    void deleteBatchUser(Long[] ids);

    /**
     * @param baseUser
     * @return void
     * @Title: updateEntity
     * @Description: 修改用户。
     */
    void updateUser(BaseUser baseUser);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    void changePassword(Long userId,String newPassword);

    /**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     */
    void correlationRoles(Long userId,Long... roleIds);

    /**
     * 移除用户-角色关系
     * @param userId
     * @param roleIds
     */
    void uncorrelationRoles(Long userId,Long... roleIds);
    /**
     * @param id
     * @return BaseUser
     * @Title: findById
     * @Description: 按主键查询用户。
     */
    BaseUser findUserById(Long id);

    /**
     * @param username
     * @param password
     * @return boolean true,表示该用户存在，可以访问；false，反之。
     * @Title: hasMatchUser
     * @Description: 根据用户名和密码判断用户是否匹配。
     */
    boolean hasMatchUser(String username, String password);

    /**
     * @param username
     * @return BaseUser
     * @Title: findUserByUsername
     * @Description: 根据用户名查询用户实例。
     */
    BaseUser findUserByUsername(String username);

    /**
     * @param string
     * @param object
     * @param page
     * @return Page<BaseUser>
     * @Title: findPageUserByCondition
     * @Description: 根据条件分页查询用户。
     */
    Page<BaseUser> findPageUserByCondition(String string, Object object, Page<BaseUser> page);

    /**
     * 查询某个月份用户的使用情况
     *
     * @param month
     * @return
     */
    List<Map<String, Object>> findUserCount(String month);

    /**
     * 查询用户的男女人数
     *
     * @return
     */
    Map<String, Object> findUserSexCount();

    /**
     * 根据用户名查找其权限
     *
     * @param username
     * @return
     */
    Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     *
     * @param username
     * @return
     */
    Set<String> findPermissions(String username);
}
