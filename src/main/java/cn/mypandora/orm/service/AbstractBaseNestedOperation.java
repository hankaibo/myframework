/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.orm.service.impl 
 * @ClassName: AbstractBaseNestedOperation 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-3-11 上午12:17:29 
 *
 */
package cn.mypandora.orm.service;

import cn.mypandora.orm.dao.IBaseNestedDao;
import cn.mypandora.orm.model.BaseTree;

/**
 * @ClassName: AbstractBaseNestedOperation
 * @Description: 嵌套接口实现抽象类。
 * @Author: kaibo
 * @date: 2014-3-11
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-3-11 上午12:17:29
 * @UpdateRemark: What is modified?
 */
public abstract class AbstractBaseNestedOperation<T extends BaseTree> {

    public abstract IBaseNestedDao<T> getDao();

}
