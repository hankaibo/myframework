/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.cpbpbc.controller;

import cn.mypandora.system.po.BaseUser;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>User: hankaibo
 * <p>Date: 2015/7/19
 * <p>Version: 1.0
 */
@Controller
@RequestMapping(value = "/report")
public class ReportController {

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
        return null;
    }
}
