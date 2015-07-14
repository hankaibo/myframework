package cn.mypandora.system.po;

import cn.mypandora.orm.model.BaseEntity;

/**
 * Created by kaibo on 2015/7/9.
 * desc
 */
public class BaseUserRole extends BaseEntity {
    private Long baseUserId;
    private Long baseRoleId;

    public Long getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(Long baseUserId) {
        this.baseUserId = baseUserId;
    }

    public Long getBaseRoleId() {
        return baseRoleId;
    }

    public void setBaseRoleId(Long baseRoleId) {
        this.baseRoleId = baseRoleId;
    }
}
