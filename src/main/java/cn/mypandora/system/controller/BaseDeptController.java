/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.system.controller 
 * @ClassName: BaseDeptController 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-3-10 下午11:45:58 
 *
 */
package cn.mypandora.system.controller;

import cn.mypandora.system.po.BaseDept;
import cn.mypandora.system.service.BaseDeptService;
import cn.mypandora.system.vo.ParentChildTree;
import cn.mypandora.util.MyTreeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: BaseDeptController
 * @Description: 部门管理Controller。
 * @Author: kaibo
 * @date: 2014-3-10
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-3-10 下午11:45:58
 * @UpdateRemark: What is modified?
 */
@Controller
@RequestMapping(value = "/depts")
public class BaseDeptController {
    @Resource
    private BaseDeptService baseDeptService;

    /**
     * @Title: list
     * @Description: 获取整个部门树。
     * @param model
     * @return
     * @return String
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<BaseDept> listDepts = baseDeptService.loadFullDept();

        List<ParentChildTree> listPCTrees = new ArrayList<>();
        for (BaseDept dept : listDepts) {
            listPCTrees.add(MyTreeUtil.lfNode2pcNode(dept));
        }
        model.put("listdepts", listPCTrees);
        return "dept/list";
    }

    /**
     * @Title: add
     * @Description: 添加部门。
     * @param id
     * @return
     * @return BaseDept
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(Long id) {
        Map<String, Object> map = new HashMap<>();
        BaseDept dept = baseDeptService.findDeptById(id);
        map.put("name", "testName");
        map.put("lft", dept.getRgt());
        map.put("rgt", dept.getRgt() + 1);
        map.put("parentId", dept.getId());
        baseDeptService.addDept(id, map);
        return map;
    }

    /**
     * @Title: edit
     * @Description: 编辑部门。
     * @param dept
     * @return
     * @return String
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String edit(@RequestBody BaseDept dept) {
        baseDeptService.updateDept(dept);
        return "true";
    }

    /**
     * @Title: del
     * @Description: 删除一个部门（叶子节点）
     * @param id
     * @return
     * @return String
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String del(@PathVariable Long id) {
        baseDeptService.delDept(id);
        return "true";
    }

    /**
     * @Title: moveUp
     * @Description: 上移。（同级叶子节点）
     * @param id
     *            弟弟节点ID
     * @param upId
     *            哥哥节点ID
     * @return void
     */
    @RequestMapping(value = "/{id}/{upId}/up", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> moveUp(@PathVariable Long id, @PathVariable Long upId) {
        Map<String, String> result = new HashMap<>();
        baseDeptService.moveUpDept(id, upId);

        result.put("result", "true");
        return result;
    }

    /**
     * @Title: moveDown
     * @Description: 下移。（同级叶子节点）
     * @param id
     *            哥哥节点ID
     * @param downId
     *            弟弟节点ID
     * @return void
     */
    @RequestMapping(value = "{id}/{upId}/down", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> moveDown(@PathVariable Long id, @PathVariable Long downId) {
        Map<String, Object> result = new HashMap<>();
        baseDeptService.moveDownDept(id, downId);

        result.put("result", "true");
        return result;
    }

}
