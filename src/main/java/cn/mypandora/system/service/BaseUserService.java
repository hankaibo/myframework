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
 * 用户管理Service。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface BaseUserService {
    /**
     * 用户登录成功之后，积分+5，保存。
     *
     * @param user
     */
    void loginSuccess(BaseUser user);

    /**
     * 新增用户。
     *
     * @param baseUser
     */
    void addUser(BaseUser baseUser);

    /**
     * 按主键删除用户(物理)。
     *
     * @param id
     */
    void deleteUser(Long id);

    /**
     * 按主键删除批量用户(物理)。
     *
     * @param ids
     */
    void deleteBatchUser(Long[] ids);

    /**
     * 修改用户。
     *
     * @param baseUser
     */
    void updateUser(BaseUser baseUser);

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     */
    void changePassword(Long userId, String newPassword);

    /**
     * 添加用户-角色关系
     *
     * @param userId
     * @param roleIds
     */
    void correlationRole(Long userId, Long... roleIds);

    /**
     * 移除用户-角色关系
     *
     * @param userId
     * @param roleIds
     */
    void uncorrelationRole(Long userId, Long... roleIds);

    /**
     * 按主键查询用户。
     *
     * @param id
     * @return
     */
    BaseUser findUserById(Long id);

    /**
     * 根据用户名和密码判断用户是否匹配。
     *
     * @param username
     * @param password
     * @return boolean true,表示该用户存在，可以访问；false，反之。
     */
    boolean hasMatchUser(String username, String password);

    /**
     * 根据用户名查询用户实例。
     *
     * @param username
     * @return
     */
    BaseUser findUserByUsername(String username);

    /**
     * 根据条件分页查询用户。
     *
     * @param string
     * @param object
     * @param page
     * @return
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
    Set<String> findRole(String username);

    /**
     * 根据用户名查找其权限
     *
     * @param username
     * @return
     */
    Set<String> findPermission(String username);
}
