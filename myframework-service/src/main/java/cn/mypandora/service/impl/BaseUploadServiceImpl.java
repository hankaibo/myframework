/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service.impl;

import cn.mypandora.system.dao.BaseUploadDao;
import cn.mypandora.po.UploadFile;
import cn.mypandora.system.service.BaseUploadService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 上传文件Service实现类。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Service
public class BaseUploadServiceImpl implements BaseUploadService {

    @Resource
    private BaseUploadDao dao;

    /**
     * 上传文件。
     *
     * @param baseFile
     */
    @Override
    @Transactional
    public void saveFile(UploadFile baseFile) {
        dao.add(baseFile);
    }

    /**
     * 按主键删除文件(物理)。
     *
     * @param id
     */
    @Override
    @Transactional
    public void deleteFile(Long id) {
        dao.delete(id);
    }

    /**
     * 按主键查询。
     *
     * @param id
     * @return
     */
    @Override
    public UploadFile findFileById(Long id) {
        return dao.findById(id);
    }

    /**
     * 根据条件分页查询文件。
     *
     * @param string
     * @param object
     * @param page
     * @return
     */
    @Override
    public PageInfo<UploadFile> findPageFileByCondition(String string, Object object, PageInfo<UploadFile> page) {
        return dao.findPageByCondition(string, object, page);
    }

}
