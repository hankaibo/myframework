package cn.mypandora.system.po;

import cn.mypandora.orm.model.BaseEntity;

/**
 * Created by kaibo on 2015/7/9.
 * desc
 */
public class BaseRolePermission extends BaseEntity {

    private Long baseRoleId;
    private Long basePermissionId;

    public Long getBaseRoleId() {
        return baseRoleId;
    }

    public void setBaseRoleId(Long baseRoleId) {
        this.baseRoleId = baseRoleId;
    }

    public Long getBasePermissionId() {
        return basePermissionId;
    }

    public void setBasePermissionId(Long basePermissionId) {
        this.basePermissionId = basePermissionId;
    }
}
