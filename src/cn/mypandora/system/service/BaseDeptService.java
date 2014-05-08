/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.system.service 
 * @ClassName: BaseDeptService 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014年3月7日 下午5:08:53 
 *
 */
package cn.mypandora.system.service;

import cn.mypandora.orm.service.IBaseNestedService;
import cn.mypandora.system.po.BaseDept;

/**
 * @ClassName: BaseDeptService
 * @Description: 接口。
 * @Author: kaibo
 * @date: 2014年3月7日
 * @UpdateUser: kaibo
 * @UpdateDate: 2014年3月7日 下午5:08:53
 * @UpdateRemark: What is modified?
 */
public interface BaseDeptService extends IBaseNestedService<BaseDept> {

    /** 
     * @Title: findById
     * @Description: TODO
     * @param id
     * @return
     * @return BaseDept
     */
    BaseDept findById(Long id);

    /** 
     * @Title: updateEntity
     * @Description: TODO
     * @param dept
     * @return void
     */
    void updateEntity(BaseDept dept);

}
