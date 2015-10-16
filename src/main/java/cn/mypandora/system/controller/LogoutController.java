/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

/**
 * @ClassName: LogoutController
 * @Description: 登出管理Controller。
 * @Author: kaibo
 * @date: 2014-3-23
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-3-23 上午12:22:20
 * @UpdateRemark: What is modified?
 */
@Controller
@RequestMapping(value = "/logout")
public class LogoutController {

    /**
     * 跳转到登录页面。
     *
     * @param request
     * @return 登录逻辑页面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request) {
        //TODO 放置于缓存中
        ResourceBundle resourceBundle = ResourceBundle.getBundle("captcha");
        String isCaptcha = resourceBundle.getString("isCaptcha");
        if (isCaptcha.equalsIgnoreCase("true")) {
            request.setAttribute("isCaptcha", true);
        }
        Subject currentUser = SecurityUtils.getSubject();
        if (SecurityUtils.getSubject().getSession() != null) {
            currentUser.logout();
        }
        return "login";
    }

}
