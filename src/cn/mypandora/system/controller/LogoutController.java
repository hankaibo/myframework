/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.system.controller 
 * @ClassName: LogoutController 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-3-23 上午12:22:20 
 *
 */
package cn.mypandora.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.mypandora.util.MyCookieUtil;

/**
 * @ClassName: LogoutController
 * @Description: TODO
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
     * @Title: loginPage
     * @Description: 删除cookie中的值以及session中的值，并跳转到登录页面。
     * @param request
     * @param response
     * @return
     * @return String
     */
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, HttpServletResponse response) {
        MyCookieUtil.removeCookie(MyCookieUtil.COOKIE_KEY, request, response);
        request.getSession().removeAttribute("loginId");
        return "login";
    }

}
