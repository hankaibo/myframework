package cn.mypandora.system.dao;

import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.system.po.BasePermission;

/**
 * Created by kaibo on 2015/7/16.
 * desc
 */

/**
 * 登录页面PO。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface BasePermissionDao extends IBaseEntityDao<BasePermission> {
    /**
     * 根据条件删除实体。
     * @param sqlKey
     * @param params
     */
    void deleteByConditions(String sqlKey, Object params);
}
