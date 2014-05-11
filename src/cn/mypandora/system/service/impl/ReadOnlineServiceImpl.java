/**   
 * @ProjectName: myframework
 * @Package: cn.mypandora.system.service.impl 
 * @ClassName: ReadOnlineServiceImpl 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-5-7 下午12:42:34 
 *
 */
package cn.mypandora.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mypandora.log.MyMethodAnno;
import cn.mypandora.orm.Page;
import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.orm.service.AbstractBaseEntityOperation;
import cn.mypandora.system.dao.ReadOnlineDao;
import cn.mypandora.system.po.UploadFile;
import cn.mypandora.system.service.ReadOnlineService;

/**
 * @ClassName: ReadOnlineServiceImpl
 * @Description: 在线阅读Service实现类。
 * @Author: kaibo
 * @date: 2014-5-7
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-7 下午12:42:34
 * @UpdateRemark: What is modified?
 */
@Service
public class ReadOnlineServiceImpl extends AbstractBaseEntityOperation<UploadFile> implements ReadOnlineService {

    @Resource
    private ReadOnlineDao dao;

    //@formatter:off
    /* (非 Javadoc)
     * Title: getDao
     * Description:
     * @return
     * @see cn.mypandora.orm.service.impl.AbstractBaseEntityOperation#getDao()
     */
    //@formatter:on
    @Override
    public IBaseEntityDao<UploadFile> getDao() {
        return dao;
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: saveFile
     * Description:
     * @param uploadFile
     * @see cn.mypandora.system.service.ReadOnlineService#upload(cn.mypandora.system.po.UploadFile)
     */
    //@formatter:on
    @Override
    @MyMethodAnno(description ="上传文件")
    public void saveFile(UploadFile uploadFile) {
        dao.addEntity(uploadFile);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: deleteFile
     * Description:
     * @param id
     * @see cn.mypandora.system.service.ReadOnlineService#deleteFile(java.lang.Long)
     */
    //@formatter:on
    @Override
    @MyMethodAnno(description="删除文件")
    public void deleteFile(Long id) {
        dao.deleteEntity(id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: findFileById
     * Description:
     * @param id
     * @return
     * @see cn.mypandora.system.service.ReadOnlineService#findFileById(java.lang.Long)
     */
    //@formatter:on
    @Override
    public UploadFile findFileById(Long id) {
        return dao.findById(id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: findPageFileByCondition
     * Description:
     * @param string
     * @param object
     * @param page
     * @return
     * @see cn.mypandora.system.service.ReadOnlineService#findPageFileByCondition(java.lang.String, java.lang.Object, cn.mypandora.orm.Page)
     */
    //@formatter:on
    @Override
    public Page<UploadFile> findPageFileByCondition(String string, Object object, Page<UploadFile> page) {
        return dao.findByCondition(string, object, page);
    }

}
