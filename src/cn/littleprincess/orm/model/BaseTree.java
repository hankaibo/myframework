/**   
 * @ProjectName: MyFramework
 * @Package: cn.littleprincess.orm.model 
 * @ClassName: BaseTree 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-3-11 上午12:21:30 
 *
 */
package cn.littleprincess.orm.model;

/**
 * @ClassName: BaseTree
 * @Description: TODO
 * @Author: kaibo
 * @date: 2014-3-11
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-3-11 上午12:21:30
 * @UpdateRemark: What is modified?
 */
public class BaseTree extends BaseEntity {

    private static final long serialVersionUID = 5603244638320710647L;
    private String name;
    private int nodeId;
    private int lft;
    private int rgt;
    private int parentId;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the nodeId
     */
    public int getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId
     *            the nodeId to set
     */
    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * @return the lft
     */
    public int getLft() {
        return lft;
    }

    /**
     * @param lft
     *            the lft to set
     */
    public void setLft(int lft) {
        this.lft = lft;
    }

    /**
     * @return the rgt
     */
    public int getRgt() {
        return rgt;
    }

    /**
     * @param rgt
     *            the rgt to set
     */
    public void setRgt(int rgt) {
        this.rgt = rgt;
    }

    /**
     * @return the parentId
     */
    public int getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     *            the parentId to set
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

}
