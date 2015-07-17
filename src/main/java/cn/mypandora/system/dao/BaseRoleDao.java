package cn.mypandora.system.dao;

import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.system.po.BaseRole;

import java.util.List;
import java.util.Map;

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
public interface BaseRoleDao extends IBaseEntityDao<BaseRole> {
    /**
     * 根据条件获取Map实体列表。
     * @param sqlKey
     * @param params
     * @return
     */
    List<Map<String, Object>> findMapByCondition(String sqlKey, Object params);
    /**
     * 按条件添加实体。
     *
     * @param sqlKey
     * @param params
     */
    void insertByCondetion(String sqlKey, Object params);

    /**
     * 根据条件删除实体。
     *
     * @param sqlKey
     * @param params
     */
    void deleteByConditions(String sqlKey, Object params);
}
