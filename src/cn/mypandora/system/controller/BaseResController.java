package cn.mypandora.system.controller;

import cn.mypandora.system.po.BaseRes;
import cn.mypandora.system.service.BaseResService;
import cn.mypandora.system.vo.ParentChildTree;
import cn.mypandora.util.MyTreeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kaibo on 2014/8/5.
 * desc
 */
@Controller
@RequestMapping(value = "/res")
public class BaseResController {
    @Resource
    private BaseResService baseResService;

    /**
     * @Title: list
     * @Description: 获取整个资源树。
     * @param model
     * @return
     * @return String
     */
    @RequestMapping(value = "/resources", method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<BaseRes> listResoureces = baseResService.loadFullRes();

        List<ParentChildTree> listPCTrees = new ArrayList<>();
        for (BaseRes res : listResoureces) {
            listPCTrees.add(MyTreeUtil.lfNode2pcNode(res));
        }
        model.put("listres", listPCTrees);
        return "res/list";
    }

    /**
     * @Title: add
     * @Description: 添加资源。
     * @param id
     * @param BaseRes
     * @return
     * @return BaseRes
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(Long id) {
        Map<String, Object> map = new HashMap<>();
        BaseRes res = (BaseRes) baseResService.findResById(id);
        map.put("name", "testName");
        map.put("lft", res.getRgt());
        map.put("rgt", res.getRgt() + 1);
        map.put("parentId", res.getId());
        baseResService.addRes(id, map);
        return map;
    }

    /**
     * @Title: edit
     * @Description: 编辑资源。
     * @param dept
     * @return
     * @return String
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String edit(BaseRes res) {
        baseResService.updateRes(res);
        return "true";
    }

    /**
     * @Title: del
     * @Description: 删除一个资源（叶子节点）
     * @param id
     * @return
     * @return String
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String del(@PathVariable Long id) {
        baseResService.delRes(id);
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
        baseResService.moveUpRes(id, upId);

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
        baseResService.moveDownRes(id, downId);

        result.put("result", true);
        return result;
    }
}
