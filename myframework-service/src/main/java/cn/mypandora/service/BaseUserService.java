/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service;

import cn.mypandora.po.BaseUser;
import com.github.pagehelper.PageInfo;

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
     * @param user 用户实体
     */
    void loginSuccess(BaseUser user);

    /**
     * 新增用户。
     *
     * @param baseUser 用户实体
     */
    void addUser(BaseUser baseUser);

    /**
     * 按主键删除用户(物理)。
     *
     * @param id 用户id
     */
    void deleteUser(Long id);

    /**
     * 按主键删除批量用户(物理)。
     *
     * @param ids 用户id数组
     */
    void deleteBatchUser(Long[] ids);

    /**
     * 修改用户。
     *
     * @param baseUser 用户实体
     */
    void updateUser(BaseUser baseUser);

    /**
     * 修改密码
     *
     * @param userId      用户id
     * @param newPassword 新密码
     */
    void changePassword(Long userId, String newPassword);

    /**
     * 添加用户-角色关系
     *
     * @param userId  用户id
     * @param roleIds 角色id数组
     */
    void correlationRole(Long userId, Long... roleIds);

    /**
     * 移除用户-角色关系
     *
     * @param userId  用户id
     * @param roleIds 角色id数组
     */
    void uncorrelationRole(Long userId, Long... roleIds);

    /**
     * 按主键查询用户。
     *
     * @param id 用户id
     * @return 一个用户实体
     */
    BaseUser findUserById(Long id);

    /**
     * 根据用户名和密码判断用户是否匹配。
     *
     * @param username 用户名称
     * @param password 用户密码
     * @return boolean <tt>true</tt>,表示该用户存在，可以访问；<tt>false</tt>，反之。
     */
    boolean hasMatchUser(String username, String password);

    /**
     * 根据用户名查询用户实例。
     *
     * @param username 用户名称
     * @return 一个用户实体
     */
    BaseUser findUserByUsername(String username);

    /**
     * 根据条件分页查询用户。
     *
     * @param string sql映射名称
     * @param object 参数
     * @param page   分页信息
     * @return 分页用户
     */
    PageInfo<BaseUser> findPageUserByCondition(String string, Object object, PageInfo<BaseUser> page);

    /**
     * 查询某个月份用户的使用情况
     *
     * @param month 指定的月份
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
     * 根据用户名查找其角色
     *
     * @param username 用户名称
     * @return 用户的角色集合
     */
    Set<String> findRole(String username);

    /**
     * 根据用户名查找其权限
     *
     * @param username 用户名称
     * @return 用户的权限集合
     */
    Set<String> findPermission(String username);
}
