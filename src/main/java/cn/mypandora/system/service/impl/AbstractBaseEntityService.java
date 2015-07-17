/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */package cn.mypandora.system.service.impl;

import cn.mypandora.log.MyMethodAnno;
import cn.mypandora.orm.Page;
import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.orm.service.AbstractBaseEntityService;
import cn.mypandora.system.dao.BaseUserDao;
import cn.mypandora.system.po.BaseUser;
import cn.mypandora.system.service.BaseUserService;
import cn.mypandora.system.service.PasswordHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName:UserService
 * @Description:用户管理Service实现类。
 * @Author:hankaibo
 * @date:2013-8-14
 * @UpdateUser:hankaibo
 * @UpdateDate:2013-8-14 下午11:12:00
 * @UpdateRemark:What is modified?
 */
/**
 * 登录页面PO。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Service
public class AbstractBaseEntityService extends AbstractBaseEntityService<BaseUser> implements BaseUserService {
    @Resource
    private BaseUserDao dao;
    @Resource
    private PasswordHelper passwordHelper;

    //@formatter:off
    /* (非 Javadoc)
     * Title: getDao
     * Description:
     * @return
     * @see cn.mypandora.orm.service.AbstractBaseEntityService#getDao()
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
        Map<String, String> params = new HashMap<>();
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
    @MyMethodAnno(description = "用户登录成功")
    public void loginSuccess(BaseUser user) {
        user.setCredits(5 + user.getCredits());
        dao.updateEntity(user);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: addUser
     * Description:
     * @param baseUser
     * @see cn.mypandora.system.service.BaseUserService#addUser(cn.mypandora.system.po.BaseUser)
     */
    //@formatter:on
    @Override
    @MyMethodAnno(description = "新增用户")
    public void addUser(BaseUser baseUser) {
        passwordHelper.encryptPassword(baseUser);
        dao.addEntity(baseUser);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: deleteUser
     * Description:
     * @param id
     * @see cn.mypandora.system.service.BaseUserService#deleteUser(java.lang.Long)
     */
    //@formatter:on
    @Override
    @Transactional
    @MyMethodAnno(description = "删除用户")
    public void deleteUser(Long id) {
        dao.deleteEntity(id);
    }

    /**
     * @param ids
     * @return void
     * @Title: deleteBatchUser
     * @Description: 按主键删除批量用户(物理)。
     */
    @Override
    @Transactional
    @MyMethodAnno(description = "删除批量用户")
    public void deleteBatchUser(Long[] ids) {
        dao.deleteBatchEntity(ids);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: updateUser
     * Description:
     * @param baseUser
     * @see cn.mypandora.system.service.BaseUserService#updateUser(cn.mypandora.system.po.BaseUser)
     */
    //@formatter:on
    @Override
    @MyMethodAnno(description = "修改用户")
    public void updateUser(BaseUser baseUser) {
        dao.updateEntity(baseUser);
    }

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     */
    @Override
    public void changePassword(Long userId, String newPassword) {
        BaseUser baseUser = getDao().findById(userId);
        baseUser.setPassword(newPassword);
        passwordHelper.encryptPassword(baseUser);
        dao.updateEntity(baseUser);
    }

    /**
     * 添加用户-角色关系
     *
     * @param userId
     * @param roleIds
     */
    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        if(roleIds == null || roleIds.length == 0) {
            return;
        }
        Map<String,Object> params=new HashMap<>();
        for(Long roleId : roleIds) {
            if(!exists(userId, roleId)) {
                params.put("userId",userId);
                params.put("roleId",roleId);
                dao.insertByCondetion("correlationUserRole", params);
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
    public void uncorrelationRoles(Long userId, Long... roleIds) {
        if(roleIds == null || roleIds.length == 0) {
            return;
        }
        Map<String,Object> params=new HashMap<>();
        for(Long roleId : roleIds) {
            if(exists(userId, roleId)) {
                params.put("userId",userId);
                params.put("roleId",roleId);
                dao.deleteByConditions("uncorrelationUserRole", params);
            }
        }
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: findUserById
     * Description:
     * @param id
     * @return
     * @see cn.mypandora.system.service.BaseUserService#findUserById(java.lang.Long)
     */
    //@formatter:on
    @Override
    public BaseUser findUserById(Long id) {
        return dao.findById(id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: findPageUserByCondition
     * Description:
     * @param string
     * @param object
     * @param page
     * @return
     * @see cn.mypandora.system.service.BaseUserService#findPageUserByCondition(java.lang.String, java.lang.Object, cn.mypandora.orm.Page)
     */
    //@formatter:on
    @Override
    public Page<BaseUser> findPageUserByCondition(String string, Object object, Page<BaseUser> page) {
        return dao.findByCondition(string, object, page);
    }

    @Override
    public List<Map<String, Object>> findUserCount(String month) {
        Map<String, String> params = new HashMap<>();
        params.put("month", month);
        return dao.findMapByCondition("findUserByDate", params);
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
     * 根据用户名查找其权限
     *
     * @param username
     * @return
     */
    @Override
    public Set<String> findRoles(String username) {
        Set<String> set=new HashSet<>();
        List<String> list=dao.findObjectListByCondition(null, null);
        for(String str:list){
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
    public Set<String> findPermissions(String username) {
        Set<String> set=new HashSet<>();
        List<String> list=dao.findObjectListByCondition(null, null);
        for(String str:list){
            set.add(str);
        }
        return set;
    }

    private boolean exists(Long userId, Long roleId) {
        Map<String,Object> params=new HashMap<>();
        params.put("userId",userId);
        params.put("roleId",roleId);
        return dao.findMapByCondition("isExistsUserRole",params).size()>0;
    }
}
