/**   
 * @ProjectName: myframework
 * @Package: cn.mypandora.system.controller 
 * @ClassName: UploadController 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-5-7 上午11:24:54 
 *
 */
package cn.mypandora.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.mypandora.orm.Page;
import cn.mypandora.system.po.UploadFile;
import cn.mypandora.system.service.UploadFileService;
import cn.mypandora.util.MyFileConversionUtil;

/**
 * @ClassName: UploadController
 * @Description: TODO
 * @Author: kaibo
 * @date: 2014-5-7
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-7 上午11:24:54
 * @UpdateRemark: What is modified?
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController {
    @Resource
    private UploadFileService uploadFileService;

    @RequestMapping(value = "/list.html")
    public String list(ModelMap model,
            @RequestParam(value = "currentPage", required = true, defaultValue = "1") int currentPage) {
        Page<UploadFile> page = new Page<>();
        page.setCurrentPage(currentPage);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("fileType", 1);
        page = uploadFileService.findPageFileByCondition("pageFiles", params, page);
        model.put("files", page.getResultList());
        model.put("page", page);
        return "upload/list";
    }

    /**
     * @Title: toUpload
     * @Description: 打开上传页面。
     * @return
     * @return String
     */
    @RequestMapping(value = "/toupload.html")
    public String toUpload() {
        return "upload/add";
    }

    /**
     * @Title: upload
     * @Description: 上传文件。
     * @param part
     * @return
     * @return String
     */
    @RequestMapping(value = "/upload.html", method = RequestMethod.POST)
    public String upload(@RequestParam("file") Part part) {
        System.out.println(getFileName(part));
        System.out.println(part.getContentType());
        System.out.println(part.getSize());
        try {
            /* 将原始材料文件进行保存 */
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
            uploadFileService.saveFile(file);

            /* 将原始材料转换PDF文件进行保存 */
            String shortFileName = getFileName(part).substring(0, getFileName(part).lastIndexOf("."));
            String fullFileName = shortFileName + ".pdf";
            String pdfPath = resourceBundle.getString("pdfPath") + fullFileName;
            MyFileConversionUtil.word2pdf(savePath, pdfPath);
            // 保存到数据库
            UploadFile filePDF = new UploadFile();
            filePDF.setFileSize(part.getSize());
            filePDF.setFileName(fullFileName);
            filePDF.setFileType(2);
            filePDF.setSavePath(pdfPath);
            uploadFileService.saveFile(filePDF);

            /* 将PDF文件转换成SWF文件 */
            String targetPath = resourceBundle.getString("swfPath");
            String swfFileName = shortFileName + ".swf";
            String swfPath = targetPath + swfFileName;
            MyFileConversionUtil.pdf2swf(pdfPath, targetPath, swfFileName);
            // 保存到数据库
            UploadFile fileSWF = new UploadFile();
            fileSWF.setFileSize(part.getSize());
            fileSWF.setFileName(swfFileName);
            fileSWF.setFileType(3);
            fileSWF.setSavePath(swfPath);
            uploadFileService.saveFile(fileSWF);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 显示上传的文件列表
        return "redirect:/upload/list.html";
    }

    @RequestMapping(value = "/listswf.html")
    public String listSWF(ModelMap model,
            @RequestParam(value = "currentPage", required = true, defaultValue = "1") int currentPage) {
        Page<UploadFile> page = new Page<>();
        page.setCurrentPage(currentPage);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("fileType", 3);
        page = uploadFileService.findPageFileByCondition("pageFiles", null, page);
        model.put("files", page.getResultList());
        model.put("page", page);
        return "upload/list_swf";
    }

    @RequestMapping(value = "/toshow.html", method = RequestMethod.GET)
    public String toShow(Long id, ModelMap model) {
        UploadFile uploadFile = uploadFileService.findFileById(id);
        model.put("file", uploadFile);
        return "upload/show_swf";
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
