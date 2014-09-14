/**   
 * @ProjectName: myframework
 * @Package: cn.mypandora.system.po 
 * @ClassName: UploadFile 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-5-7 下午12:36:16 
 *
 */
package cn.mypandora.system.po;

import cn.mypandora.orm.model.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @ClassName: UploadFile
 * @Description: 上传文件实体域对象。
 * @Author: kaibo
 * @date: 2014-5-7
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-7 下午12:36:16
 * @UpdateRemark: 添加JsonSerialize注解，过滤掉null值。
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UploadFile extends BaseEntity {

    private static final long serialVersionUID = 9138179297461260717L;
    /** @Fields fileName :文件名称 */
    private String fileName;
    /** @Fields fileSize :文件大小 */
    private Long fileSize;
    /** @Fields saveName :文件保存到数据库的名称 */
    private String saveName;
    /** @Fields savePath :文件保存到数据库的路径 */
    private String savePath;
    /** @Fields fileType :文件类型：1表示原始文件；2表示加工之后的pdf文件；3表示要显示的swf文件 */
    private Integer fileType;

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     *            the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the fileSize
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize
     *            the fileSize to set
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return the saveName
     */
    public String getSaveName() {
        return saveName;
    }

    /**
     * @param saveName
     *            the saveName to set
     */
    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    /**
     * @return the savePath
     */
    public String getSavePath() {
        return savePath;
    }

    /**
     * @param savePath
     *            the savePath to set
     */
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    /**
     * @return the fileType
     */
    public Integer getFileType() {
        return fileType;
    }

    /**
     * @param fileType the fileType to set
     */
    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

}
