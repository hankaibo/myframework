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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.mypandora.orm.Page;
import cn.mypandora.system.po.BaseUser;
import cn.mypandora.system.service.BaseUserService;

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
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(ModelMap model,
            @RequestParam(value = "currentPage", required = true, defaultValue = "1") int currentPage) {
        Page<BaseUser> page = new Page<>();
        page.setCurrentPage(currentPage);
        page = baseUserService.findPageUserByCondition("pageUsers", null, page);
        model.put("users", page.getResultList());
        model.put("page", page);
        return "user/list";
    }

    /**
     * @Title: add
     * @Description: 跳转到添加页面。
     * @return
     * @return String
     */
    @RequestMapping(method = RequestMethod.GET)
    public String add() {
        return "user/add";
    }

    /**
     * @Title: add
     * @Description: 添加用户。
     * @param baseUser
     * @return
     * @return String
     */
    @RequestMapping(method = RequestMethod.POST)
    public String add(BaseUser baseUser) {
        baseUserService.addUser(baseUser);
        return "redirect:/user/users";
    }

    /**
     * @Title: delete
     * @Description: 删除用户。
     * @param id
     * @return
     * @return String
     */
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        baseUserService.deleteUser(id);
        return "redirect:/user/users";
    }

    /**
     * @Title: update
     * @Description: 跳转到用户修改页面。
     * @param id
     * @return
     * @return String
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, ModelMap model) {
        BaseUser baseUser = baseUserService.findUserById(id);
        model.put("user", baseUser);
        return "user/edit";
    }

    /**
     * @Title: update
     * @Description: 用户修改。
     * @param baseUser
     * @return
     * @return String
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(BaseUser baseUser) {
        baseUserService.updateUser(baseUser);
        return "redirect:/user/users";
    }
    
    /***********************个人信息**********************/
    /** 
     * @Title: myInfo
     * @Description: 显示我的个人信息。
     * @param model
     * @return
     * @return String
     */
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public String myInfo(ModelMap model) {
        BaseUser userself=new BaseUser();
        // TODO 用户ID应从session中获取。
        userself=baseUserService.findUserById(5L);
        model.put("userself", userself);
        return "user/myinfo";
    }
    
    /** 
     * @Title: updateMe
     * @Description: 跳转到修改我的信息页面。
     * @param id
     * @param model
     * @return
     * @return String
     */
    @RequestMapping(value = "/me/{id}", method = RequestMethod.GET)
    public String updateMe(@PathVariable Long id, ModelMap model) {
        BaseUser baseUser = baseUserService.findUserById(id);
        model.put("user", baseUser);
        return "user/myedit";
    }
    
    /** 
     * @Title: updateMe
     * @Description: 修改我的个人信息。
     * @param baseUser
     * @return
     * @return String
     */
    @RequestMapping(value = "/me", method = RequestMethod.PUT)
    public String updateMe(BaseUser baseUser) {
        baseUserService.updateUser(baseUser);
        return "redirect:/user/me";
    }
}
