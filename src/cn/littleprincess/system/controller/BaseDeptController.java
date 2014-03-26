/**   
 * @ProjectName: MyFramework
 * @Package: cn.littleprincess.system.controller 
 * @ClassName: BaseDeptController 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-3-10 下午11:45:58 
 *
 */
package cn.littleprincess.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.littleprincess.system.po.BaseDept;
import cn.littleprincess.system.service.BaseDeptService;
import cn.littleprincess.system.vo.ParentChildTree;
import cn.littleprincess.util.MyTreeUtil;

/**
 * @ClassName: BaseDeptController
 * @Description: TODO
 * @Author: kaibo
 * @date: 2014-3-10
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-3-10 下午11:45:58
 * @UpdateRemark: What is modified?
 */
@Controller
@RequestMapping(value = "/dept")
public class BaseDeptController {
    @Resource
    private BaseDeptService baseDeptService;

    /**
     * @Title: list
     * @Description: 获取整棵树
     * @param model
     * @return
     * @return String
     */
    @RequestMapping(value = "/list.html")
    public String list(ModelMap model) {
        List<BaseDept> listDepts = baseDeptService.loadFullTree();

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
     * @param baseDept
     * @return
     * @return BaseDept
     */
    @RequestMapping(value = "/add.html", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> add(Long id) {
        Map<String, Object> map = new HashMap<>();
        BaseDept dept = (BaseDept) baseDeptService.findById(id);
        map.put("name", "testName");
        map.put("lft", dept.getRgt());
        map.put("rgt", dept.getRgt() + 1);
        map.put("parentId", dept.getId());
        baseDeptService.addChild(id, map);
        return map;
    }

    /**
     * @Title: edit
     * @Description: 编辑部门。
     * @param dept
     * @return
     * @return String
     */
    @RequestMapping(value = "/edit.html", method = RequestMethod.GET)
    @ResponseBody
    public String edit(BaseDept dept) {
        baseDeptService.updateEntity(dept);
        return "true";
    }

    /**
     * @Title: del
     * @Description: 删除一个部门（叶子节点）
     * @param id
     * @return
     * @return String
     */
    @RequestMapping(value = "/del.html", method = RequestMethod.GET)
    @ResponseBody
    public String del(Long id) {
        baseDeptService.delChild(id);
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
    @RequestMapping(value = "/moveUp.json", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> moveUp(Long id, Long upId) {
        Map<String, String> result=new HashMap<>();
        baseDeptService.moveUp(id, upId);
        
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
    @RequestMapping(value = "/moveDown.json", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> moveDown(Long id, Long downId) {
        Map<String, Object> result=new HashMap<>();
        baseDeptService.moveDown(id, downId);
        
        result.put("result", true);
        return result;
    }

}
