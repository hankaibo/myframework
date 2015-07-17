/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mypandora.log.MyMethodAnno;
import cn.mypandora.orm.Page;
import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.orm.service.AbstractBaseEntityService;
import cn.mypandora.system.dao.BaseUploadDao;
import cn.mypandora.system.po.UploadFile;
import cn.mypandora.system.service.BaseUploadService;

/**
 * @ClassName: BaseUploadServiceImpl
 * @Description: 上传文件Service实现类。
 * @Author: kaibo
 * @date: 2014-5-7
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-7 下午12:42:34
 * @UpdateRemark: What is modified?
 */
/**
 * 登录页面PO。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Service
public class BaseUploadServiceImpl extends AbstractBaseEntityService<UploadFile> implements BaseUploadService {

    @Resource
    private BaseUploadDao dao;

    //@formatter:off
    /* (非 Javadoc)
     * Title: getDao
     * Description:
     * @return
     * @see cn.mypandora.orm.service.impl.AbstractBaseEntityService#getDao()
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
     * @see cn.mypandora.system.service.BaseUploadService#upload(cn.mypandora.system.po.UploadFile)
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
     * @see cn.mypandora.system.service.BaseUploadService#deleteFile(java.lang.Long)
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
     * @see cn.mypandora.system.service.BaseUploadService#findFileById(java.lang.Long)
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
     * @see cn.mypandora.system.service.BaseUploadService#findPageFileByCondition(java.lang.String, java.lang.Object, cn.mypandora.orm.Page)
     */
    //@formatter:on
    @Override
    public Page<UploadFile> findPageFileByCondition(String string, Object object, Page<UploadFile> page) {
        return dao.findByCondition(string, object, page);
    }

}
