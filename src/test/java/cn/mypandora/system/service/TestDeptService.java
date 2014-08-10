/**
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.system.service
 * @ClassName: TestDeptService
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-3-11 下午3:49:16
 *
 */
package cn.mypandora.system.service;

import cn.mypandora.system.po.BaseDept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: TestDeptService
 * @Description: 部门JUnit测试。
 * @Author: kaibo
 * @date: 2014-3-11
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-3-11 下午3:49:16
 * @UpdateRemark: What is modified?
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class TestDeptService {
    @Resource
    private BaseDeptService deptService;

    /*----------测试方法开始----------*/
//    @Test
//    public void loadFullTree() {
//        List<BaseDept> listBaseDepts = deptService.loadFullTree();
//        myPrint(listBaseDepts);
//    }

//    @Test
//    public void getDescendants() {
//        List<BaseDept> list = deptService.getDescendants(4);
//        myPrint(list);
//    }

//    @Test
//    public void getChilds() {
//        List<BaseDept> list = deptService.getChilds(2);
//        myPrint(list);
//    }

//    @Test
//    public void getParent(){
//         BaseDept dept=deptService.getParent(1);
//         if(null!=dept){
//             System.out.println(dept.getNodeId()+"__"+dept.getName());
//         }
//    }

    @Test
    public void getAncestry() {
        List<BaseDept> list = deptService.getDeptAncestry(9L);
        myPrint(list);
    }

//     @Test
//     public void addChild(){
//         Map<String, Object> map=new HashMap<>();
//         BaseDept dept=(BaseDept) deptService.findById(1);
//         map.put("name", "testName");
//         map.put("lft", dept.getRgt());
//         map.put("rgt", dept.getRgt()+1);
//         map.put("parentId", dept.getNodeId());
//         deptService.addChild(1, map);
//     }


    private void myPrint(List<BaseDept> list){
        for (BaseDept dept : list) {
            System.out.print(dept.getNodeId() + "___");
            System.out.print(dept.getName());
            for (int i = 0; i < (7 - dept.getName().length()); i++) {
                System.out.print(" ");
            }
            System.out.print("___" + (dept.getLft() > 9 ? dept.getLft() : "_" + dept.getLft()));
            System.out.print("___" + (dept.getRgt() > 9 ? dept.getRgt() : "_" + dept.getRgt()));
            System.out.println("___" + dept.getParentId());
        }
    }

}
