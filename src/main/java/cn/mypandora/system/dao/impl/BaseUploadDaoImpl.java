/**   
 * @ProjectName: myframework
 * @Package: cn.mypandora.system.dao.impl 
 * @ClassName: BaseUploadDaoImpl 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-5-7 下午2:21:28 
 *
 */
package cn.mypandora.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.mypandora.orm.dao.impl.BaseEntityDaoImpl;
import cn.mypandora.system.dao.BaseUploadDao;
import cn.mypandora.system.po.UploadFile;

/**
 * @ClassName: BaseUploadDaoImpl
 * @Description: 上传文件DAO操作实现类。
 * @Author: kaibo
 * @date: 2014-5-7
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-7 下午2:21:28
 * @UpdateRemark: What is modified?
 */
@Repository
public class BaseUploadDaoImpl extends BaseEntityDaoImpl<UploadFile> implements BaseUploadDao {

    //@formatter:off
    /* (非 Javadoc)
     * Title: getNameSpace
     * Description:
     * @return
     * @see cn.mypandora.orm.dao.impl.BaseEntityDaoImpl#getNameSpace()
     */
    //@formatter:on
    @Override
    public String getNameSpace() {
        return "cn.mypandora.system.uploadFileEntity";
    }

}
