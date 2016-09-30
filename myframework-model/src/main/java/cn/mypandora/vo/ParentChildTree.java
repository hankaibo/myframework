/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.vo;

/**
 * 与zTree数据结构对应，充当VO，封装页面展示数据。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public class ParentChildTree {
    // treeNode节点的唯一标识tid
    private int id;
    // 节点名字.
    private String name;
    // 记录treeNode节点的展开/折叠状态。默认值：false
    private boolean open;
    // 节点的checkBox/radio的勾选状态。默认值false.
    private boolean checked;
    // 强制节点的checkBox/radio的半勾选状态
    private boolean halfCheck;
    // 设置节点是否隐藏checkBox/radio
    private boolean nocheck;
    // 设置节点的checkBox/radio是否禁用。默认值false
    private boolean chkDisabled;
    // 记录treeNode节点是否为父节点
    private boolean isParent;
    // treeNode节点的父节点唯一标识tid
    private int pid;
    // URL
    private String URL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isHalfCheck() {
        return halfCheck;
    }

    public void setHalfCheck(boolean halfCheck) {
        this.halfCheck = halfCheck;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }

    public boolean isChkDisabled() {
        return chkDisabled;
    }

    public void setChkDisabled(boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean isParent) {
        this.isParent = isParent;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
