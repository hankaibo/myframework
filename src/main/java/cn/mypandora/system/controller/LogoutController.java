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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @Title: loginPage
     * @Description: 跳转到登录页面。
     * @param request
     * @param response
     * @return
     * @return String
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("captcha");
        String isCaptcha=resourceBundle.getString("isCaptcha");
        if(isCaptcha.equalsIgnoreCase("true")){
            request.setAttribute("isCaptcha", true);
        }
        return "login";
    }

}
