/**   
 * @ProjectName: myframework
 * @Package: cn.mypandora.system.controller 
 * @ClassName: ReadOnlineController 
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.mypandora.orm.Page;
import cn.mypandora.system.po.UploadFile;
import cn.mypandora.system.service.ReadOnlineService;
import cn.mypandora.util.MyFileConversionUtil;

/**
 * @ClassName: ReadOnlineController
 * @Description: 在线阅读管理Controller。
 * @Author: kaibo
 * @date: 2014-5-7
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-7 上午11:24:54
 * @UpdateRemark: What is modified?
 */
@Controller
@RequestMapping(value = "/readonline")
public class ReadOnlineController {
    @Resource
    private ReadOnlineService readOnlineService;

    /**
     * @Title: list
     * @Description: 显示原始文件列表。
     * @param model
     * @param currentPage
     * @return
     * @return String
     */
    @RequestMapping(value = "/files")
    public String list(ModelMap model,
            @RequestParam(value = "currentPage", required = true, defaultValue = "1") int currentPage) {
        Page<UploadFile> page = new Page<>();
        page.setCurrentPage(currentPage);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("fileType", 1);
        page = readOnlineService.findPageFileByCondition("pageFiles", params, page);
        model.put("files", page.getResultList());
        model.put("page", page);
        return "readonline/list";
    }

    /**
     * @Title: add
     * @Description: 打开上传页面。
     * @return
     * @return String
     */
    @RequestMapping(method = RequestMethod.GET)
    public String add() {
        return "readonline/add";
    }

    /**
     * @Title: add
     * @Description: 上传原始文件。
     * @param part
     * @return
     * @return String
     */
    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestParam("file") Part part) {
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
            readOnlineService.saveFile(file);

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
            readOnlineService.saveFile(filePDF);

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
            readOnlineService.saveFile(fileSWF);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 显示上传的文件列表
        return "redirect:/readonline/files";
    }

    /**
     * @Title: listSWF
     * @Description: 显示swf文件列表。
     * @param model
     * @param currentPage
     * @return
     * @return String
     */
    @RequestMapping(value = "/flashs", method = RequestMethod.GET)
    public String listSWF(ModelMap model,
            @RequestParam(value = "currentPage", required = true, defaultValue = "1") int currentPage) {
        Page<UploadFile> page = new Page<>();
        page.setCurrentPage(currentPage);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("fileType", 3);
        page = readOnlineService.findPageFileByCondition("pageFiles", params, page);
        model.put("files", page.getResultList());
        model.put("page", page);
        return "readonline/list_swf";
    }

    /**
     * @Title: show
     * @Description: 查看swf文件。
     * @param id
     * @param model
     * @return
     * @return String
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, ModelMap model) {
        UploadFile uploadFile = readOnlineService.findFileById(id);
        model.put("file", uploadFile);
        return "readonline/show_swf";
    }

    /**
     * @Title: delete
     * @Description: 删除原始文件。
     * @param id
     * @return
     * @return String
     */
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        readOnlineService.deleteFile(id);
        return "redirect:/readonline/files";
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
