/**   
 * @ProjectName: myframework
 * @Package: cn.mypandora.system.service 
 * @ClassName: ReadOnlineService 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-5-7 下午12:35:15 
 *
 */
package cn.mypandora.system.service;

import cn.mypandora.orm.Page;
import cn.mypandora.system.po.UploadFile;

/**
 * @ClassName: ReadOnlineService
 * @Description: 在线管理Service。
 * @Author: kaibo
 * @date: 2014-5-7
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-7 下午12:35:15
 * @UpdateRemark: What is modified?
 */
public interface ReadOnlineService {
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
