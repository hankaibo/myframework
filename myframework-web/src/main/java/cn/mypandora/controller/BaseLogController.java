/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.controller;

import cn.mypandora.po.BaseLog;
import cn.mypandora.service.BaseLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: BaseLogController
 * @Description: 日志管理Controller。
 * @Author: kaibo
 * @date: 2014-4-30
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-4-30 下午3:31:00
 * @UpdateRemark: What is modified?
 */
@Controller
@RequestMapping(value = "/logs")
public class BaseLogController {
    @Resource
    private BaseLogService baseLogService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/toList", method = RequestMethod.GET)
    public String toList() {
        return "log/list";
    }
    /**
     * @Title: list
     * @Description: 查询日志列表。
     * @param model
     * @param currentPage
     * @return
     * @return String
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    PageInfo<BaseLog> list(ModelMap model,
            @RequestParam(value = "currentPage", required = true, defaultValue = "1") int currentPage) {
        PageInfo<BaseLog> page = new PageInfo<>();
        page.setPageNum(currentPage);
        page = baseLogService.findLogByCondition("pageLogs", null, page);
        return page;
    }

    /**
     * @Title: del
     * @Description: 删除日志。
     * @param id
     * @return
     * @return String
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String del(@PathVariable Long id) {
        baseLogService.deleteLog(id);
        return "redirect:/log/logs";
    }

    /************* 进入我的日志 **************/
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public String myLog(ModelMap model,
            @RequestParam(value = "currentPage", required = true, defaultValue = "1") int currentPage) {
        Map<String, Object> params = new HashMap<String, Object>();
        //从session中获取用户的ID。
        params.put("id", 5);
        PageInfo<BaseLog> page = new PageInfo<>();
        page.setPageNum(currentPage);
        page = baseLogService.findLogByCondition("pageLogs", null, page);
        model.put("logs", page.getList());
        model.put("page", page);
        return "log/mylog";
    }

}
