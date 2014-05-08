/**   
 * @ProjectName: myframework
 * @Package: cn.mypandora.system.controller 
 * @ClassName: BaseLogController 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-4-30 下午3:31:00 
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
import cn.mypandora.system.po.BaseLog;
import cn.mypandora.system.service.BaseLogService;

/**
 * @ClassName: BaseLogController
 * @Description: TODO
 * @Author: kaibo
 * @date: 2014-4-30
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-4-30 下午3:31:00
 * @UpdateRemark: What is modified?
 */
@Controller
@RequestMapping(value = "/log")
public class BaseLogController {
    @Resource
    private BaseLogService baseLogService;

    /**
     * @Title: list
     * @Description: 查询日志列表。
     * @param model
     * @param currentPage
     * @return
     * @return String
     */
    @RequestMapping(value = "/list.html")
    public String list(ModelMap model,
            @RequestParam(value = "currentPage", required = true, defaultValue = "1") int currentPage) {
        Page<BaseLog> page = new Page<>();
        page.setCurrentPage(currentPage);
        page = baseLogService.findLogByCondition("pageLogs", null, page);
        model.put("logs", page.getResultList());
        model.put("page", page);
        return "log/list";
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
        baseLogService.deleteLog(id);
        return "redirect:/log/list.html";
    }

}
