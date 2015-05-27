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
     *
     * @param sqlKey
     * @param params
     * @return
     */
    List<Map<String, Object>> findMapByCondition(String sqlKey, Object params);

    Map<String,Map<String,Object>> findMapByCondition(String sqlKey,Object params,String mapKey);
}
