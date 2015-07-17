/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service;

import cn.mypandora.orm.Page;
import cn.mypandora.system.po.UploadFile;

/**
 * @ClassName: BaseUploadService
 * @Description: 上传文件Service。
 * @Author: kaibo
 * @date: 2014-5-7
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-7 下午12:35:15
 * @UpdateRemark: What is modified?
 */
/**
 * 登录页面PO。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface BaseUploadService {
    /**
     * @Title: saveFile
     * @Description: 上传文件。
     * @param baseFile
     * @return void
     */
    void saveFile(UploadFile baseFile);

    /**
     * @Title: deleteFile
     * @Description: 按主键删除文件(物理)。
     * @param id
     * @return void
     */
    void deleteFile(Long id);

    /**
     * @Title: findFileById
     * @Description: 按主键查询。
     * @param id
     * @return
     * @return UploadFile
     */
    UploadFile findFileById(Long id);

    /**
     * @Title: findPageFileByCondition
     * @Description: 根据条件分页查询文件。
     * @param string
     * @param object
     * @param page
     * @return
     * @return Page<UploadFile>
     */
    Page<UploadFile> findPageFileByCondition(String string, Object object, Page<UploadFile> page);

}
