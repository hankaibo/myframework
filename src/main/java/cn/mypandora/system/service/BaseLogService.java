/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.system.service 
 * @ClassName: BaseLogService 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-4-27 下午3:30:10 
 *
 */
package cn.mypandora.system.service;

import cn.mypandora.orm.Page;
import cn.mypandora.system.po.BaseLog;


/**
 * @ClassName: BaseLogService
 * @Description: 日志管理Service。
 * @Author: kaibo
 * @date: 2014-4-27
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-4-27 下午3:30:10
 * @UpdateRemark: What is modified?
 */
public interface BaseLogService {

    /** 
     * @Title: addLog
     * @Description: 新增日志。
     * @param log
     * @return void
     */
    void addLog(BaseLog log);

    /** 
     * @Title: deleteLog
     * @Description: 删除日志(物理)。
     * @param id
     * @return void
     */
    void deleteLog(Long id);

    /** 
     * @Title: findByCondition
     * @Description: 分页查询日志。
     * @param string
     * @param object
     * @param page
     * @return
     * @return Page<BaseLog>
     */
    Page<BaseLog> findLogByCondition(String string, Object object, Page<BaseLog> page);

}
