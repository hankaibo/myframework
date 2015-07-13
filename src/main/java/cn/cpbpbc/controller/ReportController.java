package cn.cpbpbc.controller;

import cn.mypandora.orm.Page;
import cn.mypandora.system.po.BaseUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kaibo on 2015/6/16.
 * desc
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
