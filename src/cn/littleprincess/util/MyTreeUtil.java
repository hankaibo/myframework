package cn.littleprincess.util;

import cn.littleprincess.system.po.BaseDept;
import cn.littleprincess.system.vo.ParentChildTree;

/**
 * @ClassName:MyTreeUtil
 * @Description:完成父子节点树与左右值节点树的格式转换。
 * @Author:hankaibo
 * @date:2013-9-10
 * @UpdateUser:hankaibo
 * @UpdateDate:2013-9-10 下午5:27:57
 * @UpdateRemark:What is modified?
 */
public class MyTreeUtil {
    /**
     * @Title:lfNode2pcNode
     * @Description: 将左右值节点的树转成父子节点的树。
     * @param treeNode
     * @return
     * @return ParentChildTree
     */
    public static ParentChildTree lfNode2pcNode(BaseDept treeNode) {
        ParentChildTree pcTree = new ParentChildTree();
        //TODO 在这里新添加的节点没有noteId，故暂时用主键id替换，待更好的方法出现。
        pcTree.setId(Integer.parseInt(String.valueOf(treeNode.getId())));
        pcTree.setName(treeNode.getName());
        pcTree.setOpen(true);
        pcTree.setPid(treeNode.getParentId());
        pcTree.setParent((treeNode.getRgt() - treeNode.getLft() == 1) ? false : true);
        // pcTree.setChkDisabled(treeNode.isChkDisabled());

        return pcTree;
    }

}
