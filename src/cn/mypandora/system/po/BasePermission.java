/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.system.po 
 * @ClassName: BasePermission 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-4-22 下午6:40:37 
 *
 */
package cn.mypandora.system.po;

import java.util.Collection;

import cn.mypandora.orm.model.BaseEntity;

/**
 * @ClassName: BasePermission
 * @Description: TODO
 * @Author: kaibo
 * @date: 2014-4-22
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-4-22 下午6:40:37
 * @UpdateRemark: What is modified?
 */
public class BasePermission extends BaseEntity {

    private static final long serialVersionUID = 7779139095887287122L;

    private String name;
    private String description;
    private String permission;
    private BasePermission parent;
    private Collection<BasePermission> children;
    private Collection<BaseRole> roles;

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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the permission
     */
    public String getPermission() {
        return permission;
    }

    /**
     * @param permission
     *            the permission to set
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * @return the parent
     */
    public BasePermission getParent() {
        return parent;
    }

    /**
     * @param parent
     *            the parent to set
     */
    public void setParent(BasePermission parent) {
        this.parent = parent;
    }

    /**
     * @return the children
     */
    public Collection<BasePermission> getChildren() {
        return children;
    }

    /**
     * @param children
     *            the children to set
     */
    public void setChildren(Collection<BasePermission> children) {
        this.children = children;
    }

    /**
     * @return the roles
     */
    public Collection<BaseRole> getRoles() {
        return roles;
    }

    /**
     * @param roles
     *            the roles to set
     */
    public void setRoles(Collection<BaseRole> roles) {
        this.roles = roles;
    }

}
