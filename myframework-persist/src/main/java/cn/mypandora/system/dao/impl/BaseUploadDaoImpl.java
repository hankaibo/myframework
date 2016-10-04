/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.mypandora.orm.dao.impl.BaseEntityDaoImpl;
import cn.mypandora.system.dao.BaseUploadDao;
import cn.mypandora.po.UploadFile;

/**
 * 上传文件DAO操作实现类。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Repository
public class BaseUploadDaoImpl extends BaseEntityDaoImpl<UploadFile> implements BaseUploadDao {

    /**
     * 查询sql配置文件命名空间
     *
     * @return sql配置文件命名空间名称
     */
    @Override
    protected String getNameSpace() {
        return "cn.mypandora.uploadFileEntity";
    }
}
