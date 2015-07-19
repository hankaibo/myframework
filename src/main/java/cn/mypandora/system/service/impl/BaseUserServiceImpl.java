/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service.impl;

import cn.mypandora.log.MyMethodAnno;
import cn.mypandora.orm.Page;
import cn.mypandora.system.dao.BaseUserDao;
import cn.mypandora.system.po.BaseUser;
import cn.mypandora.system.service.BaseUserService;
import cn.mypandora.system.service.PasswordHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户管理Service实现类。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Service
public class BaseUserServiceImpl implements BaseUserService {
    @Resource
    private BaseUserDao dao;
    @Resource
    private PasswordHelper passwordHelper;

    /**
     * 用户登录成功之后，积分+5，保存。
     *
     * @param user
     */
    @Override
    public void loginSuccess(BaseUser user) {
        user.setCredit(5 + user.getCredit());
        dao.update(user);
    }

    /**
     * 新增用户。
     *
     * @param baseUser
     */
    @MyMethodAnno(description = "新增用户")
    public void addUser(BaseUser baseUser) {
        passwordHelper.encryptPassword(baseUser);
        dao.add(baseUser);
    }

    /**
     * 按主键删除用户(物理)。
     *
     * @param id
     */
    @Transactional
    @MyMethodAnno(description = "删除用户")
    public void deleteUser(Long id) {
        dao.delete(id);
    }

    /**
     * 按主键删除批量用户(物理)。
     *
     * @param ids
     */
    @Override
    @Transactional
    @MyMethodAnno(description = "删除批量用户")
    public void deleteBatchUser(Long[] ids) {
        dao.deleteBatch(ids);
    }

    /**
     * 修改用户。
     *
     * @param baseUser
     */
    @Override
    @MyMethodAnno(description = "修改用户")
    public void updateUser(BaseUser baseUser) {
        dao.update(baseUser);
    }

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     */
    @Override
    public void changePassword(Long userId, String newPassword) {
        BaseUser baseUser = dao.findById(userId);
        baseUser.setPassword(newPassword);
        passwordHelper.encryptPassword(baseUser);
        dao.update(baseUser);
    }

    /**
     * 添加用户-角色关系
     *
     * @param userId
     * @param roleIds
     */
    @Override
    public void correlationRole(Long userId, Long... roleIds) {
        if (roleIds == null || roleIds.length == 0) {
            return;
        }
        Map<String, Object> params = new HashMap<>();
        for (Long roleId : roleIds) {
            if (!exists(userId, roleId)) {
                params.put("userId", userId);
                params.put("roleId", roleId);
                dao.addByCondetion("correlationUserRole", params);
            }
        }
    }

    /**
     * 移除用户-角色关系
     *
     * @param userId
     * @param roleIds
     */
    @Override
    public void uncorrelationRole(Long userId, Long... roleIds) {
        if (roleIds == null || roleIds.length == 0) {
            return;
        }
        Map<String, Object> params = new HashMap<>();
        for (Long roleId : roleIds) {
            if (exists(userId, roleId)) {
                params.put("userId", userId);
                params.put("roleId", roleId);
                dao.deleteByCondition("uncorrelationUserRole", params);
            }
        }
    }

    /**
     * 按主键查询用户。
     *
     * @param id
     * @return
     */
    @Override
    public BaseUser findUserById(Long id) {
        return dao.findById(id);
    }

    /**
     * 根据用户名和密码判断用户是否匹配。
     *
     * @param username
     * @param password
     * @return boolean true,表示该用户存在，可以访问；false，反之。
     */
    @Override
    public boolean hasMatchUser(String username, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        List<BaseUser> list = dao.findListByCondition("hasMatchUser", params);
        return list.size() > 0;
    }

    /**
     * 根据用户名查询用户实例。
     *
     * @param username
     * @return
     */
    @Override
    public BaseUser findUserByUsername(String username) {
        return dao.findByCondition("findUserByName", username);
    }

    /**
     * 根据条件分页查询用户。
     *
     * @param string
     * @param object
     * @param page
     * @return
     */
    @Override
    public Page<BaseUser> findPageUserByCondition(String string, Object object, Page<BaseUser> page) {
        return dao.findPageByCondition(string, object, page);
    }

    /**
     * 查询某个月份用户的使用情况
     *
     * @param month
     * @return
     */
    @Override
    public List<Map<String, Object>> findUserCount(String month) {
        Map<String, String> params = new HashMap<>();
        params.put("month", month);
        return dao.findListMapByCondition("findUserByDate", params);
    }


    /**
     * 查询用户的男女人数
     *
     * @return [0](女), [1](男), [2](保密)
     */
    @Override
    public Map<String, Object> findUserSexCount() {
        Map<String, Object> mapSexCount = new HashMap<>();
        Map<String, Map<String, Object>> result = dao.findMapByCondition("findUserSexCount", null, "sex");

        mapSexCount.put("女", result.get(0) == null ? 0 : result.get(0).get("sexCount"));
        mapSexCount.put("男", result.get(1) == null ? 0 : result.get(1).get("sexCount"));
        mapSexCount.put("保密", result.get(2) == null ? 0 : result.get(2).get("sexCount"));

        return mapSexCount;
    }

    /**
     * 根据用户名查找其角色
     *
     * @param username
     * @return
     */
    @Override
    public Set<String> findRole(String username) {
        Set<String> set = new HashSet<>();
        List<String> list = dao.findListCustomByCondition(null, null);
        for (String str : list) {
            set.add(str);
        }
        return set;

    }

    /**
     * 根据用户名查找其权限
     *
     * @param username
     * @return
     */
    @Override
    public Set<String> findPermission(String username) {
        Set<String> set = new HashSet<>();
        List<String> list = dao.findListCustomByCondition(null, null);
        for (String str : list) {
            set.add(str);
        }
        return set;
    }

    private boolean exists(Long userId, Long roleId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("roleId", roleId);
        return dao.findListMapByCondition("isExistsUserRole", params).size() > 0;
    }
}
