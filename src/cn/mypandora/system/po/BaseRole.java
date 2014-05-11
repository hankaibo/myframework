/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.system.po 
 * @ClassName: BaseRole 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-4-22 下午6:37:01 
 *
 */
package cn.mypandora.system.po;

import java.util.Collection;

import cn.mypandora.orm.model.BaseEntity;

/**
 * @ClassName: BaseRole
 * @Description: 角色实体域对象。
 * @Author: kaibo
 * @date: 2014-4-22
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-4-22 下午6:37:01
 * @UpdateRemark: What is modified?
 */
public class BaseRole extends BaseEntity {

    private static final long serialVersionUID = 6199599081168354432L;
    private String name;
    private String description;
    private Collection<BaseUser> users;
    private Collection<BasePermission> pmss;

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
     * @return the users
     */
    public Collection<BaseUser> getUsers() {
        return users;
    }

    /**
     * @param users
     *            the users to set
     */
    public void setUsers(Collection<BaseUser> users) {
        this.users = users;
    }

    /**
     * @return the pmss
     */
    public Collection<BasePermission> getPmss() {
        return pmss;
    }

    /**
     * @param pmss
     *            the pmss to set
     */
    public void setPmss(Collection<BasePermission> pmss) {
        this.pmss = pmss;
    }

}
