/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.system.dao 
 * @ClassName: BaseUserDao 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014年3月7日 下午5:17:32 
 *
 */
package cn.mypandora.system.dao;

import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.system.po.BaseUser;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: BaseUserDao
 * @Description: 用户管理DAO。
 * @Author: kaibo
 * @date: 2014年3月7日
 * @UpdateUser: kaibo
 * @UpdateDate: 2014年3月7日 下午5:17:32
 * @UpdateRemark: What is modified?
 */
/**
 * 登录页面PO。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface BaseUserDao extends IBaseEntityDao<BaseUser> {
    /**
     * @Title: findByCondition
     * @Description: 根据条件返回实体列表。
     * @param sqlKey
     * @param params
     * @return
     * @return List<BaseUser>
     */
    List<BaseUser> findByCondition(String sqlKey, Object params);
    
    /** 
     * @Title: findEntityByCondition
     * @Description: 根据条件获取一个实体。
     * @param sqlKey
     * @param params
     * @return
     * @return BaseUser
     */
    BaseUser findEntityByCondition(String sqlKey, Object params);

    /**
     * 根据条件获取Map实体列表。
     * @param sqlKey
     * @param params
     * @return
     */
    List<Map<String, Object>> findMapByCondition(String sqlKey, Object params);

    /**
     *
     * @param sqlKey
     * @param params
     * @param mapKey
     * @return
     */
    Map<String,Map<String,Object>> findMapByCondition(String sqlKey,Object params,String mapKey);

    /**
     *
     * @param sqlKey
     * @param params
     * @return
     */
    <O> List<O> findObjectListByCondition(String sqlKey, Object params);

    /**
     *  按条件添加实体。
     * @param sqlKey
     * @param params
     */
    void insertByCondetion(String sqlKey, Object params);

    /**
     * 根据条件删除实体。
     * @param sqlKey
     * @param params
     */
    void deleteByConditions(String sqlKey, Object params);
}
