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

import cn.mypandora.orm.model.BaseEntity;

/**
 * @ClassName: BasePermission
 * @Description: 权限实体域对象。
 * @Author: kaibo
 * @date: 2014-4-22
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-4-22 下午6:40:37
 * @UpdateRemark: What is modified?
 */
public class BasePermission extends BaseEntity {

    private static final long serialVersionUID = 7779139095887287122L;

    /**
     * 权限标识
     */
    private String permission;
    /**
     * 权限描述
     */
    private String description;
    /**
     * 是否可用,如果不可用将不会添加给用户
     */
    private Boolean available = Boolean.FALSE;


    public BasePermission() {

    }

    private BasePermission(String permission, String description, Boolean available) {
        this.permission = permission;
        this.description = description;
        this.available = available;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
