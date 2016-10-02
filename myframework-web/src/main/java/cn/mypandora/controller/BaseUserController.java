///**
// * Copyright © 2015.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// */
//package cn.mypandora.controller;
//
//import cn.mypandora.po.BaseUser;
//import cn.mypandora.po.UploadFile;
//import cn.mypandora.service.BaseUserService;
//import cn.mypandora.util.MyDateUtils;
//import cn.mypandora.util.MyExcelUtil;
//import com.github.pagehelper.PageInfo;
//import io.swagger.annotations.*;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.authz.annotation.RequiresRoles;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.Part;
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @ClassName: BaseUserController
// * @Description: 用户管理Controller。
// * @Author: kaibo
// * @date: 2014-3-13
// * @UpdateUser: kaibo
// * @UpdateDate: 2014-3-13 下午2:17:38
// * @UpdateRemark: What is modified?
// */
//@Controller
//@RequestMapping(value = "/users")
//@Api(value = "contacts-api", description = "有关于用户的CURD操作", position = 5)
//public class BaseUserController {
//    private final static String XLS = ".xls";
//    private final static String XLSX = ".xlsx";
//
//    @Resource
//    private BaseUserService baseUserService;
//
//    @ResponseBody
//    @RequestMapping(value = "/show", method=RequestMethod.POST)
//    @ApiOperation(value="测试接口", notes="测试接口详细描述")
//    public String show(
//            @ApiParam(required=true, name="name", value="姓名")
//            @RequestParam(name = "name") String stuName){
//        return "success";
//    }
//
//
//    /**
//     * @return String
//     * @Title: toList
//     * @Description: 跳转到用户列表页面。
//     */
//    @RequestMapping(value = "/toList", method = RequestMethod.GET)
//    public String toList() {
//        return "user/list";
//    }
//
//    /**
//     * @param request
//     * @return
//     * @Title: list
//     * Description: : 获取用户列表数据。
//     */
//    @ApiOperation(value = "获取用户列表", notes = "")
//    @RequestMapping(value = { "" }, method = RequestMethod.GET)
//    public
//    @ResponseBody
//    PageInfo<BaseUser> list(HttpServletRequest request) {
//        //获取jqGrid的分页参数、排序参数和查询参数
//        int currentPage = Integer.parseInt(request.getParameter("page"));
//        int pageSize = Integer.parseInt(request.getParameter("rows"));
//        String sortName = request.getParameter("sidx");
//        String orderName = request.getParameter("sord");
//        String username = request.getParameter("username");
//
//        PageInfo<BaseUser> page = new PageInfo<>();
//        page.setPageNum(currentPage);
//        page.setPageSize(pageSize);
//
//        Map<String, Object> params = new HashMap<>();
//        if (null != sortName && StringUtils.isNotBlank(sortName)) {
//            params.put("sortName", sortName);
//        }
//        if (null != orderName && StringUtils.isNotBlank(orderName)) {
//            params.put("orderName", orderName);
//        }
//        if (null != username && StringUtils.isNotBlank(username)) {
//            params.put("username", username);
//        }
//
//        page = baseUserService.findPageUserByCondition("pageUsers", params, page);
//        return page;
//    }
//
//    /**
//     * @return String
//     * @Title: taAdd
//     * @Description: 跳转到添加页面。
//     */
//    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
//    public String toAdd() {
//        return "user/add";
//    }
//
//    /**
//     * @param baseUser
//     * @return String
//     * @Title: add
//     * @Description: 添加用户。
//     */
//    @ApiOperation(value = "创建用户", notes = "返回用户实体对象", response = BaseUser.class, position = 2)
//    @RequestMapping(method = RequestMethod.POST)
//    public String add(BaseUser baseUser) {
//        baseUserService.addUser(baseUser);
//        return "user/list";
//    }
//
//    /**
//     * @param id
//     * @return
//     * @Title: delete
//     * @Description: 删除用户。
//     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public
//    @ResponseBody
//    Map<String, Object> delete(@PathVariable Long id) {
//        Map<String, Object> result = new HashMap<>();
//        baseUserService.deleteUser(id);
//        result.put("code", 200);
//        result.put("msg", "OK");
//        return result;
//    }
//
//    /**
//     * @param ids
//     * @return
//     * @Title: delete
//     * @Description: 删除批量用户。
//     */
//    @RequiresRoles("admin")
//    @RequestMapping(value = "/batch/{ids}", method = RequestMethod.DELETE)
//    public
//    @ResponseBody
//    Map<String, Object> delete(@PathVariable Long[] ids) {
//        Map<String, Object> result = new HashMap<>();
//        baseUserService.deleteBatchUser(ids);
//        result.put("code", 200);
//        result.put("msg", "OK");
//        return result;
//    }
//
//    /**
//     * @param id
//     * @return String
//     * @Title: update
//     * @Description: 跳转到用户修改页面。
//     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public String update(@PathVariable Long id, ModelMap model) {
//        BaseUser baseUser = baseUserService.findUserById(id);
//        model.put("user", baseUser);
//        return "user/edit";
//    }
//
//    /**
//     * @param baseUser
//     * @return String
//     * @Title: update
//     * @Description: 用户修改。
//     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "更新成功", response = BaseUser.class),
//            @ApiResponse(code = 404, message = "找不到页面"),
//            @ApiResponse(code = 500, message = "内部报错")}
//    )
//    public String update(BaseUser baseUser) {
//        baseUserService.updateUser(baseUser);
//        return "user/list";
//    }
//
//    @RequestMapping(value = "/up", method = RequestMethod.POST)
//    public void up(@RequestParam("FileDate") Part part) {
//        UploadFile file = new UploadFile();
//        file.setFileSize(part.getSize());
//
//    }
//
//    /**
//     * @return void
//     * @throws IOException
//     * @Title: down
//     * @Description: 下载。
//     */
//    @RequestMapping(value = "/down/{currentPage}", method = RequestMethod.GET)
//    public ResponseEntity<byte[]> down(@PathVariable int currentPage, HttpServletRequest request) throws IOException {
//        // 获取要生成的Excel表格数据
//        PageInfo<BaseUser> page = new PageInfo<>();
//        page.setPageNum(currentPage);
//        page = baseUserService.findPageUserByCondition("pageUsers", null, page);
//        // 获取项目根路径并用查询数据生成表格
//        String rootpath = request.getSession().getServletContext().getRealPath("/");
//        String fileName = MyDateUtils.getCurrentDate() + XLSX;
//        MyExcelUtil.exportExcel(rootpath + "download" + fileName, "sheet1", "ID,用户名,性别,生日,积分", page.getList(), BaseUser.class, "id,username,sex,birthday,credits");
//        // 下载
//        File file = new File(rootpath + "download" + fileName);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDispositionFormData("attachment", fileName);
//        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
//    }
//
//    /*********************** 个人信息 **********************/
//    /**
//     * @param model
//     * @return String
//     * @Title: myInfo
//     * @Description: 显示我的个人信息。
//     */
//    @RequestMapping(value = "/me", method = RequestMethod.GET)
//    public String myInfo(ModelMap model) {
//        BaseUser userself = new BaseUser();
//        // TODO 用户ID应从session中获取。
//        userself = baseUserService.findUserById(5L);
//        model.put("userself", userself);
//        return "user/myinfo";
//    }
//
//    /**
//     * @param id
//     * @param model
//     * @return String
//     * @Title: updateMe
//     * @Description: 跳转到修改我的信息页面。
//     */
//    @RequestMapping(value = "/me/{id}", method = RequestMethod.GET)
//    public String updateMe(@PathVariable Long id, ModelMap model) {
//        BaseUser baseUser = baseUserService.findUserById(id);
//        model.put("userself", baseUser);
//        return "user/myedit";
//    }
//
//    /**
//     * @param baseUser
//     * @return String
//     * @Title: updateMe
//     * @Description: 修改我的个人信息。
//     */
//    @RequestMapping(value = "/me", method = RequestMethod.PUT)
//    public String updateMe(BaseUser baseUser) {
//        baseUserService.updateUser(baseUser);
//        return "redirect:/user/me";
//    }
//
//    /*********************** 用户分析 **********************/
//    /**
//     * @param model
//     * @return String
//     * @Title: userAnalysis
//     * @Description: 用户分析。
//     */
//    @RequestMapping(value = "/analysis", method = RequestMethod.GET)
//    public String userAnalysis(ModelMap model) {
//        //获取当前月份
////        String curMonth=MyDateUtils.getCurrentMonth();
////        List<Map<String,Object>> userCount=baseUserService.findUserCount(curMonth);
////        model.put("userCount", userCount);
////        return "user/useranalysis";
//
//        Map<String, Object> sexCount = baseUserService.findUserSexCount();
//        model.put("sexCount", sexCount);
//        return "user/analysis";
//    }
//
//}
