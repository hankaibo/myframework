package cn.littleprincess.system.vo;

/**
 * @ClassName:ParentChildTree
 * @Description:与zTree数据结构对应，充当VO，封装页面展示数据。
 * @Author:hankaibo
 * @date:2013-8-29
 * @UpdateUser:hankaibo
 * @UpdateDate:2013-8-29 下午4:38:38
 * @UpdateRemark:What is modified?
 */
public class ParentChildTree {
    /**
     * @Fields id :treeNode节点的唯一标识tid.
     */
    private int id;
    /**
     * @Fields name :节点名字.
     */
    private String name;
    /**
     * @Fields open :记录treeNode节点的展开/折叠状态。默认值：false.
     */
    private boolean open;
    /**
     * @Fields checked :节点的checkBox/radio的勾选状态。默认值false.
     */
    private boolean checked;
    /**
     * @Fields halfCheck :强制节点的checkBox/radio的半勾选状态。
     */
    private boolean halfCheck;
    /**
     * @Fields nocheck :设置节点是否隐藏checkBox/radio.
     */
    private boolean nocheck;
    /**
     * @Fields chkDisabled :设置节点的checkBox/radio是否禁用。默认值false.
     */
    private boolean chkDisabled;
    /**
     * @Fields isParent :记录treeNode节点是否为父节点。
     */
    private boolean isParent;
    /**
     * @Fields pid :treeNode节点的父节点唯一标识tid.
     */
    private int pid;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

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
     * @return the open
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * @param open
     *            the open to set
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * @return the checked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked
     *            the checked to set
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * @return the halfCheck
     */
    public boolean isHalfCheck() {
        return halfCheck;
    }

    /**
     * @param halfCheck
     *            the halfCheck to set
     */
    public void setHalfCheck(boolean halfCheck) {
        this.halfCheck = halfCheck;
    }

    /**
     * @return the nocheck
     */
    public boolean isNocheck() {
        return nocheck;
    }

    /**
     * @param nocheck
     *            the nocheck to set
     */
    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }

    /**
     * @return the chkDisabled
     */
    public boolean isChkDisabled() {
        return chkDisabled;
    }

    /**
     * @param chkDisabled
     *            the chkDisabled to set
     */
    public void setChkDisabled(boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }

    /**
     * @return the isParent
     */
    public boolean isParent() {
        return isParent;
    }

    /**
     * @param isParent
     *            the isParent to set
     */
    public void setParent(boolean isParent) {
        this.isParent = isParent;
    }

    /**
     * @return the pid
     */
    public int getPid() {
        return pid;
    }

    /**
     * @param pid
     *            the pid to set
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

}
