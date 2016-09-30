/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.po;

import cn.mypandora.model.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 上传文件实体域对象。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UploadFile extends BaseEntity {
    private static final long serialVersionUID = 9138179297461260717L;
    // 文件名称
    private String fileName;
    // 文件大小
    private Long fileSize;
    // 文件保存到数据库的名称
    private String saveName;
    // 文件保存到数据库的路径
    private String savePath;
    // 文件类型：1表示原始文件；2表示加工之后的pdf文件；3表示要显示的swf文件
    private Integer fileType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }
}
