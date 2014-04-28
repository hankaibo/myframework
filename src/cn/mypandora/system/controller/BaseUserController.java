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

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.mypandora.orm.Page;
import cn.mypandora.system.po.BaseUser;
import cn.mypandora.system.service.BaseUserService;

/**
 * @ClassName: BaseUserController
 * @Description: 用户的CRUD.
 * @Author: kaibo
 * @date: 2014-3-13
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-3-13 下午2:17:38
 * @UpdateRemark: What is modified?
 */
@Controller
@RequestMapping(value = "/user")
public class BaseUserController {
    @Resource
    private BaseUserService baseUserService;

    /**
     * @Title: list
     * @Description: 查询用户列表。
     * @param model
     * @return
     * @return String
     */
    @RequestMapping(value = "/list.html")
    public String list(ModelMap model,
            @RequestParam(value = "currentPage", required = true, defaultValue = "1") int currentPage) {
        Page<BaseUser> page = new Page<>();
        page.setCurrentPage(currentPage);
        page = baseUserService.findByCondition("pageUsers", null, page);
        model.put("users", page.getResultList());
        model.put("page", page);
        return "user/list";
    }

    /**
     * @Title: toAdd
     * @Description: 跳转到添加页面。
     * @return
     * @return String
     */
    @RequestMapping(value = "/toadd.html")
    public String toAdd() {
        return "user/add";
    }

    /**
     * @Title: add
     * @Description: 添加用户。
     * @param baseUser
     * @return
     * @return String
     */
    @RequestMapping(value = "/add.html", method = RequestMethod.POST)
    public String add(BaseUser baseUser) {
        baseUserService.addEntity(baseUser);
        return "redirect:/user/list.html";
    }

    /**
     * @Title: del
     * @Description: 删除用户。
     * @param id
     * @return
     * @return String
     */
    @RequestMapping(value = "/del.html", method = RequestMethod.GET)
    public String del(Long id) {
        baseUserService.deleteEntity(id);
        return "redirect:/user/list.html";
    }

    /**
     * @Title: toEdit
     * @Description: 跳转到用户修改页面。
     * @param id
     * @return
     * @return String
     */
    @RequestMapping(value = "/toedit.html", method = RequestMethod.GET)
    public String toEdit(Long id, ModelMap model) {
        BaseUser baseUser = baseUserService.findById(id);
        model.put("user", baseUser);
        return "user/edit";
    }

    /**
     * @Title: edit
     * @Description: 用户修改。
     * @param baseUser
     * @return
     * @return String
     */
    @RequestMapping(value = "/edit.html", method = RequestMethod.POST)
    public String edit(BaseUser baseUser) {
        baseUserService.updateEntity(baseUser);
        return "redirect:/user/list.html";
    }
}
