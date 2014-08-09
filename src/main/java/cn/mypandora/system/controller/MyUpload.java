/**
* @ProjectName: myframework
* @Package: cn.mypandora.util
* @ClassName: MyUpload
* Copyright © hankaibo. All rights reserved.
* @Author: kaibo
* @CreateDate: 2014-6-15 上午12:06:22
*
*/
package cn.mypandora.system.controller;

import cn.mypandora.system.po.UploadFile;
import cn.mypandora.system.service.BaseUploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ResourceBundle;

/**
* @ClassName: MyUpload
* @Description: 上传工具类。
* @Author: kaibo
* @date: 2014-6-15
* @UpdateUser: kaibo
* @UpdateDate: 2014-6-15 上午12:06:22
* @UpdateRemark: What is modified?
*/
@Controller
@RequestMapping(value = "/upload")
public class MyUpload {
    @Resource
    private BaseUploadService baseUploadService;

    @RequestMapping(method = RequestMethod.GET)
    public String add() {
        return "readonline/add";
    }
    /**
     * @Title: upload
     * @Description: 上传文件。
     * @param part
     * @return
     * @return void
     */
    @RequestMapping(method = RequestMethod.POST)
    public void upload(@RequestParam("myFile") Part part,String choosePath) {
        try {
            /* 将文件进行保存 */
            /* 获取上传文件的存放文件夹 */
            // String path = request.getServletContext().getRealPath("/upload");
            ResourceBundle resourceBundle = ResourceBundle.getBundle("upload");
            String savePath = resourceBundle.getString("docxPath") + getFileName(part);
            part.write(savePath);
            // 保存到数据库
            UploadFile file = new UploadFile();
            file.setFileSize(part.getSize());
            file.setFileName(getFileName(part));
            file.setFileType(1);
            file.setSavePath(savePath);
            baseUploadService.saveFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Title: getFileName
     * @Description: 从Part的Header信息中提取上传文件的文件名
     * @param part
     *            上传文件的文件名，如果如果没有则返回null
     * @return
     * @return String
     */
    private String getFileName(Part part) {
        if (part == null) {
            return null;
        }
        // 获取header信息中的content-disposition，如果为文件，则可以从其中提取出文件名
        String fileName = part.getHeader("content-disposition");
        if (StringUtils.isBlank(fileName)) {
            return null;
        }
        return StringUtils.substringBetween(fileName, "filename=\"", "\"");
    }
}
