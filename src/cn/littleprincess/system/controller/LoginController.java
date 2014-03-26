/**   
 * @ProjectName:MySpring
 * @Package:cn.littleprincess.web 
 * @ClassName:LoginController 
 * @Description:TODO
 * Copyright © 2013东软集团股份有限公司. All rights reserved.
 * @Author:hankaibo
 * @CreateDate: 2013-8-15 上午1:04:21 
 * @Version:v1.0
 *
 */
package cn.littleprincess.system.controller;

import java.awt.image.BufferedImage;
import java.util.Date;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.littleprincess.system.po.BaseUser;
import cn.littleprincess.system.service.BaseUserService;
import cn.littleprincess.system.vo.LoginCommand;
import cn.littleprincess.util.MyCookieUtil;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * @ClassName:LoginController
 * @Description:用户登录。
 * @Author:hankaibo
 * @date:2013-8-15
 * @UpdateUser:hankaibo
 * @UpdateDate:2013-8-15 上午1:04:21
 * @UpdateRemark:What is modified?
 */
@Controller
// 1标注一个spring mvc的bean
@RequestMapping(value = "/login")
public class LoginController {
    @Resource
    private Producer captchaProducer;
    @Resource
    private BaseUserService baseUserService;

    // 2负责处理/index.html请求
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    // 3负责处理/loginCheck.html的请求
    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, HttpServletResponse response, LoginCommand loginCommand) {
        // 首先判断cookie是否有效，是否能自动登录
        Cookie autoCookie = MyCookieUtil.isCookieExist(MyCookieUtil.COOKIE_KEY, request);
        if (autoCookie != null) {
            String loginId = validateCookie(autoCookie);
            if (loginId != null) {
                BaseUser user = baseUserService.findUserByUsername(loginId);
                user.setLastIp(request.getRemoteAddr());
                user.setLastVisit(new Date());
                baseUserService.loginSuccess(user);
                // 记录session的值
                request.getSession().setAttribute("user", user);
                return new ModelAndView("main");
            }
        }
        // 获取后台生成的验证码
        String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (loginCommand.getKaptcha().equals(code)) {
            System.out.println("---------------------------------");
        }
        // cookie无效之后
        boolean isValidUser = baseUserService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
        if (!isValidUser) {
            return new ModelAndView("login", "error", "用户名或密码错误.");
        } else {
            // 如果“记住我的登录状态”的复选框被选中
            if (loginCommand.isRememberMe()) {
                String cookieValue = loginCommand.getUserName() + "," + loginCommand.getPassword();
                Cookie cookie = new Cookie(MyCookieUtil.COOKIE_KEY, cookieValue);
                // cookie.setDomain("localhost");
                cookie.setMaxAge(86400);// 一天
                cookie.setPath(request.getContextPath());
                response.addCookie(cookie);
            }
            BaseUser user = baseUserService.findUserByUsername(loginCommand.getUserName());
            user.setLastIp(request.getRemoteAddr());
            user.setLastVisit(new Date());
            baseUserService.loginSuccess(user);
            // 记录session的值
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }
    }

    private String validateCookie(Cookie cookie) {
        String cookieStr = cookie.getValue();
        if (cookieStr != null) {
            String[] values = cookieStr.split(",");
            if (values.length == 2) {
                boolean isValidUser = baseUserService.hasMatchUser(values[0], values[1]);
                if (isValidUser == true) {
                    return values[0];
                }
            }
        }
        return null;
    }

    /** 
     * @Title: getKaptchaImage
     * @Description: 生成验证码。
     * @param request
     * @param response
     * @throws Exception
     * @return void
     */
    @RequestMapping(value="/captcha-image.html")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // create the text for the image
        String capText = captchaProducer.createText();
        // store the text in the session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

}
