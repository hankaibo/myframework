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
    private String role;
    private String description;
    private Boolean available = Boolean.FALSE;

    public BaseRole() {
    }

    private BaseRole(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
