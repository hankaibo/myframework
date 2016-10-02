///**
// * Copyright © 2015.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// */
//package cn.mypandora.controller;
//
//import cn.mypandora.service.BaseResService;
//import cn.mypandora.service.BaseUserService;
//import cn.mypandora.vo.LoginCommand;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @ClassName:LoginController
// * @Description:登录管理Controller。
// * @Author:hankaibo
// * @date:2013-8-15
// * @UpdateUser:hankaibo
// * @UpdateDate:2013-8-15 上午1:04:21
// * @UpdateRemark:What is modified?
// */
//@Controller
//@RequestMapping(value = "/login")
//public class LoginController {
//    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
//
//    @Resource
//    private BaseUserService baseUserService;
//    @Resource
//    private BaseResService baseResService;
//
//    @RequestMapping(method = RequestMethod.GET)
//    public String loginPage(ModelMap model) {
//        return "login";
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public ModelAndView loginCheck(HttpServletRequest request, HttpServletResponse response, LoginCommand loginCommand) {
//        return actualLoginCheck(request, response, loginCommand, false);
//    }
//
//
//    private ModelAndView actualLoginCheck(HttpServletRequest request, HttpServletResponse response, LoginCommand loginCommand, boolean isCaptcha) {
////        boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
////
////        //构造登陆令牌环
////        UsernamePasswordToken token = new UsernamePasswordToken(loginCommand.getUsername(), loginCommand.getPassword());
////        try {
//
////            SecurityUtils.getSubject().login(token);
////            HttpSession session = request.getSession(true);
////            //登陆成功后，积分+5；查询对应资源；显示相应页面。
////            BaseUser user = baseUserService.findUserByUsername(loginCommand.getUsername());
////            user.setLastIp(request.getRemoteAddr());
////            user.setLastVisit(new Timestamp(System.currentTimeMillis()));
////            baseUserService.loginSuccess(user);
////            // 记录session的值
////            session.setAttribute("user", user);
////            List<BaseRes> listResoureces = baseResService.getResDescendant(1L);
////            List<ParentChildTree> listPCTrees = new ArrayList<>();
////            for (BaseRes res : listResoureces) {
////                listPCTrees.add(MyTreeUtil.lfNode2pcNode(res));
////            }
////            request.getSession().setAttribute("menuTree", listPCTrees);
//            return new ModelAndView("main");
////        } catch (UnknownAccountException e) {
////            logger.error("账号不存在!:" + e);
////        } catch (IncorrectCredentialsException e) {
////            logger.error("用户名/密码错误!:" + e);
////        } catch (ExcessiveAttemptsException e) {
////            logger.error("账户错误次数过多,暂时禁止登录!:" + e);
////        } catch (Exception e) {
////            logger.error("用户登录错误:" + e);
////        }
////        return isCaptcha ? new ModelAndView("login", "error", "用户名或密码错误.").addObject("isCaptcha", true) : new ModelAndView("login", "error", "用户名或密码错误.");
//    }
//}
