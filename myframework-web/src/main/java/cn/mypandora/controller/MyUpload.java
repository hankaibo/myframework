///**
// * Copyright © 2015.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// */
//package cn.mypandora.controller;
//
//import cn.mypandora.po.UploadFile;
//import cn.mypandora.service.BaseUploadService;
//import cn.mypandora.util.MyExcelUtil;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.annotation.Resource;
//import javax.servlet.http.Part;
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.Timestamp;
//import java.util.List;
//import java.util.Map;
//import java.util.ResourceBundle;
//
///**
// * @ClassName: MyUpload
// * @Description: 上传工具类。
// * @Author: kaibo
// * @date: 2014-6-15
// * @UpdateUser: kaibo
// * @UpdateDate: 2014-6-15 上午12:06:22
// * @UpdateRemark: What is modified?
// */
//@Controller
//@RequestMapping(value = "/upload")
//public class MyUpload {
//    private static final Logger logger = LoggerFactory.getLogger(MyUpload.class);
//
//    @Resource
//    private BaseUploadService baseUploadService;
//
//    /**
//     * @param part
//     * @return void
//     * @Title: upload
//     * @Description: 上传文件。
//     */
//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public void upload(@RequestParam("myFile") Part part, @RequestParam("choosePath") String choosePath) {
//        try {
//            /* 将文件进行保存 */
//            /* 获取上传文件的存放文件夹 */
//            ResourceBundle resourceBundle = ResourceBundle.getBundle("upload");
//            String savePath = resourceBundle.getString(choosePath != null ? choosePath : "defaultPath") + getFileName(part);
//            // String webRootPath = request.getServletContext().getRealPath("/upload");
//            String webRootPath = System.getProperty("contentPath");
//            part.write(webRootPath + savePath);
//
//            // 保存到数据库
//            UploadFile file = new UploadFile();
//            file.setFileSize(part.getSize());
//            file.setFileName(getFileName(part));
//            file.setSaveName(getFileName(part));
//            file.setFileType(1);
//            file.setSavePath(savePath);
//            file.setCreateTime(new Timestamp(1234567890L));
//            file.setUpdateTime(new Timestamp(1234567891L));
//            baseUploadService.saveFile(file);
//
////            return file;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 导入用户。数据将直接保存到数据库而不在本地文件路径下保存。
//     *
//     * @param part
//     */
//    @RequestMapping(value = "import", method = RequestMethod.POST)
//    public void importUser(@RequestParam("myFile") Part part) {
//        try {
//            InputStream inputStream = part.getInputStream();
//            String fileName = getFileName(part);
//            String fieldNames = "08级,姓名, 性别, 身份证号, 学号, 年级, 系部代码, 系部, 专业";
//            List<Map<String, String>> listmap = MyExcelUtil.importExcelToMap(inputStream, fileName, fieldNames);
//            for (Map<String, String> foo : listmap) {
//                while (foo.entrySet().iterator().hasNext()) {
//                    System.out.println(123);
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * @param part 上传文件的文件名，如果如果没有则返回null
//     * @return String
//     * @Title: getFileName
//     * @Description: 从Part的Header信息中提取上传文件的文件名
//     */
//    private String getFileName(Part part) {
//        if (part == null) {
//            return null;
//        }
//        // 获取header信息中的content-disposition，如果为文件，则可以从其中提取出文件名
//        String fileName = part.getHeader("content-disposition");
//        if (StringUtils.isBlank(fileName)) {
//            return null;
//        }
//        return StringUtils.substringBetween(fileName, "filename=\"", "\"");
//    }
//}
