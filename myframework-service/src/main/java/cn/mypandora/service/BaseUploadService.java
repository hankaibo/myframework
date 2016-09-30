/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service;

import com.github.pagehelper.PageInfo;

import cn.mypandora.po.UploadFile;

/**
 * 上传文件Service。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public interface BaseUploadService {
    /**
     * 上传文件。
     *
     * @param baseFile
     */
    void saveFile(UploadFile baseFile);

    /**
     * 按主键删除文件(物理)。
     *
     * @param id
     */
    void deleteFile(Long id);

    /**
     * 按主键查询。
     *
     * @param id
     * @return
     */
    UploadFile findFileById(Long id);

    /**
     * 根据条件分页查询文件。
     *
     * @param string
     * @param object
     * @param page
     * @return
     */
    PageInfo<UploadFile> findPageFileByCondition(String string, Object object, PageInfo<UploadFile> page);

}
