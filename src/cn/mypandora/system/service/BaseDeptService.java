/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.system.service 
 * @ClassName: BaseDeptService 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014年3月7日 下午5:08:53 
 *
 */
package cn.mypandora.system.service;

import java.util.List;

import cn.mypandora.system.po.BaseDept;

/**
 * @ClassName: BaseDeptService
 * @Description: 部门管理Service。
 * @Author: kaibo
 * @date: 2014年3月7日
 * @UpdateUser: kaibo
 * @UpdateDate: 2014年3月7日 下午5:08:53
 * @UpdateRemark: What is modified?
 */
public interface BaseDeptService {
    /**
     * @Title: loadFullDept
     * @Description: 获取所有部门（一次性全部加载，适合数据量少的情况）。
     * @return
     * @return List<BaseDept>
     */
    List<BaseDept> loadFullDept();

    /**
     * @Title: getDeptDescendants
     * @Description: 获得本部门（节点）及下面的所有部门（节点）。
     * @param id
     *            当前操作部门（节点）id
     * @return
     * @return List<BaseDept>
     */
    List<BaseDept> getDeptDescendants(Long id);

    /**
     * @Title: getDeptChilds
     * @Description: 获得本部门（节点）的孩子部门（节点）。
     * @param id
     *            当前操作部门（节点）id
     * @return
     * @return List<BaseDept>
     */
    List<BaseDept> getDeptChilds(Long id);

    /**
     * @Title: getDeptParent
     * @Description: 获得本部门（节点）的父部门（节点）
     * @param id
     *            当前操作部门（节点）id
     * @return
     * @return BaseDept
     */
    BaseDept getDeptParent(Long id);

    /**
     * @Title: getDeptAncestry
     * @Description: 获得本部门（节点）的祖先部门（节点）
     * @param id
     *            当前操作部门（节点）id
     * @return
     * @return List<BaseDept>
     */
    List<BaseDept> getDeptAncestry(Long id);

    /**
     * @Title: addDept
     * @Description: 添加孩子部门（节点）
     * @param id
     *            父部门（节点）的nodeId
     * @param params
     *            子部门（节点）的信息
     * @return void
     */
    void addDept(Long id, Object params);

    /**
     * @Title: delDept
     * @Description: 删除部门（节点）
     * @param id
     *            要删除的部门（节点）ID
     * @return void
     */
    void delDept(Long id);

    /**
     * @Title: moveUpDept
     * @Description: 上移某个部门（节点）(同级叶子之间,即把弟弟部门（节点）移到哥哥部门（节点）之前)
     * @param id
     *            弟弟部门（节点）ID
     * @param upId
     *            哥哥部门（节点）ID
     * @return void
     */
    void moveUpDept(Long id, Long upId);

    /**
     * @Title: moveDownDept
     * @Description: 下移某个部门（节点）(同级叶子之间,即把哥哥部门（节点）移到弟弟部门（节点）之后)
     * @param id
     *            哥哥部门（节点）ID
     * @param downId
     *            弟弟部门（节点）ID
     * @return void
     */
    void moveDownDept(Long id, Long downId);

    /**
     * @Title: findDeptById
     * @Description: 查询一个部门。
     * @param id
     * @return
     * @return BaseDept
     */
    BaseDept findDeptById(Long id);

    /**
     * @Title: updateDept
     * @Description: 更新一个部门。
     * @param dept
     * @return void
     */
    void updateDept(BaseDept dept);

}
