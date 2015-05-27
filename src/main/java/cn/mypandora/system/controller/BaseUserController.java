/**
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.system.controller
 * @ClassName: BaseUserController
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-3-13 下午2:17:38 
 *
 */
package cn.mypandora.system.controller;

import cn.mypandora.orm.Page;
import cn.mypandora.system.po.BaseUser;
import cn.mypandora.system.service.BaseUserService;
import cn.mypandora.util.MyDateUtils;
import cn.mypandora.util.MyExcelUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: BaseUserController
 * @Description: 用户管理Controller。
 * @Author: kaibo
 * @date: 2014-3-13
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-3-13 下午2:17:38
 * @UpdateRemark: What is modified?
 */
@Controller
@RequestMapping(value = "/users")
public class BaseUserController {
    private final static String XLS = ".xls";
    private final static String XLSX = ".xlsx";

    @Resource
    private BaseUserService baseUserService;

    /**
     * @return String
     * @Title: toList
     * @Description: 跳转到用户列表页面。
     */
    @RequestMapping(value = "/toList", method = RequestMethod.GET)
    public String toList() {
        return "user/list";
    }

    /**
     * @param request
     * @return
     * @Title: list
     * Description: : 获取用户列表数据。
     */
    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Page<BaseUser> list(HttpServletRequest request) {
        //获取jqGrid的分页参数、排序参数和查询参数
        int currentPage = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        String sortName = request.getParameter("sidx");
        String orderName = request.getParameter("sord");
        String username = request.getParameter("username");

        Page<BaseUser> page = new Page<>();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);

        Map<String, Object> params = new HashMap<>();
        params.put("sortName", sortName);
        params.put("orderName", orderName);
        params.put("username", username);

        page = baseUserService.findPageUserByCondition("pageUsers", params, page);
        return page;
    }

    /**
     * @return String
     * @Title: taAdd
     * @Description: 跳转到添加页面。
     */
    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAdd() {
        return "user/add";
    }

    /**
     * @param baseUser
     * @return String
     * @Title: add
     * @Description: 添加用户。
     */
    @RequestMapping(method = RequestMethod.POST)
    public String add(BaseUser baseUser) {
        baseUserService.addUser(baseUser);
        return "user/list";
    }

    /**
     * @param id
     * @return
     * @Title: delete
     * @Description: 删除用户。
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    Map<String, Object> delete(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        baseUserService.deleteUser(id);
        result.put("code", 200);
        result.put("msg", "OK");
        return result;
    }

    /**
     * @param ids
     * @return
     * @Title: delete
     * @Description: 删除批量用户。
     */
    @RequestMapping(value = "/batch/{ids}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    Map<String, Object> delete(@PathVariable Long[] ids) {
        Map<String, Object> result = new HashMap<>();
        baseUserService.deleteBatchUser(ids);
        result.put("code", 200);
        result.put("msg", "OK");
        return result;
    }

    /**
     * @param id
     * @return String
     * @Title: update
     * @Description: 跳转到用户修改页面。
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, ModelMap model) {
        BaseUser baseUser = baseUserService.findUserById(id);
        model.put("user", baseUser);
        return "user/edit";
    }

    /**
     * @param baseUser
     * @return String
     * @Title: update
     * @Description: 用户修改。
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String update(BaseUser baseUser) {
        baseUserService.updateUser(baseUser);
        return "user/list";
    }

    /**
     * @return void
     * @throws IOException
     * @Title: down
     * @Description: 下载。
     */
    @RequestMapping(value = "/down/{currentPage}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> down(@PathVariable int currentPage, HttpServletRequest request) throws IOException {
        // 获取要生成的Excel表格数据
        Page<BaseUser> page = new Page<>();
        page.setCurrentPage(currentPage);
        page = baseUserService.findPageUserByCondition("pageUsers", null, page);
        // 获取项目根路径并用查询数据生成表格
        String rootpath = request.getSession().getServletContext().getRealPath("/");
        String fileName = MyDateUtils.getCurrentDate() + XLSX;
        MyExcelUtil.exportExcel(rootpath + "download" + fileName, "sheet1", "ID,用户名,性别,生日,积分", page.getResultList(), BaseUser.class, "id,username,sex,birthday,credits");
        // 下载
        File file = new File(rootpath + "download" + fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    /*********************** 个人信息 **********************/
    /**
     * @param model
     * @return String
     * @Title: myInfo
     * @Description: 显示我的个人信息。
     */
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public String myInfo(ModelMap model) {
        BaseUser userself = new BaseUser();
        // TODO 用户ID应从session中获取。
        userself = baseUserService.findUserById(5L);
        model.put("userself", userself);
        return "user/myinfo";
    }

    /**
     * @param id
     * @param model
     * @return String
     * @Title: updateMe
     * @Description: 跳转到修改我的信息页面。
     */
    @RequestMapping(value = "/me/{id}", method = RequestMethod.GET)
    public String updateMe(@PathVariable Long id, ModelMap model) {
        BaseUser baseUser = baseUserService.findUserById(id);
        model.put("userself", baseUser);
        return "user/myedit";
    }

    /**
     * @param baseUser
     * @return String
     * @Title: updateMe
     * @Description: 修改我的个人信息。
     */
    @RequestMapping(value = "/me", method = RequestMethod.PUT)
    public String updateMe(BaseUser baseUser) {
        baseUserService.updateUser(baseUser);
        return "redirect:/user/me";
    }

    /*********************** 用户分析 **********************/
    /**
     * @param model
     * @return String
     * @Title: userAnalysis
     * @Description: 用户分析。
     */
    @RequestMapping(value = "/analysis", method = RequestMethod.GET)
    public String userAnalysis(ModelMap model) {
        //获取当前月份
//        String curMonth=MyDateUtils.getCurrentMonth();
//        List<Map<String,Object>> userCount=baseUserService.findUserCount(curMonth);
//        model.put("userCount", userCount);
//        return "user/useranalysis";

        Map<String, Object> sexCount = baseUserService.findUserSexCount();
        model.put("sexCount", sexCount);
        return "user/analysis";
    }

}
